package com.yogandrn.bumdeskm.Model;

import java.util.List;

public class ResponseProduk {

    private int kode;
    private String pesan;
    private List<ModelProduk> data ;

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

    public List<ModelProduk> getData() {
        return data;
    }

    public void setData(List<ModelProduk> data) {
        this.data = data;
    }
}
