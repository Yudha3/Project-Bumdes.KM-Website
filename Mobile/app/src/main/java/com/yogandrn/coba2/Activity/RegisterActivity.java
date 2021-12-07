package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Model.ResponseModel;
import com.yogandrn.coba2.Model.ResponseUser;
import com.yogandrn.coba2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText txtFullname, txtUsername, txtEmail, txtPassword, txtConfpass, txtNoTelp;
    TextView txtLogin;
    Button btnRegister;
    String fullname, username, email, password, no_telp, confpass;
    String SERVER_REGISTER_URL = "http://undeveloppedcity.000webhostapp.com/android/volley/register.php";
    private LinearLayout loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        txtFullname = (TextInputEditText) findViewById(R.id.txtFullname_register);
        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername_register);
        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail_register);
        txtNoTelp = (TextInputEditText) findViewById(R.id.txtTelp_register);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword_register);
        txtConfpass = (TextInputEditText) findViewById(R.id.txtConfPass_register);
        txtLogin = (TextView) findViewById(R.id.txtLogin_register);
        btnRegister = (Button) findViewById(R.id.btnRegister_register);
        loading = (LinearLayout) findViewById(R.id.progressRegister);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        txtLogin.setText(fromHtml("Sudah punya akun? " + "<font color='#24882A'><b>Masuk</b></font>"));

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fullname = txtFullname.getText().toString();
                username = txtUsername.getText().toString();
                email = txtEmail.getText().toString();
                no_telp = txtNoTelp.getText().toString();
                password = txtPassword.getText().toString();
                confpass = txtConfpass.getText().toString();

                if (fullname.trim().equals("")) {
                    txtFullname.setError("Nama Lengkap tidak boleh kosong!");
                } else if (username.trim().equals("")) {
                    txtUsername.setError("Username tidak boleh kosong!");
                } else if (email.trim().equals("")) {
                    txtEmail.setError("Email tidak boleh kosong!");
                } else if (no_telp.trim().equals("")) {
                    txtNoTelp.setError("Nomor Telepon tidak boleh kosong!");
                } else if (password.trim().equals("")) {
                    txtPassword.setError("Anda harus mengisi password");
                } else if (!password.equals(confpass)) {
                    txtConfpass.setError("Konfirmasi Password salah!");
                }else {
                    loading.setVisibility(View.VISIBLE);
                    registerUser();
                }
            }
        });

    }

    private void registerUser() {
        APIRequestData ardData = RetroServer.koneksiRetrofit().create(APIRequestData.class); // menghubungkan class interface ke retrofit
        Call<ResponseUser> simpanData = ardData.userRegister(fullname, username, email, no_telp, password);

        simpanData.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String pesan = response.body().getPesan();
                loading.setVisibility(View.INVISIBLE);
                Toast.makeText(RegisterActivity.this, pesan, Toast.LENGTH_SHORT).show();
                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                loading.setVisibility(View.INVISIBLE);
                Toast.makeText(RegisterActivity.this, "Terjadi kesalahan : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    // !---- register dengan volley berhasil ----!
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getSupportActionBar().hide();
//        txtFullname = (TextInputEditText) findViewById(R.id.txtFullname_register);
//        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername_register);
//        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail_register);
//        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword_register);
//        txtConfpass = (TextInputEditText) findViewById(R.id.txtConfPass_register);
//        txtLogin = (TextView) findViewById(R.id.txtLogin_register);
//        btnRegister = (Button) findViewById(R.id.btnRegister_register);
//        progressDialog = new ProgressDialog(RegisterActivity.this);
//
//        txtLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(loginIntent);
//            }
//        });
//
//        txtLogin.setText(fromHtml("Belum punya akun? " + "<font color='#24882A'>Daftar</font>"));
//
//        btnRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String fullname = txtFullname.getText().toString();
//                String username = txtUsername.getText().toString();
//                String email = txtEmail.getText().toString();
//                String password = txtPassword.getText().toString();
//                String confpass = txtConfpass.getText().toString();
//
//                if (!fullname.equals("") && !username.equals("") && !email.equals("") && !password.equals("")) {
//                    if (password.equals(confpass)) {
//                        CreateDataToServer(fullname, username, email, password);
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Gagal! Pasword tidak cocok!", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Fields can not be empty!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    public void CreateDataToServer(final String fullname, final String username, final String email, final String password) {
//        if (checkNetworkConnection()) {
//            progressDialog.show();
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_REGISTER_URL,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                String resp = jsonObject.getString("server_response");
//                                if (resp.equals("[{\"status\":\"OK\"}]")) {
//                                    Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
//                                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
//                                    startActivity(loginIntent);
//                                } else if (resp.equals("[{\"status\":\"REGISTERED\"}]")) {
//                                    Toast.makeText(getApplicationContext(), "Pengguna sudah terdaftar! \nCobalah masuk dengan akun ini.", Toast.LENGTH_SHORT).show();
//                                }else {
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
//                    params.put("fullname", fullname);
//                    params.put("username", username);
//                    params.put("email", email);
//                    params.put("password", password);
//                    return params;
//                }
//            };
//
//            VolleyConnection.getInstance(RegisterActivity.this).addToRequestQue(stringRequest);
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
//    public boolean checkNetworkConnection() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
//        return (networkInfo != null && networkInfo.isConnected());
//    }
// !----- akhir register dgn volley ----!

    private static Spanned fromHtml (String html) {
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

//    public void register(View view) {
//        fullname = txtFullname.getText().toString().trim();
//        email = txtEmail.getText().toString().trim();
//        username = txtUsername.getText().toString().trim();
//        password = txtPassword.getText().toString().trim();
//        confpass = txtConfpass.getText().toString().trim();
//        if (!password.equals(confpass)) {
//            Toast.makeText(this, "Confirm Password doesn't match!", Toast.LENGTH_SHORT).show();
//        } else if (!fullname.equals("") && !email.equals("") && !username.equals("") && !password.equals("")) {
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    if (response.equals("success")) {
//                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
//                        startActivity(intent);
//                    } else if (response.equals("failure")) {
//                        Toast.makeText(RegisterActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
//                }
//            }){
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> data = new HashMap<>();
//                    data.put("fullname", fullname);
//                    data.put("username", username);
//                    data.put("email", email);
//                    data.put("password", password);
//                    return data;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//            requestQueue.add(stringRequest);
//        } else {
//            Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    public void login(View view) {
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
//        finish();
//    }
}