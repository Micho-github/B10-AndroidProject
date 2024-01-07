package com.example.mag_marketplace;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ItemDisplay extends AppCompatActivity {

    private TextView itemNameTextView;
    private TextView itemDescriptionTextView;
    private TextView priceTextView;
    private ImageView itemImageView;
    private TextView usernameTextView;
    private TextView emailTextView;
    private TextView phoneNoTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item);

        itemNameTextView = findViewById(R.id.itemName);
        itemDescriptionTextView = findViewById(R.id.description);
        priceTextView = findViewById(R.id.price);
        itemImageView = findViewById(R.id.image);
        usernameTextView = findViewById(R.id.sellerName);
        emailTextView = findViewById(R.id.sellerEmail);
        phoneNoTextView = findViewById(R.id.sellerPhoneNumber);

        // Get item ID from the Intent
        int itemId = getIntent().getIntExtra("Item_id", -1);
        Log.d("ITEM_ID_DEBUG", "Item ID: " + itemId);
        if (itemId != -1) {
            // Execute AsyncTask with the URL containing the item ID
            new FetchItemTask().execute("http://10.0.2.2:8080/displayitem.php?Item_id="+itemId);
        } else {
            showToast("Item Not found");
        }
    }

    private class FetchItemTask extends AsyncTask<String, Void, JSONObject> {

        @Override
        protected JSONObject doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }

                String jsonString = result.toString();
                Log.d("URL_DEBUG", "URL: " + params[0]);
                // Debugging: Print the received JSON response to the console
                Log.d("JSON_RESPONSE", jsonString);

                return new JSONObject(jsonString);

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        @Override
        protected void onPostExecute(JSONObject item) {
            if (item != null) {
                try {
                    itemNameTextView.setText(item.getString("Item_Name"));
                    itemDescriptionTextView.setText(item.getString("Item_Description"));
                    priceTextView.setText(item.getString("Price"));
                    usernameTextView.setText(item.getString("Username"));
                    emailTextView.setText(item.getString("Email"));
                    phoneNoTextView.setText(item.getString("Phone_No"));

                    // Decode and set the base64 image into ImageView
                    String base64Image = item.getString("Item_Image");
                    byte[] decodedString = android.util.Base64.decode(base64Image, android.util.Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    itemImageView.setImageBitmap(decodedByte);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                // Handle the case when there's an issue with the network request or parsing JSON
            }
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void Request(View v){
        showToast("Item successfully reserved");
    }
    public void favorite(View v) {
        showToast("Item added to favorites");
    }
}
