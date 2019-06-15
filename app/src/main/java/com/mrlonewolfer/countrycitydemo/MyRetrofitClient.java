package com.mrlonewolfer.countrycitydemo;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyRetrofitClient {
    public static MyRetrofitServices getClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyRetrofitServices myRetrofitServices = retrofit.create(MyRetrofitServices.class);

        return myRetrofitServices;
    }
}
