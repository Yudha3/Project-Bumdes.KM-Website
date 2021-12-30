package com.yogandrn.bumdeskm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yogandrn.bumdeskm.API.APIRequestData;
import com.yogandrn.bumdeskm.API.RetroServer;
import com.yogandrn.bumdeskm.Adapter.AdapterKeranjang;
import com.yogandrn.bumdeskm.Global;
import com.yogandrn.bumdeskm.Model.ModelKeranjang;
import com.yogandrn.bumdeskm.Model.ResponseKeranjang;
import com.yogandrn.bumdeskm.R;
import com.yogandrn.bumdeskm.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentKeranjangPreorder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentKeranjangPreorder extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnPreorder;
    private TextView txtEmpty;
    private RecyclerView rvKeranjang;
    private RecyclerView.Adapter adapterKeranjang;
    private SwipeRefreshLayout srlCartPreorder;
    private ProgressBar pbKeranjang;
    private List<ModelKeranjang> listKeranjang = new ArrayList<>();
    SessionManager sessionManager;

    public FragmentKeranjangPreorder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentKeranjangPreorder.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentKeranjangPreorder newInstance(String param1, String param2) {
        FragmentKeranjangPreorder fragment = new FragmentKeranjangPreorder();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_keranjang_preorder, container, false);
        rvKeranjang = view.findViewById(R.id.rv_keranjang_preorder);
        btnPreorder = view.findViewById(R.id.btn_preorder_now);
        txtEmpty = view.findViewById(R.id.txt_empty_keranjang_preorder);
        pbKeranjang = view.findViewById(R.id.progress_keranjang_preorder);
        srlCartPreorder = view.findViewById(R.id.srl_keranjang_preorder);
        rvKeranjang.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        sessionManager = new SessionManager(getContext());

        Global gb = new Global();
        gb.getTotalPreorder(String.valueOf(sessionManager.getSessionID()));

        retrieveCart();

        srlCartPreorder.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlCartPreorder.setRefreshing(true);
                retrieveCart();
                srlCartPreorder.setRefreshing(false);
            }
        });

        btnPreorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), BuatPreorderActivity.class));
            }
        });

        return  view;
    }

    private void retrieveCart() {
        pbKeranjang.setVisibility(View.VISIBLE);

        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponseKeranjang> getKeranjang = apiRequestData.readCartPreorder(String.valueOf(sessionManager.getSessionID()));

        getKeranjang.enqueue(new Callback<ResponseKeranjang>() {
            @Override
            public void onResponse(Call<ResponseKeranjang> call, Response<ResponseKeranjang> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Data tersedia")){
                    btnPreorder.setVisibility(View.VISIBLE);
                    listKeranjang = response.body().getData();

                    adapterKeranjang = new AdapterKeranjang(getContext(), listKeranjang);
                    rvKeranjang.setAdapter(adapterKeranjang);
                    adapterKeranjang.notifyDataSetChanged();
                    pbKeranjang.setVisibility(View.GONE);
                } else if (pesan.equals("Data tidak tersedia")) {
                    txtEmpty.setVisibility(View.VISIBLE);
                    rvKeranjang.setVisibility(View.GONE);
                    pbKeranjang.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseKeranjang> call, Throwable t) {
                pbKeranjang.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Terjadi Kesalahan : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}