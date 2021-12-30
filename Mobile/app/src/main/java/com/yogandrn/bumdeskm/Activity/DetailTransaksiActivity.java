
package com.yogandrn.bumdeskm.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yogandrn.bumdeskm.API.APIRequestData;
import com.yogandrn.bumdeskm.API.RetroServer;
import com.yogandrn.bumdeskm.Adapter.AdapterItemTransaksi;
import com.yogandrn.bumdeskm.Global;
import com.yogandrn.bumdeskm.Model.ModelListItemTransaksi;
import com.yogandrn.bumdeskm.Model.ResponseDetailTransaksi;
import com.yogandrn.bumdeskm.Model.ResponseModel;
import com.yogandrn.bumdeskm.R;
import com.yogandrn.bumdeskm.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiActivity extends AppCompatActivity {

    private TextView txtStatus, txtID, txtTgl, txtAlamat, txtPenerima, txtNoTelp, txtTotal, txtSubtotal, txtResi, txtOngkir, txtPengiriman;
    private Button btnKonfirmasi, btnBayar;
    private RecyclerView rvDetailPesanan;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelListItemTransaksi> listItem = new ArrayList<>();
    private SwipeRefreshLayout srlDetailTransaksi;
    private ProgressBar pbDetailTransaksi;
    private String id_transaksi;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Detail Pesanan");

//        sessionManager = new SessionManager(DetailTransaksiActivity.this);

        txtStatus = findViewById(R.id.txt_status_detail);
        txtAlamat = findViewById(R.id.txt_alamat_detail);
        txtID = findViewById(R.id.txt_id_transaksi_detail);
        txtTgl = findViewById(R.id.txt_tgl_detail);
        txtPenerima = findViewById(R.id.txt_penerima_detail);
        txtNoTelp = findViewById(R.id.txt_telp_detail);
        txtTotal = findViewById(R.id.txt_total_detail_transaksi);
        txtSubtotal = findViewById(R.id.txt_subtotal_detail_transaksi);
        txtResi = findViewById(R.id.txt_resi_detail);
        txtPengiriman = findViewById(R.id.txt_pengiriman_detail);
        txtOngkir = findViewById(R.id.txt_ongkir_detail);
        rvDetailPesanan = findViewById(R.id.rvDetailTransaksi);
        btnKonfirmasi = findViewById(R.id.btn_konfirmasi_detail);
        btnBayar = findViewById(R.id.btn_bayar_detail);
        srlDetailTransaksi = findViewById(R.id.srl_detail_transaksi);
        pbDetailTransaksi = findViewById(R.id.progress_detail_transaksi);

        Bundle data = getIntent().getExtras();
        id_transaksi = data.getString("id_transaksi");

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvDetailPesanan.setLayoutManager(layoutManager);
        getProduk();
        getDetailTransaksi();

        srlDetailTransaksi.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlDetailTransaksi.setRefreshing(true);
                getDetailTransaksi();
                getProduk();
                srlDetailTransaksi.setRefreshing(false);
            }
        });

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bayar = new Intent(DetailTransaksiActivity.this, BayarActivity.class);
                bayar.putExtra("id_transaksi", id_transaksi);
                startActivity(bayar);
            }
        });

        btnKonfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogConfirm = new AlertDialog.Builder(view.getContext());
                dialogConfirm.setCancelable(true);
                dialogConfirm.setTitle("Konfirmasi Pesanan");
                dialogConfirm.setMessage("Apakah Anda yakin untuk melakukan konfirmasi pesanan?");
                dialogConfirm.setPositiveButton("Konfirmasi", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirOder();
                    }
                });
                dialogConfirm.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialogConfirm.show();
            }
        });
    }

    private void getDetailTransaksi() {
        pbDetailTransaksi.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseDetailTransaksi> ambilData = apiRequestData.getDetailTransaksi(id_transaksi);
        ambilData.enqueue(new Callback<ResponseDetailTransaksi>() {
            @Override
            public void onResponse(Call<ResponseDetailTransaksi> call, Response<ResponseDetailTransaksi> response) {
                String tgl = response.body().getTgl_transaksi();
                int id_trx = response.body().getId_transaksi();
                int id_ongkir = response.body().getId_ongkir();
                int id_user = response.body().getId_user();
                int total = response.body().getTotal_transaksi();
                String penerima = response.body().getPenerima();
                String alamat = response.body().getAlamat();
                String no_telp = response.body().getNo_telp();
                String status = response.body().getStatus();
                String resi = response.body().getResi();

                if (id_ongkir == 1) {
                    txtOngkir.setText("Rp 30.000");
                    txtPengiriman.setText("Reguler");
                    txtSubtotal.setText(Global.formatRupiah(total - 30000));
                } else if (id_ongkir == 2) {
                    txtOngkir.setText("Rp 48.000");
                    txtPengiriman.setText("Express");
                    txtSubtotal.setText(Global.formatRupiah(total - 48000));
                }
                txtResi.setText(resi);
                txtStatus.setText(status);
                txtID.setText(String.valueOf(id_trx));
                txtTgl.setText(tgl);
                txtPenerima.setText(penerima);
                txtAlamat.setText(alamat);
                txtNoTelp.setText(no_telp);
                txtTotal.setText(Global.formatRupiah(total));

                if (status.equals("MENUNGGU PEMBAYARAN")) {
                    btnKonfirmasi.setVisibility(View.GONE);
                    btnBayar.setVisibility(View.VISIBLE);
                } else{
                    btnKonfirmasi.setVisibility(View.VISIBLE);
                    btnBayar.setVisibility(View.GONE);
                }

                pbDetailTransaksi.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseDetailTransaksi> call, Throwable t) {
                pbDetailTransaksi.setVisibility(View.GONE);
                Toast.makeText(DetailTransaksiActivity.this, "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProduk() {
        pbDetailTransaksi.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> ambilProduk = apiRequestData.getItemDetailTransaksi(id_transaksi);
        ambilProduk.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Data tersedia")) {
                    listItem = response.body().getItem_transaksi();
                    adapter = new AdapterItemTransaksi(DetailTransaksiActivity.this, listItem);
                    rvDetailPesanan.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    pbDetailTransaksi.setVisibility(View.GONE);
                } else if (pesan.equals("Data tidak tersedia")) {
                    pbDetailTransaksi.setVisibility(View.GONE);
                    rvDetailPesanan.setVisibility(View.INVISIBLE);
                } else if (pesan.equals("Not connected")) {
                    pbDetailTransaksi.setVisibility(View.GONE);
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pbDetailTransaksi.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void confirOder() {
        pbDetailTransaksi.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> callKonfirmasi = apiRequestData.konfirmasiPesanan(id_transaksi);
        callKonfirmasi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    pbDetailTransaksi.setVisibility(View.GONE);
                    Toast.makeText(DetailTransaksiActivity.this, "Pesanan Selesai", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(DetailTransaksiActivity.this, ListPesanan.class));
                    finish();
                } else if (pesan.equals("GAGAL")) {
                    pbDetailTransaksi.setVisibility(View.GONE);
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal melakukan konfirmasi", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("NOT CONNECTED")) {
                    pbDetailTransaksi.setVisibility(View.GONE);
                    Toast.makeText(DetailTransaksiActivity.this, "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pbDetailTransaksi.setVisibility(View.GONE);
                Toast.makeText(DetailTransaksiActivity.this, "Terjadi kesalahn :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
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