package com.example.ltdd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TrangChu extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor editor;

    private final static String FILE_NAME_SHAREREF = "com.example.ltdd";

    private Button btnTaiKhoan,btnGame,btnLichSu,btnBangXepHang,btnShop,btnDangXuat;
    private EditText txtTenDangNhap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        TextView textView = findViewById(R.id.txtTenDangNhap);

        btnShop= findViewById(R.id.btnShop);
        btnTaiKhoan = findViewById(R.id.btnTaiKhoan);
        btnGame= findViewById(R.id.btnGame);
        btnBangXepHang = findViewById(R.id.btnBangXepHang);
        btnLichSu = findViewById(R.id.btnLichSu);
        btnDangXuat=findViewById(R.id.btnDangXuat);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF,MODE_PRIVATE);
        editor = sharedPreferences.edit();
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

    public void DangXuat(View view) {
        //Intent intent = new Intent(this,MainActivity.class);
        taoThongBao("Thông báo","Bạn chắc chắn đăng xuất tài khoản?").show();
        //startActivity(intent);
        editor.clear();
        editor.commit();
       // finish();
    }
    public AlertDialog taoThongBao(String tieuDe, String thongBao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(thongBao).setTitle(tieuDe);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TrangChu();
            }
        });
        return builder.create();
    }
    public void TrangChu(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
