
package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ResponseDetailTransaksi;
import com.yogandrn.coba2.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailTransaksiActivity extends AppCompatActivity {

    private TextView txtStatus, txtID, txtTgl, txtAlamat, txtPenerima, txtNoTelp, txtTotal, txtSubtotal, txtResi, txtOngkir, txtPengiriman;
    private Button btnKonfirmasi;
    private RecyclerView rvDetailPesanan;
    private SwipeRefreshLayout srlDetailTransaksi;
    private ProgressBar pbDetailTransaksi;
    private String id_transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_transaksi);
        setTitle("Detail Pesanan");

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
        btnKonfirmasi = findViewById(R.id.btn_konfirmasi_detail);
        srlDetailTransaksi = findViewById(R.id.srl_detail_transaksi);
        pbDetailTransaksi = findViewById(R.id.progress_detail_transaksi);

        Bundle data = getIntent().getExtras();
        id_transaksi = data.getString("id_transaksi");

        getDetailTransaksi();
    }

    private void getDetailTransaksi() {
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
                    txtPengiriman.setText("Reguler");
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
            }

            @Override
            public void onFailure(Call<ResponseDetailTransaksi> call, Throwable t) {
                Toast.makeText(DetailTransaksiActivity.this, "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}