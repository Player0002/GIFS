package New_Login_Register_Request;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.players.gif.LoginActivity;
import com.players.gif.R;

import org.json.JSONObject;

// 로그인 서비스 처리 - 경훈 -
public class activity_newLogin extends AppCompatActivity {

    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);

        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(activity_newLogin.this, activity_newRegister.class);
                activity_newLogin.this.startActivity(registerIntent);
            }
        });

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();

                Response.Listener<String> responseListiner = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity_newLogin.this);
                                dialog = builder.setMessage("로그인에 성공하였습니다.")
                                        .setPositiveButton("확인", null)
                                        .create();
                                dialog.show();

                                Intent intent = new Intent(activity_newLogin.this, NewMainActivity.class);
                                activity_newLogin.this.startActivity(intent);
                                finish();
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(activity_newLogin.this);
                                dialog = builder.setMessage("계정을 다시 확인해 주세요.")
                                        .setNegativeButton("다시 시도", null)
                                        .create();
                                dialog.show();
                            }
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest(userID, userPassword, responseListiner);
                RequestQueue queue = Volley.newRequestQueue(activity_newLogin.this);
                queue.add(loginRequest);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
