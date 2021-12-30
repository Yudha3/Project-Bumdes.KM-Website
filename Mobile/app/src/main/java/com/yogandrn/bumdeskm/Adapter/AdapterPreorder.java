package com.yogandrn.bumdeskm.Adapter;

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
import com.yogandrn.bumdeskm.Activity.DetailPreorder;
import com.yogandrn.bumdeskm.Global;
import com.yogandrn.bumdeskm.Model.ModelPreorder;
import com.yogandrn.bumdeskm.R;

import java.util.List;

public class AdapterPreorder extends RecyclerView.Adapter<AdapterPreorder.HolderPreorder> {

    private Context ctx;
    private List<ModelPreorder> listPreorder;

    public AdapterPreorder(Context ctx, List<ModelPreorder> listPreorder) {
        this.ctx = ctx;
        this.listPreorder = listPreorder;
    }

    @NonNull
    @Override
    public HolderPreorder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_transaksi, viewGroup, false);
        HolderPreorder holder = new HolderPreorder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderPreorder holderPreorder, int position) {
        ModelPreorder model = listPreorder.get(position);

        holderPreorder.txtID_transaksi.setText(String.valueOf(model.getId_preorder()));
        holderPreorder.txtStatus.setText(model.getStatus());
        holderPreorder.txtTotal.setText(Global.formatRupiah(model.getTotal_preorder()));
        Glide.with(holderPreorder.itemView.getContext()).load(Global.IMG_PRODUK_URL + model.getGambar()).into(holderPreorder.imgTransaksi);

        holderPreorder.cardTransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(ctx.getApplicationContext(), DetailPreorder.class);
                detail.putExtra("id_preorder", String.valueOf(model.getId_preorder()));
                ctx.startActivity(detail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listPreorder.size();
    }

    public class HolderPreorder extends RecyclerView.ViewHolder {
        TextView txtID_transaksi, txtTotal, txtStatus;
        ImageView imgTransaksi;
        CardView cardTransaksi;
        public HolderPreorder(@NonNull View itemView) {
            super(itemView);
            txtID_transaksi = itemView.findViewById(R.id.txt_id_transaksi);
            txtStatus = itemView.findViewById(R.id.txt_status_transaksi);
            txtTotal = itemView.findViewById(R.id.txt_total_transaksi);
            imgTransaksi = itemView.findViewById(R.id.img_list_transaksi);
            cardTransaksi = itemView.findViewById(R.id.transaksi_card);
        }
    }
}
