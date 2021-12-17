package com.yogandrn.coba2.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Adapter.AdapterProduk;
import com.yogandrn.coba2.Model.ModelKeranjang;
import com.yogandrn.coba2.Model.ModelProduk;
import com.yogandrn.coba2.Model.ResponseProduk;
import com.yogandrn.coba2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView rvProduk;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelProduk> listData = new ArrayList<>();
    private TextView txtKeyword;
    private SwipeRefreshLayout srlSearch;
    private ProgressBar pbSearch;
    private String keyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Hasil pencarian");

        Bundle data = getIntent().getExtras();
        keyword = data.getString("keyword");

        srlSearch = findViewById(R.id.srl_search);
        pbSearch = findViewById(R.id.progress_search);
        txtKeyword = findViewById(R.id.txt_keyword_search);
        rvProduk = findViewById(R.id.recycler_search);

        layoutManager = new GridLayoutManager(this, 2);
        rvProduk.setLayoutManager(layoutManager);

        tampilHasilSearch();

        srlSearch.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlSearch.setRefreshing(true);
                tampilHasilSearch();
                srlSearch.setRefreshing(false);
            }
        });
    }

    public void tampilHasilSearch() {
        pbSearch.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseProduk> callSearch = apiRequestData.searchProduk(keyword);
        callSearch.enqueue(new Callback<ResponseProduk>() {
            @Override
            public void onResponse(Call<ResponseProduk> call, Response<ResponseProduk> response) {
                int kode = response.body().getKode();
                String pesan = response.body().getPesan();
                if (kode == 1 ) {
                listData = response.body().getData();

                adapter = new AdapterProduk(SearchActivity.this, listData);
                rvProduk.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                pbSearch.setVisibility(View.INVISIBLE);
                txtKeyword.setText("\"" + keyword + "\"");
                } else {
                    txtKeyword.setText("\"" + keyword + "\"");
                    txtKeyword.setVisibility(View.VISIBLE);
                    rvProduk.setVisibility(View.INVISIBLE);
                    pbSearch.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<ResponseProduk> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
                pbSearch.setVisibility(View.INVISIBLE);
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