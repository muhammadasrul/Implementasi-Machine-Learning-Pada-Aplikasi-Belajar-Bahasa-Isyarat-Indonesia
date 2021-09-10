package com.asrul.skripsi.data.alphabet;

import com.asrul.skripsi.R;

import java.util.ArrayList;

public class AlphabetData {

    private final char[] alphabetData = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private final int[] signData = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e,
            R.drawable.f,
            R.drawable.g,
            R.drawable.h,
            R.drawable.i,
            R.drawable.j,
            R.drawable.k,
            R.drawable.l,
            R.drawable.m,
            R.drawable.n,
            R.drawable.o,
            R.drawable.p,
            R.drawable.q,
            R.drawable.r,
            R.drawable.s,
            R.drawable.t,
            R.drawable.u,
            R.drawable.v,
            R.drawable.w,
            R.drawable.x,
            R.drawable.y,
            R.drawable.z,
    };

    public ArrayList<Alphabet> getAlphabetData() {
        ArrayList<Alphabet> alphabetList = new ArrayList<>();

        for (int i = 0; i < alphabetData.length; i++) {
            Alphabet alphabet = new Alphabet();
            alphabet.setAlphabet(alphabetData.clone()[i]);
            alphabet.setSign(signData.clone()[i]);
            alphabetList.add(alphabet);
        }

        return alphabetList;
    }
}
