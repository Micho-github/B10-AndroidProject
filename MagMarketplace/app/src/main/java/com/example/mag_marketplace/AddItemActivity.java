package com.example.mag_marketplace;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
public class AddItemActivity extends AppCompatActivity {
    private EditText ItemName,Price,Description;
    private Spinner Category,SubCategory,City;
    private static final int PICK_IMAGE_REQUEST = 1;
    LinearLayout linearLayout;
    private ImageView Itemimage;
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


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);
        ItemName = findViewById(R.id.InputItemName);
        Category = findViewById(R.id.SpinnerCategory);
        SubCategory = findViewById(R.id.SpinnerSubCategory);
        City = findViewById(R.id.SpinnerCity);
        Price = findViewById(R.id.InputItemPrice);
        Description = findViewById(R.id.InputItemDescription);
        Itemimage = findViewById(R.id.InputImage);
        linearLayout = findViewById(R.id.linearLayout);
        ArrayAdapter<String> Categoryadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        Categoryadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Category.setAdapter(Categoryadapter);
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

    }
    public void selectImage(View view) {
        // Open the gallery to select an image
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    public void ShowToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            linearLayout.setVisibility(View.INVISIBLE);
            Itemimage.setBackgroundColor(Color.TRANSPARENT);
            Itemimage.setImageURI(data.getData());
        }
    }

    public void GoBack(View v){
        finish();
    }
}
