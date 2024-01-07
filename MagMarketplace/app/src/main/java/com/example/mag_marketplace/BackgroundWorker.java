package com.example.mag_marketplace;

import static androidx.core.content.ContextCompat.startActivity;

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

public class BackgroundWorker extends AsyncTask<String,Void,String> {
    Context context;
    private static final int DURATION_MILLIS = 3000; // 1 second
    AlertDialog alertDialog;
    BackgroundWorker (Context ctx){
        context = ctx;
    }

    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://10.0.2.2:8080/login.php";
        String register_url = "http://10.0.2.2:8080/register.php";
        if(type.equals("login")){
            try {
                String username = params[1];
                String password = params[2];
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("username","UTF-8")+"=" +URLEncoder.encode(username,"UTF-8")+"&"
                +URLEncoder.encode("password","UTF-8")+"=" +URLEncoder.encode(password,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!=null){
                    result +=line;
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
        }else if(type.equals("register")){
            try {
                String firstname = params[1];
                String lastname = params[2];
                String username = params[3];
                String email = params[4];
                String phone = params[5];
                String password = params[6];
                URL url = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String post_data = URLEncoder.encode("first_name","UTF-8")+"=" +URLEncoder.encode(firstname,"UTF-8")+"&"
                +URLEncoder.encode("last_name","UTF-8")+"=" +URLEncoder.encode(lastname,"UTF-8")+"&"
                +URLEncoder.encode("username","UTF-8")+"=" +URLEncoder.encode(username,"UTF-8")+"&"
                +URLEncoder.encode("email","UTF-8")+"=" +URLEncoder.encode(email,"UTF-8")+"&"
                +URLEncoder.encode("password","UTF-8")+"=" +URLEncoder.encode(password,"UTF-8")+"&"
                +URLEncoder.encode("phone_no","UTF-8")+"=" +URLEncoder.encode(phone,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!=null){
                    result +=line;
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
        alertDialog.setTitle("login status");
    }

    @Override
    protected void onPostExecute(String result) {
        showAlertDialog(context, result);
        if (result.equals("Login successfull")) {
            new Handler().postDelayed(() -> {
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
            }, DURATION_MILLIS);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    public static void showAlertDialog(Context context, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle("Login Status")
                .setMessage(message)
                .setCancelable(false)
                .create();

        alertDialog.show();

        // Use a Handler to dismiss the dialog after a specific duration
        new Handler().postDelayed(() -> {
            if (alertDialog.isShowing()) {
                alertDialog.dismiss();
            }
        }, DURATION_MILLIS);
    }
}
