package com.asrul.skripsi.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;
import com.asrul.skripsi.ui.home.alphabet.AlphabetActivity;
import com.asrul.skripsi.ui.home.news.NewsAdapter;
import com.asrul.skripsi.ui.home.word.WordActivity;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private RecyclerView rvNews;
    private NewsAdapter newsAdapter;
    private ProgressBar progressBar;
    private LinearLayout linearLayout;
    private TextView tvError;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView tvGreeting = view.findViewById(R.id.tvGreeting);
        Button btnWord = view.findViewById(R.id.btnWord);
        Button btnAlphabet = view.findViewById(R.id.btnAlphabet);
        ImageView imgProfile = view.findViewById(R.id.imgProfile);
        tvError = view.findViewById(R.id.tvError);
        linearLayout = view.findViewById(R.id.emptyState);
        linearLayout.setVisibility(View.GONE);

        btnAlphabet.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), AlphabetActivity.class));
        });
        btnWord.setOnClickListener(v -> {
            startActivity(new Intent(requireContext(), WordActivity.class));
        });

        if (user != null) {
            tvGreeting.setText(getString(R.string.greeting, user.getDisplayName()));
            if (user.getPhotoUrl() != null) {
                Glide.with(requireContext())
                        .load(user.getPhotoUrl())
                        .optionalCircleCrop()
                        .into(imgProfile);
            }
        }

        rvNews = view.findViewById(R.id.rvBerita);
        progressBar = view.findViewById(R.id.progressBar);
        newsAdapter = new NewsAdapter();

        observeConnectionState();
    }

    private void observeNews() {
        viewModel.getNews().observe(getViewLifecycleOwner(), newsList -> {
            newsAdapter.setNews(newsList);
            rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
            rvNews.setHasFixedSize(true);
            rvNews.setAdapter(newsAdapter);
        });
    }

    private void observeLoading() {
        viewModel.isLoading().observe(getViewLifecycleOwner(), isLoading -> {
            if (isLoading) {
                progressBar.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void observeConnectionState() {
        viewModel.isConnected().observe(getViewLifecycleOwner(), isConnected -> {
            if (isConnected) {
                linearLayout.setVisibility(View.GONE);
                rvNews.setVisibility(View.VISIBLE);
            } else {
                linearLayout.setVisibility(View.VISIBLE);
                rvNews.setVisibility(View.GONE);
                tvError.setText("Tidak ada koneksi internet!");
            }
            observeLoading();
            observeNews();
        });
    }
}