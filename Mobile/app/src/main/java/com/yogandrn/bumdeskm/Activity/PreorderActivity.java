package com.yogandrn.bumdeskm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.tabs.TabLayout;
import com.yogandrn.bumdeskm.R;
import com.yogandrn.bumdeskm.SessionManager;

public class PreorderActivity extends AppCompatActivity {

    private Button btnBelanja;
    private TabLayout tabPreorder;
    private FrameLayout framePreorder;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preorder);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Pre-Order");

        btnBelanja = findViewById(R.id.btnBelanja_preorder);
        tabPreorder = findViewById(R.id.tabs_preorder);

        tabPreorder.addTab(tabPreorder.newTab().setText("My Preorder"));
        tabPreorder.addTab(tabPreorder.newTab().setText("Keranjang"));

        getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_preorder, new FragmentListPreorder()).commit();

        tabPreorder.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()){
                    case 0 :
                        fragment = new FragmentListPreorder();
                        break;
                    case 1:
                        fragment =  new FragmentKeranjangPreorder();
                        break;
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.framelayout_preorder, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        btnBelanja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent belanja = new Intent(PreorderActivity.this, KatalogActivity.class);
                startActivity(belanja);
            }
        });

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
}