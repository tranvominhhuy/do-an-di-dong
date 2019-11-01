package com.example.ltdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangQuenMatKhau extends AppCompatActivity {

    private Button btnLayLaiMatKhau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_quen_mat_khau);
        btnLayLaiMatKhau= findViewById(R.id.btnLayLaiMatKhau);
    }

    public void LayLaiMatKhau(View view) {
        Intent intent= new Intent(this,TrangChu.class);
        startActivity(intent);
    }
}
