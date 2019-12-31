package com.example.ltdd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

public class TrangQuenMatKhau extends AppCompatActivity {
    private EditText txtEmailQuenMK, txtMaXacNhan, txtMatKhauMoi, txtXacNhanMKMoi;
    private View layoutGuiEmail, layoutDoiMK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_quen_mat_khau);

        txtEmailQuenMK = findViewById(R.id.txtEmailQuenMK);
        txtMaXacNhan = findViewById(R.id.txtMaXacNhan);
        txtMatKhauMoi = findViewById(R.id.txtMatKhauMoi);
        txtXacNhanMKMoi = findViewById(R.id.txtXacNhanMKMoi);

        layoutGuiEmail = (View) findViewById(R.id.layoutSendEmail);
        layoutDoiMK = (View) findViewById(R.id.layoutDoiMK);

    }

    public void guiEmail(View view) {
        String email = txtEmailQuenMK.getText().toString();

        new SendEmailLoader(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject json = new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    String msg = json.getString("msg");

                    if (success) {
                        layoutGuiEmail.setVisibility(View.GONE);
                        layoutDoiMK.setVisibility(View.VISIBLE);
                    } else {
                        taoThongBao("Lỗi", msg).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(email);
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

    public void lamMoiMatKhau(View view) {
        String maXacNhan = txtMaXacNhan.getText().toString();
        String email = txtEmailQuenMK.getText().toString();
        String matKhau = txtMatKhauMoi.getText().toString();
        String xacNhanMatKhau = txtXacNhanMKMoi.getText().toString();
        if (!matKhau.equals(xacNhanMatKhau)) {
            taoThongBao("Lỗi", "Mật khẩu và mật khẩu xác nhận không giống nhau").show();
            return;
        }

        new LamMoiMatKhauLoader() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject json  = new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    String msg = json.getString("msg");
                    Log.d("dataabc-json", s + "");
                    if (success) {
                        finish();
                    } else {
                        taoThongBao("Lỗi", msg).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(email, maXacNhan, matKhau);
    }
}
