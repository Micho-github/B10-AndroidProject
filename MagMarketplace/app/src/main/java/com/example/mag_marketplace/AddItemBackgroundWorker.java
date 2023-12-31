package com.example.mag_marketplace;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class AddItemBackgroundWorker extends AsyncTask<String, Void, String> {
    private AddItemActivity context;

    AlertDialog alertDialog;
    private static final int DURATION_MILLIS = 3000;

    public AddItemBackgroundWorker(AddItemActivity ctx) {
        this.context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];


        String additem_url = "http://10.0.2.2:8080/additem.php";
        if (type.equals("additem")) {
            try {
                String encodedImage = params[1];
                String itemname = params[2];
                String description = params[3];
                String price = params[4];
                String userid = params[5];
                String categorycode = params[6];
                String subcategorycode = params[7];
                String citycode = params[8];

                URL url = new URL(additem_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data =
                        URLEncoder.encode("itemimage", "UTF-8") + "=" + URLEncoder.encode(encodedImage, "UTF-8") + "&"
                                + URLEncoder.encode("itemname", "UTF-8") + "=" + URLEncoder.encode(itemname, "UTF-8") + "&"
                                + URLEncoder.encode("itemdescription", "UTF-8") + "=" + URLEncoder.encode(description, "UTF-8") + "&"
                                + URLEncoder.encode("itemprice", "UTF-8") + "=" + URLEncoder.encode(price, "UTF-8") + "&"
                                + URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8") + "&"
                                + URLEncoder.encode("categorycode", "UTF-8") + "=" + URLEncoder.encode(categorycode, "UTF-8") + "&"
                                + URLEncoder.encode("subcategorycode", "UTF-8") + "=" + URLEncoder.encode(subcategorycode, "UTF-8") + "&"
                                + URLEncoder.encode("citycode", "UTF-8") + "=" + URLEncoder.encode(citycode, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle("submit status");
    }

    @Override
    protected void onPostExecute(String result) {
        showAlertDialogWithButton(context, result);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public static void showAlertDialogWithButton(final Context context, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("Submit Status");
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false);

        // Set a positive button with a click listener
        alertDialogBuilder.setPositiveButton("Done", (dialog, which) -> {
            // Finish the activity
            if (context instanceof Activity) {
                ((Activity) context).finish();
            }
        });

        // Create and show the dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
