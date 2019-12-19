package com.example.ltdd;

import android.os.AsyncTask;

import java.util.HashMap;

public class DangNhapLoader1 extends AsyncTask <String,Void,String>{
    @Override
    protected String doInBackground(String... strings) {
        String tenDangNhap= strings[0];
        String password= strings[1];
        HashMap<String,String> params = new HashMap<>();
        params.put("ten_dang_nhap",tenDangNhap);
        params.put("password",password);
        return NetWorkUtils.postRequest("dang-nhap",params);

    }
}
