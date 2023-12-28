package com.example.mag_marketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText Email,NewPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword);
        Email = (EditText) findViewById(R.id.RegisteredUsername);
        NewPassword = (EditText) findViewById(R.id.RegisteredEmail);

    }
    public void GoBack(View v){
        finish();
    }
}
