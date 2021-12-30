package com.yogandrn.coba2.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ResponseUser;
import com.yogandrn.coba2.R;
import com.yogandrn.coba2.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahFotoProfil extends AppCompatActivity {

    SessionManager sessionManager;
    private CircleImageView imgPath;
    private TextView btnChoose;
    private Button btnSimpan;
    private ProgressBar pbEditFoto;
    private int IMG_REQUEST = 23;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_foto_profil);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Ubah Foto Profil");

        sessionManager = new SessionManager(UbahFotoProfil.this);

        imgPath = findViewById(R.id.img_ubah_foto);
        btnChoose = findViewById(R.id.btn_choose_foto);
        btnSimpan = findViewById(R.id.btn_simpan_foto);
        pbEditFoto = findViewById(R.id.progress_edit_foto);

        getFoto();

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMG_REQUEST);
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ubahFotoProfil();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ( resultCode == RESULT_OK && requestCode == IMG_REQUEST && data != null) {

            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgPath.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void ubahFotoProfil(){
        pbEditFoto.setVisibility(View.VISIBLE);
        String id = String.valueOf(sessionManager.getSessionID());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 45, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);

        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseUser> callSimpan = apiRequestData.updateFotoProfil(id, encodedImage);
        callSimpan.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    Toast.makeText(UbahFotoProfil.this, "Foto profil berhasil diubah", Toast.LENGTH_SHORT).show();
                    pbEditFoto.setVisibility(View.GONE);
                    startActivity(new Intent(UbahFotoProfil.this, ProfilActivity.class));
                    finish();
                } else if (pesan.equals("GAGAL")) {
                    Toast.makeText(UbahFotoProfil.this, "Gagal mengubah data\nCobalah beberapa saat lagi", Toast.LENGTH_SHORT).show();
                    pbEditFoto.setVisibility(View.GONE);
                } else if (pesan.equals("NOT CONNECTED")) {
                    Toast.makeText(UbahFotoProfil.this, "Gagal menghubungi server\nPeriksa koneksi internet Anda", Toast.LENGTH_SHORT).show();
                    pbEditFoto.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                pbEditFoto.setVisibility(View.GONE);
                Toast.makeText(UbahFotoProfil.this, "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFoto(){
        pbEditFoto.setVisibility(View.VISIBLE);
        String id = String.valueOf(sessionManager.getSessionID());
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseUser> callFoto = apiRequestData.getUser(id);
        callFoto.enqueue(new Callback<ResponseUser>() {
            @Override
            public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                String foto = response.body().getFoto_profil();
                Glide.with(UbahFotoProfil.this).load(Global.IMG_USER_URL + foto).circleCrop().into(imgPath);
                pbEditFoto.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseUser> call, Throwable t) {
                Toast.makeText(UbahFotoProfil.this, "Terjadi Kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                pbEditFoto.setVisibility(View.GONE);
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