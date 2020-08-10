package com.example.Home_step1;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDemo {
    Retrofit retrofit= RetrofitUtils.getInstence()
            .baseUrl("https://www.baidu.com")
            .addConverterFactory(GsonConverterFactory.create(new Gson()))
            .build();

}
