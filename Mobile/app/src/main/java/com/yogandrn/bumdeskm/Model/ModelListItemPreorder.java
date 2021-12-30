package com.yogandrn.bumdeskm.Model;

public class ModelListItemPreorder {
    private int id_item_preorder, qty, subtotal, harga;
    private String id_brg, barang, gambar;

    public int getId_item_preorder() {
        return id_item_preorder;
    }

    public void setId_item_preorder(int id_item_preorder) {
        this.id_item_preorder = id_item_preorder;
    }

    public String getId_brg() {
        return id_brg;
    }

    public void setId_brg(String id_brg) {
        this.id_brg = id_brg;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
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
