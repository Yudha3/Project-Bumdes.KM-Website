 package com.yogandrn.coba2.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yogandrn.coba2.API.APIRequestData;
import com.yogandrn.coba2.API.RetroServer;
import com.yogandrn.coba2.Adapter.AdapterKeranjang;
import com.yogandrn.coba2.Global;
import com.yogandrn.coba2.Model.ModelKeranjang;
import com.yogandrn.coba2.Model.ResponseKeranjang;
import com.yogandrn.coba2.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class OrderActivity extends AppCompatActivity {

     private RecyclerView rvOrder;
     private RecyclerView.Adapter adapter;
     private RecyclerView.LayoutManager layoutManager;
     private List<ModelKeranjang> listitem = new ArrayList<>();
     private Button btnOrder;
     private RadioGroup rgOngkir, rgBayar;
     private RadioButton ongkir1, ongkir2;
     private TextView txtTotal, txtSubtotal, txtOngkir, txtTotal2, txtSubtotal2, txtOngkir2;
     private EditText etAlamat, etNoTelp;
     private int subtotalitem = Global.total;
     private int ongkir = 30000;
     private String id_ongkir = "1";
     private String alamat, no_telp;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        txtSubtotal = findViewById(R.id.txt_total_item);
        txtSubtotal2 = findViewById(R.id.txt_total2_item);
        txtOngkir = findViewById(R.id.txt_ongkir_order);
        txtOngkir2 = findViewById(R.id.txt_ongkir2_order);
        txtTotal = findViewById(R.id.txt_total_order);
        txtTotal2 = findViewById(R.id.txt_total2_order);
        etAlamat = findViewById(R.id.et_alamat_transaksi);
        etNoTelp = findViewById(R.id.et_notelp_transaksi);
        rgOngkir = findViewById(R.id.radioGroup_ongkir);
        rgBayar = findViewById(R.id.radioGroup_bayar);
        ongkir1 = findViewById(R.id.id_ongkir_1);
        ongkir2 = findViewById(R.id.id_ongkir_2);
        btnOrder = findViewById(R.id.btn_transaksi);
        rvOrder = findViewById(R.id.rvOrder);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        txtOngkir.setText(formatRupiah(ongkir));
        txtOngkir2.setText(formatRupiah(ongkir));
        rvOrder.setLayoutManager(layoutManager);
        getItem();

        alamat = etAlamat.getText().toString();
        no_telp = etNoTelp.getText().toString();

        txtSubtotal.setText(formatRupiah(subtotalitem));
        txtSubtotal2.setText(formatRupiah(subtotalitem));
        txtTotal.setText(formatRupiah(subtotalitem+ongkir));
        txtTotal2.setText(formatRupiah(subtotalitem+ongkir));
        ongkir1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ongkir1.isChecked()) {
                    id_ongkir = "1";
                    ongkir = 30000;
                    txtOngkir.setText(formatRupiah(30000));
                    txtOngkir2.setText(formatRupiah(30000));
                    txtTotal2.setText(formatRupiah(subtotalitem+30000));
                    txtTotal.setText(formatRupiah(subtotalitem+30000));
                }
            }
        });
        ongkir2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (ongkir2.isChecked()) {
                    id_ongkir = "2";
                    ongkir = 48000;
                    txtOngkir.setText(formatRupiah(48000));
                    txtOngkir2.setText(formatRupiah(48000));
                    txtTotal2.setText(formatRupiah(subtotalitem+48000));
                    txtTotal.setText(formatRupiah(subtotalitem+48000));
                }
            }
        });
    }

    public void getItem() {
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseKeranjang> getKeranjang = apiRequestData.readCart(Global.id_user);

        getKeranjang.enqueue(new Callback<ResponseKeranjang>() {
            @Override
            public void onResponse(Call<ResponseKeranjang> call, Response<ResponseKeranjang> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Data tersedia")){
                    listitem = response.body().getData();

                    adapter = new AdapterKeranjang(OrderActivity.this, listitem);
                    rvOrder.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else if (pesan.equals("Data tidak tersedia")) {
                    Toast.makeText(OrderActivity.this, "Tidak ada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseKeranjang> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Terjadi Kesalahan : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buatTransaksi() {

    }

     private String formatRupiah(int number) {
         Locale localeID = new Locale("IND", "ID");
         NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
         String formatRupiah = numberFormat.format(number);
         String[] split = formatRupiah.split(",");
         int length = split[0].length();
         return split[0].substring(0,2) + " " + split[0].substring(2,length);
     }

//     public void getOngkir(View v) {
////         int radioID = rgOngkir.getCheckedRadioButtonId();
////         if (radioID == 1000233) {
////             id_ongkir = 1;
////             ongkir = 30000;
////             txtOngkir.setText(formatRupiah(ongkir));
////         } else if (radioID == 1000230) {
////             id_ongkir = 2;
////             ongkir = 48000;
////             txtOngkir.setText(formatRupiah(ongkir));
////         }
////     }
 }