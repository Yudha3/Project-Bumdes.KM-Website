package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText txtEmail, txtPassword;
    String email, password;
    TextView txtRegister;
    Button btnLogin;
    private List<ModelUser> listData = new ArrayList<>();
    String SERVER_LOGIN_URL = "http://undeveloppedcity.000webhostapp.com/android/volley/checklogin.php";
    ProgressDialog progressDialog;
    private LinearLayout loading;

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
        loading = (LinearLayout) findViewById(R.id.progressLogin);

        //mengakses halaman register
        txtRegister.setOnClickListener(new View.OnClickListener() {
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

                    Global.id_user = id_user;
                    Global.fullname = fullname;
                    Global gb = new Global();
                    gb.getTotal();

                    Intent login = new Intent(LoginActivity.this, MainActivity.class);
                    login.putExtra("id_user", id_user);
                    login.putExtra("fullname", fullname);
                    login.putExtra("username", username);
                    login.putExtra("email", email);
                    login.putExtra("no_telp", no_telp);
                    login.putExtra("foto_profil", foto_profil);
                    loading.setVisibility(View.INVISIBLE);
                    startActivity(login);
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

// !--- login dengan volley yang berhasil -----!
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_login);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
//        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail_login);
//        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword_login);
//        txtRegister = (TextView) findViewById(R.id.txtRegister_login);
//        btnLogin = (Button) findViewById(R.id.btnLogin_login);
//        progressDialog = new ProgressDialog(LoginActivity.this);
//
//        //mengakses halaman register
//        txtRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
//                startActivity(registerIntent);
//                finish();
//            }
//        });
//
//        txtRegister.setText(fromHtml("Belum punya akun? " + "<font color='#24882A'>Daftar</font>"));
//
//        // tombol login untuk memanggil method checkLogin
//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String email = txtEmail.getText().toString();
//                String password = txtPassword.getText().toString();
//
//                if (email.equals("")) {
//                    Toast.makeText(LoginActivity.this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
//                } else if ( password.equals("")) {
//                    Toast.makeText(LoginActivity.this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//                } if (email.equals("") && password.equals("")) {
//                    Toast.makeText(LoginActivity.this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show();
//                } else if (!email.equals("") && !password.equals("")){
//                    CheckLogin(email, password);
//                }
//
//            }
//        });
//    }
//
//    // method checkLogin
//    public void CheckLogin(final String email, final String password) {
//        if (checkNetworkConnection()) {
//            progressDialog.show();
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_LOGIN_URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                String resp = jsonObject.getString("server_response");
//                                if (resp.equals("[{\"status\":\"OK\"}]")) {
//                                    Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
//                                    Intent dashboardIntent = new Intent(LoginActivity.this, MainActivity.class);
//                                    startActivity(dashboardIntent);
//                                } else if (resp.equals("[{\"status\":\"FAILED\"}]")) {
//                                    Toast.makeText(getApplicationContext(), "Username atau password salah", Toast.LENGTH_SHORT).show();
//                                } else {
//                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("email", email);
//                    params.put("password", password);
//                    return params;
//                }
//            };
//
//            VolleyConnection.getInstance(LoginActivity.this).addToRequestQue(stringRequest);
//
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    progressDialog.cancel();
//                }
//            }, 2000);
//        } else {
//            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    //method memeriksa Koneksi
//    private boolean checkNetworkConnection() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        return (networkInfo != null && networkInfo.isConnected());
//    }
// !---- batas login dengan volley----!


    private static Spanned fromHtml (String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }



//    public void login(View view) {
//        email = txtEmail.getText().toString().trim();
//        password = txtPassword.getText().toString().trim();
//        if (!email.equals("") && !password.equals("")) {
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    if (response.equals("success")) {
//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);
//                    } else if (response.equals("failure")) {
//                        Toast.makeText(LoginActivity.this, "Invalid Login", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(LoginActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
//                }
//            }){
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> data = new HashMap<>();
//                    data.put("email", email);
//                    data.put("password", password);
//                    return data;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        } else {
//            Toast.makeText(LoginActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void register(View view) {
//        Intent intent = new Intent(this, RegisterActivity.class);
//        startActivity(intent);
//        finish();
//    }
}