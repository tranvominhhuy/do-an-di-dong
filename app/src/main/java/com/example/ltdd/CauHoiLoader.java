package com.example.ltdd;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class CauHoiLoader extends AsyncTaskLoader<String> {
    public CauHoiLoader(@NonNull Context context) {
        super(context);
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return null;
    }
}
