package com.example.mag_marketplace;

import android.os.Handler;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    private EditText Username, Password, FirstName, LastName, NewUsername, Email, Phone,ConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        FirstName = (EditText) findViewById(R.id.FirstName);
        LastName = (EditText) findViewById(R.id.LastName);
        NewUsername = (EditText) findViewById(R.id.NewUsername);
        Email = (EditText) findViewById(R.id.NewEmail);
        Password = (EditText) findViewById(R.id.Password);
        Phone = (EditText) findViewById(R.id.Phone);
    }
    public void RegisterAction(View v) {
        String firstname = FirstName.getText().toString().toLowerCase();
        String lastname = LastName.getText().toString().toLowerCase();
        String username = NewUsername.getText().toString().toLowerCase();
        String email = Email.getText().toString();
        String password = Password.getText().toString().toLowerCase();
        String phone = Phone.getText().toString();
        String type = "register";
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type,firstname,lastname,username,email,phone,password);
        }
        public void GoBack(View v){
            finish();

    }
}

