package com.ahmed.m.hassaan.cryptograph.ui.playfair;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.m.hassaan.cryptograph.data.model.Hill;
import com.ahmed.m.hassaan.cryptograph.data.model.PlayFair;

public class PlayFairViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();



    public MutableLiveData<String> getText() {
        return mText;
    }


    public void encrypt(String key, String plain) {
        PlayFair playFair = new PlayFair(key);
        String encryption = playFair.encode(plain);
        mText.setValue(encryption);
    }




}