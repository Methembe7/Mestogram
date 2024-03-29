package com.mmoyo.mestogram;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private static String TAG = "tag for logging";
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ClickLister is working");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                login(username, password);
            }
        });
        btnSignup = findViewById(R.id.btnSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "ClickLister is working to navigate to goSignupActivity()");
                goSignupActivity();
            }
        });
    }

    private void goSignupActivity() {

        Log.d(TAG, "Navigating to signup activity");
        Intent i = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(i);
        finish();

    }

    private void login(String username, String password) {
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "issue with log in" , e);
                    e.printStackTrace();
                    return;

                }

                //TO DO : NAMIGATE TO NEW ACTIVITY IF USER HAS SIGNED IN PROPERLY
                goMainActivity();
                Log.i(TAG, "goMainActivity() called");

            }
        });
    }
    private void goMainActivity(){
        Log.d(TAG, "Navigating to main activity");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();

    }
}

