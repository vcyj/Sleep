package com.example.Home_step1;

import retrofit2.Retrofit;

public class RetrofitUtils {
    private static Retrofit.Builder retrofit;

    private  RetrofitUtils() {
        retrofit=new Retrofit.Builder();
    }

    public static   Retrofit.Builder getInstence(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder();
        }
        return  retrofit;
    }
}