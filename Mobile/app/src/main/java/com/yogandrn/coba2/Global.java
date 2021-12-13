package com.yogandrn.coba2;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Global {
    public static String id_user;
    public static String fullname;
    public static String IMG_PRODUK_URL = "http://undeveloppedcity.000webhostapp.com/android/img/produk/";

    public int total;

    public void getTotal(){
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> countTotal = apiRequestData.countTotal(id_user);
        countTotal.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int hasil = response.body().getKode();
                total = hasil;
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                int hasil = 0;
                total = hasil;
            }
        });
    }
}
