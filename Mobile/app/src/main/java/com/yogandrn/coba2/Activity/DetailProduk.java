package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ModelProduk;
import com.yogandrn.coba2.Model.ResponseModel;
import com.yogandrn.coba2.Model.ResponseProduk;
import com.yogandrn.coba2.Model.ResponseShowDetail;
import com.yogandrn.coba2.R;
import com.yogandrn.coba2.SessionManager;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduk extends AppCompatActivity {
    private TextView txtid_brg;
    private TextView txtbarang;
    private TextView txtharga;
    private TextView txtdeskripsi;
    private TextView txtstok;
    private TextView btnPlus;
    private TextView btnMin;
    private TextView qtyProduk;
    private int qty = 1;
//    String qty;
    private Button add, increment, decrement;
    private EditText etQty;
    private ImageView img_produk;
    private List<ModelProduk> listData;
    private String IMG_PRODUK_URL = "http://undeveloppedcity.000webhostapp.com/android/img/produk/";
    private String id_barang, barang, deskripsi, gambar;
    private int stok, harga;
    private int subtotal = 0;
    private SwipeRefreshLayout srlDetail;
    private ProgressBar pbDetail;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sessionManager = new SessionManager(DetailProduk.this);
        Bundle data = getIntent().getExtras();
        id_barang = data.getString("id_brg");

        txtid_brg = (TextView) findViewById(R.id.tv_id_brg_detail);
        txtbarang = (TextView) findViewById(R.id.tv_barang_detail);
        txtharga = (TextView) findViewById(R.id.tv_harga_detail);
        txtstok = (TextView) findViewById(R.id.tv_stok_detail);
        txtdeskripsi = (TextView) findViewById(R.id.tv_deskripsi_detail);
        add = (Button) findViewById(R.id.btn_add_to_cart);
        increment = (Button) findViewById(R.id.btn_increment);
        decrement = (Button) findViewById(R.id.btn_decrement);
        qtyProduk = (TextView) findViewById(R.id.et_qty_detail);
        img_produk = (ImageView) findViewById(R.id.img_detail);
        pbDetail = (ProgressBar) findViewById(R.id.progress_detail_produk);
        srlDetail = (SwipeRefreshLayout) findViewById(R.id.srl_detail_produk);

        getDetailProduk();

        srlDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlDetail.setRefreshing(true);
                getDetailProduk();
                srlDetail.setRefreshing(false);
            }
        });



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
            }
        });

    }

    public void increment(View view){
        if (qty == stok) {
            Toast.makeText(getApplicationContext(), "Stok tidak mencukupi", Toast.LENGTH_SHORT).show();
        } else {
            qty++;
            qtyProduk.setText(""+qty);
        }
    }

    public void decrement(View view) {
        if (qty == 1) {
            Toast.makeText(getApplicationContext(),"Minimal jumlah pembelian adalah 1", Toast.LENGTH_SHORT).show();
        } else {
            qty--;
            qtyProduk.setText("" + qty);
        }
    }

    public void getDetailProduk() {
        pbDetail.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseShowDetail> getDetail = apiRequestData.getDetailProduk(id_barang);

        getDetail.enqueue(new Callback<ResponseShowDetail>() {
            @Override
            public void onResponse(Call<ResponseShowDetail> call, Response<ResponseShowDetail> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    barang = response.body().getBarang();
                    stok = response.body().getStok();
                    harga = response.body().getHarga();
                    deskripsi = response.body().getDeskripsi();
                    gambar = response.body().getGambar();

                    Glide.with(DetailProduk.this).load(Global.IMG_PRODUK_URL + gambar).into(img_produk);
                    txtid_brg.setText(id_barang);
                    txtbarang.setText(barang);
                    txtharga.setText(formatRupiah(harga));
                    txtstok.setText("Stok : " + stok);
                    txtdeskripsi.setText(deskripsi);
                    setTitle(barang);
                    pbDetail.setVisibility(View.GONE);
                }else if (pesan.equals("TIDAK ADA")){
                    pbDetail.setVisibility(View.GONE);
                    Toast.makeText(DetailProduk.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseShowDetail> call, Throwable t) {
                pbDetail.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addToCart(){
        pbDetail.setVisibility(View.VISIBLE);
        String id = String.valueOf(sessionManager.getSessionID());
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> addCart = apiRequestData.addToCart(id, id_barang, qty);
        addCart.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    Global gb = new Global();
                    gb.getTotal(id);
                    Toast.makeText(DetailProduk.this, "Berhasil menambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("GAGAL")) {
                    Toast.makeText(DetailProduk.this, "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                }
                pbDetail.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                pbDetail.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // menampilkan format harga rupiah
    private String formatRupiah(int number) {
        Locale localeID = new Locale("IND", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
        String formatRupiah = numberFormat.format(number);
        String[] split = formatRupiah.split(",");
        int length = split[0].length();
        return split[0].substring(0,2) + " " + split[0].substring(2,length);
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

    private String formatRupiah(String number) {
        Locale localeID = new Locale("IND", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
        String formatRupiah = numberFormat.format(number);
        String[] split = formatRupiah.split(",");
        int length = split[0].length();
        return split[0].substring(0,2) + "." + split[0].substring(2,length);
    }
}