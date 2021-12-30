package com.yogandrn.bumdeskm.Model;

import java.util.List;

public class ResponseKeranjang {
    private String pesan;
    private List<ModelKeranjang> data;

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelKeranjang> getData() {
        return data;
    }

    public void setData(List<ModelKeranjang> data) {
        this.data = data;
    }
}
