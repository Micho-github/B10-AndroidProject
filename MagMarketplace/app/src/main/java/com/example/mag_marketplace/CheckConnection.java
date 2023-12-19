package com.example.mag_marketplace;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CheckConnection extends AppCompatActivity {
    Boolean wasOffline=true;
    private static final int CHECK_INTERVAL = 5000; // Check every 5 seconds
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startInternetCheck();
    }
    public static class NetworkUtils {
        public static boolean isInternetConnected(Context context) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void startInternetCheck() {
        LoadingBar loadingbar = new LoadingBar();

// Show the progress bar with a message
        loadingbar.showProgressBar(this, "Connecting...");
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (NetworkUtils.isInternetConnected(CheckConnection.this)) {
                    if (wasOffline == true) {
                        showToast("You are online");
                        wasOffline = false;
                        finish();
                    }
                } else {
                    setContentView(R.layout.noconnection_layout);
                    if (wasOffline == false) {
                        showToast("No Internet Connection");
                        setContentView(R.layout.noconnection_layout);
                        wasOffline = true;
                    }
                }
                // Continue checking periodically
                handler.postDelayed(this, CHECK_INTERVAL);
            }
        }, CHECK_INTERVAL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Remove the callback to prevent memory leaks when the activity is destroyed
        handler.removeCallbacksAndMessages(null);
    }

}
