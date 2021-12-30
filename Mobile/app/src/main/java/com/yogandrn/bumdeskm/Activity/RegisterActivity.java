package com.yogandrn.bumdeskm.Activity;

import android.content.Intent;
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
import com.yogandrn.bumdeskm.Model.ResponseUser;
import com.yogandrn.bumdeskm.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText txtFullname, txtUsername, txtEmail, txtPassword, txtConfpass, txtNoTelp;
    TextView txtLogin;
    Button btnRegister, btnLogin;
    String fullname, username, email, password, no_telp, confpass;
    String SERVER_REGISTER_URL = "http://undeveloppedcity.000webhostapp.com/android/volley/register.php";
    private ProgressBar pbLoading;
    private View vLoading;
    private boolean passwordVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        txtFullname = (EditText) findViewById(R.id.txtFullname_register);
        txtUsername = (EditText) findViewById(R.id.txtUsername_register);
        txtEmail = (EditText) findViewById(R.id.txtEmail_register);
        txtNoTelp = (EditText) findViewById(R.id.txtTelp_register);
        txtPassword = (EditText) findViewById(R.id.txtPassword_register);
        txtConfpass = (EditText) findViewById(R.id.txtConfPass_register);
        txtLogin = (TextView) findViewById(R.id.txtLogin_register);
        btnLogin = (Button) findViewById(R.id.btnLogin_register);
        btnRegister = (Button) findViewById(R.id.btnRegister_register);
        pbLoading = (ProgressBar) findViewById(R.id.progressRegister) ;
        vLoading = (View) findViewById(R.id.view_loading);

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

        txtConfpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                final int Right = 2;
                if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                    if(motionEvent.getRawX() >= txtConfpass.getRight()-txtConfpass.getCompoundDrawables()[Right].getBounds().width()){
                        int selection = txtConfpass.getSelectionEnd();
                        if (passwordVisible) {
                            txtConfpass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_visibility_grey, 0);
                            txtConfpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            passwordVisible= false;
                        } else {
                            txtConfpass.setCompoundDrawablesRelativeWithIntrinsicBounds(0,0, R.drawable.ic_visibility_grey_off, 0);
                            txtConfpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            passwordVisible= true;
                        }
                        txtConfpass.setSelection(selection);
                        return  true;
                    }
                }
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        txtLogin.setText(fromHtml("Sudah punya akun? " + "<font color='#24882A'>Masuk</font>"));

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
                    registerUser();
                }
            }
        });

    }

    private void registerUser() {
        vLoading.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.VISIBLE);
        APIRequestData ardData = RetroServer.koneksiRetrofit().create(APIRequestData.class); // menghubungkan class interface ke retrofit
        Call<ResponseUser> simpanData = ardData.userRegister(fullname, username, email, no_telp, password);

        simpanData.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Daftar berhasil!")){
                pbLoading.setVisibility(View.INVISIBLE);
                vLoading.setVisibility(View.INVISIBLE);
                Toast.makeText(RegisterActivity.this, "Registrasi berhasil", Toast.LENGTH_SHORT).show();
                Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(login);
                } else if (pesan.equals("Daftar gagal!")){
                    pbLoading.setVisibility(View.INVISIBLE);
                    vLoading.setVisibility(View.INVISIBLE);
                    Toast.makeText(RegisterActivity.this, "Gagal melakukan registrasi", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("Username terdaftar")){
                    pbLoading.setVisibility(View.INVISIBLE);
                    vLoading.setVisibility(View.INVISIBLE);
                    txtUsername.setError("Username sudah terdaftar");
                    Toast.makeText(RegisterActivity.this, "Username sudah terdaftar!", Toast.LENGTH_SHORT).show(); 
                } else if (pesan.equals("Email terdaftar")) {
                    pbLoading.setVisibility(View.INVISIBLE);
                    vLoading.setVisibility(View.INVISIBLE);
                    txtEmail.setError("Email sudah terdaftar");
                    Toast.makeText(RegisterActivity.this, "Email sudah terdaftar!", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("Nomor telepon terdaftar")) {
                    pbLoading.setVisibility(View.INVISIBLE);
                    vLoading.setVisibility(View.INVISIBLE);
                    txtNoTelp.setError("Nomor telepon sudah terdaftar");
                    Toast.makeText(RegisterActivity.this, "Nomor telepon sudah terdaftar", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                pbLoading.setVisibility(View.INVISIBLE);
                vLoading.setVisibility(View.INVISIBLE);
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