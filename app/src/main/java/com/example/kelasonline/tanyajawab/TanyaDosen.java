package com.example.kelasonline.tanyajawab;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kelasonline.R;
import com.example.kelasonline.mahasiswa.HomeMahasiswa;
import com.example.kelasonline.model.TanyaJawab;
import com.example.kelasonline.util.api.BaseApiService;
import com.example.kelasonline.util.api.UtilsApi;
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

public class TanyaDosen extends AppCompatActivity {
    TextView tvNama, tvEmail;
    String resultnama, resultemail;
    EditText txtSubjek, txtMhs, txtDosen, txtMatkul, txtTanya;
    Button bKirim;
    ProgressDialog loading;
    Context mContext;
    BaseApiService mApiservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan);
        getSupportActionBar().setTitle("Tanya Dosen");
        mContext = this;
        mApiservice = UtilsApi.getAPIService();
        initComponents();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            resultnama = extras.getString("result_nama");
            resultemail = extras.getString("result_email");

            tvNama.setText(resultnama);
            tvEmail.setText(resultemail);
        }
    }

    public void initComponents() {
        tvNama = (TextView) findViewById(R.id.tvNama);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        txtSubjek = (EditText) findViewById(R.id.etSubjek);
        txtMhs = (EditText) findViewById(R.id.nmMhs);
        txtDosen = (EditText) findViewById(R.id.nmDosen);
        txtMatkul = (EditText) findViewById(R.id.nmMatkul);
        txtTanya = (EditText) findViewById(R.id.etTanya);
        bKirim = (Button) findViewById(R.id.btnKirim);

        bKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kirimPertanyaan();
            }
        });
    }

    public void kirimPertanyaan() {
        String subjek = txtSubjek.getText().toString().trim();
        String mhs = txtMhs.getText().toString().trim();
        String dosen = txtDosen.getText().toString().trim();
        String matkul = txtMatkul.getText().toString().trim();
        String tanya = txtTanya.getText().toString().trim();

        mApiservice = UtilsApi.getAPIService();

        if (TextUtils.isEmpty(txtSubjek.getText().toString()) ||
                TextUtils.isEmpty(txtMhs.getText().toString()) ||
                TextUtils.isEmpty(txtDosen.getText().toString()) ||
                TextUtils.isEmpty(txtMatkul.getText().toString()) ||
                TextUtils.isEmpty(txtTanya.getText().toString()) ){
            Toast.makeText(getApplicationContext(), "Semua kolom wajib diisi", Toast.LENGTH_SHORT).show();
        }

        else {

            Call<TanyaJawab> call = mApiservice.tambahPertanyaan(subjek, mhs, dosen, matkul, tanya);

            call.enqueue(new Callback<TanyaJawab>() {
                @Override
                public void onResponse(Call<TanyaJawab> call, Response<TanyaJawab> response) {

                    Log.i(TanyaDosen.class.getSimpleName(), response.toString());
                    loading = ProgressDialog.show(mContext, null, "Mengirim pertanyaan...",
                            true, false);

                    String value = response.body().getValue();


                    if (value.equals("1")) {
                        Toast.makeText(mContext, "Pertanyaan Terkirim", Toast.LENGTH_SHORT).show();
                        String nama = tvNama.getText().toString();
                        String email = tvEmail.getText().toString();

                        Intent i = new Intent(TanyaDosen.this, HomeMahasiswa.class);
                        i.putExtra("result_nama", nama);
                        i.putExtra("result_email", email);
                        startActivity(i);
                        finish();
                    } else {
                        Toast.makeText(mContext, "Gagal Mengirim Pertanyaan", Toast.LENGTH_SHORT).show();
                    }

                }


                @Override
                public void onFailure(Call<TanyaJawab> call, Throwable t) {
                    Toast.makeText(TanyaDosen.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}