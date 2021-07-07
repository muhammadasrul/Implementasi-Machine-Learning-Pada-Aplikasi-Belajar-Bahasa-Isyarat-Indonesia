package com.asrul.skripsi.ui.home;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.asrul.skripsi.data.ApiConfig;
import com.asrul.skripsi.data.newsapiresponse.ArticlesItem;
import com.asrul.skripsi.data.newsapiresponse.NewsApiResponse;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<ArticlesItem>> newsApiList;
    public LiveData<List<ArticlesItem>> getNewsApi() {
        if (newsApiList == null) {
            newsApiList = new MutableLiveData<>();
            loadNewsApiList();
        }
        return newsApiList;
    }

    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    private void loadNewsApiList() {
        isLoading.setValue(true);
        ApiConfig.getNewsApi().getNewsApi().enqueue(new Callback<NewsApiResponse>() {
            @Override
            public void onResponse(Call<NewsApiResponse> call, Response<NewsApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    newsApiList.postValue(response.body().getArticles());
                } else {
                    Log.e("onResponse", "onResponse: "+ response.message());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<NewsApiResponse> call, Throwable t) {
                Log.e("OnFailure", "onFailure: "+ t.getMessage());
                isLoading.setValue(false);
            }
        });
    }
}
