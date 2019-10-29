package com.players.gif;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;


public class LoginActivity extends AppCompatActivity {
    public static final String GOOGLE_ACCOUNT = "google_account";
    private TextView textView;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView = findViewById(R.id.showData);
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
    }
}