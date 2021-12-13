package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ResponseUser;
import com.yogandrn.coba2.R;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilActivity extends AppCompatActivity {

    private TextView titleUsername, titleEmail, txtEmail, txtFullname, txtUsername, txtID, txtNoTelp;
    private CircleImageView fotoProfil;
    private String URL_IMG_USER = "http://undeveloppedcity.000webhostapp.com/android/img/user/";
//    private String URL_IMG_USER = "http://192.168.1.100:8080/android/img/user/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Profil");

//        //Tangkap data intent login
//        Bundle data = getIntent().getExtras();
//        String id_user = data.getString("id_user");
//        String fullname = data.getString("fullname");
//        String username = data.getString("username");
//        String email = data.getString("email");
//        String no_telp = data.getString("no_telp");
//        String foto_profil = data.getString("foto_profil");

        fotoProfil = (CircleImageView) findViewById(R.id.img_profil_profil);
        titleEmail = (TextView) findViewById(R.id.title_email);
        titleUsername = (TextView) findViewById(R.id.title_username);
        txtID = (TextView) findViewById(R.id.txtID_User);
        txtFullname = (TextView) findViewById(R.id.txt_nama_profil);
        txtUsername = (TextView) findViewById(R.id.txt_username_profil);
        txtEmail = (TextView) findViewById(R.id.txt_email_profil);
        txtNoTelp = (TextView) findViewById(R.id.txt_notelp_profil);

        getUserData();

//        Glide.with(this).load(URL_IMG_USER + foto_profil).circleCrop().into(fotoProfil);
//        titleEmail.setText(email);
//        titleUsername.setText(username);
//        txtID.setText(id_user);
//        txtFullname.setText(fullname);
//        txtUsername.setText(username);
//        txtEmail.setText(email);
//        txtNoTelp.setText(no_telp);

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

                Glide.with(getApplicationContext()).load(URL_IMG_USER + foto_profil).circleCrop().into(fotoProfil);
                titleEmail.setText(email);
                titleUsername.setText(username);
                txtFullname.setText(fullname);
                txtUsername.setText(username);
                txtEmail.setText(email);
                txtNoTelp.setText(no_telp);
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {

            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}