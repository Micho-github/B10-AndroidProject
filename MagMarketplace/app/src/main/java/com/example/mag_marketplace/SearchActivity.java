package com.example.mag_marketplace;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SearchActivity extends AppCompatActivity {

    private AutoCompleteTextView autoCompleteTextView;
    private ListView searchResultsListView;
    private TextView noResultsTextView;

    private List<String> allItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);

        allItems = getSampleItems();

        autoCompleteTextView = findViewById(R.id.searchEditText);
        searchResultsListView = findViewById(R.id.searchResultsListView);
        noResultsTextView = findViewById(R.id.noResultsTextView);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, allItems);
        autoCompleteTextView.setAdapter(adapter);

        autoCompleteTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filterResults(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedText = autoCompleteTextView.getText().toString();
                filterResults(selectedText);
            }
        });
    }

    private void filterResults(String query) {
        List<String> filteredList = new ArrayList<>();

        for (String item : allItems) {
            if (item.toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()) {
            showNoResults();
        } else {
            showResults(filteredList);
        }
    }

    private void showResults(List<String> results) {
        searchResultsListView.setVisibility(View.VISIBLE);
        noResultsTextView.setVisibility(View.GONE);

        ArrayAdapter<String> resultsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, results);
        searchResultsListView.setAdapter(resultsAdapter);
    }

    private void showNoResults() {
        searchResultsListView.setVisibility(View.GONE);
        noResultsTextView.setVisibility(View.VISIBLE);
    }

    private List<String> getSampleItems() {
        List<String> sampleItems = new ArrayList<>();
        sampleItems.add("Item 1");
        sampleItems.add("Item 2");
        sampleItems.add("Item 3");
        // Add more sample items as needed
        return sampleItems;
    }
}