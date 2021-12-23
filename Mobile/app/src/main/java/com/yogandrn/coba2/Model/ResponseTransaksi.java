package com.yogandrn.coba2.Model;

import java.util.List;

public class ResponseTransaksi {
    private String pesan;
    private int kode;

    public int getId_transaksi() {
        return id_transaksi;
    }

    public void setId_transaksi(int id_transaksi) {
        this.id_transaksi = id_transaksi;
    }

    private int id_transaksi;

    public int getKode() { return kode; }

    public void setKode(int kode) { this.kode = kode; }

    private List<ModelTransaksi> data;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelTransaksi> getData() {
        return data;
    }

    public void setListTransaksi(List<ModelTransaksi> listTransaksi) {
        this.data = listTransaksi;
    }
}
