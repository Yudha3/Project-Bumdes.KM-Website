package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Adapter.AdapterProduk;
import com.yogandrn.coba2.Model.ModelProduk;
import com.yogandrn.coba2.Model.ResponseProduk;
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
    private List<ModelProduk> listData = new ArrayList<>();
    private SwipeRefreshLayout srlKatalog;
    private ProgressBar pbKatalog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Produk Kami");

        srlKatalog = findViewById(R.id.srl_katalog);
        pbKatalog = findViewById(R.id.progress_katalog);
        layoutManager =  new GridLayoutManager(KatalogActivity.this, 2);
        rvProduk = findViewById(R.id.recycler_katalog);

//        layoutManager =  new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        rvProduk.setLayoutManager(layoutManager);
        retrieveData();

        srlKatalog.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlKatalog.setRefreshing(true);
                retrieveData();
                srlKatalog.setRefreshing(false);
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

    public void retrieveData() {
        pbKatalog.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseProduk> getData = apiRequestData.ReadData();

        getData.enqueue(new Callback<ResponseProduk>() {
            @Override
            public void onResponse(Call<ResponseProduk> call, Response<ResponseProduk> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();

                listData = response.body().getData();

                adapter = new AdapterProduk(KatalogActivity.this, listData);
                rvProduk.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                pbKatalog.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseProduk> call, Throwable t) {
                pbKatalog.setVisibility(View.GONE);
                Toast.makeText(KatalogActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
            }
        });
    }

}