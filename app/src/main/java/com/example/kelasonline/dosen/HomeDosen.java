package com.example.kelasonline.dosen;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kelasonline.jadwal.DaftarJadwalD;
import com.example.kelasonline.mahasiswa.DaftarMahasiswa;
import com.example.kelasonline.R;
import com.example.kelasonline.materi.DaftarMateriD;
import com.example.kelasonline.tanyajawab.DaftarTanyaD;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class HomeDosen extends AppCompatActivity {
    TextView tvNama, tvEmail;
    String resultnama, resultemail;
    Button bProfil, bMhs, bJadwal, bJawab, bMateri;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_dosen);
        mContext = this;
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvNama = (TextView) findViewById(R.id.tvNama);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        bProfil = (Button) findViewById(R.id.btnProfil);
        bMhs = (Button) findViewById(R.id.btnMhs);
        bJadwal = (Button) findViewById(R.id.btnJadwal);
        bJawab = (Button) findViewById(R.id.btnJawab);
        bMateri = (Button) findViewById(R.id.btnMateri);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            resultnama = extras.getString("result_nama");
            resultemail = extras.getString("result_email");

            tvNama.setText(resultnama);
            tvEmail.setText(resultemail);
        }

        bProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(HomeDosen.this, ProfilDosen.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });

        bMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(HomeDosen.this, DaftarMahasiswa.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });

        bJadwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(HomeDosen.this, DaftarJadwalD.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });

        bJawab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(HomeDosen.this, DaftarTanyaD.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });

        bMateri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(HomeDosen.this, DaftarMateriD.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });
    }
}
