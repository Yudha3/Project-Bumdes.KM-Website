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

public class CekPassword extends AppCompatActivity {

    private EditText etPassword;
    private Button btnCek;
    private View vCek;
    private ProgressBar pbCek;
    private String password, id_user;
    private boolean passwordVisible;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_password);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Masukkan password");

        sessionManager = new SessionManager(CekPassword.this);
        id_user = sessionManager.getSessionID();

        vCek = findViewById(R.id.view_cek_password);
        pbCek = findViewById(R.id.progress_cek_password);
        etPassword = findViewById(R.id.et_cek_password);
        btnCek = findViewById(R.id.btn_cek_password);

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

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                password = etPassword.getText().toString();
                if (password.trim().equals("")){
                    etPassword.setError("Tidak boleh kosong!");
                } else {
                    cekPassword();
                }
            }
        });
    }

    private void cekPassword() {
        vCek.setVisibility(View.VISIBLE);
        pbCek.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> callCek = apiRequestData.cekPassword(id_user, password);
        callCek.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BENAR")) {
                    vCek.setVisibility(View.INVISIBLE);
                    pbCek.setVisibility(View.INVISIBLE);
                    Intent next = new Intent(CekPassword.this, ChangePassword.class);
                    next.putExtra("password", password);
                    startActivity(next);
                    finish();
                } else if (pesan.equals("WRONG")) {
                    vCek.setVisibility(View.INVISIBLE);
                    pbCek.setVisibility(View.INVISIBLE);
                    Toast.makeText(CekPassword.this, "Password salah!", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("NOT CONNECTED")){
                    vCek.setVisibility(View.INVISIBLE);
                    pbCek.setVisibility(View.INVISIBLE);
                    Toast.makeText(CekPassword.this, "Terjadi kesalahan saat menghubungi server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable throwable) {
                vCek.setVisibility(View.INVISIBLE);
                pbCek.setVisibility(View.INVISIBLE);
                Toast.makeText(CekPassword.this, "Terjadi kesalahan :\n" + throwable.getMessage(), Toast.LENGTH_SHORT).show();
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