package com.example.minim2.service;

import com.example.minim2.models.Repo;
import com.example.minim2.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface gitHubService {

    @GET("users/{nickname}")
    Call<User> getInfoUser(@Path("nickname") String userName);

    @GET("users/{nickname}/repos")
    Call<List<Repo>> getReposUser(@Path("nickname") String userName);

}
