package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Adapter.AdapterProduk;
import com.yogandrn.coba2.Model.ProdukModel;
import com.yogandrn.coba2.Model.ResponseModel;
import com.yogandrn.coba2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KatalogActivity extends AppCompatActivity {

    private RecyclerView rvProduk;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ProdukModel> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        layoutManager =  new GridLayoutManager(KatalogActivity.this, 2);
        rvProduk = findViewById(R.id.recycler_katalog);

//        layoutManager =  new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rvProduk.setLayoutManager(layoutManager);
        retrieveData();
    }

    public void retrieveData() {
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> getData = apiRequestData.ReadData();

        getData.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                listData = response.body().getData();

                adapter = new AdapterProduk(KatalogActivity.this, listData);
                rvProduk.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(KatalogActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }
}