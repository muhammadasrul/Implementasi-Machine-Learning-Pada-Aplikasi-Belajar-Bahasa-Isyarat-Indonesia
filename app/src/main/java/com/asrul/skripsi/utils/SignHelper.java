package com.asrul.skripsi.utils;

import com.asrul.skripsi.R;

import java.util.Map;

public class SignHelper {

    Map<Character, Integer> signImageMap = Map.of(
            'a', R.drawable.a,
            'k', R.drawable.k,
            'u', R.drawable.u,
            'm', R.drawable.m,
            'd', R.drawable.d,
            'i', R.drawable.i);

    public Integer getSignImage(Character character) {
        return this.signImageMap.get(character);
    }
}
