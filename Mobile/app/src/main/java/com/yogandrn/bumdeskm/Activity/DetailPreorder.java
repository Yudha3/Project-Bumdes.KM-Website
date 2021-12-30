package com.yogandrn.bumdeskm.Activity;

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
import com.yogandrn.bumdeskm.Adapter.AdapterItemPreorder;
import com.yogandrn.bumdeskm.Global;
import com.yogandrn.bumdeskm.Model.ModelListItemPreorder;
import com.yogandrn.bumdeskm.Model.ResponseDetailPreorder;
import com.yogandrn.bumdeskm.Model.ResponsePreorder;
import com.yogandrn.bumdeskm.R;
import com.yogandrn.bumdeskm.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPreorder extends AppCompatActivity {

    private TextView txtStatus, txtID, txtTgl, txtAlamat, txtPenerima, txtNoTelp, txtTotal, txtSubtotal, txtResi, txtOngkir, txtPengiriman;
    private Button btnBack;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelListItemPreorder> listItem = new ArrayList<>();
    private SwipeRefreshLayout srlDetailPreorder;
    private ProgressBar pbDetailPreorder;
    private String id_preorder;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_preorder);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Detail Preorder");

        txtStatus = findViewById(R.id.txt_status_detail_preorder);
        txtAlamat = findViewById(R.id.txt_alamat_detail_preorder);
        txtID = findViewById(R.id.txt_id_preorder_detail);
        txtTgl = findViewById(R.id.txt_tgl_detail_preorder);
        txtPenerima = findViewById(R.id.txt_penerima_detail_preorder);
        txtNoTelp = findViewById(R.id.txt_telp_detail_preorder);
        txtTotal = findViewById(R.id.txt_total_detail_preorder);
        txtSubtotal = findViewById(R.id.txt_subtotal_detail_preorder);
        txtPengiriman = findViewById(R.id.txt_pengiriman_detail_preorder);
        txtOngkir = findViewById(R.id.txt_ongkir_detail_preorder);
        recyclerView = findViewById(R.id.rvDetailPreorder);
        btnBack = findViewById(R.id.btn_back_preorder);
        srlDetailPreorder = findViewById(R.id.srl_detail_preorder);
        pbDetailPreorder = findViewById(R.id.progress_detail_preorder);

        Bundle data = getIntent().getExtras();
        id_preorder = data.getString("id_preorder");

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        getProduk();
        getDetailPreorder();

        srlDetailPreorder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlDetailPreorder.setRefreshing(true);
                getDetailPreorder();
                getProduk();
                srlDetailPreorder.setRefreshing(false);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    private void getDetailPreorder() {
        pbDetailPreorder.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseDetailPreorder> callCall = apiRequestData.getDetailPreorder(id_preorder);
        callCall.enqueue(new Callback<ResponseDetailPreorder>() {
            @Override
            public void onResponse(Call<ResponseDetailPreorder> call, Response<ResponseDetailPreorder> response) {
                String tgl = response.body().getTgl_preorder();
                int id_pre = response.body().getId_preorder();
                int id_ongkir = response.body().getId_ongkir();
                int id_user = response.body().getId_user();
                int total = response.body().getTotal_preorder();
                String penerima = response.body().getPenerima();
                String alamat = response.body().getAlamat();
                String no_telp = response.body().getNo_telp();
                String status = response.body().getStatus();

                if (id_ongkir == 1) {
                    txtOngkir.setText("Rp 30.000");
                    txtPengiriman.setText("Reguler");
                    txtSubtotal.setText(Global.formatRupiah(total - 30000));
                } else if (id_ongkir == 2) {
                    txtOngkir.setText("Rp 48.000");
                    txtPengiriman.setText("Express");
                    txtSubtotal.setText(Global.formatRupiah(total - 48000));
                }

                txtStatus.setText(status);
                txtID.setText(String.valueOf(id_pre));
                txtTgl.setText(tgl);
                txtPenerima.setText(penerima);
                txtAlamat.setText(alamat);
                txtNoTelp.setText(no_telp);
                txtTotal.setText(Global.formatRupiah(total));

                pbDetailPreorder.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseDetailPreorder> call, Throwable t) {
                pbDetailPreorder.setVisibility(View.GONE);
                Toast.makeText(DetailPreorder.this, "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProduk() {
        pbDetailPreorder.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponsePreorder> callItem = apiRequestData.getItemDetailPreorder(id_preorder);
        callItem.enqueue(new Callback<ResponsePreorder>() {
            @Override
            public void onResponse(Call<ResponsePreorder> call, Response<ResponsePreorder> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Data tersedia")) {
                    listItem = response.body().getItem_preorder();
                    adapter = new AdapterItemPreorder(DetailPreorder.this, listItem);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    pbDetailPreorder.setVisibility(View.GONE);
                } else if (pesan.equals("Data tidak tersedia")) {
                    pbDetailPreorder.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.INVISIBLE);
                } else if (pesan.equals("Not connected")) {
                    pbDetailPreorder.setVisibility(View.GONE);
                    Toast.makeText(DetailPreorder.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponsePreorder> call, Throwable t) {
                pbDetailPreorder.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
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