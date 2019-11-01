package com.example.ltdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangShop extends AppCompatActivity {

    private Button btnTroVe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_shop);
        btnTroVe = findViewById(R.id.btnTroVe);
    }

    public void QuayVe(View view) {
        Intent intent = new Intent(this,TrangChu.class);
        startActivity(intent);
    }
}
