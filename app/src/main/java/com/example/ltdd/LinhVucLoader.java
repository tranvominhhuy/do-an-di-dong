package com.example.ltdd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class LinhVucLoader extends AsyncTaskLoader<String> {
    private String token;


    public LinhVucLoader(@NonNull Context context,String token) {
        super(context);
        this.token=token;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.doRequest("linh-vuc","GET",null,token);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
