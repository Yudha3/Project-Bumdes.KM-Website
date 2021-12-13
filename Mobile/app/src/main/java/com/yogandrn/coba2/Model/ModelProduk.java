package com.yogandrn.coba2.Model;

public class ModelProduk {

    private int id_brg, hg_jual, jml_stok ;
    private String barang, deskripsi, gambar;

    public int getId_brg() {
        return id_brg;
    }

    public void setId_brg(int id_brg) {
        this.id_brg = id_brg;
    }

    public int getHg_jual() {
        return hg_jual;
    }

    public void setHg_jual(int hg_jual) {
        this.hg_jual = hg_jual;
    }

    public int getJml_stok() {
        return jml_stok;
    }

    public void setJml_stok(int jml_stok) {
        this.jml_stok = jml_stok;
    }

    public String getBarang() {
        return barang;
    }

    public void setBarang(String barang) {
        this.barang = barang;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }
}
