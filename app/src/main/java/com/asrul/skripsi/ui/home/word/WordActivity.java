package com.asrul.skripsi.ui.home.word;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;

import java.util.ArrayList;

public class WordActivity extends AppCompatActivity {

    private WordAdapter adapter;
    private WordResultAdapter resultAdapter;
    private ArrayList<String> words;
    private RecyclerView rvWord, rvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        rvWord = findViewById(R.id.rvWord);
        rvResult = findViewById(R.id.rvResult);

        words = new ArrayList<>();

        adapter = new WordAdapter(getWords());
        adapter.setOnButtonClicked(word -> {
            ArrayList<Character> charList = new ArrayList<>();
            word = word.toLowerCase();

            for (int i = 0; i < word.length(); i++) {
                charList.add(word.charAt(i));
            }

            resultAdapter = new WordResultAdapter(charList);

            rvResult.setAdapter(resultAdapter);
            rvResult.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            rvResult.setHasFixedSize(true);
        });


        rvWord.setAdapter(adapter);
        rvWord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvWord.setHasFixedSize(true);

    }

    private ArrayList<String> getWords() {
        words.add("Aku");
        words.add("Kamu");
        words.add("Dia");
        return words;
    }
}