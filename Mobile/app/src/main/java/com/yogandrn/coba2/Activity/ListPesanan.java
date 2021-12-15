package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Adapter.AdapterTransaksi;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ModelTransaksi;
import com.yogandrn.coba2.Model.ResponseTransaksi;
import com.yogandrn.coba2.R;

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
    private Button btnBelanja;
    private SwipeRefreshLayout srlTransaksi;
    private ProgressBar pbTransaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_pesanan);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Pesanan Saya");

        txtEmpty = findViewById(R.id.txt_empty_pesanan);
        btnBelanja = findViewById(R.id.btnBelanja_pesanan);
        pbTransaksi = findViewById(R.id.progress_list_pesanan);
        srlTransaksi = findViewById(R.id.srl_transaksi);
        rvPesanan = findViewById(R.id.rvPesanan);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        btnBelanja.setVisibility(View.GONE);
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
    }

    public void getTransaksi(){
        pbTransaksi.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseTransaksi> getPesanan = apiRequestData.readTransaksi(Global.id_user);
        getPesanan.enqueue(new Callback<ResponseTransaksi>() {
            @Override
            public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Data tersedia")) {
                listPesanan = response.body().getData();

                adapterPesanan = new AdapterTransaksi(ListPesanan.this, listPesanan);
                rvPesanan.setAdapter(adapterPesanan);
                adapterPesanan.notifyDataSetChanged();
                pbTransaksi.setVisibility(View.GONE);
                } else if (pesan.equals("Data tidak tersedia")) {
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