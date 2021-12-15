package com.yogandrn.coba2.Model;

import java.util.List;

public class ResponseModel {
    private int kode;
    private String pesan;

    public List<ModelTransaksi> getListTransaksi() {
        return listTransaksi;
    }

    public void setListTransaksi(List<ModelTransaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

    private List<ModelTransaksi> listTransaksi;

    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

}
