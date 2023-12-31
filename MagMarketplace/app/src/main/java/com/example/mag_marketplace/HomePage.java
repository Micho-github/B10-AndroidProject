import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView1, imageView2, imageView3;
    private TextView name1, name2, name3, price1, price2, price3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);

        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        name3 = findViewById(R.id.name3);

        price1 = findViewById(R.id.price1);
        price2 = findViewById(R.id.price2);
        price3 = findViewById(R.id.price3);

        new FetchItemsTask().execute("http://localhost/display_item_home.php");
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

                    name1.setText(item1.getString("Item_Name"));
                    price1.setText(item1.getString("Price"));
                    new DownloadImageTask(imageView1).execute(item1.getString("Item_Image"));

                    name2.setText(item2.getString("Item_Name"));
                    price2.setText(item2.getString("Price"));
                    new DownloadImageTask(imageView2).execute(item2.getString("Item_Image"));

                    name3.setText(item3.getString("Item_Name"));
                    price3.setText(item3.getString("Price"));
                    new DownloadImageTask(imageView3).execute(item3.getString("Item_Image"));
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
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
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
