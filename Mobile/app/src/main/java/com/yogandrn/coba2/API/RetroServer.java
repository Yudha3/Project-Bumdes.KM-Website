package com.yogandrn.coba2.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
    private static final String baseURL = "http://192.168.1.100:8080/android/retrofit/";
    private static Retrofit retro;

    public static Retrofit koneksiRetrofit() {
        if (retro == null ) {
            retro = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retro;
    }


}
