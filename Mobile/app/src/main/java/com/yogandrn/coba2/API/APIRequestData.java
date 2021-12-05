package com.yogandrn.coba2.API;

import com.yogandrn.coba2.Model.ResponseProduk;
import com.yogandrn.coba2.Model.ResponseUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {

    @FormUrlEncoded
    @POST("register.php")
    Call<ResponseUser> userRegister(
            @Field("fullname") String fullname,
            @Field("username") String username,
            @Field("email") String email,
            @Field("no_telp") String no_telp,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("checklogin.php")
    Call<ResponseUser> checkLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET ("retrieve.php")
    Call<ResponseProduk> ReadData();
}
