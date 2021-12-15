package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Adapter.AdapterKeranjang;
import com.yogandrn.coba2.Adapter.AdapterProduk;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ModelKeranjang;
import com.yogandrn.coba2.Model.ResponseKeranjang;
import com.yogandrn.coba2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeranjangActivity extends AppCompatActivity {

    private RecyclerView rvKeranjang;
    private RecyclerView.Adapter adapterKeranjang;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelKeranjang> listKeranjang = new ArrayList<>();
    private Button btnBelanja, btnOrder;
    private TextView txtEmpty;
    private SwipeRefreshLayout srlKeranjang;
    private ProgressBar pbKeranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Keranjang Belanja");

        pbKeranjang = findViewById(R.id.progress_keranjang);
        srlKeranjang = findViewById(R.id.srl_keranjang);
        txtEmpty = findViewById(R.id.txt_empty_keranjang);
        btnBelanja = findViewById(R.id.btnBelanja_keranjang);
        btnOrder = findViewById(R.id.btn_order_keranjang);
        rvKeranjang = findViewById(R.id.rvKeranjang);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        rvKeranjang.setLayoutManager(layoutManager);

        retrieveCart();

        srlKeranjang.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlKeranjang.setRefreshing(true);
                retrieveCart();
                srlKeranjang.setRefreshing(false);
            }
        });

        btnBelanja.setVisibility(View.GONE);
        btnBelanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent belanja = new Intent(KeranjangActivity.this, KatalogActivity.class);
                startActivity(belanja);
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pbKeranjang.setVisibility(View.VISIBLE);
                Intent order = new Intent(KeranjangActivity.this, OrderActivity.class);
                startActivity(order); pbKeranjang.setVisibility(View.GONE);
            }
        });

    }

    public void retrieveCart() {
        pbKeranjang.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseKeranjang> getKeranjang = apiRequestData.readCart(Global.id_user);

        getKeranjang.enqueue(new Callback<ResponseKeranjang>() {
            @Override
            public void onResponse(Call<ResponseKeranjang> call, Response<ResponseKeranjang> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Data tersedia")){
                    listKeranjang = response.body().getData();

                    adapterKeranjang = new AdapterKeranjang(KeranjangActivity.this, listKeranjang);
                    rvKeranjang.setAdapter(adapterKeranjang);
                    adapterKeranjang.notifyDataSetChanged();
                    pbKeranjang.setVisibility(View.GONE);
                } else if (pesan.equals("Data tidak tersedia")) {
                    txtEmpty.setVisibility(View.VISIBLE);
                    rvKeranjang.setVisibility(View.GONE);
                    btnBelanja.setVisibility(View.VISIBLE);
                    btnOrder.setVisibility(View.GONE);
                    pbKeranjang.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseKeranjang> call, Throwable t) {
                pbKeranjang.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Terjadi Kesalahan : " + t.getMessage(), Toast.LENGTH_SHORT).show();
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