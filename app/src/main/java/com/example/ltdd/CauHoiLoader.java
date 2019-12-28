package com.example.ltdd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CauHoiLoader extends AsyncTaskLoader<String> {

    private String token;
    private int id;

    public CauHoiLoader(@NonNull Context context,String token, int id) {
        super(context);
        this.token=token;
        this.id = id;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.doRequest("cau-hoi/"+id,"GET",null,token);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
