package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ResponseUser;
import com.yogandrn.coba2.Model.ModelUser;
import com.yogandrn.coba2.R;
import com.yogandrn.coba2.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
//        implements View.OnClickListener


    TextInputEditText txtEmail, txtPassword;
    String email, password;
    TextView txtRegister;
    Button btnLogin,  btnRegister;
    private List<ModelUser> listData = new ArrayList<>();
    String SERVER_LOGIN_URL = "http://undeveloppedcity.000webhostapp.com/android/volley/checklogin.php";
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    private LinearLayout loading;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail_login);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword_login);
        txtRegister = (TextView) findViewById(R.id.txtRegister_login);
        btnLogin = (Button) findViewById(R.id.btnLogin_login);
        btnRegister = (Button) findViewById(R.id.btnRegister_login);
        loading = (LinearLayout) findViewById(R.id.progressLogin);

        //mengakses halaman register
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    startActivity(registerIntent);
                    finish();
            }
        });

        txtRegister.setText(fromHtml("Belum punya akun? " + "<font color='#24882A'>Daftar</font>"));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = txtEmail.getText().toString();
                password = txtPassword.getText().toString();

                if (email.trim().equals("")) {
                    txtEmail.setError("Email tidak boleh kosong!");
                } else if (password.trim().equals("")) {
                    txtPassword.setError("Password tidak boleh kosong!");
                } else {
                    checkLogin();
                }
            }
        });


    }

    public void checkLogin() {
        loading.setVisibility(View.VISIBLE);
        APIRequestData ardData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseUser> checkData = ardData.checkLogin(email, password);
        
        checkData.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String pesan = response.body().getPesan();

                if ( pesan.equals("BERHASIL")) {
                    String id_user = response.body().getId_user();
                    String fullname = response.body().getFullname();
                    String username = response.body().getUsername();
                    String email = response.body().getEmail();
                    String no_telp = response.body().getNo_telp();
                    String foto_profil = response.body().getFoto_profil();

                    sessionManager =  new SessionManager(LoginActivity.this);
                    sessionManager.setLogin(true);
                    sessionManager.setSessionID(id_user);
                    Global gb = new Global();
                    gb.getTotal();

                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    loading.setVisibility(View.INVISIBLE);
                    startActivity(login);
                    finish();
                } else if (pesan.equals("WRONG")) {
                    Toast.makeText(LoginActivity.this, "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.INVISIBLE);
                } else if (pesan.equals("FAILED")) {
                    Toast.makeText(LoginActivity.this, "Terjadi kesalahan saat menghubungi server!", Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                loading.setVisibility(View.INVISIBLE);
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static Spanned fromHtml (String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }
}