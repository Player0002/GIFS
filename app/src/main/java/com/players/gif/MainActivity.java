package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//ASFdsfdsf
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this, LoginActivity.class));
        Handler h = new Handler();
        new Thread(()->{
            try{
                Thread.sleep(1000);
                h.post(()->{});
            }catch (Exception e){e.printStackTrace();}
        }).start();
    }
}
