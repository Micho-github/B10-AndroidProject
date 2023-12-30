package com.example.mag_marketplace.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mag_marketplace.Item;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<List<Item>> yourLiveDataForYou = new MutableLiveData<>();
    private final MutableLiveData<List<Item>> yourLiveDataOther = new MutableLiveData<>();

    public LiveData<List<Item>> getYourLiveDataForYou() {
        return yourLiveDataForYou;
    }

    public LiveData<List<Item>> getYourLiveDataOther() {
        return yourLiveDataOther;
    }

    public HomeViewModel() {
        // Initialize yourLiveDataForYou and yourLiveDataOther with sample data or leave them empty
        List<Item> itemsForYou = generateSampleItemsForYou();
        List<Item> itemsOther = generateSampleItemsOther();

        yourLiveDataForYou.setValue(itemsForYou);
        yourLiveDataOther.setValue(itemsOther);
    }

    private List<Item> generateSampleItemsForYou() {
        // Replace this with the actual logic to generate "For You" items
        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            items.add(new Item());
        }
        return items;
    }

    private List<Item> generateSampleItemsOther() {
        // Replace this with the actual logic to generate "Other" items
        List<Item> items = new ArrayList<>();
        for (int i = 1; i <= 16; i++) {
            items.add(new Item());
        }
        return items;
    }
}
