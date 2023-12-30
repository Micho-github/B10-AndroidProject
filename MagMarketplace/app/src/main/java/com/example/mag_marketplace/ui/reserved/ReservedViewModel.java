package com.example.mag_marketplace.ui.reserved;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReservedViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ReservedViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Reserved fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}