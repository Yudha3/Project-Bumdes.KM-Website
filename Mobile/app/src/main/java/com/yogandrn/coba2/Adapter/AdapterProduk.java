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
import com.yogandrn.coba2.Activity.DetailProduk;
import com.yogandrn.coba2.Model.ModelProduk;
import com.yogandrn.coba2.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class AdapterProduk extends RecyclerView.Adapter<AdapterProduk.HolderProduk> {
    private Context ctx;
    private List<ModelProduk> listModel;

    public AdapterProduk(Context ctx, List<ModelProduk> listModel) {
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
        ModelProduk modelProduk = listModel.get(position);

        String harga = formatRupiah(modelProduk.getHg_jual());
        holder.tvID.setText(String.valueOf(modelProduk.getId_brg()));
        holder.tvBarang.setText(modelProduk.getBarang());
        holder.tvHarga.setText(harga);
        holder.tvStok.setText(String.valueOf((modelProduk.getJml_stok())));
        holder.tvDeskripsi.setText(modelProduk.getDeskripsi());

        Glide.with(holder.itemView.getContext()).load("http://undeveloppedcity.000webhostapp.com/android/img/produk/" + modelProduk.getGambar())
//                .apply(new RequestOptions().centerCrop())
                .into(holder.imgProduk);

        holder.cardProduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent showDetail = new Intent(ctx, DetailProduk.class);
                showDetail.putExtra("id_brg", String.valueOf(modelProduk.getId_brg()));
                showDetail.putExtra("stok", String.valueOf(modelProduk.getJml_stok()));
                showDetail.putExtra("barang", modelProduk.getBarang());
                showDetail.putExtra("harga", String.valueOf(modelProduk.getHg_jual()));
                showDetail.putExtra("deskripsi", modelProduk.getDeskripsi());
                showDetail.putExtra("gambar",  modelProduk.getGambar());
                ctx.startActivity(showDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listModel.size();
    }

    public class HolderProduk extends RecyclerView.ViewHolder {
        TextView tvDeskripsi, tvStok, tvBarang, tvHarga,tvID;
        ImageView imgProduk;
        CardView cardProduk;

        public HolderProduk(@NonNull View itemView) {
            super(itemView);
            cardProduk = itemView.findViewById(R.id.produk_card);
            tvID = itemView.findViewById(R.id.tvID_Brg);
            tvStok = itemView.findViewById(R.id.tvStok);
            tvBarang = itemView.findViewById(R.id.tvBarang);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvDeskripsi = itemView.findViewById(R.id.tvDeskripsi);
            imgProduk = itemView.findViewById(R.id.imgProduk);
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
