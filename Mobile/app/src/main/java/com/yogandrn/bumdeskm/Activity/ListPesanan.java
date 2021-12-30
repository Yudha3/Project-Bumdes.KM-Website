package com.yogandrn.bumdeskm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yogandrn.bumdeskm.API.APIRequestData;
import com.yogandrn.bumdeskm.API.RetroServer;
import com.yogandrn.bumdeskm.Adapter.AdapterTransaksi;
import com.yogandrn.bumdeskm.Model.ModelTransaksi;
import com.yogandrn.bumdeskm.Model.ResponseTransaksi;
import com.yogandrn.bumdeskm.R;
import com.yogandrn.bumdeskm.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPesanan extends AppCompatActivity {

    private RecyclerView rvPesanan;
    private RecyclerView.Adapter adapterPesanan;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelTransaksi> listPesanan =  new ArrayList<>();
    private TextView txtEmpty;
    private Button btnBelanja, btnBack;
    private SwipeRefreshLayout srlTransaksi;
    private ProgressBar pbTransaksi;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pesanan);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Pesanan Saya");

//        sessionManager = new SessionManager(ListPesanan.this);

        txtEmpty = (TextView) findViewById(R.id.txt_empty_pesanan);
        btnBelanja = findViewById(R.id.btnBelanja_pesanan);
        btnBack = findViewById(R.id.btnBack_pesanan);
        pbTransaksi = findViewById(R.id.progress_list_pesanan);
        srlTransaksi = findViewById(R.id.srl_transaksi);
        rvPesanan = findViewById(R.id.rvPesanan);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

//        btnBelanja.setVisibility(View.GONE);
        rvPesanan.setLayoutManager(layoutManager);
        getTransaksi();

        srlTransaksi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlTransaksi.setRefreshing(true);
                getTransaksi();
                srlTransaksi.setRefreshing(false);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(ListPesanan.this, MainActivity.class);
                startActivity(back);
            }
        });

        btnBelanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent belanja = new Intent(ListPesanan.this, KatalogActivity.class);
                startActivity(belanja);
            }
        });
    }

    public void getTransaksi(){
        pbTransaksi.setVisibility(View.VISIBLE);
        sessionManager = new SessionManager(ListPesanan.this);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseTransaksi> getPesanan = apiRequestData.readTransaksi(String.valueOf(sessionManager.getSessionID()));
        getPesanan.enqueue(new Callback<ResponseTransaksi>() {
            @Override
            public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                String pesan = response.body().getPesan();
                int kode = response.body().getKode();
                if (kode == 1) {
                listPesanan = response.body().getData();

                adapterPesanan = new AdapterTransaksi(ListPesanan.this, listPesanan);
                rvPesanan.setAdapter(adapterPesanan);
                adapterPesanan.notifyDataSetChanged();
                pbTransaksi.setVisibility(View.GONE);
                } else if (kode == 0) {
                  txtEmpty.setVisibility(View.VISIBLE);
                  btnBelanja.setVisibility(View.VISIBLE);
                  pbTransaksi.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ResponseTransaksi> call, Throwable t) {
                Toast.makeText(ListPesanan.this, "Terjadi kesalahan :\n" +t.getMessage(), Toast.LENGTH_SHORT).show();
                pbTransaksi.setVisibility(View.GONE);
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