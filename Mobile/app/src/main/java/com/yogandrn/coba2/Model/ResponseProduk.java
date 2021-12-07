package com.yogandrn.coba2.Model;

import java.util.List;

public class ResponseProduk {

    private int kode;
    private String pesan;
    private List<ProdukModel> data ;

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

    public List<ProdukModel> getData() {
        return data;
    }

    public void setData(List<ProdukModel> data) {
        this.data = data;
    }
}
