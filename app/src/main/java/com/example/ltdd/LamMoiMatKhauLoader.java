package com.example.ltdd;

import android.os.AsyncTask;

import java.util.HashMap;

public class LamMoiMatKhauLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        HashMap<String, String> param = new HashMap<>();
        param.put("email", strings[0]);
        param.put("ma_xac_nhan", strings[1]);
        param.put("mat_khau", strings[2]);
        return NetWorkUtils.postRequest("lam-moi-mat-khau", param);
    }
}
