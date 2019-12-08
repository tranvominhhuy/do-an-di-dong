package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        return new CauHoiLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemArray = jsonObject.getJSONArray("data");
            for(int i=0;i<itemArray.length();i++) {
                txtDe_TL.setText(itemArray.getJSONObject(i).getString("noi_dung"));
                btnDapAnA_TL.setText(itemArray.getJSONObject(i).getString("phuong_an_A"));
                btnDapAnB_TL.setText(itemArray.getJSONObject(i).getString("phuong_an_B"));
                btnDapAnC_TL.setText(itemArray.getJSONObject(i).getString("phuong_an_C"));
                btnDapAnD_TL.setText(itemArray.getJSONObject(i).getString("phuong_an_D"));
            }
        } catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
