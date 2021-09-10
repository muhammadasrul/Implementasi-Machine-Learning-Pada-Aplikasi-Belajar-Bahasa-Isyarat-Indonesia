package com.asrul.skripsi.ui.home.alphabet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.asrul.skripsi.R;
import com.asrul.skripsi.data.alphabet.Alphabet;
import com.asrul.skripsi.data.alphabet.AlphabetData;
import com.asrul.skripsi.ui.home.word.WordResultAdapter;

import java.util.ArrayList;

public class AlphabetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);

        RecyclerView rvAlphabet = findViewById(R.id.rvAlphabet);

        ArrayList<Alphabet> alphabetList = new AlphabetData().getAlphabetData();
        AlphabetAdapter adapter = new AlphabetAdapter(alphabetList);
        rvAlphabet.setAdapter(adapter);
        rvAlphabet.setHasFixedSize(true);
        rvAlphabet.setLayoutManager(new GridLayoutManager(this, 2));
    }
}