package com.example.tugaskuis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private SharedPrefManager sharedPrefManager;
    private EditText etUsername, etPassword;
    private Button btnLogin;
    private ProgressBar pbLogin;
    private ImageView ivLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPrefManager = new SharedPrefManager(this);

        if (sharedPrefManager.getIsLogin()) {
            startHomeUi();
        } else {
            etUsername = findViewById(R.id.etUsername);
            etPassword = findViewById(R.id.etPassword);
            btnLogin = findViewById(R.id.btnLogin);
            pbLogin = findViewById(R.id.pbLogin);
            ivLogin = findViewById(R.id.ivLogin);

            login();
        }
    }

    private void startHomeUi() {
        Intent i = new Intent(MainActivity.this, HomeActivity.class);
        startActivity(i);
        finishAffinity();
    }

    private void login() {
        btnLogin.setOnClickListener(v -> {
            final String username = etUsername.getText().toString();
            final String password = etPassword.getText().toString();

            pbLogin.setVisibility(View.VISIBLE);
            ivLogin.setVisibility(View.GONE);

            if (username.isEmpty() || password.isEmpty()) {
                pbLogin.setVisibility(View.GONE);
                ivLogin.setVisibility(View.VISIBLE);
                Toast.makeText(MainActivity.this, "Username dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
            } else {
                handler.postDelayed(() -> {
                    String spUsername = sharedPrefManager.getUsername();
                    String spPassword = sharedPrefManager.getPassword();

                    Log.d("username", "user" + username);
                    Log.d("password", "pass" + password);

                    if (username.equals(spUsername) && password.equals(spPassword)) {
                        Intent i = new Intent(MainActivity.this, HomeActivity.class);
                        sharedPrefManager.saveIsLogin(true);
                        finishAffinity();
                        startActivity(i);
                    } else {
                        pbLogin.setVisibility(View.GONE);
                        ivLogin.setVisibility(View.VISIBLE);
                        Toast.makeText(MainActivity.this, "Username dan password salah", Toast.LENGTH_SHORT).show();
                    }
                }, 3000);
            }
        });
    }
}