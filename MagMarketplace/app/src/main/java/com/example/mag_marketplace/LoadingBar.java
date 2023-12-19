package com.example.mag_marketplace;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Handler;

public class LoadingBar {
    private ProgressDialog progressDialog;

    public void showProgressBar(final Activity activity, String message) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Automatically dismiss the progress bar after 5 seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideProgressBar();
            }
        }, 5000); // 5000 milliseconds (5 seconds)
    }

    public void hideProgressBar() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
