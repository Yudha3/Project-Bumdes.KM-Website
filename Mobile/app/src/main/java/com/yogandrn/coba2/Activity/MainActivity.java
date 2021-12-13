package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ResponseUser;
import com.yogandrn.coba2.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout btnKatalog, btnPesanan, btnPreorder, btnKeranjang;
    private String id_user;
    private TextView txtWelcome, btnHeadline1, btnHeadline2;
    private long exitTime = 0;
    private CircleImageView imgProfil;
    private ImageView ic_keranjang, ic_notifikasi;
    private String URL_IMG_USER = "http://undeveloppedcity.000webhostapp.com/android/img/user/";
//    private String URL_IMG_USER = "http://192.168.1.100:8080/android/img/user/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //Tangkap data intent login
//        Bundle data = getIntent().getExtras();
//        String id_user = data.getString("id_user");
//        String fullname = data.getString("fullname");
//        String username = data.getString("username");
//        String email = data.getString("email");
//        String no_telp = data.getString("no_telp");
//        String foto_profil = data.getString("foto_profil");

//        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
//        Call<ResponseUser> getData = apiRequestData.getUser(id_user);
//        getData.enqueue(new Callback<ResponseUser>() {
//            @Override
//            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
//                String fullname = response.body().getFullname();
//                String username = response.body().getUsername();
//                String email = response.body().getEmail();
//                String no_telp = response.body().getNo_telp();
//                String foto_profil = response.body().getFoto_profil();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseUser> call, Throwable t) {
//
//            }
//        });

        ic_keranjang = (ImageView) findViewById(R.id.keranjang_main);
        imgProfil = (CircleImageView) findViewById(R.id.img_profil_main);
        btnKatalog = (LinearLayout) findViewById(R.id.layoutKatalog);
        btnKeranjang = (LinearLayout) findViewById(R.id.layoutKeranjang);
        btnPesanan = (LinearLayout) findViewById(R.id.layoutPesanan);
        btnPreorder = (LinearLayout) findViewById(R.id.layoutPreorder);
        txtWelcome = (TextView) findViewById(R.id.txtuser);
        btnHeadline1 = (TextView) findViewById(R.id.btn_headline1);
        btnHeadline2 = (TextView) findViewById(R.id.btn_headline2);

        getUserData();

//        txtWelcome.setText(fullname);
//
//        Glide.with(this).load(URL_IMG_USER + foto_profil).placeholder(R.drawable.bg_foto_default).circleCrop().into(imgProfil);

        ic_keranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keranjang = new Intent(MainActivity.this, KeranjangActivity.class);
                startActivity(keranjang);
            }
        });

        imgProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profil = new Intent(MainActivity.this, ProfilActivity.class);
//                profil.putExtra("id_user", id_user);
//                profil.putExtra("fullname", fullname);
//                profil.putExtra("username", username);
//                profil.putExtra("email", email);
//                profil.putExtra("no_telp", no_telp);
//                profil.putExtra("foto_profil", foto_profil);
                startActivity(profil);
            }
        });

        btnKatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent katalog = new Intent(MainActivity.this, KatalogActivity.class);
                katalog.putExtra("id_user", id_user);
                startActivity(katalog);
            }
        });

        btnPreorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent preorder = new Intent(MainActivity.this, PreorderActivity.class);
                preorder.putExtra("id_user", id_user);
                startActivity(preorder);
            }
        });

        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keranjang = new Intent(MainActivity.this, KeranjangActivity.class);
                keranjang.putExtra("id_user", id_user);
                startActivity(keranjang);
            }
        });

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesanan = new Intent(MainActivity.this, PesananActivity.class);
                pesanan.putExtra("id_user", id_user);
                startActivity(pesanan);
            }
        });

        btnHeadline2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sosmed = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/west_bone_craft"));
                startActivity(sosmed);
            }
        });

        btnHeadline1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent produk = new Intent(MainActivity.this, KatalogActivity.class);
                startActivity(produk);
            }
        });
    }

    public void getUserData(){
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseUser> getData = apiRequestData.getUser(Global.id_user);
        getData.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String fullname = response.body().getFullname();
                String username = response.body().getUsername();
                String email = response.body().getEmail();
                String no_telp = response.body().getNo_telp();
                String foto_profil = response.body().getFoto_profil();

                txtWelcome.setText(fullname);

                Glide.with(getApplicationContext()).load(URL_IMG_USER + foto_profil).placeholder(R.drawable.bg_foto_default).circleCrop().into(imgProfil);

            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {

            }
        });

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        if (System.currentTimeMillis() - exitTime > 2000 ) {
//            Toast.makeText(MainActivity.this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
//            exitTime = System.currentTimeMillis();
//        } else {
//            onStop();
//        }
//    }
}