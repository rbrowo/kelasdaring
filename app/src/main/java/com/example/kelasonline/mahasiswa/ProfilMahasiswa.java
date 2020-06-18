package com.example.kelasonline.mahasiswa;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.kelasonline.R;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class ProfilMahasiswa extends AppCompatActivity {
    TextView tvNama, tvEmail;
    String resultnama, resultemail;
    Button bLogout, bHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_mahasiswa);
        getSupportActionBar().hide();
        tvNama = (TextView) findViewById(R.id.tvNama);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        bLogout = (Button)findViewById(R.id.btnLogout);
        bHome = (Button) findViewById(R.id.btnHome);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            resultnama = extras.getString("result_nama");
            resultemail = extras.getString("result_email");

            tvNama.setText(resultnama);
            tvEmail.setText(resultemail);
        }

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilMahasiswa.this, LoginMahasiswa.class));
            }
        });

        bHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i =new Intent(ProfilMahasiswa.this, HomeMahasiswa.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });
    }
}