package com.asrul.skripsi.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asrul.skripsi.data.News;
import com.asrul.skripsi.utils.EspressoIdlingResource;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://bisindo-11e09-default-rtdb.asia-southeast1.firebasedatabase.app/");

    private MutableLiveData<List<News>> _newsList;

    public LiveData<List<News>> getNews() {
        if (_newsList == null) {
            _newsList = new MutableLiveData<>();
            loadNewsFromFirebaseDB();
        }
        return _newsList;
    }

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    private void loadNewsFromFirebaseDB() {
        ArrayList<News> newsList = new ArrayList<>();

        isLoading.postValue(true);
        EspressoIdlingResource.increment();
        database.getReference()
                .child("news")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        newsList.clear();
                        for (DataSnapshot keyNode : snapshot.getChildren()) {
                            News news = keyNode.getValue(News.class);
                            newsList.add(news);
                        }
                        _newsList.postValue(newsList);
                        isLoading.postValue(false);
                        EspressoIdlingResource.decrement();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.d("NEWSDATA", "Failed to read value.", error.toException());
                        isLoading.postValue(false);
                        EspressoIdlingResource.decrement();
                    }
                });

    }
}
