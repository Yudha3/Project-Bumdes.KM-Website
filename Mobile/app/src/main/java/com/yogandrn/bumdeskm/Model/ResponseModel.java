package com.yogandrn.bumdeskm.Model;

import java.util.List;

public class ResponseModel {
    private int kode;
    private List<ModelListItemPreorder> item_preorder;

    public List<ModelListItemPreorder> getItem_preorder() {
        return item_preorder;
    }

    public void setItem_preorder(List<ModelListItemPreorder> item_preorder) {
        this.item_preorder = item_preorder;
    }

    //    @SerializedName("pesan")
    private String pesan;

    private List<ModelTransaksi> listTransaksi;

    public List<ModelListItemTransaksi> getItem_transaksi() {
        return item_transaksi;
    }

    public void setItem_transaksi(List<ModelListItemTransaksi> item_transaksi) {
        this.item_transaksi = item_transaksi;
    }

    private List<ModelListItemTransaksi> item_transaksi;

    public List<ModelTransaksi> getListTransaksi() {
        return listTransaksi;
    }

    public void setListTransaksi(List<ModelTransaksi> listTransaksi) {
        this.listTransaksi = listTransaksi;
    }

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
