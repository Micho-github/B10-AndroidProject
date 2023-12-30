package com.example.mag_marketplace.ui.additem;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AdditemViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public AdditemViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("My Items");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
