package com.example.kelasonline.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kelasonline.dosen.DaftarDosen;
import com.example.kelasonline.R;
import com.example.kelasonline.jadwal.DaftarJadwalM;
import com.example.kelasonline.materi.DaftarMateriM;
import com.example.kelasonline.tanyajawab.DaftarTanyaM;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class HomeMahasiswa extends AppCompatActivity {
    TextView tvNama, tvEmail;
    String resultnama, resultemail;
    Button bProfil, bDosen, bJadwal, bTanya, bMateri;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_mahasiswa);
        mContext = this;
        getSupportActionBar().hide();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tvNama = (TextView) findViewById(R.id.tvNama);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        bProfil = (Button) findViewById(R.id.btnProfil);
        bDosen = (Button) findViewById(R.id.btnDosen);
        bJadwal = (Button) findViewById(R.id.btnJadwal);
        bTanya = (Button) findViewById(R.id.btnTanya);
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

                Intent i = new Intent(HomeMahasiswa.this, ProfilMahasiswa.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });

        bDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(HomeMahasiswa.this, DaftarDosen.class);
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

                Intent i = new Intent(HomeMahasiswa.this, DaftarJadwalM.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });

        bTanya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i = new Intent(HomeMahasiswa.this, DaftarTanyaM.class);
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

                Intent i = new Intent(HomeMahasiswa.this, DaftarMateriM.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });
    }
}