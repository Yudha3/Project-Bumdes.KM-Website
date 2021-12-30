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
import com.yogandrn.bumdeskm.Activity.DetailProduk;
import com.yogandrn.bumdeskm.Global;
import com.yogandrn.bumdeskm.Model.ModelListItemTransaksi;
import com.yogandrn.bumdeskm.R;

import java.util.List;

public class AdapterItemTransaksi extends RecyclerView.Adapter<AdapterItemTransaksi.HolderItem>{
    private Context ctx;
    private List<ModelListItemTransaksi> listItemTransaksi;

    public AdapterItemTransaksi(Context ctx, List<ModelListItemTransaksi> listItemTransaksi) {
        this.ctx = ctx;
        this.listItemTransaksi = listItemTransaksi;
    }

    @NonNull
    @Override
    public HolderItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_produk, parent, false);
        HolderItem holder = new HolderItem(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItem holder, int position) {
        ModelListItemTransaksi itemModel = listItemTransaksi.get(position);

        holder.txtID.setText(String.valueOf(itemModel.getId_brg()));
        holder.txtNama.setText(itemModel.getBarang());
        holder.txtHarga.setText(Global.formatRupiah(itemModel.getHarga()));
        holder.txtQty.setText("Qty : " + String.valueOf(itemModel.getQty()));
        holder.txtSubtotal.setText(Global.formatRupiah(itemModel.getSubtotal()));
        Glide.with(holder.itemView.getContext()).load(Global.IMG_PRODUK_URL + itemModel.getGambar()).fitCenter().into(holder.imgItemProduk);

        holder.cardItemProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeProduk = new Intent(ctx.getApplicationContext(), DetailProduk.class);
                seeProduk.putExtra("id_brg", String.valueOf(itemModel.getId_brg()));
                ctx.startActivity(seeProduk);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItemTransaksi.size();
    }

    public class HolderItem extends RecyclerView.ViewHolder {
        TextView txtID, txtHarga, txtNama, txtSubtotal, txtQty;
        ImageView imgItemProduk;
        CardView cardItemProduk;

        public HolderItem(@NonNull View itemView) {
            super(itemView);
            cardItemProduk = itemView.findViewById(R.id.produk_card_transaksi);
            imgItemProduk = itemView.findViewById(R.id.img_list_produk);
            txtID = itemView.findViewById(R.id.txt_id_produk_produk);
            txtNama = itemView.findViewById(R.id.txt_namaproduk_produk);
            txtHarga = itemView.findViewById(R.id.txt_harga_produk);
            txtQty = itemView.findViewById(R.id.txt_qty_produk);
            txtSubtotal = itemView.findViewById(R.id.txt_subtotal_produk);
        }
    }
}
