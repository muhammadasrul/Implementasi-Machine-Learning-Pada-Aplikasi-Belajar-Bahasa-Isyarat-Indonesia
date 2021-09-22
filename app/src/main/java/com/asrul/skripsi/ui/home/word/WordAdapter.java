package com.asrul.skripsi.ui.home.word;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;
import com.asrul.skripsi.data.word.Word;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private final ArrayList<Word> words;

    private OnButtonClicked onButtonClicked;

    public void setOnButtonClicked(OnButtonClicked onButtonClicked) {
        this.onButtonClicked = onButtonClicked;
    }

    public WordAdapter(ArrayList<Word> word) {
        this.words = word;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_item, parent, false);
        return new WordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        Word word = words.get(position);
        holder.btnWord.setText(word.getWord());
        holder.btnWord.setOnClickListener(v -> {
            onButtonClicked.onWordClicked(word);
        });
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {

        Button btnWord;

        public WordViewHolder(@NonNull View itemView) {
            super(itemView);
            btnWord = itemView.findViewById(R.id.btnWord);
        }
    }

    interface OnButtonClicked {
        void onWordClicked(Word word);
    }
}
