package com.yogandrn.coba2.API;

import com.yogandrn.coba2.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET ("retrieve.php")
    Call<ResponseModel> ReadData();
}
