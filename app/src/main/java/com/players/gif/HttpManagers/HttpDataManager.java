package com.players.gif.HttpManagers;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDataManager {
    public static JSONObject postData(String url, JSONObject objs) throws Exception{
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection(); // 연결 활성화
        connection.setRequestMethod("POST"); //전송방식
        connection.setDoInput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);

        OutputStream os = connection.getOutputStream();
        os.write(objs.toString().getBytes("UTF-8"));
        os.flush(); // 다 쓴 이후 스트림을 비움

        InputStream is = connection.getInputStream();
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 성능 향상을 위해 Buffered Reader 이용
        String s;
        while((s = br.readLine()) != null) builder.append(s);

        connection.disconnect();

        s = builder.toString();
        return s.equals("null") ? null : new JSONObject(s);
    }
}
