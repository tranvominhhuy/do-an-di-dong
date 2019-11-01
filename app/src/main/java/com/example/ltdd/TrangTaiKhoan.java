package com.example.ltdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangTaiKhoan extends AppCompatActivity {

    private Button btnCapNhat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_tai_khoan);
        btnCapNhat= findViewById(R.id.btnCapNhat_TK);
    }

    public void CapNhat(View view) {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
