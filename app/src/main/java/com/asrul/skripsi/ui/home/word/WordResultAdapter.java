package com.asrul.skripsi.ui.home.word;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;
import com.asrul.skripsi.utils.SignHelper;

import java.util.ArrayList;

public class WordResultAdapter extends RecyclerView.Adapter<WordResultAdapter.WordResultViewHolder> {

    private final ArrayList<Character> words;

    public WordResultAdapter(ArrayList<Character> word) {
        this.words = word;
    }

    @NonNull
    @Override
    public WordResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_result_item, parent, false);
        return new WordResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WordResultViewHolder holder, int position) {
        Character word = words.get(position);

        SignHelper signHelper = new SignHelper();

        Log.d("Char", word.toString());
        holder.imgWord.setImageResource(signHelper.getSignImage(word));
        holder.tvWord.setText(word.toString());
    }

    @Override
    public int getItemCount() {
        return words.size();
    }

    public static class WordResultViewHolder extends RecyclerView.ViewHolder {

        ImageView imgWord;
        TextView tvWord;

        public WordResultViewHolder(@NonNull View itemView) {
            super(itemView);

            imgWord = itemView.findViewById(R.id.imgWord);
            tvWord = itemView.findViewById(R.id.tvWord);
        }
    }
}
