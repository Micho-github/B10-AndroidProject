package com.example.mag_marketplace.ui.additem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.mag_marketplace.AddItemActivity;
import com.example.mag_marketplace.ForgotPasswordActivity;
import com.example.mag_marketplace.ItemDisplay;
import com.example.mag_marketplace.LoginActivity;
import com.example.mag_marketplace.MainActivity;
import com.example.mag_marketplace.R;
import com.example.mag_marketplace.databinding.FragmentAdditemBinding;
import com.example.mag_marketplace.databinding.FragmentFavoritesBinding;
import com.example.mag_marketplace.edititem;
import com.example.mag_marketplace.ui.favorites.FavoritesViewModel;
import com.example.mag_marketplace.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AddItemFragment extends Fragment {

    private ImageView imageView1, imageView2, imageView3;
    private TextView name1, name2, name3;
    private LinearLayout linearLayout1,linearLayout2,linearLayout3;
    private int itemid1,itemid2,itemid3;
private FragmentAdditemBinding binding;
    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        AdditemViewModel AdditemViewModel =
                new ViewModelProvider(this).get(AdditemViewModel.class);

    binding = FragmentAdditemBinding.inflate(inflater, container, false);
    View root = binding.getRoot();

        final TextView textView = binding.textReflow;
        AdditemViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        LinearLayout linearLayout = root.findViewById(R.id.linearLayout);

        imageView1 = binding.myitemimage1;
        imageView2 = binding.myitemimage2;
        imageView3 = binding.myitemimage3;

        name1 = binding.myitemtext1;
        name2 = binding.myitemtext2;
        name3 = binding.myitemtext3;

        linearLayout1 = root.findViewById(R.id.myitemlinearLayout1);
        linearLayout2 = root.findViewById(R.id.myitemlinearLayout2);
        linearLayout3 = root.findViewById(R.id.myitemlinearLayout3);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(getActivity(), edititem.class);
                intent10.putExtra("Item_id", itemid1);
                startActivity(intent10);
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent11 = new Intent(getActivity(), edititem.class);
                intent11.putExtra("Item_id", itemid2);
                startActivity(intent11);
            }
        });

        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent12 = new Intent(getActivity(), edititem.class);
                intent12.putExtra("Item_id", itemid3);
                startActivity(intent12);
            }
        });

        new AddItemFragment.FetchItemsTask().execute("http://10.0.2.2:8080/myitems.php");

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddNewItem(v);
            }
        });

        return root;
    }

@Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void AddNewItem(View v){
        Log.d("AddItemFragment", "AddNewItem method called");
        // Your implementation
        Intent intent = new Intent(getActivity(), AddItemActivity.class);
        startActivity(intent);
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
                    JSONObject item2 = jsonArray.getJSONObject(1);
                    JSONObject item3 = jsonArray.getJSONObject(2);

                    itemid1 = Integer.parseInt(item1.getString("Item_id"));
                    name1.setText(item1.getString("Item_Name"));
                    new AddItemFragment.DownloadImageTask(imageView1).execute(item1.getString("Item_Image"));

                    itemid2 = Integer.parseInt(item2.getString("Item_id"));
                    name2.setText(item2.getString("Item_Name"));
                    new AddItemFragment.DownloadImageTask(imageView2).execute(item2.getString("Item_Image"));

                    itemid3 = Integer.parseInt(item3.getString("Item_id"));
                    name3.setText(item3.getString("Item_Name"));
                    new AddItemFragment.DownloadImageTask(imageView3).execute(item3.getString("Item_Image"));

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