package New_Login_Register_Request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

// 회원 ID 체크 담당 부분 -  경훈 -
public class ValidateRequest extends StringRequest {

    final static private String URL = "http://"; // 서버주소 추가할 것.
    private Map<String, String> parameters;

    public ValidateRequest(String userID, Response.Listener<String> listener)
    {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
