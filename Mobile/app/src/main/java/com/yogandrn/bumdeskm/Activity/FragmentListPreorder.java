package com.yogandrn.bumdeskm.Activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.yogandrn.bumdeskm.API.APIRequestData;
import com.yogandrn.bumdeskm.API.RetroServer;
import com.yogandrn.bumdeskm.Adapter.AdapterPreorder;
import com.yogandrn.bumdeskm.Model.ModelPreorder;
import com.yogandrn.bumdeskm.Model.ResponsePreorder;
import com.yogandrn.bumdeskm.R;
import com.yogandrn.bumdeskm.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListPreorder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListPreorder extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rvPreorder;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<ModelPreorder> listPesanan =  new ArrayList<>();
    private TextView txtEmpty;
    private SwipeRefreshLayout srlPreorder;
    private ProgressBar pbPreorder;
    SessionManager sessionManager;

    public FragmentListPreorder() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentListPreorder.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentListPreorder newInstance(String param1, String param2) {
        FragmentListPreorder fragment = new FragmentListPreorder();
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
        View view = inflater.inflate(R.layout.fragment_list_preorder, container, false);
        rvPreorder = view.findViewById(R.id.rv_list_preorder);
        txtEmpty = view.findViewById(R.id.txt_empty_list_preorder);
        srlPreorder = view.findViewById(R.id.srl_list_preorder);
        pbPreorder = view.findViewById(R.id.progress_list_preorder);

        rvPreorder.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));

        sessionManager = new SessionManager(getContext());

        getListPreorder();

        return  view;
    }

    private void getListPreorder() {
        pbPreorder.setVisibility(View.VISIBLE);
        APIRequestData apiRequestData = RetroServer.koneksiRetrofit().create(APIRequestData.class);
        Call<ResponsePreorder> callPreorder = apiRequestData.readPreorder(String.valueOf(sessionManager.getSessionID()));
        callPreorder.enqueue(new Callback<ResponsePreorder>() {
            @Override
            public void onResponse(Call<ResponsePreorder> call, Response<ResponsePreorder> response) {
                String pesan = response.body().getPesan();
                if (pesan.equals("Data tersedia")) {
                    listPesanan = response.body().getData();

                    adapter = new AdapterPreorder(getContext(), listPesanan);
                    rvPreorder.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    pbPreorder.setVisibility(View.GONE);
                } else if (pesan.equals("Data tidak tersedia")) {
                    txtEmpty.setVisibility(View.VISIBLE);
                    pbPreorder.setVisibility(View.GONE);
                }

            }

            @Override
            public void onFailure(Call<ResponsePreorder> call, Throwable t) {
                Toast.makeText(getContext(), "Terjadi kesalahan :\n" +t.getMessage(), Toast.LENGTH_SHORT).show();
                pbPreorder.setVisibility(View.GONE);
            }
        });
    }
}