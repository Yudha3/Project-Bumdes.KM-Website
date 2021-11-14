package com.yogandrn.coba2;

public class ItemModel {

    String namaProduk, harga;
    int fotoProduk;

    public ItemModel(String namaProduk, String harga, int fotoProduk) {
        this.namaProduk = namaProduk;
        this.harga = harga;
        this.fotoProduk = fotoProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public String getHarga() {
        return harga;
    }

    public int getFotoProduk() {
        return fotoProduk;
    }
}

