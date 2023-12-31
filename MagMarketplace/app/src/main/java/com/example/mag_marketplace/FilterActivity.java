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

public class FilterActivity extends AppCompatActivity {
    private ListView filterResultsListView;
    private TextView noResultsTextView;
    private Button FilterButton;
    private ArrayAdapter<String> subcategoryAdapter;
    private List<String> allCategories;
    private List<String> allSubcategories;
    private List<String> allCities;
    private EditText ItemName, Price, Description;
    private Spinner Category, SubCategory, City;
    String[] categories = {
            "Select Category",
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
            "Select City",
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
        FilterButton = (Button) findViewById(R.id.filter);
        Category = findViewById(R.id.CategoryFilter);
        SubCategory = findViewById(R.id.SubcategoryFilter);
        City = findViewById(R.id.CityFilter);
        filterResultsListView = findViewById(R.id.filterResultsListView);
        noResultsTextView = findViewById(R.id.noResultsTextView);

        subcategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SubCategory.setAdapter(subcategoryAdapter);
        ArrayAdapter<String> Categoryadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        Categoryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category.setAdapter(Categoryadapter);
        Category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle the selection
                String selectedCategory = categories[position];
                updateSubcategories(selectedCategory);
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

    private void updateSubcategories(String selectedCategory) {
        // Clear the previous subcategories
        subcategoryAdapter.clear();

        // Add a placeholder item
        subcategoryAdapter.add("Select Subcategory");

        switch (selectedCategory) {
            case "Electronics":
                subcategoryAdapter.addAll(electronicsSubcategories);
                break;

            case "Clothing":
                subcategoryAdapter.addAll(clothingSubcategories);
                break;

            case "Home and Garden":
                subcategoryAdapter.addAll(homeAndGardenSubcategories);
                break;

            case "Sports and Outdoors":
                subcategoryAdapter.addAll(sportsAndOutdoorsSubcategories);
                break;

            case "Books and Stationery":
                subcategoryAdapter.addAll(booksAndStationerySubcategories);
                break;

            case "Health and Beauty":
                subcategoryAdapter.addAll(healthAndBeautySubcategories);
                break;

            case "Toys and Games":
                subcategoryAdapter.addAll(toysAndGamesSubcategories);
                break;

            case "Automotive":
                subcategoryAdapter.addAll(automotiveSubcategories);
                break;

            case "Appliances":
                subcategoryAdapter.addAll(appliancesSubcategories);
                break;

            case "Jewelry and Accessories":
                subcategoryAdapter.addAll(jewelryAndAccessoriesSubcategories);
                break;

            case "Grocery and Gourmet":
                subcategoryAdapter.addAll(groceryAndGourmetSubcategories);
                break;

            case "Music and Instruments":
                subcategoryAdapter.addAll(musicAndInstrumentsSubcategories);
                break;
            // Add cases for other categories

            default:
                // Handle the default case
                break;
        }

        // Notify the adapter that the data has changed
        subcategoryAdapter.notifyDataSetChanged();
    }
        public void GoBack(View v){
            finish();
        }

}
