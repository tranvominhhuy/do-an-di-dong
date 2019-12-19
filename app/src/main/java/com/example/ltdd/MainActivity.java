package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final static String FILE_NAME_SHAREREF = "com.example.ltdd";

    private Button btnDangKy;
    private Button btnQuenMatKhau,btnDangNhap;
    private EditText txtTenDangNhap,txtMatKhau;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnQuenMatKhau = findViewById(R.id.btnQuenMatKhau);
        btnDangNhap= findViewById(R.id.btnDangNhap);
        txtTenDangNhap = findViewById(R.id.txtTenDangNhap);
        txtMatKhau=findViewById(R.id.txtMatKhau);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String token= sharedPreferences.getString("TOKEN","");
        Log.d("TOKEN",token);
        if (token!=""){
           TrangChu();
        }
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


        String tenDangNhap=txtTenDangNhap.getText().toString();
        String matKhau=txtMatKhau.getText().toString();

        final ProgressDialog dialog= new ProgressDialog(this);
        dialog.setTitle("Đang đăng nhập");
        dialog.setMessage(" Chờ xử lý ... ");
        dialog.show();

        new DangNhapLoader1(){
            @Override
            protected void onPostExecute(String s) {
                dialog.cancel();
                Log.d("DATA-JSON",s);
                try {
                    JSONObject json=new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    if(success){
                        String token= "Bearer "+ json.getString("token");
                        editor.putString("TOKEN",token);
                        editor.commit();
                        TrangChu();
                    } else {
                        String msg = json.getString("msg");
                        taoThongBao("Thông Báo",msg).show();
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.execute(tenDangNhap,matKhau);

    }

    public void TrangChu(){
        Intent intent= new Intent(this,TrangChu.class);
        startActivity(intent);
    }

    public AlertDialog taoThongBao(String tieuDe,String thongBao){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(thongBao).setTitle(tieuDe);
        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

}
