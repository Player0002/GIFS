package com.players.gif;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.players.gif.ui.DashboardFragment;
import com.players.gif.ui.TestFragment;


public class MainActivity extends AppCompatActivity {

    /*private FragmentManager fragmentManager = getSupportFragmentManager();
    private TestFragment testFragment = new TestFragment();
    private DashboardFragment dashboardFragment = new DashboardFragment();*/
    private SignInButton googleSignInButton;
    public static final String wantSignOut = "want_sign_out";
    private GoogleSignInClient googleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//ASFdsfdsf
        setContentView(R.layout.activity_main);

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
        /*BottomNavigationView navView = findViewById(R.id.nav_view);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.nav_host_fragment, testFragment).commitAllowingStateLoss();

        navView.setOnNavigationItemSelectedListener(menuItem -> {
            FragmentTransaction transactionc = fragmentManager.beginTransaction();
            switch(menuItem.getItemId()){
                case R.id.navigation_test:{
                    transactionc.replace(R.id.nav_host_fragment, testFragment).commitAllowingStateLoss();
                    break;
                }
                case R.id.navigation_dashboard:{
                    transactionc.replace(R.id.nav_host_fragment, dashboardFragment).commitAllowingStateLoss();
                    break;
                }
            }
            return true;
        });
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_dashboard, R.id.navigation_test)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/
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
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra(LoginActivity.GOOGLE_ACCOUNT, account);
        startActivity(intent);
        finish();
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
}
