package com.yogandrn.bumdeskm.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.yogandrn.bumdeskm.R;

public class PetunjukPreorderActivity extends AppCompatActivity {

    private Button btnOK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_petunjuk_preorder);

        btnOK = findViewById(R.id.btn_ok_preorder);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PetunjukPreorderActivity.this, PreorderActivity.class));
                finish();
            }
        });
    }
}