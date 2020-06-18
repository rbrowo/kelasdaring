package com.example.kelasonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
/**
 * Created by:
 * @rbrowo
 * @sherlynovia1711
 * @doctis67
 * on June 17'2020 for Final Assignment Mobile Computing
 */

public class SplashScreen extends AppCompatActivity {

    private static int SPLASHTIMEOUT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent goMain = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(goMain);

                finish();
            }
        }, SPLASHTIMEOUT);
    }
}
