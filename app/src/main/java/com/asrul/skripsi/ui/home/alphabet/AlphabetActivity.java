package com.asrul.skripsi.ui.home.alphabet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;
import com.asrul.skripsi.data.Data;
import com.asrul.skripsi.data.alphabet.Alphabet;

import java.util.ArrayList;

public class AlphabetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        RecyclerView rvAlphabet = findViewById(R.id.rvAlphabet);

        ArrayList<Alphabet> alphabetList = new Data().getAlphabetData();
        AlphabetAdapter adapter = new AlphabetAdapter(alphabetList);
        rvAlphabet.setAdapter(adapter);
        rvAlphabet.setHasFixedSize(true);
        rvAlphabet.setLayoutManager(new GridLayoutManager(this, 2));
    }
}