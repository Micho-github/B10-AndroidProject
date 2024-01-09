package com.example.mag_marketplace.ui.home;

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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mag_marketplace.FilterActivity;
import com.example.mag_marketplace.Item;
import com.example.mag_marketplace.ItemAdapter;
import com.example.mag_marketplace.ItemDisplay;
import com.example.mag_marketplace.R;
import com.example.mag_marketplace.SearchActivity;
import com.example.mag_marketplace.databinding.FragmentHomeBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ImageView imageView1, imageView2, imageView3,imageView4, imageView5, imageView6,imageView7;
    private TextView name1, name2, name3,name4, name5, name6,name7, price1, price2, price3, price4, price5, price6,price7;
    private LinearLayout linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5,linearLayout6,linearLayout7;
    private int itemid1,itemid2,itemid3,itemid4,itemid5,itemid6,itemid7;
    private Button Search, Filter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        imageView1 = binding.image1;
        imageView2 = binding.image2;
        imageView3 = binding.image3;
        imageView4 = binding.image4;
        imageView5 = binding.image5;
        imageView6 = binding.image6;
        imageView7 = binding.image7;

        name1 = binding.name1;
        name2 = binding.name2;
        name3 = binding.name3;
        name4 = binding.name4;
        name5 = binding.name5;
        name6 = binding.name6;
        name7 = binding.name7;

        price1 = binding.price1;
        price2 = binding.price2;
        price3 = binding.price3;
        price4 = binding.price4;
        price5 = binding.price5;
        price6 = binding.price6;
        price7 = binding.price7;

        linearLayout1 = root.findViewById(R.id.item1);
        linearLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getActivity(), ItemDisplay.class);
                intent1.putExtra("Item_id", itemid1);
                startActivity(intent1);
            }
        });

        linearLayout2 = root.findViewById(R.id.item2);
        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), ItemDisplay.class);
                intent2.putExtra("Item_id", itemid2);
                startActivity(intent2);
            }
        });

        linearLayout3 = root.findViewById(R.id.item3);
        linearLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getActivity(), ItemDisplay.class);
                intent3.putExtra("Item_id", itemid3);
                startActivity(intent3);
            }
        });
        linearLayout4 = root.findViewById(R.id.item4);
        linearLayout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(getActivity(), ItemDisplay.class);
                intent4.putExtra("Item_id", itemid4);
                startActivity(intent4);
            }
        });
        linearLayout5 = root.findViewById(R.id.item5);
        linearLayout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(getActivity(), ItemDisplay.class);
                intent5.putExtra("Item_id", itemid5);
                startActivity(intent5);
            }
        });
        linearLayout6 = root.findViewById(R.id.item6);
        linearLayout6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent6 = new Intent(getActivity(), ItemDisplay.class);
                intent6.putExtra("Item_id", itemid6);
                startActivity(intent6);
            }
        });
        linearLayout7 = root.findViewById(R.id.item7);
        linearLayout7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent7 = new Intent(getActivity(), ItemDisplay.class);
                intent7.putExtra("Item_id", itemid7);
                startActivity(intent7);
            }
        });


        new FetchItemsTask().execute("http://10.0.2.2:8080/display_item_home.php");


        Search = root.findViewById(R.id.search_button);
        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });
        Filter = root.findViewById(R.id.filter_button);
        Filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FilterActivity.class);
                startActivity(intent);
            }
        });


        return root;
    }

    private void updateUIForYou(Item item, ImageView imageView, TextView nameTextView, TextView priceTextView) {
        nameTextView.setText(item.getItemName());
        priceTextView.setText(String.valueOf(item.getPrice()));
        Bitmap bitmap = BitmapFactory.decodeByteArray(item.getItemImageBytes(), 0, item.getItemImageBytes().length);

        // Set the Bitmap to the ImageView
        imageView.setImageBitmap(bitmap);
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
                    JSONObject item2 = jsonArray.getJSONObject(1);
                    JSONObject item3 = jsonArray.getJSONObject(2);
                    JSONObject item4 = jsonArray.getJSONObject(3);
                    JSONObject item5 = jsonArray.getJSONObject(4);
                    JSONObject item6 = jsonArray.getJSONObject(5);
                    JSONObject item7 = jsonArray.getJSONObject(6);


                    itemid1 = Integer.parseInt(item1.getString("Item_id"));
                    name1.setText(item1.getString("Item_Name"));
                    price1.setText("$"+item1.getString("Price"));
                    new DownloadImageTask(imageView1).execute(item1.getString("Item_Image"));

                    itemid2 = Integer.parseInt(item2.getString("Item_id"));
                    name2.setText(item2.getString("Item_Name"));
                    price2.setText("$"+item2.getString("Price"));
                    new DownloadImageTask(imageView2).execute(item2.getString("Item_Image"));

                    itemid3 = Integer.parseInt(item3.getString("Item_id"));
                    name3.setText(item3.getString("Item_Name"));
                    price3.setText("$"+item3.getString("Price"));
                    new DownloadImageTask(imageView3).execute(item3.getString("Item_Image"));

                    itemid4 = Integer.parseInt(item4.getString("Item_id"));
                    name4.setText(item4.getString("Item_Name"));
                    price4.setText("$"+item4.getString("Price"));
                    new DownloadImageTask(imageView4).execute(item4.getString("Item_Image"));

                    itemid5 = Integer.parseInt(item5.getString("Item_id"));
                    name5.setText(item5.getString("Item_Name"));
                    price5.setText("$"+item5.getString("Price"));
                    new DownloadImageTask(imageView5).execute(item5.getString("Item_Image"));

                    itemid6 = Integer.parseInt(item6.getString("Item_id"));
                    name6.setText(item6.getString("Item_Name"));
                    price6.setText("$"+item6.getString("Price"));
                    new DownloadImageTask(imageView6).execute(item6.getString("Item_Image"));

                    itemid7 = Integer.parseInt(item7.getString("Item_id"));
                    name7.setText(item7.getString("Item_Name"));
                    price7.setText("$"+item7.getString("Price"));
                    new DownloadImageTask(imageView7).execute(item7.getString("Item_Image"));
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
