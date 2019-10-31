package com.players.gif;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MainActivity extends AppCompatActivity {

    /*private FragmentManager fragmentManager = getSupportFragmentManager();
    private TestFragment testFragment = new TestFragment();
    private DashboardFragment dashboardFragment = new DashboardFragment();*/
    public static final String GOOGLE_ACCOUNT = "google_account";
    private final String TAG = "[MainActivity]";

    SubtitleAdapter adapter;
    RecyclerView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GoogleSignInAccount account = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        findViewById(R.id.signout).setOnClickListener((view)->{
            Intent intent = new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).putExtra(LoginActivity.wantSignOut, true);
            startActivity(intent);
        });
        listView = findViewById(R.id.listView);

        listView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new SubtitleAdapter();

        listView.setAdapter(adapter);

        for(int i = 0; i < 100; i++) adapter.addItem();

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
