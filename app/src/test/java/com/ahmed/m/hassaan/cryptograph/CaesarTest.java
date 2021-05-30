package com.ahmed.m.hassaan.cryptograph;

import com.ahmed.m.hassaan.cryptograph.data.model.Caesar;
import com.ahmed.m.hassaan.cryptograph.data.model.PlayFair;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaesarTest {

    @Test
    public void playFairIsCorrect() {
        Caesar caesar = new Caesar(4, "hh");
        String result = caesar.encrypt();
        assertEquals("LL", result);
    }
}
