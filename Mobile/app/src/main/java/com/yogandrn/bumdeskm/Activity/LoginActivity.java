package com.yogandrn.bumdeskm.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.yogandrn.bumdeskm.API.APIRequestData;
import com.yogandrn.bumdeskm.API.RetroServer;
import com.yogandrn.bumdeskm.Global;
import com.yogandrn.bumdeskm.Model.ModelUser;
import com.yogandrn.bumdeskm.Model.ResponseUser;
import com.yogandrn.bumdeskm.R;
import com.yogandrn.bumdeskm.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
//        implements View.OnClickListener


    private EditText txtEmail, txtPassword;
    String email, password;
    TextView txtRegister;
    Button btnLogin,  btnRegister;
    private List<ModelUser> listData = new ArrayList<>();
    String SERVER_LOGIN_URL = "http://undeveloppedcity.000webhostapp.com/android/volley/checklogin.php";
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    private boolean passwordVisible;
    private ProgressBar loading;
    private View vLoading;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    public static final String session = "session";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        txtEmail = findViewById(R.id.txtEmail_login);
        txtPassword =  findViewById(R.id.txtPassword_login);
        txtRegister = (TextView) findViewById(R.id.txtRegister_login);
        btnLogin = (Button) findViewById(R.id.btnLogin_login);
        btnRegister = (Button) findViewById(R.id.btnRegister_login);
        loading = (ProgressBar) findViewById(R.id.progress_login);
        vLoading = findViewById(R.id.view_login);


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
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

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

        txtPassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= txtPassword.getRight()-txtPassword.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = txtPassword.getSelectionEnd();
                        if (passwordVisible) {
                            txtPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_visibility, 0);
                            txtPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible= false;
                        } else {
                            txtPassword.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_visibility_off, 0);
                            txtPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible= true;
                        }
                        txtPassword.setSelection(selection);
                        return  true;
                    }
                }
                return false;
            }
        });

    }

    public void checkLogin() {
        vLoading.setVisibility(View.VISIBLE);
        loading.setVisibility(View.VISIBLE);
        sessionManager =  new SessionManager(LoginActivity.this);

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

                    sessionManager.setLogin(true);
                    sessionManager.setSessionID(id_user);
                    Global gb = new Global();
                    gb.getTotal(String.valueOf(sessionManager.getSessionID()));

                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    vLoading.setVisibility(View.INVISIBLE);
                    loading.setVisibility(View.INVISIBLE);
                    startActivity(login);
                    finish();
                } else if (pesan.equals("WRONG")) {
                    Toast.makeText(LoginActivity.this, "Email atau Password salah!", Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.INVISIBLE);
                    vLoading.setVisibility(View.INVISIBLE);
                } else if (pesan.equals("FAILED")) {
                    Toast.makeText(LoginActivity.this, "Terjadi kesalahan saat menghubungi server!", Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.INVISIBLE);
                    vLoading.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                loading.setVisibility(View.INVISIBLE);
                vLoading.setVisibility(View.INVISIBLE);
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