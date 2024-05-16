package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Intro1Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_splash1); //인트로화면1 splash1실행
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), Signup1Activity.class);
                startActivity(intent);
                finish();
            }
        }, 1000); //1초 후 인트로 화면 종료
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
