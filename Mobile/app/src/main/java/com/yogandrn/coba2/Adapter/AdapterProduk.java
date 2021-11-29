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
import com.yogandrn.coba2.Model.ProdukModel;
import com.yogandrn.coba2.R;

import java.util.List;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.HolderProduk> {
    private Context ctx;
    private List<ProdukModel> listModel;

    public AdapterProduk(Context ctx, List<ProdukModel> listModel) {
        this.ctx = ctx;
        this.listModel = listModel;
    }

    @NonNull
    @Override
    public HolderProduk onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false);
        HolderProduk holder =  new HolderProduk(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProduk holder, int position) {
        ProdukModel produkModel = listModel.get(position);

        holder.tvID.setText(String.valueOf(produkModel.getId_buku()));
        holder.tvISBN.setText(produkModel.getIsbn());
        holder.tvJudul.setText(produkModel.getJudul());
        holder.tvPenulis.setText(produkModel.getPenulis());

        Glide.with(holder.itemView.getContext()).load("http://192.168.1.100:8080/android/img/" + produkModel.getGambar())
//                .apply(new RequestOptions().centerCrop())
                .into(holder.imgProduk);
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public class HolderProduk extends RecyclerView.ViewHolder {
        TextView tvISBN, tvJudul, tvPenulis,  tvID;
        ImageView imgProduk;

        public HolderProduk(@NonNull View itemView) {
            super(itemView);
            tvID = itemView.findViewById(R.id.tvID);
            tvISBN = itemView.findViewById(R.id.tvISBN);
            tvJudul = itemView.findViewById(R.id.tvJudul);
            tvPenulis = itemView.findViewById(R.id.tvPenulis);
            imgProduk = itemView.findViewById(R.id.imgProduk);
        }
    }
}
