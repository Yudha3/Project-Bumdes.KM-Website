package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputEditText;
import com.yogandrn.coba2.R;
import com.yogandrn.coba2.VolleyConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText txtEmail, txtPassword;
    String email, password;
    TextView txtRegister;
    Button btnLogin;
//    String URL = "http://192.168.1.100:8080/login-register/login.php";
    String SERVER_LOGIN_URL = "http://192.168.1.100:8080/LoginRegister_Volley/checklogin.php";
    ProgressDialog progressDialog;

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
        progressDialog = new ProgressDialog(LoginActivity.this);

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

        // tombol login untuk memanggil method checkLogin
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();

                if (email.equals("")) {
                    Toast.makeText(LoginActivity.this, "Email tidak boleh kosong", Toast.LENGTH_SHORT).show();
                } else if ( password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                } if (email.equals("") && password.equals("")) {
                    Toast.makeText(LoginActivity.this, "Kolom tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                } else if (!email.equals("") && !password.equals("")){
                    CheckLogin(email, password);
                }

            }
        });
    }

    // method checkLogin
    public void CheckLogin(final String email, final String password) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_LOGIN_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Berhasil Login", Toast.LENGTH_SHORT).show();
                                    Intent dashboardIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(dashboardIntent);
                                } else if (resp.equals("[{\"status\":\"FAILED\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Username atau password salah", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), resp, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };

            VolleyConnection.getInstance(LoginActivity.this).addToRequestQue(stringRequest);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressDialog.cancel();
                }
            }, 2000);
        } else {
            Toast.makeText(getApplicationContext(), "Tidak ada koneksi internet", Toast.LENGTH_SHORT).show();
        }
    }

    //method memeriksa Koneksi
    private boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public static Spanned fromHtml (String html) {
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