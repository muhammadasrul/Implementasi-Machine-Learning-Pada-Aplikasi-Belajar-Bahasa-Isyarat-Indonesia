package com.asrul.skripsi.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.asrul.skripsi.R;
import com.asrul.skripsi.ui.detection.DetectorActivity;
import com.asrul.skripsi.ui.home.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static final float MINIMUM_CONFIDENCE_TF_OD_API = 0.5f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            loadFragment(new HomeFragment());
        }

        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);

        FloatingActionButton fab = findViewById(R.id.fabCamera);
        fab.setOnClickListener(v -> {
            startActivity(new Intent(this, DetectorActivity.class));
        });

        bottomNav.setOnNavigationItemSelectedListener(item -> {
            Fragment fragment = null;

            switch (item.getItemId()) {
                case R.id.home_nav:
                    fragment = new HomeFragment();
                    break;
                case R.id.profile_nav:
                    fragment = new ProfileFragment();
                    break;
                case R.id.camera_nav:
                    startActivity(new Intent(this, DetectorActivity.class));
                    break;
            }
            return loadFragment(fragment);
        });
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.mainContainer, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
