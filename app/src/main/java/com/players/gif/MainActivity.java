package com.players.gif;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.util.JsonUtils;
import com.google.gson.Gson;
import com.players.gif.DataManagers.UserInfo;
import com.players.gif.HttpManagers.HttpDataManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

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
        new Thread(()->{
            try {
                UserInfo info = UserInfo.getInstance();
                JSONObject object = new JSONObject();
                object.put("id", info.getEmail());
                object.put("password", "null");
                object.put("isGoogle", true);
                JSONObject obj = HttpDataManager.postData("http://danny-dataserver.kro.kr:8080/checkUser", object);
                info.setGoogle(true);
                JSONArray array = obj.get("groups").toString().equals("null") ? null : obj.getJSONArray("groups");
                ArrayList<Long> data = new ArrayList<>();
                if(array != null)
                    for(int i = 0; i < array.length(); i++) data.add(array.getLong(i));
                info.setGroups(data);
                info.setProfileImgName(obj.get("profileImgName").toString());
                info.setUsername(obj.getString("username"));
                Log.w(TAG, obj.getString("when"));
                Date date = new Date();
                date.setTime(obj.getLong("when"));
                info.setWhen(date);
            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();

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
        startActivity(new Intent(this, PostActivity.class));
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
