package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        new Thread(()->{
           try{
                Thread.sleep(1000);
                startActivity(new Intent(this, LoginActivity.class));
           }catch(Exception e){
               e.printStackTrace();
           }
        }).start();
    }
    private void print(String s) {
        Log.println(Log.DEBUG, "DATA - > ", s);
    }
}
