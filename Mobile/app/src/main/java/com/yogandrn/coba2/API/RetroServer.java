package com.yogandrn.coba2.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "http://undeveloppedcity.000webhostapp.com/android/retrofit/";
    private static final String baseURL0 = "http://192.168.1.100:8080/android/retrofit/";
    private static Retrofit retro;

    public static Retrofit koneksiRetrofit() {
        if (retro == null ) {
            retro = new Retrofit.Builder().baseUrl(baseURL0).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retro;
    }


}
