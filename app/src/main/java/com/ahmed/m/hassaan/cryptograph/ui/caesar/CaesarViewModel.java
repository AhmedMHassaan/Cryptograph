package com.ahmed.m.hassaan.cryptograph.ui.caesar;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ahmed.m.hassaan.cryptograph.data.model.Caesar;

public class CaesarViewModel extends ViewModel {

    private final MutableLiveData<String> mText = new MutableLiveData<>();;

    public CaesarViewModel() {

    }

    public MutableLiveData<String> getText() {
        return mText;
    }


    public void encrypt(int key, String plain) {
        Caesar caesar = new Caesar(key, plain);
        String encryption = caesar.encrypt();
        mText.setValue(encryption);
    }

    public void decrypt(int key, String cipher) {
        Caesar caesar = new Caesar(key, cipher);
        String decryption = caesar.decrypt();
        mText.setValue(decryption);
    }
}