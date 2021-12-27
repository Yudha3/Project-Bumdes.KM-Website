package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ResponseUser;
import com.yogandrn.coba2.R;
import com.yogandrn.coba2.SessionManager;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    LinearLayout btnKatalog, btnPesanan, btnPreorder, btnKeranjang;
    private String id_user;
    private TextView txtWelcome, btnHeadline1, btnHeadline2;
    private ImageView btnSearch;
    private EditText etSearch;
    private long exitTime = 0;
    private CircleImageView imgProfil;
    private ImageView ic_keranjang, ic_notifikasi;
    private SwipeRefreshLayout srlMainmenu;
    private ProgressBar pbMainmenu;
    private FloatingActionButton floatingButtonChat;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setTitle("Bumdes Shop");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        btnSearch = (ImageView) findViewById(R.id.btn_search);
        etSearch = (EditText) findViewById(R.id.et_searchbar);
        ic_keranjang = (ImageView) findViewById(R.id.keranjang_main);
        imgProfil = (CircleImageView) findViewById(R.id.img_profil_main);
        btnKatalog = (LinearLayout) findViewById(R.id.layoutKatalog);
        btnKeranjang = (LinearLayout) findViewById(R.id.layoutKeranjang);
        btnPesanan = (LinearLayout) findViewById(R.id.layoutPesanan);
        btnPreorder = (LinearLayout) findViewById(R.id.layoutPreorder);
        txtWelcome = (TextView) findViewById(R.id.txtuser_main);
        btnHeadline1 = (TextView) findViewById(R.id.btn_headline1);
        btnHeadline2 = (TextView) findViewById(R.id.btn_headline2);
        srlMainmenu = findViewById(R.id.srl_main_menu);
        pbMainmenu= findViewById(R.id.progress_main_menu);
        floatingButtonChat = findViewById(R.id.btn_hubungi_kami);

        sessionManager = new SessionManager(MainActivity.this);
        if (!sessionManager.getLogin()){
            moveToLogin();
        }
        getUserData();

        srlMainmenu.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlMainmenu.setRefreshing(true);
                getUserData();
                srlMainmenu.setRefreshing(false);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = etSearch.getText().toString();
                Intent search = new Intent(MainActivity.this, SearchActivity.class);
                search.putExtra("keyword", keyword);
                etSearch.setText("");
                startActivity(search);
            }
        });

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
                startActivity(profil);
            }
        });

        btnKatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent katalog = new Intent(MainActivity.this, KatalogActivity.class);
//                katalog.putExtra("id_user", id_user);
                startActivity(katalog);
            }
        });

        btnPreorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent preorder = new Intent(MainActivity.this, ListPreorderActivity.class);
//                preorder.putExtra("id_user", id_user);
                startActivity(preorder);
            }
        });

        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent keranjang = new Intent(MainActivity.this, KeranjangActivity.class);
//                keranjang.putExtra("id_user", id_user);
                startActivity(keranjang);
            }
        });

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesanan = new Intent(MainActivity.this, ListPesanan.class);
//                pesanan.putExtra("id_user", id_user);
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

        floatingButtonChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sosmed = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wa.me/6285123456789"));
                startActivity(sosmed);
            }
        });
    }

    public void moveToLogin() {
        Intent intent = new Intent(MainActivity.this, WelcomeScreen.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void getUserData(){
        pbMainmenu.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseUser> getData = apiRequestData.getUser(sessionManager.getSessionID());
        getData.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String fullname = response.body().getFullname();
                String username = response.body().getUsername();
                String email = response.body().getEmail();
                String no_telp = response.body().getNo_telp();
                String foto_profil = response.body().getFoto_profil();

                txtWelcome.setText(fullname);

                Glide.with(getApplicationContext()).load(Global.IMG_USER_URL + foto_profil).placeholder(R.drawable.user).circleCrop().into(imgProfil);
                pbMainmenu.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                pbMainmenu.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - exitTime > 2000 ) {
            Toast.makeText(MainActivity.this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }
}