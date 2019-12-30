package com.example.ltdd;

import android.os.AsyncTask;

import java.util.HashMap;

public class SendEmailLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String email = strings[0];
        HashMap<String, String> param = new HashMap<>();
        param.put("email", email);
        return NetWorkUtils.postRequest("quen-mat-khau", param);
    }
}
