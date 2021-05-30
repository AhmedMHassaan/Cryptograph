package com.ahmed.m.hassaan.cryptograph.ui.playfair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PlayFairViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PlayFairViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}