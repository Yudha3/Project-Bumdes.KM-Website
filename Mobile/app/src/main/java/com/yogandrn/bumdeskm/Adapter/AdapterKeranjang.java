package com.yogandrn.bumdeskm.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yogandrn.bumdeskm.API.APIRequestData;
import com.yogandrn.bumdeskm.API.RetroServer;
import com.yogandrn.bumdeskm.Activity.DetailProduk;
import com.yogandrn.bumdeskm.Activity.KeranjangActivity;
import com.yogandrn.bumdeskm.Global;
import com.yogandrn.bumdeskm.Model.ModelKeranjang;
import com.yogandrn.bumdeskm.Model.ResponseModel;
import com.yogandrn.bumdeskm.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        String id_keranjang = String.valueOf(cartModel.getId_keranjang());

        holder.txtID.setText(id_keranjang);
        holder.txtNama.setText(cartModel.getBarang());
        holder.txtHarga.setText(Global.formatRupiah(cartModel.getHarga()));
        holder.txtQty.setText("Qty : " + String.valueOf( cartModel.getQty()) + "x");
        holder.txtSubtotal.setText(Global.formatRupiah(cartModel.getSubtotal()));

        Glide.with(holder.itemView.getContext()).load(Global.IMG_PRODUK_URL + cartModel.getGambar()).fitCenter().into(holder.imgProduk);

        holder.btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogHapus = new AlertDialog.Builder(ctx);
                dialogHapus.setCancelable(true);
                dialogHapus.setIcon(R.drawable.ic_delete_title);
                dialogHapus.setTitle("Hapus Item");
                dialogHapus.setMessage("Apakah Anda ingin menghapus item ini dari Keranjang?");

                dialogHapus.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hapusKeranjang(id_keranjang);
                        dialogInterface.dismiss();
                        Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ((KeranjangActivity) ctx).retrieveCart();
                            }
                        }, 1000);
                    }
                });
                dialogHapus.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                dialogHapus.show();
            }
        });

        holder.cardKeranjang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(ctx.getApplicationContext(), DetailProduk.class);
                detail.putExtra("id_brg", cartModel.getId_brg());
                ctx.startActivity(detail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listKeranjang.size();
    }

    public class HolderKeranjang extends RecyclerView.ViewHolder{
        TextView txtNama, txtID, txtHarga, txtSubtotal, txtQty;
        ImageView imgProduk, btnHapus;
        RelativeLayout cardKeranjang;

        public HolderKeranjang(@NonNull View itemView) {
            super(itemView);
            txtID = itemView.findViewById(R.id.txt_id_keranjang);
            txtNama = itemView.findViewById(R.id.txt_namaproduk_keranjang);
            txtHarga = itemView.findViewById(R.id.txt_harga_keranjang);
            txtSubtotal = itemView.findViewById(R.id.txt_subtotal_keranjang);
            txtQty = itemView.findViewById(R.id.txt_qty_keranjang);
            imgProduk = itemView.findViewById(R.id.img_keranjang);
            btnHapus = itemView.findViewById(R.id.btn_hapus_keranjang);
            cardKeranjang = itemView.findViewById(R.id.keranjang_card);
        }
    }

    private void hapusKeranjang(String id){
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseModel> callHapusKeranjang = apiRequestData.hapusKeranjang(id);
        callHapusKeranjang.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("BERHASIL")) {
                    Toast.makeText(ctx.getApplicationContext(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("GAGAL")) {
                    Toast.makeText(ctx.getApplicationContext(), "Gagal menghapus data", Toast.LENGTH_SHORT).show();
                } else if (pesan.equals("NOT CONNECTED")) {
                    Toast.makeText(ctx.getApplicationContext(), "Gagal menghubungi server", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(ctx.getApplicationContext(), "Terjadi kesalahan :\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
