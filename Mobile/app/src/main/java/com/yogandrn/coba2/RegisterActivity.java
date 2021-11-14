package com.yogandrn.coba2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    TextInputEditText txtFullname, txtUsername, txtEmail, txtPassword, txtConfpass;
    TextView txtLogin;
    Button btnRegister;
    String fullname, username, email, password, confpass;
    String URL = "http://192.168.1.100:8080/login-register/register.php";
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtFullname = (TextInputEditText) findViewById(R.id.txtFullname_register);
        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername_register);
        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail_register);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword_register);
        txtConfpass = (TextInputEditText) findViewById(R.id.txtConfPass_register);
        txtLogin = (TextView) findViewById(R.id.txtLogin_register);
        btnRegister = (Button) findViewById(R.id.btnRegister_register);
        progressDialog = new ProgressDialog(RegisterActivity.this);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fullname = txtFullname.getText().toString();
                String username = txtUsername.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confpass = txtConfpass.getText().toString();

                if (!fullname.equals("") && !username.equals("") && !email.equals("") && !password.equals("")) {
                    if (password.equals(confpass)) {
                        CreateDataToServer(fullname, username, email, password);
                    } else {
                        Toast.makeText(getApplicationContext(), "Gagal! Pasword tidak cocok!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Fields can not be empty!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void CreateDataToServer(final String fullname, final String username, final String email, final String password) {
        if (checkNetworkConnection()) {
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST, DbContract.SERVER_REGISTER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String resp = jsonObject.getString("server_response");
                                if (resp.equals("[{\"status\":\"OK\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Registrasi Berhasil", Toast.LENGTH_SHORT).show();
                                    Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(loginIntent);
                                } else if (resp.equals("[{\"status\":\"REGISTERED\"}]")) {
                                    Toast.makeText(getApplicationContext(), "Pengguna sudah terdaftar! \nCobalah masuk dengan akun ini.", Toast.LENGTH_SHORT).show();
                                }else {
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
                    params.put("fullname", fullname);
                    params.put("username", username);
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };

            VolleyConnection.getInstance(RegisterActivity.this).addToRequestQue(stringRequest);

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

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
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