package com.example.kelasonline.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.kelasonline.R;
import com.example.kelasonline.util.api.BaseApiService;
import com.example.kelasonline.util.api.UtilsApi;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class RegisterMahasiswa extends AppCompatActivity {
    EditText txtNIM, txtNama, txtJK, txtPassword, txtEmail;
    Button bRegister, bBack;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_mahasiswa);
        getSupportActionBar().hide();
        mContext = this;
        mApiservice = UtilsApi.getAPIService();
        initComponents();
    }

    public void initComponents() {
        txtNIM = (EditText) findViewById(R.id.etNIM);
        txtNama = (EditText) findViewById(R.id.etNama);
        txtEmail = (EditText) findViewById(R.id.etEmail);
        txtJK = (EditText) findViewById(R.id.etJK);
        txtPassword = (EditText) findViewById(R.id.etPassword);
        bRegister = (Button) findViewById(R.id.btnRegister);
        bBack = (Button) findViewById(R.id.btnBack);

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Harap Tunggu Sebentar",
                        true, false);
                requestDaftar();
            }
        });

        bBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginMahasiswa.class));
            }
        });
    }

    public void requestDaftar() {
        mApiservice.daftarMhsRequest(txtNIM.getText().toString(), txtNama.getText().toString(),
                txtJK.getText().toString(), txtEmail.getText().toString(), txtPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("debug", "onResponse: BERHASIL");
                    loading.dismiss();
                    try {
                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                        if (jsonRESULTS.getString("error").equals("false")) {
                            Toast.makeText(mContext, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(mContext, LoginMahasiswa.class));
                        } else {
                            String error_message = jsonRESULTS.getString("error_msg");
                            Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException errJson) {
                        errJson.printStackTrace();
                    } catch (IOException eo) {
                        eo.printStackTrace();
                    }
                } else {
                    Log.i("debug", "onResponse : TIDAK BERHASIL");
                    loading.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("debug", "onFailure : ERROR > "+t.getMessage());
                Toast.makeText(mContext, "Koneksi Internet Bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }
}