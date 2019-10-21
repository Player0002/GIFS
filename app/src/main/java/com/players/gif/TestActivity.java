package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

public class TestActivity extends AppCompatActivity {

    /*

        DATA GET
        https://graph.facebook.com/v4.0/me?fields=id,name,gender,email,events&access_token={Access token}

     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        LoginManager.getInstance().logOut();
        print(AccessToken.getCurrentAccessToken().getToken());
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
