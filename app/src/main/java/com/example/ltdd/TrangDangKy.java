package com.example.ltdd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class TrangDangKy extends AppCompatActivity {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private final static String FILE_NAME_SHAREREF = "com.example.ltdd";


    private Button btnDangKy_DK;
    private EditText txtTenDangNhap, txtMatKhau, txtXacNhanMatKhau, txtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_dang_ky);
        btnDangKy_DK = findViewById(R.id.btnLayLaiMatKhau);
        txtTenDangNhap = findViewById(R.id.txtTenDangNhap_DK);
        txtEmail = findViewById(R.id.txtEmail);
        txtMatKhau = findViewById(R.id.txtmatKhau_DK);
        txtXacNhanMatKhau = findViewById(R.id.txtXacNhanMatKhau);
        txtTenDangNhap.requestFocus();

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF,MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void DangKy_DK(View view) {
        String tenDangNhap = txtTenDangNhap.getText().toString();
        String matKhau=txtMatKhau.getText().toString();
        String xacNhanMatKhau= txtXacNhanMatKhau.getText().toString();
        String email= txtEmail.getText().toString();

        final ProgressDialog dialog= new ProgressDialog(this);
        dialog.setTitle("Đang đăng ký");
        dialog.setMessage(" Chờ xử lý ... ");
        dialog.show();


        new DangKyLoader(){
            @Override
            protected void onPostExecute(String s) {
                dialog.cancel();
                Log.d("DATA-JSON",s);
                try {
                    JSONObject json=new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    String msg = json.getString("msgg");
                    Log.d("success", success + "");
                    if (success) {
                        String token= "Bearer "+ json.getString("token");
                        editor.putString("TOKEN",token);
                        editor.commit();
                        finish();
                        Log.d("okok", "ok");
                    } else {
                        taoThongBao("Lỗi", msg);
                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }.execute(tenDangNhap,email,matKhau);

    }

    public void TrangChu(){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public AlertDialog taoThongBao(String tieuDe, String thongBao){
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
