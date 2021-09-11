package com.asrul.skripsi.ui.home;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asrul.skripsi.data.News;
import com.asrul.skripsi.utils.EspressoIdlingResource;
import com.asrul.skripsi.utils.ResponseState;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final FirebaseDatabase database = FirebaseDatabase.getInstance("https://bisindo-11e09-default-rtdb.asia-southeast1.firebasedatabase.app/");

    private MutableLiveData<List<News>> newsList;

    public LiveData<List<News>> getNews() {
        if (newsList == null) {
            newsList = new MutableLiveData<>();
            loadNewsFromFirebaseDB();
        }
        return newsList;
    }

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    private final MutableLiveData<Boolean> isConnected = new MutableLiveData<>();
    public LiveData<Boolean> isConnected() {
        connectionState();
        return isConnected;
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
                        HomeViewModel.this.newsList.postValue(newsList);
                        isLoading.postValue(false);
                        EspressoIdlingResource.decrement();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        isLoading.postValue(false);
                        EspressoIdlingResource.decrement();
                    }
                });
    }

    private void connectionState() {
        isConnected.postValue(false);
        DatabaseReference reference = database.getReference(".info/connected");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean connected = snapshot.getValue(Boolean.class);
                if (connected) {
                    isConnected.postValue(true);
                    Log.d("CONNECTED", "connected");
                } else {
                    isConnected.postValue(false);
                    Log.d("CONNECTED", "not connected");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                isConnected.postValue(false);
                Log.w("CONNECTED", "Listener was cancelled");
            }
        });
    }
}
