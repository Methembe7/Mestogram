package com.mmoyo.mestogram;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mmoyo.mestogram.fragments.ComposeFragment;
import com.mmoyo.mestogram.fragments.PostsFragment;
import com.mmoyo.mestogram.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Main Activity";


    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();


        bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragment = new PostsFragment();
                        //Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        //Toast.makeText(MainActivity.this, "Compose selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                    default:
                        fragment = new ProfileFragment();
                        // Toast.makeText(MainActivity.this, "Profile selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit(); //fix this fragment issue!!
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }


}
