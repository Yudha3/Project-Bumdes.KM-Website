package com.yogandrn.coba2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {

    ArrayList<ItemModel> dataItem;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaProduk, txtHarga;
        ImageView fotoProduk;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNamaProduk = itemView.findViewById(R.id.txtNamaProduk);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            fotoProduk = itemView.findViewById(R.id.fotoProduk);
        }
    }

    AdapterRecyclerView(ArrayList<ItemModel> data) {
        this.dataItem = data;
    }

    @NonNull
    @Override
    public AdapterRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecyclerView.ViewHolder holder, int position) {

        TextView nama = holder.txtNamaProduk;
        TextView harga = holder.txtHarga;
        ImageView foto = holder.fotoProduk;

        nama.setText(dataItem.get(position).getNamaProduk());
        harga.setText(dataItem.get(position).getHarga());
        foto.setImageResource(dataItem.get(position).getFotoProduk());

    }

    @Override
    public int getItemCount() {
        return dataItem.size();
    }

}
