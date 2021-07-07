package com.asrul.skripsi.data.newsapiresponse;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsApiResponse{

	@SerializedName("totalResults")
	private int totalResults;

	@SerializedName("articles")
	private List<ArticlesItem> articles;

	@SerializedName("status")
	private String status;

	public int getTotalResults(){
		return totalResults;
	}

	public List<ArticlesItem> getArticles(){
		return articles;
	}

	public String getStatus(){
		return status;
	}
}