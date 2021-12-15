package com.yogandrn.coba2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yogandrn.coba2.Activity.DetailTransaksiActivity;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ModelTransaksi;
import com.yogandrn.coba2.Model.ResponseTransaksi;
import com.yogandrn.coba2.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterTransaksi extends RecyclerView.Adapter<AdapterTransaksi.HolderTransaksi>{
    private Context ctx;
    private List<ModelTransaksi> listTransaksi;

    public AdapterTransaksi(Context ctx, List<ModelTransaksi> listTransaksi) {
        this.ctx = ctx;
        this.listTransaksi = listTransaksi;
    }

    @NonNull
    @Override
    public HolderTransaksi onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_transaksi, parent, false);
        HolderTransaksi holder = new HolderTransaksi(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTransaksi holder, int position) {
        ModelTransaksi itemModel = listTransaksi.get(position);

        holder.txtID_transaksi.setText(String.valueOf(itemModel.getId_transaksi()));
        holder.txtStatus.setText(itemModel.getStatus());
        holder.txtTotal.setText(Global.formatRupiah(itemModel.getTotal_transaksi()));
        Glide.with(holder.itemView.getContext()).load(Global.IMG_PRODUK_URL + itemModel.getGambar()).fitCenter().into(holder.imgTransaksi);

        holder.cardTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentku = new Intent(ctx, DetailTransaksiActivity.class);
                intentku.putExtra("id_transaksi", String.valueOf(itemModel.getId_transaksi())); // int
                ctx.startActivity(intentku);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTransaksi.size();
    }

    public class HolderTransaksi extends RecyclerView.ViewHolder {
        TextView txtID_transaksi, txtTotal, txtStatus;
        ImageView imgTransaksi;
        CardView cardTransaksi;

        public HolderTransaksi(@NonNull View itemView) {
            super(itemView);
            txtID_transaksi = itemView.findViewById(R.id.txt_id_transaksi);
            txtStatus = itemView.findViewById(R.id.txt_status_transaksi);
            txtTotal = itemView.findViewById(R.id.txt_total_transaksi);
            imgTransaksi = itemView.findViewById(R.id.img_list_transaksi);
            cardTransaksi = itemView.findViewById(R.id.transaksi_card);
        }
    }
}
