package com.yogandrn.coba2.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.yogandrn.coba2.R;

import java.text.NumberFormat;
import java.util.Locale;

public class PertunjukBayarActivity extends AppCompatActivity {

    private TextView txtTotal;
    private Button btnBayar, btnLihatPesanan;
    private String id_transaksi;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertunjuk_bayar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        Bundle data = getIntent().getExtras();
        id_transaksi = data.getString("id_transaksi");
        total = data.getInt("total");

        btnBayar = findViewById(R.id.btn_bayar_sekarang);
        btnLihatPesanan = findViewById(R.id.btn_ke_pesanan);
        txtTotal = findViewById(R.id.txt_total_petunjuk);
        txtTotal.setText(formatRupiah(total));

        btnBayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bayar = new Intent(PertunjukBayarActivity.this, BayarActivity.class);
                bayar.putExtra("id_transaksi", id_transaksi);
                startActivity(bayar);
                finish();
            }
        });

        btnLihatPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PertunjukBayarActivity.this, ListPesanan.class));
                finish();
            }
        });
    }

    private String formatRupiah(int number) {
        Locale localeID = new Locale("IND", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
        String formatRupiah = numberFormat.format(number);
        String[] split = formatRupiah.split(",");
        int length = split[0].length();
        return split[0].substring(0,2) + " " + split[0].substring(2,length);
    }
}