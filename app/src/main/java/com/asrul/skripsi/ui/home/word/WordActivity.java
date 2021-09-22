package com.asrul.skripsi.ui.home.word;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;
import com.asrul.skripsi.data.Data;
import com.asrul.skripsi.data.word.Word;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class WordActivity extends AppCompatActivity {

    private WordAdapter adapter;
    private ArrayList<Word> words;
    private ImageView imgWord;
    private FloatingActionButton fabBack;
    private TextView tvCategory;
    private RecyclerView rvWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);

        rvWord = findViewById(R.id.rvWord);
        imgWord = findViewById(R.id.imgWord);
        tvCategory = findViewById(R.id.tvCategory);
        fabBack = findViewById(R.id.fabBack);

        fabBack.setOnClickListener(view -> {
            onBackPressed();
        });

        words = new Data().getWord();

        adapter = new WordAdapter(words);
        adapter.setOnButtonClicked(word -> {
            tvCategory.setVisibility(View.GONE);
            Glide.with(this)
                    .asGif()
                    .load(word.getGesture())
                    .listener(new RequestListener<GifDrawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, DataSource dataSource, boolean isFirstResource) {
                            resource.setLoopCount(1);
                            return false;
                        }
                    })
                    .into(imgWord);
        });

        rvWord.setAdapter(adapter);
        rvWord.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvWord.setHasFixedSize(true);

    }
}