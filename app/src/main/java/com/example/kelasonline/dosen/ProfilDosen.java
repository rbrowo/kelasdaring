package com.example.kelasonline.dosen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.kelasonline.R;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class ProfilDosen extends AppCompatActivity {
    TextView tvNama, tvEmail;
    String resultnama, resultemail;
    Button bLogout, bHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_dosen);
        getSupportActionBar().hide();
        bLogout = (Button)findViewById(R.id.btnLogout);
        bHome = (Button) findViewById(R.id.btnHome);
        tvNama = (TextView) findViewById(R.id.tvNama);
        tvEmail = (TextView) findViewById(R.id.tvEmail);

        Bundle extras = getIntent().getExtras();
        if(extras!=null)
        {
            resultnama = extras.getString("result_nama");
            resultemail = extras.getString("result_email");

            tvNama.setText(resultnama);
            tvEmail.setText(resultemail);
        }

        bLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilDosen.this, LoginDosen.class));
            }
        });

        bHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = tvNama.getText().toString();
                String email = tvEmail.getText().toString();

                Intent i =new Intent(ProfilDosen.this, HomeDosen.class);
                i.putExtra("result_nama", nama);
                i.putExtra("result_email", email);
                startActivity(i);
            }
        });
    }
}