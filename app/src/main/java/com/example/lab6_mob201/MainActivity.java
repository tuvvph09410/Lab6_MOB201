package com.example.lab6_mob201;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.lab6_mob201.Fragment.Fragment_Bai1;
import com.example.lab6_mob201.Fragment.Fragment_Bai2;
import com.example.lab6_mob201.Fragment.Fragment_Bai3;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNav;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initToolbar();
        this.initViewById();
        this.initBottomNav();
    }

    private void initToolbar() {
        this.actionBar = getSupportActionBar();
    }

    private void initBottomNav() {
        BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.id_bai1:
                        loadFragment(new Fragment_Bai1());
                        checkIdNav(R.id.id_bai1);
                        actionBar.setTitle("Lab6 Bài 1 MOB201");
                        break;
                    case R.id.id_bai2:
                        loadFragment(new Fragment_Bai2());
                        checkIdNav(R.id.id_bai2);
                        actionBar.setTitle("Lab6 Bài 2 MOB201");
                        break;
                    case R.id.id_bai3:
                        loadFragment(new Fragment_Bai3());
                        checkIdNav(R.id.id_bai3);
                        actionBar.setTitle("Lab6 Bài 3 MOB201");
                        break;
                }
                return true;
            }
        };
        this.bottomNav.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);
        this.checkIdNav(R.id.id_bai1);
        loadFragment(new Fragment_Bai1());
        this.actionBar.setTitle("Lab6 Bài 1 MOB201");
    }

    private void initViewById() {
        this.bottomNav = findViewById(R.id.bottom_nav);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void checkIdNav(int item) {
        this.bottomNav.getMenu().findItem(item).setChecked(true);
    }
}