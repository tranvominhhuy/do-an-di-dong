package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class TrangTraLoiCauHoi extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private TextView txtDe_TL;
    private Button btnDapAnA_TL,btnDapAnB_TL,btnDapAnC_TL,btnDapAnD_TL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_tra_loi_cau_hoi);

        btnDapAnA_TL= findViewById(R.id.btnDapAnA_TL);
        btnDapAnB_TL= findViewById(R.id.btnDapAnB_TL);
        btnDapAnC_TL= findViewById(R.id.btnDapAnC_TL);
        btnDapAnD_TL= findViewById(R.id.btnDapAnD_TL);
        txtDe_TL= findViewById(R.id.txtDe_TL);

    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
