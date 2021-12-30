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
import com.yogandrn.bumdeskm.Model.ModelListItemPreorder;
import com.yogandrn.bumdeskm.R;

import java.util.List;

public class AdapterItemPreorder extends RecyclerView.Adapter<AdapterItemPreorder.HolderItem>{

    private Context ctx;
    private List<ModelListItemPreorder> listItemPreorder;

    public AdapterItemPreorder(Context ctx, List<ModelListItemPreorder> listItemPreorder) {
        this.ctx = ctx;
        this.listItemPreorder = listItemPreorder;
    }

    @NonNull
    @Override
    public HolderItem onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_produk, viewGroup, false);
        HolderItem holder = new HolderItem(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderItem holderItem, int position) {
        ModelListItemPreorder model = listItemPreorder.get(position);

        holderItem.txtID.setText(String.valueOf(model.getId_brg()));
        holderItem.txtNama.setText(model.getBarang());
        holderItem.txtHarga.setText(Global.formatRupiah(model.getHarga()));
        holderItem.txtQty.setText("Qty : " + String.valueOf(model.getQty()));
        holderItem.txtSubtotal.setText(Global.formatRupiah(model.getSubtotal()));
        Glide.with(holderItem.itemView.getContext()).load(Global.IMG_PRODUK_URL + model.getGambar()).fitCenter().into(holderItem.imgItemProduk);

        holderItem.cardItemProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent seeProduk = new Intent(ctx.getApplicationContext(), DetailProduk.class);
                seeProduk.putExtra("id_brg", String.valueOf(model.getId_brg()));
                ctx.startActivity(seeProduk);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItemPreorder.size();
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
