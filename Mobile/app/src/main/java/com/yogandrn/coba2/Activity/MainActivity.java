package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yogandrn.coba2.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    LinearLayout btnKatalog, btnPesanan, btnPreorder, btnKeranjang;
    private TextView txtWelcome;
    private long exitTime = 0;
    private CircleImageView imgProfil;
    private ImageView ic_keranjang, ic_notifikasi;
//    private String URL_IMG_USER = "http://undeveloppedcity.000webhostapp.com/android/img/user/";
    private String URL_IMG_USER = "http://192.168.1.100:8080/android/img/user/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        //Tangkap data intent login
        Bundle data = getIntent().getExtras();
        String id_user = data.getString("id_user");
        String fullname = data.getString("fullname");
        String username = data.getString("username");
        String email = data.getString("email");
        String no_telp = data.getString("no_telp");
        String foto_profil = data.getString("foto_profil");

        ic_keranjang = (ImageView) findViewById(R.id.keranjang_main);
        imgProfil = (CircleImageView) findViewById(R.id.img_profil_main);
        btnKatalog = (LinearLayout) findViewById(R.id.layoutKatalog);
        btnKeranjang = (LinearLayout) findViewById(R.id.layoutKeranjang);
        btnPesanan = (LinearLayout) findViewById(R.id.layoutPesanan);
        btnPreorder = (LinearLayout) findViewById(R.id.layoutPreorder);
        txtWelcome = (TextView) findViewById(R.id.txtuser);

        txtWelcome.setText(fullname);

        Glide.with(this).load(URL_IMG_USER + foto_profil).placeholder(R.drawable.bg_foto_default).circleCrop().into(imgProfil);

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
                profil.putExtra("id_user", id_user);
                profil.putExtra("fullname", fullname);
                profil.putExtra("username", username);
                profil.putExtra("email", email);
                profil.putExtra("no_telp", no_telp);
                profil.putExtra("foto_profil", foto_profil);
                startActivity(profil);
            }
        });

        btnKatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KatalogActivity.class));
            }
        });

        btnPreorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PreorderActivity.class));
            }
        });

        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KeranjangActivity.class));
            }
        });

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PesananActivity.class));
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