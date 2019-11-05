package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnDangKy;
    private Button btnQuenMatKhau,btnDangNhap;
    public static final String EXTRA_MESSAGE =
            "com.example.doan.extra.MESSAGE";
    private EditText txtTenDangNhap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnQuenMatKhau = findViewById(R.id.btnQuenMatKhau);
        btnDangNhap= findViewById(R.id.btnDangNhap);
        txtTenDangNhap = findViewById(R.id.txtTenDangNhap);



    }



    public void DangKy(View view) {
        Intent intent= new Intent(this,TrangDangKy.class);
        startActivity(intent);
    }

    public void QuenMatKhau(View view) {
        Intent intent= new Intent(this,TrangQuenMatKhau.class);
        startActivity(intent);
    }

    public void DangNhap(View view) {
        Intent intent= new Intent(this,TrangChu.class);
        String message = txtTenDangNhap.getText().toString();
        intent.putExtra(EXTRA_MESSAGE,message);
        startActivity(intent);

    }

}
