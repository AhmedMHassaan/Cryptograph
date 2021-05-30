package com.ahmed.m.hassaan.cryptograph.ui.viegenre;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.m.hassaan.cryptograph.data.model.PlayFair;
import com.ahmed.m.hassaan.cryptograph.data.model.Viegenre;

public class ViegenreViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();


    public MutableLiveData<String> getText() {
        return mText;
    }


    public void encrypt(String key, String plain) {
        Viegenre playFair = new Viegenre();
        String encryption = playFair.encrypt(plain, key);
        mText.setValue(encryption);
    }


}



