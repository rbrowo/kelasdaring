package com.example.kelasonline;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.kelasonline.dosen.LoginDosen;
import com.example.kelasonline.mahasiswa.LoginMahasiswa;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class MainActivity extends AppCompatActivity{
    Button bDosen, bMhs;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        mContext = this;
        bDosen = (Button)findViewById(R.id.btnDosen);
        bMhs = (Button)findViewById(R.id.btnMhs);

        bDosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginDosen.class));
            }
        });

        bMhs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, LoginMahasiswa.class));
            }
        });
    }
}