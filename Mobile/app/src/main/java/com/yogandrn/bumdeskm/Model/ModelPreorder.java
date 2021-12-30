package com.yogandrn.bumdeskm.Model;

public class ModelPreorder {
    private int id_preorder, total_preorder, id_user, id_ongkir;
    private String tgl_preorder, penerima, alamat, no_telp, gambar, status;

    public int getId_preorder() {
        return id_preorder;
    }

    public void setId_preorder(int id_preorder) {
        this.id_preorder = id_preorder;
    }

    public int getTotal_preorder() {
        return total_preorder;
    }

    public void setTotal_preorder(int total_preorder) {
        this.total_preorder = total_preorder;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_ongkir() {
        return id_ongkir;
    }

    public void setId_ongkir(int id_ongkir) {
        this.id_ongkir = id_ongkir;
    }

    public String getTgl_preorder() {
        return tgl_preorder;
    }

    public void setTgl_preorder(String tgl_preorder) {
        this.tgl_preorder = tgl_preorder;
    }

    public String getPenerima() {
        return penerima;
    }

    public void setPenerima(String penerima) {
        this.penerima = penerima;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
