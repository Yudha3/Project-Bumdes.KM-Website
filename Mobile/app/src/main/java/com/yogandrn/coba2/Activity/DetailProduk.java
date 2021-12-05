package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yogandrn.coba2.R;

import java.text.NumberFormat;
import java.util.Locale;

public class DetailProduk extends AppCompatActivity {
    private TextView id_brg, barang, harga, deskripsi, stok;
    private ImageView img_produk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        id_brg = (TextView) findViewById(R.id.tv_id_brg_detail);
        barang = (TextView) findViewById(R.id.tv_barang_detail);
        harga = (TextView) findViewById(R.id.tv_harga_detail);
        stok = (TextView) findViewById(R.id.tv_stok_detail);
        deskripsi = (TextView) findViewById(R.id.tv_deskripsi_detail);
        img_produk = (ImageView) findViewById(R.id.img_detail);

        Bundle data = getIntent().getExtras();

        Glide.with(DetailProduk.this).load(data.getString("gambar")).into(img_produk);
        id_brg.setText(data.getString("id_brg"));
        barang.setText(data.getString("barang"));
        harga.setText(data.getString("harga"));
        stok.setText("Stok : " + data.getString("stok"));
        deskripsi.setText(data.getString("deskripsi"));

        setTitle(data.getString("barang"));
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