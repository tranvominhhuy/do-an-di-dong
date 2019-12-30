package com.example.ltdd;

import android.os.AsyncTask;

import java.util.HashMap;

public class DangKyLoader extends AsyncTask <String,Void,String>{
    @Override
    protected String doInBackground(String... strings) {
        String tenDangNhap= strings[0];
        String email= strings[1];
        String matKhau= strings[2];
        HashMap<String,String> params = new HashMap<>();
        params.put("ten_dang_nhap",tenDangNhap);
        params.put("email",email);
        params.put("password",matKhau);
        return NetWorkUtils.postRequest("dang-ky",params);

    }
}