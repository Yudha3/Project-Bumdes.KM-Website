package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle data = getIntent().getExtras();
        id_barang = data.getString("id_brg");
//        String barang = data.getString("barang");
//        int harga = Integer.parseInt(data.getString("harga"));
//        int stok = Integer.parseInt(data.getString("stok"));
//        String deskripsi = data.getString("deskripsi");
//        String gambar = IMG_URL + data.getString("gambar");

        txtid_brg = (TextView) findViewById(R.id.tv_id_brg_detail);
        txtbarang = (TextView) findViewById(R.id.tv_barang_detail);
        txtharga = (TextView) findViewById(R.id.tv_harga_detail);
        txtstok = (TextView) findViewById(R.id.tv_stok_detail);
        txtdeskripsi = (TextView) findViewById(R.id.tv_deskripsi_detail);
//        etQty = (EditText) findViewById(R.id.et_qty_detail);
        add = (Button) findViewById(R.id.btn_add_to_cart);
        increment = (Button) findViewById(R.id.btn_increment);
        decrement = (Button) findViewById(R.id.btn_decrement);
        qtyProduk = (TextView) findViewById(R.id.et_qty_detail);
        img_produk = (ImageView) findViewById(R.id.img_detail);

        getDetailProduk();

//        qty = Integer.parseInt(etQty.getText().toString());
//        qty = etQty.getText().toString();

//        qty = new Integer(etQty.getText().toString()).intValue();
////        id_barang = id_brg;

//        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
//        Call<ResponseProduk> getDetail = apiRequestData.getDetailProduk(id_brg);
//
//        getDetail.enqueue(new Callback<ResponseProduk>() {
//            @Override
//            public void onResponse(Call<ResponseProduk> call, Response<ResponseProduk> response) {
//                String pesan = response.body().getPesan();
//                if (pesan.equals("Data tersedia")) {
//                listData = response.body().getData();
//                String barang = listData.get(1).getBarang();
//                int harga = listData.get(2).getHg_jual();
//                int jml_stok =listData.get(3).getJml_stok();
//                String gambar = listData.get(4).getGambar();
//                String deskripsi = listData.get(5).getDeskripsi();
//
//                Glide.with(DetailProduk.this).load(IMG_URL+gambar).into(img_produk);
//                txtid_brg.setText(id_brg);
//                txtbarang.setText(barang);
//                txtharga.setText(harga);
//                txtstok.setText("Stok : " + jml_stok);
//                txtdeskripsi.setText(deskripsi);
//
//                setTitle(barang);
//                }else {
//                    Toast.makeText(DetailProduk.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseProduk> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });

//        Glide.with(DetailProduk.this).load(gambar).into(img_produk);
//        txtid_brg.setText(id_brg);
//        txtbarang.setText(barang);
//        txtharga.setText(formatRupiah(harga));
//        txtstok.setText("Stok : " + stok);
//        txtdeskripsi.setText(deskripsi);
//        setTitle(barang);

//        qtyProduk.setText(qty);

//        if (qty == 1) {
//            btnMin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(DetailProduk.this, "Minimum pembelian adalah 1...", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            btnMin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    qty = qty - 1;
//                    qtyProduk.setText(qty);
//                }
//            });
//            btnPlus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    qty++;
//                    qtyProduk.setText(qty);
//                }
//            });
//        }
//        if (qty == stok) {
//        btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    Toast.makeText(DetailProduk.this, "Stok barang tidak mencukupi..", Toast.LENGTH_SHORT).show();
//            }
//        });
//        } else if (qty < stok) {
//        btnPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                qty = qty + 1;
//                qtyProduk.setText(String.valueOf(qty));
//            }
//        });
//        }

//        if (qty == 1) {
//            btnMin.setVisibility(View.INVISIBLE);
//        }
//        if (qty == 1) {
//        btnMin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    Toast.makeText(DetailProduk.this, "Minimum pembelian adalah 1...", Toast.LENGTH_SHORT).show();
//            }
//        }); } else if (qty > 1) {
//            btnMin.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    qty = qty - 1;
//                   qtyProduk.setText(String.valueOf(qty));
//                }
//            });
//        }

//        subtotal = qty * harga;

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addToCart();
//                Toast.makeText(DetailProduk.this, "Add to Cart", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void increment(View view){
        if (qty == stok) {
            Toast.makeText(getApplicationContext(), "Max ", Toast.LENGTH_SHORT).show();
        } else {
            qty++;
            qtyProduk.setText(""+qty);
        }
    }

    public void decrement(View view) {
        if (qty == 1) {
            Toast.makeText(getApplicationContext(),"Min 1", Toast.LENGTH_SHORT).show();
        } else {
            qty--;
            qtyProduk.setText("" + qty);
        }
    }

    public void getDetailProduk() {
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
                }else if (pesan.equals("TIDAK ADA")){
                    Toast.makeText(DetailProduk.this, "Gagal mengambil data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseShowDetail> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addToCart(){
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> addCart = apiRequestData.addToCart(Global.id_user, id_barang, qty);
        addCart.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    Toast.makeText(DetailProduk.this, "Berhasil menambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("GAGAL")) {
                    Toast.makeText(DetailProduk.this, "Gagal menambahkan ke keranjang", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
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