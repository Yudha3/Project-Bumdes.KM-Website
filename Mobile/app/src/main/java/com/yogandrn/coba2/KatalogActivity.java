package com.yogandrn.coba2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class KatalogActivity extends AppCompatActivity {

    RecyclerView katalogProduk;
    AdapterRecyclerView adapterProduk;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_katalog);

        katalogProduk = findViewById(R.id.recycler_katalog);
        katalogProduk.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        katalogProduk.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < MyItem.namaProduk.length; i++) {
            data.add(new ItemModel(
                    MyItem.namaProduk[i],
                    MyItem.harga[i],
                    MyItem.fotoProduk[i]
            ));
        }

        adapterProduk = new AdapterRecyclerView(data);
        katalogProduk.setAdapter(adapterProduk);
    }
}