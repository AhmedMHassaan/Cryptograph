package com.ahmed.m.hassaan.cryptograph.ui.hill;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.m.hassaan.cryptograph.data.model.Caesar;
import com.ahmed.m.hassaan.cryptograph.data.model.Hill;

public class HillViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();;



    public MutableLiveData<String> getText() {
        return mText;
    }


    public void encrypt(int[][] key,int n,  String plain) {
        Hill hill = new Hill(key, n, plain);
        String encryption = hill.encrypt(key);
        mText.setValue(encryption);
    }

}