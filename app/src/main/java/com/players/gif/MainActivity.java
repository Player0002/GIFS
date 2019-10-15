package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.players.gif.DataManagers.DataFormatter;
import com.players.gif.HttpManagers.HttpDataManager;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//ASFdsfdsf
        JSONObject obj = new JSONObject();
        try {
            obj.put("id", "rladudwo0914@naver.com");
            obj.put("password", DataFormatter.getSHA512("danny12345!"));
        }catch (Exception e) { e.printStackTrace(); }
        new Thread(()-> {
            try {
                JSONObject obc = HttpDataManager.postData("http://danny-dataserver.kro.kr:8080/loginUser", obj);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        setContentView(R.layout.activity_main);
    }
}
