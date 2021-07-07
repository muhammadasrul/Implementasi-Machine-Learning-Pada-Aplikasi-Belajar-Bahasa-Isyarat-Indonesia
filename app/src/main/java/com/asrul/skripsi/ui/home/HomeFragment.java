package com.asrul.skripsi.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.asrul.skripsi.R;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        TextView tvGreeting = view.findViewById(R.id.tvGreeting);
        Button btnWord = view.findViewById(R.id.btnWord);
        ImageView imgProfile = view.findViewById(R.id.imgProfile);

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

        observeNews();
        observeLoading();
    }

    private void observeNews() {
        viewModel.getNewsApi().observe(getViewLifecycleOwner(), newsList -> {
            Log.e("HomeFragment", newsList.get(0).getTitle());
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
}