package com.mrlonewolfer.countrycitydemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface MyRetrofitServices {


    @GET
    Call<List<CountryRoot>> selectCountry(
            @Url String Countryurl);


//    @GET("api/{country}/all/?key=00000000000000000000000000000000")
//    Call<List<CountryRoot>> selectRegion(@Path("country") String country);

    @GET
    Call<List<Region>> selectRegion(@Url String RegionUrl);

    @GET
    Call<List<City>> selectCity(@Url String CityUrl);
}
