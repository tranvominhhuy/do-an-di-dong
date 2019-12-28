package com.example.ltdd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.HashMap;

public class BangXepHangLoader extends AsyncTaskLoader<String> {
    private  String token;
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public BangXepHangLoader(@NonNull Context context, String token) {
        super(context);
        this.token=token;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.doRequest("xep-hang","GET",null,token);
    }
}
