package com.example.android.gogreen;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users")
    Call<Pojo> getData();

}
