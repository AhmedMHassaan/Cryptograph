package com.ahmed.m.hassaan.cryptograph.ui.des;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.m.hassaan.cryptograph.data.model.DES;
import com.ahmed.m.hassaan.cryptograph.data.model.Hill;

public class DesViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();;



    public MutableLiveData<String> getText() {
        return mText;
    }


    public void encrypt(String plain) {
        DES des = new DES();
        String encryption = des.encrypt(plain);
        mText.setValue(encryption);
    }

}