package com.example.ltdd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class GoiCreditLoader extends AsyncTaskLoader<String> {
    private String token;

    public GoiCreditLoader(@NonNull Context context,String token) {
        super(context);
        this.token=token;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.doRequest("goi-credit","GET",null,token);
    }
}
