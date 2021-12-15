package com.yogandrn.coba2.API;

import com.yogandrn.coba2.Model.ResponseKeranjang;
import com.yogandrn.coba2.Model.ResponseModel;
import com.yogandrn.coba2.Model.ResponseProduk;
import com.yogandrn.coba2.Model.ResponseShowDetail;
import com.yogandrn.coba2.Model.ResponseTransaksi;
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

    @FormUrlEncoded
    @POST("get_user.php")
    Call<ResponseUser> getUser(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("detail_produk.php")
    Call<ResponseShowDetail> getDetailProduk(
        @Field("id_brg") String id_brg
    );

    @FormUrlEncoded
    @POST("addToCart.php")
    Call<ResponseModel> addToCart(
            @Field("id_user") String id_user,
            @Field("id_brg") String id_brg,
            @Field("qty") int qty
    );

    @FormUrlEncoded
    @POST("get_cart.php")
    Call<ResponseKeranjang> readCart(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("countTotal.php")
    Call<ResponseModel> countTotal(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("transaksi_1.php")
    Call<ResponseModel> createTransaksi(
            @Field("id_user") String id_user,
            @Field("penerima") String penerima,
            @Field("alamat") String alamat,
            @Field("no_telp") String no_telp,
            @Field("id_ongkir") String id_ongkir
    );

    @FormUrlEncoded
    @POST("get_list_transaksi.php")
    Call<ResponseTransaksi> readTransaksi(
            @Field("id_user") String id_user
    );

    @GET ("retrieve.php")
    Call<ResponseProduk> ReadData();
}
