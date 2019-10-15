package com.players.gif.HttpManagers;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpDataManager {
    /*

        서버에 데이터 쓰는건 나중에 문서작업 하도록 하겠습니당 ><



     */

    //RestAPI 서버에 데이터를 쓸때 사용합니다 ( POST )
    public static JSONObject postData(String url, JSONObject objs) throws Exception{
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection(); // 연결 활성화
        connection.setRequestMethod("POST"); //전송방식
        connection.setDoInput(true);
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setReadTimeout(10000);
        connection.setConnectTimeout(10000);

        //Write data

        OutputStream os = connection.getOutputStream();
        os.write(objs.toString().getBytes("UTF-8"));
        os.flush(); // 다 쓴 이후 스트림을 비움

        //Read data
        InputStream is = connection.getInputStream();
        StringBuilder builder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(is)); // 성능 향상을 위해 Buffered Reader 이용
        String s;
        while((s = br.readLine()) != null) builder.append(s);

        connection.disconnect();

        s = builder.toString(); // StringBuilder를 사용해 성능 향상

        return s.equals("null") ? null : new JSONObject(s);
    }
}
