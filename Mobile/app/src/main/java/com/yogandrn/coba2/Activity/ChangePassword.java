package com.yogandrn.coba2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Model.ResponseModel;
import com.yogandrn.coba2.R;
import com.yogandrn.coba2.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePassword extends AppCompatActivity {

    private View viewUbah;
    private ProgressBar pbUbah;
    SessionManager sessionManager;
    private EditText etPassword, etConfPass;
    private Button btnUbah;
    private  String oldPassword, id_user, password, confpass;
    private boolean passwordVisible;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Ubah password");

        sessionManager = new SessionManager(ChangePassword.this);
        id_user = sessionManager.getSessionID();

        Bundle data = getIntent().getExtras();
        oldPassword = data.getString("password");

        viewUbah = findViewById(R.id.view_ubah_password);
        pbUbah = findViewById(R.id.progress_ubah_password);
        etPassword = findViewById(R.id.et_password_ubah);
        etConfPass = findViewById(R.id.et_confpass_ubah);
        btnUbah = findViewById(R.id.btn_simpan_password);



        etPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= etPassword.getRight()-etPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = etPassword.getSelectionEnd();
                        if (passwordVisible) {
                            etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_visibility_grey, 0);
                            etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible= false;
                        } else {
                            etPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_visibility_grey_off, 0);
                            etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible= true;
                        }
                        etPassword.setSelection(selection);
                        return  true;
                    }
                }
                return false;
            }
        });

        etConfPass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= etConfPass.getRight()-etConfPass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = etConfPass.getSelectionEnd();
                        if (passwordVisible) {
                            etConfPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_visibility_grey, 0);
                            etConfPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible= false;
                        } else {
                            etConfPass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_visibility_grey_off, 0);
                            etConfPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible= true;
                        }
                        etConfPass.setSelection(selection);
                        return  true;
                    }
                }
                return false;
            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = etPassword.getText().toString();
                confpass = etConfPass.getText().toString();

                if (password.trim().equals("")) {
                    etPassword.setError("Tidak boleh kosong!");
                } else if (confpass.trim().equals("")){
                    etConfPass.setError("Tidak boleh kosong!");
                } else if (!password.equals(confpass)){
                    etConfPass.setError("Password tidak sesuai");
                    Toast.makeText(ChangePassword.this, "Password tidak sesuai\nPeriksa kembali password Anda!", Toast.LENGTH_SHORT).show();
                } else {
                    changePassword();
                }

            }
        });
    }

    private void changePassword() {
        viewUbah.setVisibility(View.VISIBLE);
        pbUbah.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> callUbah = apiRequestData.ubahPassword(id_user,oldPassword, confpass);
        callUbah.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    viewUbah.setVisibility(View.INVISIBLE);
                    pbUbah.setVisibility(View.INVISIBLE);
                    Toast.makeText(ChangePassword.this, "Password berhasil diperbarui", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ChangePassword.this, ProfilActivity.class));
                    finish();
                } else if (pesan.equals("GAGAL")) {
                    viewUbah.setVisibility(View.INVISIBLE);
                    pbUbah.setVisibility(View.INVISIBLE);
                    Toast.makeText(ChangePassword.this, "Gagal memperbarui password", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("NOT CONNECTED")) {
                    viewUbah.setVisibility(View.INVISIBLE);
                    pbUbah.setVisibility(View.INVISIBLE);
                    Toast.makeText(ChangePassword.this, "Terjadi kesalahan saat menghubungi server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable throwable) {
                viewUbah.setVisibility(View.INVISIBLE);
                pbUbah.setVisibility(View.INVISIBLE);
                Toast.makeText(ChangePassword.this, "Terjadi kesalahan :\n" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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