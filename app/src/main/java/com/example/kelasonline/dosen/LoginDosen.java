package com.example.kelasonline.dosen;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import com.example.kelasonline.MainActivity;
import com.example.kelasonline.R;
import com.example.kelasonline.util.api.BaseApiService;
import com.example.kelasonline.util.api.UtilsApi;
import java.io.IOException;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class LoginDosen extends AppCompatActivity {
    EditText txtID, txtPassword;
    Button bLogin, bRegister, bHome;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_dosen);
        getSupportActionBar().hide();
        mContext = this;
        mApiService = UtilsApi.getAPIService();
        txtID = (EditText)findViewById(R.id.etID);
        txtPassword = (EditText)findViewById(R.id.etPassword);
        bLogin = (Button)findViewById(R.id.btnLogin);
        bRegister = (Button)findViewById(R.id.btnRegister);
        bHome = (Button) findViewById(R.id.btnHome);

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading = ProgressDialog.show(mContext, null, "Mohon Tunggu...",
                        true, false);
                requestLogin();
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, RegisterDosen.class));
            }
        });

        bHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));
            }
        });
    }

    public void requestLogin(){
        mApiService.loginDosenRequest(txtID.getText().toString(), txtPassword.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            loading.dismiss();
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if(jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "LOGIN BERHASIL", Toast.LENGTH_SHORT).show();
                                    String nama = jsonRESULTS.getJSONObject("user").getString("nama");
                                    String email = jsonRESULTS.getJSONObject("user").getString("email");
                                    Intent i = new Intent(mContext, HomeDosen.class);
                                    i.putExtra("result_nama", nama);
                                    i.putExtra("result_email", email);
                                    startActivity(i);
                                }else{
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();;
                                }
                            }catch (JSONException json){
                                json.printStackTrace();
                            }catch (IOException io){
                                io.printStackTrace();
                            }
                        }else{
                            loading.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > "+t.getMessage());
                        loading.dismiss();
                    }
                });
    }
}