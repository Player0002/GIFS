package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.facebook.login.LoginManager;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LoginManager.getInstance().logOut();
        new Thread(()->{
           try{
                Thread.sleep(1000);
                startActivity(new Intent(this, LoginActivity.class));
           }catch(Exception e){
               e.printStackTrace();
           }
        });
    }
}
