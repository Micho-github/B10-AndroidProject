package com.example.mag_marketplace.ui.reserved;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mag_marketplace.R;
import com.example.mag_marketplace.databinding.FragmentReservedBinding;
import com.example.mag_marketplace.ui.additem.AddItemFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReservedFragment extends Fragment {

    private ImageView imageView1;
    private TextView name1,sellername,selleremail,sellerphone,sellerprice,reservedate;
    private RelativeLayout relativeLayout1;

    private Button removebtn;

    private int itemid1;

private FragmentReservedBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        ReservedViewModel ReservedViewModel =
                new ViewModelProvider(this).get(ReservedViewModel.class);

        binding = FragmentReservedBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final TextView textView = binding.textReflow;
        ReservedViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);


        imageView1 = binding.reservedimage1;
        name1 = binding.reservedtext1;
        sellername = binding.Sellername;
        selleremail = binding.selleremail;
        sellerphone = binding.sellerphone;
        sellerprice = binding.sellerprice;
        reservedate = binding.reservedate;
        removebtn = binding.button2;

        removebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowToast("Reservation cancelled action.");
            }
        });

        relativeLayout1 = root.findViewById(R.id.reservedrelativeLayout1);


        new ReservedFragment.FetchItemsTask().execute("http://10.0.2.2:8080/myreserveditems.php");

        return root;
    }
    public void ShowToast( String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private class FetchItemsTask extends AsyncTask<String, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
                return new JSONArray(s.hasNext() ? s.next() : "");
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            if (jsonArray != null) {
                try {
                    // Assuming jsonArray has items in the expected format
                    JSONObject item1 = jsonArray.getJSONObject(0);

                    sellername.setText(item1.getString("Username"));
                    selleremail.setText(item1.getString("Email"));
                    sellerphone.setText(item1.getString("Phone_no"));
                    itemid1 = Integer.parseInt(item1.getString("Item_id"));
                    new ReservedFragment.DownloadImageTask(imageView1).execute(item1.getString("Item_Image"));
                    name1.setText(item1.getString("Item_Name"));
                    sellerprice.setText(item1.getString("Price"));
                    reservedate.setText(item1.getString("Date_Of_Request"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;

        DownloadImageTask(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                // Decode the base64 string to obtain the image data
                byte[] imageBytes = Base64.decode(params[0], Base64.DEFAULT);

                // Convert the image data to a Bitmap
                return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }
        }
    }
}