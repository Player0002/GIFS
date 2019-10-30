package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.players.gif.DataManagers.DataFormatter;
import com.players.gif.HttpManagers.HttpDataManager;

import org.json.JSONObject;
import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity {

    public static final String GOOGLE_ACCOUNT = "google_account";

    private EditText text;
    private ImageView imageView;
    private Button OkButton;

    private TextView userName;

    private boolean isError = true;
    private int currentalpha = 0;
    private Thread currentThread = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        text = findViewById(R.id.UserNameBox);
        imageView = findViewById(R.id.OkImg);
        imageView.setAlpha(currentalpha);
        OkButton = findViewById(R.id.Ok);

        userName = findViewById(R.id.UserNameTV);

        userName.setText("사용자명에는 영어와 한글만 입력이 가능합니다.");

        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String data = charSequence.toString();
                Log.w("CURRENT : ", data + " / " + data.matches("[가-힣a-zA-Z]+") + " / " + data.matches("^[가-힣a-zA-Z]+$"));
                if(charSequence.length() == 0 || !data.matches("^[가-힣a-zA-Z]+$")){
                    if(!isError){
                        isError = true;
                        startErrorAnimation();
                        setErrorText(userName, "사용자명에는 영어와 한글만 입력이 가능합니다.");
                    }
                }else{
                    if(isError) {
                        isError = false;
                        startNoErrorAnimation();
                        setOkText(userName, "사용 가능한 사용자명 입니다.");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        OkButton.setOnClickListener((v)->{
            if(isError) return;
            GoogleSignInAccount account = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
            Handler h = new Handler();
            new Thread(()->{
                try{
                    JSONObject user = new JSONObject();
                    user.put("email", account.getEmail());
                    user.put("google", true);
                    user.put("username", text.getText());
                    JSONObject data = HttpDataManager.postData("http://danny-dataserver.kro.kr:8080/registerUser", user);
                    Intent intent = new Intent(this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra(MainActivity.GOOGLE_ACCOUNT, account);
                    startActivity(intent);
                    h.post(()->finish());
                }catch(Exception e){
                    e.printStackTrace();
                }
            }).start();
        });
    }
    private void startNoErrorAnimation(){
        if(currentThread != null) currentThread.interrupt();
        currentThread = new Thread(()-> {
            for ( ; currentalpha < 256; currentalpha++) {
                imageView.setImageAlpha(currentalpha);
                try{Thread.sleep(500 / 255);}catch (Exception e){return;}
            }
        });
        currentThread.start();
    }
    private void startErrorAnimation(){
        if(currentThread != null) currentThread.interrupt();
        currentThread = new Thread(()-> {
            for (; currentalpha > 0; currentalpha--) {
                imageView.setImageAlpha(currentalpha);
                try{Thread.sleep(500 / 255);}catch (Exception e){return;}
            }
        });
        currentThread.start();
    }
    private void setOkText(TextView view, String str){
        view.setText(str);
        view.setTextColor(Color.GREEN);
    }
    private void setErrorText(TextView view, String str){
        view.setText(str);
        view.setTextColor(Color.RED);
    }
}
