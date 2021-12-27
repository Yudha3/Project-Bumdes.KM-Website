package com.yogandrn.coba2.Model;

public class ModelListItemTransaksi {
    private int id_transaksi_produk, id_transaksi, subtotal, harga, qty;
    private String barang, gambar,  id_brg;

    public int getId_transaksi_produk() {
        return id_transaksi_produk;
    }

    public void setId_transaksi_produk(int id_transaksi_produk) {
        this.id_transaksi_produk = id_transaksi_produk;
    }

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    public String getId_brg() {
        return id_brg;
    }

    public void setId_brg(String id_brg) {
        this.id_brg = id_brg;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
