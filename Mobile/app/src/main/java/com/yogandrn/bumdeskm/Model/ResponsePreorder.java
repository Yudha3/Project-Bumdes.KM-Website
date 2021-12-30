package com.yogandrn.bumdeskm.Model;

import java.util.List;

public class ResponsePreorder {
    private String pesan;
    private List<ModelPreorder> data;
    private List<ModelListItemPreorder> item_preorder;

    public List<ModelListItemPreorder> getItem_preorder() {
        return item_preorder;
    }

    public void setItem_preorder(List<ModelListItemPreorder> item_preorder) {
        this.item_preorder = item_preorder;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public List<ModelPreorder> getData() {
        return data;
    }

    public void setData(List<ModelPreorder> data) {
        this.data = data;
    }
}
