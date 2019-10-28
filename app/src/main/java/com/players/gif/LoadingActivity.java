package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Handler h = new Handler();
        h.postDelayed(()->{
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }, 4000);
    }
}
