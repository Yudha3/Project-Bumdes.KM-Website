package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

public class ProfilActivity extends AppCompatActivity {

    private TextView titleUsername, titleEmail, txtEmail, txtFullname, txtUsername, txtID, txtNoTelp, txtJKelamin;
    private CircleImageView fotoProfil;
    private Button btnEdit, btnLogout, btnUbahPassword;
    private SwipeRefreshLayout srlProfil;
    private ProgressBar pbProfil;
    SessionManager sessionManager;

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

        btnEdit = (Button) findViewById(R.id.btn_edit_profil);
        btnLogout = (Button) findViewById(R.id.btn_logout_profil);
        btnUbahPassword = (Button) findViewById(R.id.btn_ubah_password);
        fotoProfil = (CircleImageView) findViewById(R.id.img_profil_profil);
        titleEmail = (TextView) findViewById(R.id.title_email);
        titleUsername = (TextView) findViewById(R.id.title_username);
        txtID = (TextView) findViewById(R.id.txtID_User);
        txtFullname = (TextView) findViewById(R.id.txt_nama_profil);
        txtJKelamin = (TextView) findViewById(R.id.txt_kelamin_profil);
        txtUsername = (TextView) findViewById(R.id.txt_username_profil);
        txtEmail = (TextView) findViewById(R.id.txt_email_profil);
        txtNoTelp = (TextView) findViewById(R.id.txt_notelp_profil);
        srlProfil = findViewById(R.id.srl_profil);
        pbProfil =findViewById(R.id.progress_profil);

        sessionManager = new SessionManager(ProfilActivity.this);
        getUserData();

//        Glide.with(this).load(URL_IMG_USER + foto_profil).circleCrop().into(fotoProfil);
//        titleEmail.setText(email);
//        titleUsername.setText(username);
//        txtID.setText(id_user);
//        txtFullname.setText(fullname);
//        txtUsername.setText(username);
//        txtEmail.setText(email);
//        txtNoTelp.setText(no_telp);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(ProfilActivity.this, EditProfilActivity.class);
                startActivity(edit);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogLogout = new AlertDialog.Builder(view.getContext());
                dialogLogout.setCancelable(true);
                dialogLogout.setTitle("Logout");
                dialogLogout.setMessage("Apakah Anda yakin ingin keluar?");
                dialogLogout.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        sessionManager.logoutSession();
                        Intent intent = new Intent(ProfilActivity.this, WelcomeScreen.class);
                        // intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                });
                dialogLogout.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialogLogout.show();
            }
        });

        btnUbahPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfilActivity.this, CekPassword.class));
                finish();
            }
        });

        srlProfil.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlProfil.setRefreshing(true);
                getUserData();
                srlProfil.setRefreshing(false);
            }
        });
    }

    public void getUserData(){
        pbProfil.setVisibility(View.VISIBLE);
        String id = String.valueOf(sessionManager.getSessionID());
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseUser> callData = apiRequestData.getUser(id);
        callData.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String fullname = response.body().getFullname();
                String jkelamin = response.body().getJenis_kelamin();
                String username = response.body().getUsername();
                String email = response.body().getEmail();
                String no_telp = response.body().getNo_telp();
                String foto_profil = response.body().getFoto_profil();

                Glide.with(getApplicationContext()).load(Global.IMG_USER_URL + foto_profil).placeholder(R.drawable.user).circleCrop().into(fotoProfil);
                titleEmail.setText(email);
                titleUsername.setText(username);
                txtFullname.setText(fullname);
                txtJKelamin.setText(jkelamin);
                txtUsername.setText(username);
                txtEmail.setText(email);
                txtNoTelp.setText(no_telp);
                pbProfil.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                pbProfil.setVisibility(View.GONE);
                Toast.makeText(ProfilActivity.this, "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
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