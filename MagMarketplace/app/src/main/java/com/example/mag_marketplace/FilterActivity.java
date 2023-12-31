package com.example.mag_marketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FilterActivity extends AppCompatActivity{
        private ListView filterResultsListView;
        private TextView noResultsTextView;
        private Button FilterButton;
        private List<String> allCategories;
        private List<String> allSubcategories;
        private List<String> allCities;
    private EditText ItemName,Price,Description;
    private Spinner Category,SubCategory,City;
    String[] categories = {
            "Electronics",
            "Clothing",
            "Home and Garden",
            "Sports and Outdoors",
            "Books and Stationery",
            "Health and Beauty",
            "Toys and Games",
            "Automotive",
            "Appliances",
            "Jewelry and Accessories",
            "Grocery and Gourmet",
            "Music and Instruments"
    };
    // Subcategories for Electronics
    String[] electronicsSubcategories = {
            "Smartphones",
            "Laptops",
            "Cameras",
            "Audio Devices",
            "Headphones",
            "Speakers"
    };

    // Subcategories for Clothing
    String[] clothingSubcategories = {
            "Men's",
            "Shirts",
            "Pants",
            "Jackets",
            "Women's",
            "Dresses",
            "Skirts",
            "Blouses",
            "Children's",
            "Boys",
            "Girls"
    };

    // Subcategories for Home and Garden
    String[] homeAndGardenSubcategories = {
            "Furniture",
            "Living Room",
            "Bedroom",
            "Outdoor",
            "Appliances",
            "Kitchen",
            "Laundry",
            "Home Decor",
            "Lighting",
            "Wall Art",
            "Rugs"
    };

    // Subcategories for Sports and Outdoors
    String[] sportsAndOutdoorsSubcategories = {
            "Exercise Equipment",
            "Treadmills",
            "Weights",
            "Yoga Mats",
            "Outdoor Recreation",
            "Camping",
            "Hiking",
            "Biking"
    };

    // Subcategories for Books and Stationery
    String[] booksAndStationerySubcategories = {
            "Fiction",
            "Non-Fiction",
            "Notebooks",
            "Pens",
            "Calendars"
    };

    // Subcategories for Health and Beauty
    String[] healthAndBeautySubcategories = {
            "Skincare",
            "Cleansers",
            "Moisturizers",
            "Serums",
            "Haircare",
            "Shampoo",
            "Conditioner",
            "Styling Products",
            "Makeup",
            "Lipstick",
            "Eyeshadow",
            "Foundation"
    };

    // Subcategories for Toys and Games
    String[] toysAndGamesSubcategories = {
            "Board Games",
            "Puzzles",
            "Action Figures",
            "Outdoor Toys"
    };

    // Subcategories for Automotive
    String[] automotiveSubcategories = {
            "Car Parts",
            "Tools",
            "Accessories",
            "Tires"
    };

    // Subcategories for Appliances
    String[] appliancesSubcategories = {
            "Kitchen Appliances",
            "Blenders",
            "Coffee Makers",
            "Microwaves",
            "Home Appliances",
            "Vacuum Cleaners",
            "Air Purifiers",
            "Irons"
    };

    // Subcategories for Jewelry and Accessories
    String[] jewelryAndAccessoriesSubcategories = {
            "Necklaces",
            "Bracelets",
            "Watches",
            "Sunglasses"
    };

    // Subcategories for Grocery and Gourmet
    String[] groceryAndGourmetSubcategories = {
            "Snacks",
            "Beverages",
            "Cooking Ingredients",
            "Organic and Natural"
    };

    // Subcategories for Music and Instruments
    String[] musicAndInstrumentsSubcategories = {
            "Guitars",
            "Keyboards",
            "Headphones",
            "Sheet Music"
    };
    String[] lebaneseCities = {
            "Beirut",
            "Tripoli",
            "Sidon (Saida)",
            "Tyre (Sour)",
            "Byblos (Jbeil)",
            "Jounieh",
            "Baabda",
            "Nabatieh",
            "Zahle",
            "Anjar",
            "Baalbek",
            "Bcharre",
            "Bint Jbeil",
            "Chouf",
            "Jezzine",
            "Keserwan",
            "Rashaya",
            "Zgharta",
            "Aley",
            "Batroun",
            "Hermel"
    };

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.filter);
            FilterButton = (Button)findViewById(R.id.filter);
            Category = findViewById(R.id.categorySpinner);
            SubCategory = findViewById(R.id.subcategorySpinner);
            City = findViewById(R.id.citySpinner);
            filterResultsListView = findViewById(R.id.filterResultsListView);
            noResultsTextView = findViewById(R.id.noResultsTextView);


            ArrayAdapter<String> Categoryadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
            Categoryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Category.setAdapter(Categoryadapter);
            ArrayAdapter<String> electronicsAdapter = new ArrayAdapter<>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, electronicsSubcategories);
            electronicsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            SubCategory.setAdapter(electronicsAdapter);
            Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    // Handle the selection
                    String selectedCategory = categories[position];
                    switch (selectedCategory){
                        case "Electronics":
                            // Set up adapter for electronics subcategories
                            ArrayAdapter<String> electronicsAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, electronicsSubcategories);
                            electronicsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(electronicsAdapter);
                            break;

                        case "Clothing":
                            // Set up adapter for clothing subcategories
                            ArrayAdapter<String> clothingAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, clothingSubcategories);
                            clothingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(clothingAdapter);
                            break;

                        case "Home and Garden":
                            // Set up adapter for fragment_home.xml and garden subcategories
                            ArrayAdapter<String> homeAndGardenAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, homeAndGardenSubcategories);
                            homeAndGardenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(homeAndGardenAdapter);
                            break;

                        case "Sports and Outdoors":
                            // Set up adapter for sports and outdoors subcategories
                            ArrayAdapter<String> sportsAndOutdoorsAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, sportsAndOutdoorsSubcategories);
                            sportsAndOutdoorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(sportsAndOutdoorsAdapter);
                            break;

                        case "Books and Stationery":
                            // Set up adapter for books and stationery subcategories
                            ArrayAdapter<String> booksAndStationeryAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, booksAndStationerySubcategories);
                            booksAndStationeryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(booksAndStationeryAdapter);
                            break;

                        case "Health and Beauty":
                            // Set up adapter for health and beauty subcategories
                            ArrayAdapter<String> healthAndBeautyAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, healthAndBeautySubcategories);
                            healthAndBeautyAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(healthAndBeautyAdapter);
                            break;

                        case "Toys and Games":
                            // Set up adapter for toys and games subcategories
                            ArrayAdapter<String> toysAndGamesAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, toysAndGamesSubcategories);
                            toysAndGamesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(toysAndGamesAdapter);
                            break;

                        case "Automotive":
                            // Set up adapter for automotive subcategories
                            ArrayAdapter<String> automotiveAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, automotiveSubcategories);
                            automotiveAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(automotiveAdapter);
                            break;

                        case "Appliances":
                            // Set up adapter for appliances subcategories
                            ArrayAdapter<String> appliancesAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, appliancesSubcategories);
                            appliancesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(appliancesAdapter);
                            break;

                        case "Jewelry and Accessories":
                            // Set up adapter for jewelry and accessories subcategories
                            ArrayAdapter<String> jewelryAndAccessoriesAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, jewelryAndAccessoriesSubcategories);
                            jewelryAndAccessoriesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(jewelryAndAccessoriesAdapter);
                            break;

                        case "Grocery and Gourmet":
                            // Set up adapter for grocery and gourmet subcategories
                            ArrayAdapter<String> groceryAndGourmetAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, groceryAndGourmetSubcategories);
                            groceryAndGourmetAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(groceryAndGourmetAdapter);
                            break;

                        case "Music and Instruments":
                            // Set up adapter for music and instruments subcategories
                            ArrayAdapter<String> musicAndInstrumentsAdapter = new ArrayAdapter<>(getApplicationContext(),
                                    android.R.layout.simple_spinner_item, musicAndInstrumentsSubcategories);
                            musicAndInstrumentsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            SubCategory.setAdapter(musicAndInstrumentsAdapter);
                            break;
                        default:
                            // Handle the default case
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // Do nothing here
                }
            });
            ArrayAdapter<String> CityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lebaneseCities);
            CityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            City.setAdapter(CityAdapter);


            // Set up filter button listener
            FilterButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    filterResults();
                }
            });
        }
        private void filterResults() {
            // Replace this with your logic to filter items based on selected filters
            List<String> filteredResults = new ArrayList<>();
            // Sample logic: Check if subcategory is selected and add items accordingly
            String selectedCategory = Category.getSelectedItem().toString();
            String selectedSubcategory = SubCategory.isEnabled() ?
                    SubCategory.getSelectedItem().toString() : "";
            String selectedCity = City.getSelectedItem().toString();
            // Update UI based on filtered results
            if (filteredResults.isEmpty()) {
                showNoResults();
            } else {
                showResults(filteredResults);
            }
        }
        private void showResults(List<String> results) {
            filterResultsListView.setVisibility(View.VISIBLE);
            noResultsTextView.setVisibility(View.GONE);

            ArrayAdapter<String> resultsAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, results);
            filterResultsListView.setAdapter(resultsAdapter);
        }

        private void showNoResults() {
            filterResultsListView.setVisibility(View.GONE);
            noResultsTextView.setVisibility(View.VISIBLE);
        }


        public void ShowToast(String message){
            Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        }

        public void GoBack(View v){
            finish();
        }


        //rest
    private Spinner categorySpinner, subcategorySpinner, citySpinner;
    private Button filterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtering);

        categorySpinner = findViewById(R.id.categorySpinner);
        subcategorySpinner = findViewById(R.id.subcategorySpinner);
        citySpinner = findViewById(R.id.citySpinner);
        filterButton = findViewById(R.id.filterButton);

        // Populate spinners with data (you may retrieve this data from your server)
        ArrayAdapter<CharSequence> categoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        ArrayAdapter<CharSequence> subcategoryAdapter = ArrayAdapter.createFromResource(this,
                R.array.subcategories_array, android.R.layout.simple_spinner_item);
        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subcategorySpinner.setAdapter(subcategoryAdapter);

        ArrayAdapter<CharSequence> cityAdapter = ArrayAdapter.createFromResource(this,
                R.array.cities_array, android.R.layout.simple_spinner_item);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(cityAdapter);

        // Add item selected listeners if needed
        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle category selection, if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        subcategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle subcategory selection, if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle city selection, if needed
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });
    }

    public void onFilterButtonClick(View view) {
        // Get selected category, subcategory, and city
        String selectedCategory = categorySpinner.getSelectedItem().toString();
        String selectedSubcategory = subcategorySpinner.getSelectedItem().toString();
        String selectedCity = citySpinner.getSelectedItem().toString();

        // Perform any additional processing or conversion of selected data as needed

        // Make HTTP request to get filtered items
        // Note: Replace the following line with the actual URL of your server and script
        String url = "http://localhost/display_item_filter.php" +
                "category_code=" + getCategoryId(selectedCategory) +
                "&subcategory_code=" + getSubcategoryId(selectedSubcategory) +
                "&city_code=" + getCityId(selectedCity);

        new FetchFilteredItemsTask().execute(url);
    }

    private int getCategoryId(String selectedCategory) {
        // Implement logic to map category names to category codes
        // For example, you might use a predefined array or retrieve data from the server
        // You can replace this with your actual implementation
        return 1; // Placeholder value
    }

    private int getSubcategoryId(String selectedSubcategory) {
        // Implement logic to map subcategory names to subcategory codes
        // Replace this with your actual implementation
        return 1; // Placeholder value
    }

    private int getCityId(String selectedCity) {
        // Implement logic to map city names to city codes
        // Replace this with your actual implementation
        return 1; // Placeholder value
    }

    private class FetchFilteredItemsTask extends AsyncTask<String, Void, List<Item>> {
        @Override
        protected List<Item> doInBackground(String... params) {
            List<Item> itemList = new ArrayList<>();
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = connection.getInputStream();
                java.util.Scanner s = new java.util.Scanner(inputStream).useDelimiter("\\A");
                String response = s.hasNext() ? s.next() : "";

                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int itemId = jsonObject.getInt("Item_id");
                    String itemName = jsonObject.getString("Item_Name");
                    double price = jsonObject.getDouble("Price");
                    String itemImageBase64 = jsonObject.getString("Item_Image");

                    // Convert base64 image string to byte array
                    byte[] itemImageBytes = android.util.Base64.decode(itemImageBase64, android.util.Base64.DEFAULT);

                    // Create an Item object with the retrieved data
                    Item item = new Item(itemId, itemName, price, itemImageBytes);

                    // Add the item to the list
                    itemList.add(item);
                }
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
            return itemList;
        }

        @Override
        protected void onPostExecute(List<Item> itemList) {
            // Update UI with filtered items
            // For example, you might display them in a RecyclerView or ListView
            // Here, I'm assuming you have a method updateUI(itemList) in your activity
            updateUI(itemList);
        }
    }

    private void updateUI(List<Item> itemList) {
        // Implement this method to update your UI with the filtered items
        // For example, you might set up a RecyclerView adapter and notify it with the new data
        // Make sure to handle this on the UI thread
    }
}
