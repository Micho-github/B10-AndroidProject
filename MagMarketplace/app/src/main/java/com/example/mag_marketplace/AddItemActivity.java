package com.example.mag_marketplace;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class AddItemActivity extends AppCompatActivity {
    private EditText ItemName,Price,Description;
    private Spinner Category,Subcategory,City;
    private static final int PICK_IMAGE_REQUEST = 1;
    LinearLayout linearLayout;
    private ArrayAdapter<String> subcategoryAdapter;
    private ImageView Itemimage;
    String[] categories = {
            "",
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
            "",
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
        Subcategory = findViewById(R.id.SpinnerSubCategory);
        City = findViewById(R.id.SpinnerCity);
        Price = findViewById(R.id.InputItemPrice);
        Description = findViewById(R.id.InputItemDescription);
        Itemimage = findViewById(R.id.InputImage);
        linearLayout = findViewById(R.id.linearLayout);
        subcategoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new ArrayList<>());
        subcategoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Subcategory.setAdapter(subcategoryAdapter);
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
    private void updateSubcategories(String selectedCategory) {
        // Clear the previous subcategories
        subcategoryAdapter.clear();

        // Add a placeholder item
        subcategoryAdapter.add("");

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
    public void AddItemAction(View v) {
        // Get the image data from the ImageView and convert it to a Base64 string
        BitmapDrawable drawable = (BitmapDrawable) Itemimage.getDrawable();
        if (drawable != null) {
            Bitmap imageBitmap = drawable.getBitmap();
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();

            String encodedImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
            String itemname = ItemName.getText().toString().toLowerCase();
            String price = Price.getText().toString().toLowerCase();
            String description = Description.getText().toString().toLowerCase();
            String categorycode = String.valueOf(Category.getSelectedItemPosition());
            String subcategorycode = String.valueOf(Subcategory.getSelectedItemPosition());
            String citycode = String.valueOf(City.getSelectedItemPosition());

            if (isValidInput(itemname, price, description, categorycode, subcategorycode, citycode, encodedImage)) {
                String type = "additem";
                AddItemBackgroundWorker backgroundWorker = new AddItemBackgroundWorker(this);
                backgroundWorker.execute(type, encodedImage, itemname, description, price, "125", categorycode, subcategorycode, citycode);
            } else {
                // Show a message to the user indicating that some fields are empty or invalid
                Toast.makeText(this, "Please fill in all required fields", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean isValidInput(String itemname, String price, String description, String categorycode, String subcategorycode, String citycode,String encodedImage) {
        return !TextUtils.isEmpty(itemname) &&
                !TextUtils.isEmpty(price) &&
                !TextUtils.isEmpty(description) &&
                categorycode!="0" &&
                subcategorycode!="0" &&
                citycode!="0" &&
                !TextUtils.isEmpty(encodedImage);
    }
    public void GoBack(View v){
        finish();
    }
}
