package com.example.mag_marketplace;

import android.os.Handler;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText Username, Password, FirstName, LastName, NewUsername, Email, NewPassword,ConfirmPassword;
    private Drawable FirstNameInitialDrawable, LastNameInitialDrawable, NewUsernameInitialDrawable, EmailInitialDrawable, NewPasswordInitialDrawable, ConfirmPasswordInitialDrawable;
    protected boolean wasOffline = true;
    private static final int CHECK_INTERVAL = 5000; // Check every 5 seconds
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        NewUsername = (EditText) findViewById(R.id.NewUsername);
        Email = (EditText) findViewById(R.id.NewEmail);
        NewPassword = (EditText) findViewById(R.id.NewPassword);
        ConfirmPassword = (EditText) findViewById(R.id.NewPassword);
        FirstNameInitialDrawable = FirstName.getCompoundDrawables()[0];
        LastNameInitialDrawable = LastName.getCompoundDrawables()[0];
        NewUsernameInitialDrawable = NewUsername.getCompoundDrawables()[0];
        EmailInitialDrawable = Email.getCompoundDrawables()[0];
        NewPasswordInitialDrawable = NewPassword.getCompoundDrawables()[0];
        ConfirmPasswordInitialDrawable = ConfirmPassword.getCompoundDrawables()[0];

    }

    public void Register(View v) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (FirstName != null && FirstName.getText().toString().length() > 5) {
                    FirstName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_verified_24, 0);
                } else if (FirstName.getCompoundDrawables()[0] != FirstNameInitialDrawable) {
                    FirstName.setCompoundDrawables(FirstNameInitialDrawable, null, null, null);
                }

                if (LastName != null && LastName.getText().toString().length() > 5) {
                    LastName.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_verified_24, 0);
                } else if (LastName.getCompoundDrawables()[0] != LastNameInitialDrawable) {
                    LastName.setCompoundDrawables(LastNameInitialDrawable, null, null, null);
                }
                if (NewUsername != null && NewUsername.getText().toString().length() > 5) {
                    NewUsername.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_verified_24, 0);
                } else if (NewUsername.getCompoundDrawables()[0] != NewUsernameInitialDrawable) {
                    NewUsername.setCompoundDrawables(NewUsernameInitialDrawable, null, null, null);
                }
                if (Email != null && Email.getText().toString().length() > 5) {
                    Email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_verified_24, 0);
                } else if (Email.getCompoundDrawables()[0] != EmailInitialDrawable) {
                    Email.setCompoundDrawables(EmailInitialDrawable, null, null, null);
                }
                if (NewPassword != null && NewPassword.getText().toString().length() > 5) {
                    NewPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_verified_24, 0);
                } else if (NewPassword.getCompoundDrawables()[0] != NewPasswordInitialDrawable) {
                    NewPassword.setCompoundDrawables(NewPasswordInitialDrawable, null, null, null);
                }
                if (ConfirmPassword != null && ConfirmPassword.getText().toString().length() > 5) {
                    ConfirmPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.baseline_verified_24, 0);
                } else if (ConfirmPassword.getCompoundDrawables()[0] != ConfirmPasswordInitialDrawable) {
                    ConfirmPassword.setCompoundDrawables(ConfirmPasswordInitialDrawable, null, null, null);
                }
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }
        public void GoBack(View v){
            finish();

    }
}

