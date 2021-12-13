package com.yogandrn.coba2.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ModelKeranjang;
import com.yogandrn.coba2.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterKeranjang extends RecyclerView.Adapter<AdapterKeranjang.HolderKeranjang>{
    private Context ctx;
    private List<ModelKeranjang> listKeranjang;

    public AdapterKeranjang(Context ctx, List<ModelKeranjang> listKeranjang) {
        this.ctx = ctx;
        this.listKeranjang = listKeranjang;
    }

    @NonNull
    @Override
    public HolderKeranjang onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_keranjang, parent, false);
        HolderKeranjang holder = new HolderKeranjang(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderKeranjang holder, int position) {
        ModelKeranjang cartModel = listKeranjang.get(position);

        holder.txtID.setText(String.valueOf(cartModel.getId_keranjang()));
        holder.txtNama.setText(cartModel.getBarang());
        holder.txtHarga.setText(formatRupiah(cartModel.getHarga()));
        holder.txtQty.setText("Qty : " + String.valueOf( cartModel.getQty()) + "x");
        holder.txtSubtotal.setText(formatRupiah(cartModel.getSubtotal()));

        Glide.with(holder.itemView.getContext()).load(Global.IMG_PRODUK_URL + cartModel.getGambar()).fitCenter().into(holder.imgProduk);
    }

    @Override
    public int getItemCount() {
        return listKeranjang.size();
    }

    public class HolderKeranjang extends RecyclerView.ViewHolder{
        TextView txtNama, txtID, txtHarga, txtSubtotal, txtQty;
        ImageView imgProduk;

        public HolderKeranjang(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txt_id_keranjang);
            txtNama = itemView.findViewById(R.id.txt_namaproduk_keranjang);
            txtHarga = itemView.findViewById(R.id.txt_harga_keranjang);
            txtSubtotal = itemView.findViewById(R.id.txt_subtotal_keranjang);
            txtQty = itemView.findViewById(R.id.txt_qty_keranjang);
            imgProduk = itemView.findViewById(R.id.img_keranjang);
        }
    }


    private String formatRupiah(int number) {
        Locale localeID = new Locale("IND", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
        String formatRupiah = numberFormat.format(number);
        String[] split = formatRupiah.split(",");
        int length = split[0].length();
        return split[0].substring(0,2) + " " + split[0].substring(2,length);
    }

}
