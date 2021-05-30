package com.ahmed.m.hassaan.cryptograph.ui.railfence;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.m.hassaan.cryptograph.data.model.Viegenre;

public class RailFenceViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();


    public MutableLiveData<String> getText() {
        return mText;
    }


    public void encrypt(String key, String plain) {
        RailFence railFence = new RailFence();
        String encryption = railFence.encrypt(plain, key);
        mText.setValue(encryption);
    }


}



