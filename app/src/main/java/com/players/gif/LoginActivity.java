package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.players.gif.HttpManagers.HttpDataManager;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private SignInButton googleSignInButton;
    public static final String wantSignOut = "want_sign_out";
    private GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);
        googleSignInButton = findViewById(R.id.sign_in_button);

        googleSignInButton.setStyle(SignInButton.SIZE_WIDE, SignInButton.COLOR_AUTO);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestId()
                .requestEmail().requestProfile()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);

        googleSignInButton.setOnClickListener((v)->{
            Intent signInIntent = googleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, 101);
        });
        if(getIntent().getBooleanExtra(wantSignOut, false)){
            googleSignInClient.signOut().addOnSuccessListener((a)->{
                Log.w("DATA : ", "SUCCESS");
            });
        }

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println(requestCode + " / " + resultCode);
        if(resultCode == Activity.RESULT_OK)
            switch(requestCode){
                case 101:
                    try{
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    }catch(ApiException e){
                        Log.w("ERROR", "SIGN IN RESULT FAILD CODE = " + e.getStatusCode());
                    }
                    break;
            }
    }
    private void onLoggedIn(GoogleSignInAccount account){
        Handler h = new Handler();
        new Thread(()-> {
            try{
                JSONObject data = new JSONObject();
                data.put("email", account.getEmail());
                JSONObject obj = HttpDataManager.postData("http://192.168.43.249:8080/canUseEmail", data);
                System.out.println(account.getServerAuthCode());
                boolean status = obj.getBoolean("status");
                if (!status) {
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra(MainActivity.GOOGLE_ACCOUNT, account);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, RegisterActivity.class);
                    intent.putExtra(RegisterActivity.GOOGLE_ACCOUNT, account);
                    startActivity(intent);
                }
                h.post(()->finish());
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
    @Override
    public void onStart(){
        super.onStart();
        GoogleSignInAccount alreadyLogin = GoogleSignIn.getLastSignedInAccount(this);
        if(alreadyLogin != null){
            Toast.makeText(this, "이미 로그인 하였습니다.", Toast.LENGTH_LONG).show();
            onLoggedIn(alreadyLogin);
        }else{
            Log.d("DATA : ", "NOT LOGIN");
        }
    }
    /*
        /*textView = findViewById(R.id.showData);
        imageView = findViewById(R.id.profileImage);
        findViewById(R.id.login).setOnClickListener((v)->{
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(MainActivity.wantSignOut, true);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
        setShowData();
    }
    private void setShowData(){
        GoogleSignInAccount account = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        //Picasso.get().load(account.getPhotoUrl()).centerInside().into(imageView);
        Log.d("DATA : ", account.getPhotoUrl().toString());
        textView.setText(account.getDisplayName() + " / " + account.getEmail());
    }*/
}