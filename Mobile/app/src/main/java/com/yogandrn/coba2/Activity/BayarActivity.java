package com.yogandrn.coba2.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Model.ResponseModel;
import com.yogandrn.coba2.R;
import com.yogandrn.coba2.SessionManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BayarActivity extends AppCompatActivity {

    private Button btnUpload, btnChoose;
    private TextView txtDefault;
    private ImageView imgBayar;
    private String imagePath, id_transaksi;
    final int REQUEST_GALLERY = 9544;
    private int IMG_REQUEST = 23;
    private Bitmap bitmap;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bayar);

        Bundle data = getIntent().getExtras();
        id_transaksi = data.getString("id_transaksi");
//        sessionManager = new SessionManager(BayarActivity.this);

        btnUpload = findViewById(R.id.btn_upload_bayar);
        btnChoose = findViewById(R.id.btn_choose_file);
        imgBayar = findViewById(R.id.img_bukti_bayar);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, IMG_REQUEST);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadBuktiBayar();
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
                imgBayar.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadBuktiBayar() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 60, byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageInByte, Base64.DEFAULT);

        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> callUpload = apiRequestData.uploadBayar(encodedImage, id_transaksi);
        callUpload.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    Toast.makeText(BayarActivity.this, "Berhasil upload", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BayarActivity.this, ListPesanan.class));
                    finish();
                } else if (pesan.equals("GAGAL")) {
                    Toast.makeText(BayarActivity.this, "Gagal upload", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("NOT CONNECTED")) {
                    Toast.makeText(BayarActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(BayarActivity.this, "Error :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection, null, null,null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }

    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == RESULT_OK) {
//            if (requestCode == REQUEST_GALLERY) {
//                Uri dataImage = data.getData();
//                String[] imageprojection = {MediaStore.Images.Media.DATA};
//                Cursor cursor = getContentResolver().query(dataImage, imageprojection, null, null, null );
//
//                if (cursor != null) {
//                    cursor.moveToFirst();
//                    int indexImage = cursor.getColumnIndex(imageprojection[0]);
//                    part_image = cursor.getString(indexImage);
//
//                    if (part_image != null) {
//                        File image = new File(part_image);
//                        imgBayar.setImageBitmap(BitmapFactory.decodeFile(image.getAbsolutePath()));
////                        txtDefault.setVisibility(View.GONE);
//                    }
//                }
//            }
//        }
//    }
}