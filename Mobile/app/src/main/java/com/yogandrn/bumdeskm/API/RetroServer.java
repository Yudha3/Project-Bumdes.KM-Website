package com.yogandrn.bumdeskm.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroServer {
//    private static final String baseURL = "http://undeveloppedcity.000webhostapp.com/android/retrofit/";
    private static final String baseURL = "http://ws-tif.com/bumdes.km/AndroidAPI/";
    private static final String baseURL0 = "http://192.168.1.100:8080/android/AndroidAPI/";
//    private static final String baseURL0 = "http://192.168.99.140:8080/android/retrofit/";
    private static Retrofit retro;
    private static RetroServer retroClient;

    public static Retrofit koneksiRetrofit() {
        if (retro == null ) {
            retro = new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retro;
    }

    public APIRequestData getAPI() {
        return retro.create(APIRequestData.class);
    }
}
