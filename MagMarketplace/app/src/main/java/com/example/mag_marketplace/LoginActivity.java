package com.example.mag_marketplace;

import android.content.Intent;
import android.os.Handler;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ApplicationErrorReport;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Context;
import android.os.Looper;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.view.View;
import android.widget.ProgressBar;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private EditText Username, Password;
    protected boolean wasOffline = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginactivity);
        Username = (EditText) findViewById(R.id.Username);
        Password = (EditText) findViewById(R.id.Password);
        Intent checkConnectionIntent = new Intent(this, CheckConnection.class);
        startActivity(checkConnectionIntent);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void Register(View v){
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
    public void ForgotPassword(View v){
        startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
    }

}