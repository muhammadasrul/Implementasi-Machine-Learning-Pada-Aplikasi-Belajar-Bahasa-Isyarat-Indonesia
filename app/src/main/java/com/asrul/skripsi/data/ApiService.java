package com.asrul.skripsi.data;

import com.asrul.skripsi.data.newsapiresponse.NewsApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("top-headlines?country=id&apiKey=39b56226edf1424285d3b9110187c035")
    Call<NewsApiResponse> getNewsApi();
}
