package com.yogandrn.bumdeskm.Activity;

import static com.yogandrn.bumdeskm.Global.formatRupiah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yogandrn.bumdeskm.API.APIRequestData;
import com.yogandrn.bumdeskm.API.RetroServer;
import com.yogandrn.bumdeskm.Adapter.AdapterKeranjang;
import com.yogandrn.bumdeskm.Global;
import com.yogandrn.bumdeskm.Model.ModelKeranjang;
import com.yogandrn.bumdeskm.Model.ResponseKeranjang;
import com.yogandrn.bumdeskm.Model.ResponseModel;
import com.yogandrn.bumdeskm.R;
import com.yogandrn.bumdeskm.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuatPreorderActivity extends AppCompatActivity {

    private RecyclerView rvOrder;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelKeranjang> listitem = new ArrayList<>();
    private Button btnOrder;
    private RadioGroup rgOngkir, rgBayar;
    private RadioButton ongkir1, ongkir2;
    private TextView txtTotal, txtSubtotal, txtOngkir, txtTotal2, txtSubtotal2, txtOngkir2;
    private EditText etPenerima, etAlamat, etNoTelp;
    private int subtotalitem ;
    private int ongkir = 30000;
    private String id_ongkir = "1";
    private String penerima, alamat, no_telp;
    private SwipeRefreshLayout srlOrder;
    private ProgressBar pbOrder;
    private View vOrder;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buat_preorder);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Preorder");

        sessionManager = new SessionManager(BuatPreorderActivity.this);
        Global gb = new Global();
        subtotalitem = gb.getTotalPreorder(String.valueOf(sessionManager.getSessionID()));

        txtSubtotal = findViewById(R.id.txt_total2_item_preorder);
        txtSubtotal2 = findViewById(R.id.txt_total2_item_preorder);
        txtOngkir = findViewById(R.id.txt_ongkir_preorder);
        txtOngkir2 = findViewById(R.id.txt_ongkir2_preorder);
        txtTotal = findViewById(R.id.txt_total_buat_preorder);
        txtTotal2 = findViewById(R.id.txt_total2_buat_preorder);
        etPenerima = findViewById(R.id.et_penerima_preorder);
        etAlamat = findViewById(R.id.et_alamat_preorder);
        etNoTelp = findViewById(R.id.et_notelp_preorder);
        rgOngkir = findViewById(R.id.radioGroup_ongkir_preorder);
        rgBayar = findViewById(R.id.radioGroup_bayar_preorder);
        ongkir1 = findViewById(R.id.id_ongkir_01);
        ongkir2 = findViewById(R.id.id_ongkir_02);
        btnOrder = findViewById(R.id.btn_transaksi_preorder);
        pbOrder = findViewById(R.id.progress_buat_preorder);
        vOrder = findViewById(R.id.view_buat_preorder);
        srlOrder = findViewById(R.id.srl_buat_preorder);
        rvOrder = findViewById(R.id.rvBuatPreorder);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        txtOngkir.setText(formatRupiah(ongkir));
        txtOngkir2.setText(formatRupiah(ongkir));
        rvOrder.setLayoutManager(layoutManager);

        getItem();

        srlOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlOrder.setRefreshing(true);
                getItem();
                gb.getTotalPreorder(String.valueOf(sessionManager.getSessionID()));
                srlOrder.setRefreshing(false);
            }
        });

        txtSubtotal.setText(formatRupiah(subtotalitem));
        txtSubtotal2.setText(formatRupiah(subtotalitem));
        txtTotal.setText(formatRupiah(subtotalitem+ongkir));
        txtTotal2.setText(formatRupiah(subtotalitem+ongkir));

        ongkir1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ongkir1.isChecked()) {
                    id_ongkir = "1";
                    ongkir = 30000;
                    txtOngkir.setText(formatRupiah(30000));
                    txtOngkir2.setText(formatRupiah(30000));
                    txtTotal2.setText(formatRupiah(subtotalitem+30000));
                    txtTotal.setText(formatRupiah(subtotalitem+30000));
                }
            }
        });
        ongkir2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ongkir2.isChecked()) {
                    id_ongkir = "2";
                    ongkir = 48000;
                    txtOngkir.setText(formatRupiah(48000));
                    txtOngkir2.setText(formatRupiah(48000));
                    txtTotal2.setText(formatRupiah(subtotalitem+48000));
                    txtTotal.setText(formatRupiah(subtotalitem+48000));
                }
            }
        });

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPenerima.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Kolom Alamat wajib diisi", Toast.LENGTH_SHORT).show();
                    etPenerima.setError("Wajib diisi!");
                } else if (etAlamat.getText().toString().equals("")){
                    etAlamat.setError("Wajib diisi!");
                } else if (etNoTelp.getText().toString().equals("")){
                    etNoTelp.setError("Wajib diisi!");
                } else {
                    alamat = etAlamat.getText().toString();
                    no_telp = etNoTelp.getText().toString();
                    penerima = etPenerima.getText().toString();
                    buatPreorder();
                }
            }
        });
    }

    private void getItem() {
        vOrder.setVisibility(View.VISIBLE);
        pbOrder.setVisibility(View.VISIBLE);

        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseKeranjang> getKeranjang = apiRequestData.readCartPreorder(String.valueOf(sessionManager.getSessionID()));

        getKeranjang.enqueue(new Callback<ResponseKeranjang>() {
            @Override
            public void onResponse(Call<ResponseKeranjang> call, Response<ResponseKeranjang> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Data tersedia")){
                    listitem = response.body().getData();

                    adapter = new AdapterKeranjang(BuatPreorderActivity.this, listitem);
                    rvOrder.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                } else if (pesan.equals("Data tidak tersedia")) {
                    Toast.makeText(BuatPreorderActivity.this, "Tidak ada produk dalam keranjang", Toast.LENGTH_SHORT).show();
                }
                vOrder.setVisibility(View.GONE);
                pbOrder.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseKeranjang> call, Throwable t) {
                vOrder.setVisibility(View.GONE);
                pbOrder.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Terjadi Kesalahan : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buatPreorder() {
        vOrder.setVisibility(View.VISIBLE);
        pbOrder.setVisibility(View.VISIBLE);
        String id = String.valueOf(sessionManager.getSessionID());
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> transaksi = apiRequestData.buatPreorder(id, penerima, alamat, no_telp, id_ongkir);
        transaksi.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();

                if (pesan.equals("BERHASIL")) {
                    vOrder.setVisibility(View.GONE);
                    pbOrder.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Berhasil melakukan Preorder", Toast.LENGTH_SHORT).show();
                    Intent selesai = new Intent(BuatPreorderActivity.this, PetunjukPreorderActivity.class);
//                    selesai.putExtra("id_transaksi", String.valueOf(id_transaksi));
//                    selesai.putExtra("total", subtotalitem + ongkir);
                    startActivity(selesai);
                    finish();
                } else if (pesan.equals("GAGAL")) {
                    vOrder.setVisibility(View.GONE);
                    pbOrder.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Gagal melakukan transaksi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                vOrder.setVisibility(View.GONE);
                pbOrder.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
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