package com.example.mag_marketplace.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mag_marketplace.Item;
import com.example.mag_marketplace.ItemAdapter;

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



    }



}
