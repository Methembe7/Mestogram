package com.mmoyo.mestogram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mmoyo.mestogram.fragments.ComposeFragment;
import com.mmoyo.mestogram.fragments.PostsFragment;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "Main Activity";

    private ImageButton ibLogout;
    private ImageButton ibTimeline;


    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentManager fragmentManager = getSupportFragmentManager();

        ibLogout = findViewById(R.id.ibLogout);
        ibTimeline = findViewById(R.id.ibTimeline);
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        ibLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ClickLister is working");

                goLogoutActivity();
            }
        });


        ibTimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ClickLister is working");

                goTimelineActivity();
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()) {
                    case R.id.action_home:
                        //TODO Swap Fragment Here
                        fragment = new PostsFragment();
                        Toast.makeText(MainActivity.this, "Home selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_compose:
                        fragment = new ComposeFragment();
                        Toast.makeText(MainActivity.this, "Compose selected", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_profile:
                    default:
                        //TODO Swap Fragment Here
                        fragment = new ComposeFragment();
                        Toast.makeText(MainActivity.this, "Profile selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragment).commit(); //fix this fragment issue!!
                return true;
            }
        });
        // Set default selection
        bottomNavigationView.setSelectedItemId(R.id.action_home);
    }




    private void goTimelineActivity() {
        Log.d(TAG, "Navigating to timeline activity");
        Intent i = new Intent(MainActivity.this, TimelineActivity.class);
        startActivity(i);
        finish();
    }

    private void goLogoutActivity() {
        Log.d(TAG, "Navigating to logout activity");
        Intent i = new Intent(MainActivity.this, LogoutActivity.class);
        startActivity(i);
        finish();
    }


            }
