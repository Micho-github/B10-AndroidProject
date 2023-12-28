package com.example.mag_marketplace;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Handler;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ApplicationErrorReport;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.content.Context;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
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
        final TextView Broadcast = findViewById(R.id.Broadcast);
        int screenWidth = getResources().getDisplayMetrics().widthPixels;
        ObjectAnimator slideRight = ObjectAnimator.ofFloat(Broadcast, "translationX", 0f, screenWidth*2);
        slideRight.setDuration(5000); // Set the duration of the animation in milliseconds

        // Create an ObjectAnimator to slide back from the left
        ObjectAnimator slideLeft = ObjectAnimator.ofFloat(Broadcast, "translationX", -screenWidth, 0f);
        slideLeft.setDuration(5000); // Set the duration of the animation in milliseconds

        // Create an AnimatorSet to play the animations sequentially
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(slideRight, slideLeft);
        animatorSet.setInterpolator(new LinearInterpolator()); // Linear interpolation for smooth movement

        // Set the repeat mode to restart for both animations
        slideRight.setRepeatMode(ObjectAnimator.RESTART);
        slideLeft.setRepeatMode(ObjectAnimator.RESTART);

        // Set the repeat count to infinite for both animations
        slideRight.setRepeatCount(ObjectAnimator.INFINITE);
        slideLeft.setRepeatCount(ObjectAnimator.INFINITE);

        animatorSet.start(); // Start the animation

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
    public void LoginRequest(View v){
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

}