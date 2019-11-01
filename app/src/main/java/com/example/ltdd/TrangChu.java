package com.example.ltdd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TrangChu extends AppCompatActivity {

    private Button btnTaiKhoan,btnGame,btnLichSu,btnBangXepHang,btnShop;
    private EditText txtTenDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        TextView textView = findViewById(R.id.txtTenDangNhap);
        textView.setText(message);

        btnShop= findViewById(R.id.btnShop);
        btnTaiKhoan = findViewById(R.id.btnTaiKhoan);
        btnGame= findViewById(R.id.btnGame);
        btnBangXepHang = findViewById(R.id.btnBangXepHang);
        btnLichSu = findViewById(R.id.btnLichSu);
    }

    public void TaiKhoan(View view) {
        Intent intent= new Intent(this,TrangTaiKhoan.class);
        startActivity(intent);
    }


    public void VaoGame(View view) {
        Intent intent = new Intent(this,TrangGame.class);
        startActivity(intent);
    }

    public void Shop(View view) {
        Intent intent = new Intent(this, TrangShop.class);
        startActivity(intent);
    }

    public void BangXepHang(View view) {
        Intent intent = new Intent(this, TrangBangXepHang.class);
        startActivity(intent);
    }

    public void LichSu(View view) {
        Intent intent = new Intent(this,TrangLichSu.class);
        startActivity(intent);
    }
}
