package com.yogandrn.coba2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout btnKatalog, btnPesanan, btnPreorder, btnKeranjang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnKatalog = (LinearLayout) findViewById(R.id.layoutKatalog);
        btnKeranjang = (LinearLayout) findViewById(R.id.layoutKeranjang);
        btnPesanan = (LinearLayout) findViewById(R.id.layoutPesanan);
        btnPreorder = (LinearLayout) findViewById(R.id.layoutPreorder);

        btnKatalog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KatalogActivity.class));
            }
        });

        btnPreorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PreorderActivity.class));
            }
        });

        btnKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KeranjangActivity.class));
            }
        });

        btnPesanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, PesananActivity.class));
            }
        });
    }
}