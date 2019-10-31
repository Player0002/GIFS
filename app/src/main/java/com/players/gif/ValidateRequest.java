package com.players.gif;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


// 현재 ID 체크 담당 기능 - 경훈 -
public class ValidateRequest extends StringRequest {

    final static private String URL = "http://"; // 서버주소 추가할 것.
    private Map<String, String> parameters;

    public VailidateRequest(String userID, String userPassword, String userGender, String userMajor, String userEmail, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        parameters.put("userMajor", userMajor);
        parameters.put("userEmail", userEmail);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
