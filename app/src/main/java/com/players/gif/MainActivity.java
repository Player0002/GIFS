package com.players.gif;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.players.gif.ui.DashboardFragment;
import com.players.gif.ui.TestFragment;


public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private TestFragment testFragment = new TestFragment();
    private DashboardFragment dashboardFragment = new DashboardFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//ASFdsfdsf
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

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
}
