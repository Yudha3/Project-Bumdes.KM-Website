 package com.yogandrn.bumdeskm.Activity;

 import android.content.Intent;
 import android.os.Bundle;
 import android.view.View;
 import android.view.WindowManager;
 import android.widget.Button;
 import android.widget.CompoundButton;
 import android.widget.EditText;
 import android.widget.ProgressBar;
 import android.widget.RadioButton;
 import android.widget.RadioGroup;
 import android.widget.TextView;
 import android.widget.Toast;

 import androidx.appcompat.app.AppCompatActivity;
 import androidx.recyclerview.widget.LinearLayoutManager;
 import androidx.recyclerview.widget.RecyclerView;
 import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

 import com.yogandrn.bumdeskm.API.APIRequestData;
 import com.yogandrn.bumdeskm.API.RetroServer;
 import com.yogandrn.bumdeskm.Adapter.AdapterKeranjang;
 import com.yogandrn.bumdeskm.Global;
 import com.yogandrn.bumdeskm.Model.ModelKeranjang;
 import com.yogandrn.bumdeskm.Model.ResponseKeranjang;
 import com.yogandrn.bumdeskm.Model.ResponseTransaksi;
 import com.yogandrn.bumdeskm.R;
 import com.yogandrn.bumdeskm.SessionManager;

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
     private EditText etPenerima, etAlamat, etNoTelp;
     private int subtotalitem ;
     private int ongkir = 30000;
     private String id_ongkir = "1";
     private String penerima, alamat, no_telp;
     private SwipeRefreshLayout srlOrder;
     private ProgressBar pbOrder;
     private View vOrder;
     SessionManager sessionManager;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
         getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
         getSupportActionBar().setDisplayHomeAsUpEnabled(true);
         getSupportActionBar().setDisplayShowHomeEnabled(true);
         setTitle("Order");

        sessionManager = new SessionManager(OrderActivity.this);
         Global gb = new Global();
         subtotalitem = gb.getTotal(String.valueOf(sessionManager.getSessionID()));

        txtSubtotal = findViewById(R.id.txt_total_item);
        txtSubtotal2 = findViewById(R.id.txt_total2_item);
        txtOngkir = findViewById(R.id.txt_ongkir_order);
        txtOngkir2 = findViewById(R.id.txt_ongkir2_order);
        txtTotal = findViewById(R.id.txt_total_order);
        txtTotal2 = findViewById(R.id.txt_total2_order);
        etPenerima = findViewById(R.id.et_penerima_transaksi);
        etAlamat = findViewById(R.id.et_alamat_transaksi);
        etNoTelp = findViewById(R.id.et_notelp_transaksi);
        rgOngkir = findViewById(R.id.radioGroup_ongkir);
        rgBayar = findViewById(R.id.radioGroup_bayar);
        ongkir1 = findViewById(R.id.id_ongkir_1);
        ongkir2 = findViewById(R.id.id_ongkir_2);
        btnOrder = findViewById(R.id.btn_transaksi);
        pbOrder = findViewById(R.id.progress_order);
        srlOrder = findViewById(R.id.srl_order);
        vOrder = findViewById(R.id.view_order);
        rvOrder = findViewById(R.id.rvOrder);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);

        txtOngkir.setText(formatRupiah(ongkir));
        txtOngkir2.setText(formatRupiah(ongkir));
        rvOrder.setLayoutManager(layoutManager);
        getItem();

        srlOrder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlOrder.setRefreshing(true);
                getItem();
                gb.getTotal(String.valueOf(sessionManager.getSessionID()));
                srlOrder.setRefreshing(false);
            }
        });

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

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etPenerima.getText().toString().equals("")) {
//                    Toast.makeText(getApplicationContext(), "Kolom Alamat wajib diisi", Toast.LENGTH_SHORT).show();
                    etPenerima.setError("Wajib diisi!");
                } else if (etAlamat.getText().toString().equals("")){
                    etAlamat.setError("Wajib diisi!");
                } else if (etNoTelp.getText().toString().equals("")){
                    etNoTelp.setError("Wajib diisi!");
                } else {
                    alamat = etAlamat.getText().toString();
                    no_telp = etNoTelp.getText().toString();
                    penerima = etPenerima.getText().toString();
                    buatTransaksi();
                }
            }
        });
    }

    public void getItem() {
         vOrder.setVisibility(View.VISIBLE);
         pbOrder.setVisibility(View.VISIBLE);

        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseKeranjang> getKeranjang = apiRequestData.readCart(String.valueOf(sessionManager.getSessionID()));

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
                pbOrder.setVisibility(View.GONE);
                vOrder.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ResponseKeranjang> call, Throwable t) {
                vOrder.setVisibility(View.GONE);
                pbOrder.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Terjadi Kesalahan : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void buatTransaksi() {
         vOrder.setVisibility(View.VISIBLE);
         pbOrder.setVisibility(View.VISIBLE);
         String id = String.valueOf(sessionManager.getSessionID());
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseTransaksi> transaksi = apiRequestData.createTransaksi(id, penerima, alamat, no_telp, id_ongkir);
        transaksi.enqueue(new Callback<ResponseTransaksi>() {
            @Override
            public void onResponse(Call<ResponseTransaksi> call, Response<ResponseTransaksi> response) {
                String pesan = response.body().getPesan();

                if (pesan.equals("BERHASIL")) {
                    int id_transaksi = response.body().getId_transaksi();
                    vOrder.setVisibility(View.GONE);pbOrder.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Transaksi Berhasil", Toast.LENGTH_SHORT).show();
                    Intent selesai = new Intent(OrderActivity.this, PertunjukBayarActivity.class);
                    selesai.putExtra("id_transaksi", String.valueOf(id_transaksi));
                    selesai.putExtra("total", subtotalitem + ongkir);
                    startActivity(selesai);
                    finish();
                } else if (pesan.equals("GAGAL")) {
                    vOrder.setVisibility(View.GONE);
                    pbOrder.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Gagal melakukan transaksi", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseTransaksi> call, Throwable t) {
                vOrder.setVisibility(View.GONE);
                pbOrder.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Terjadi kesalahan\n" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

     private String formatRupiah(int number) {
         Locale localeID = new Locale("IND", "ID");
         NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
         String formatRupiah = numberFormat.format(number);
         String[] split = formatRupiah.split(",");
         int length = split[0].length();
         return split[0].substring(0,2) + " " + split[0].substring(2,length);
     }

     @Override
     public boolean onSupportNavigateUp() {
         onBackPressed();
         return true;
     }

     @Override
     public void onBackPressed() {
         super.onBackPressed();
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