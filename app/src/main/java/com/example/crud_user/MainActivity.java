package com.example.crud_user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fragment defaultFragment = new addFragment();
        String URL = "http://192.168.1.4:80/";
        Bundle bundle = new Bundle();
        bundle.putString("url", URL);
        defaultFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplaceholder, defaultFragment).commit();

        bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigListener);
    }
    public BottomNavigationView.OnNavigationItemSelectedListener navigListener=new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment unFragment = null;
            switch(item.getItemId()){
                case R.id.add:
                    unFragment=new addFragment();
                    break;
                case R.id.list:
                    unFragment=new AllFragment();
                    break;
            }
            String URL="http://192.168.1.4:80/";
            Bundle bundle=new Bundle();
            bundle.putString("url",URL);
            unFragment.setArguments(bundle);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentplaceholder,unFragment).commit();
            return true;
        }
    };
}