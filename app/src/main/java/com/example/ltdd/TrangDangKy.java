package com.example.ltdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TrangDangKy extends AppCompatActivity {

    private Button btnDangKy_DK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_dang_ky);
        btnDangKy_DK = findViewById(R.id.btnLayLaiMatKhau);
    }

    public void DangKy_DK(View view) {
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
