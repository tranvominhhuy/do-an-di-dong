package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TrangShop extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{

    private Button btnTroVe,btnMua1,btnMua2,btnMua3,btnMua4;
    private TextView txtCredit1,txtCredit2,txtCredit3,txtCredit4,txtCredit_TL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_shop);
        btnTroVe = findViewById(R.id.btnTroVe);
        btnMua1=findViewById(R.id.btnMua1);
        btnMua2=findViewById(R.id.btnMua2);
        btnMua3=findViewById(R.id.btnMua3);
        btnMua4=findViewById(R.id.btnMua4);
        txtCredit1=findViewById(R.id.txtCredit1);
        txtCredit2=findViewById(R.id.txtCredit2);
        txtCredit3=findViewById(R.id.txtCredit3);
        txtCredit4=findViewById(R.id.txtCredit4);
        txtCredit_TL=findViewById(R.id.txtCredit_TL);

        if(getSupportLoaderManager().getLoader(0)!=null)
        {
            getSupportLoaderManager().initLoader(0,null,this);

        }
        getSupportLoaderManager().restartLoader(0,null,this);
    }

    public void QuayVe(View view) {
        Intent intent = new Intent(this,TrangChu.class);
        startActivity(intent);
    }

    public void MuaCredit1(View view) {
    }

    public void MuaCredit2(View view) {
    }

    public void MuaCredit3(View view) {
    }

    public void MuaCredit4(View view) {
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new GoiCreditLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("credit",data);
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemArray = jsonObject.getJSONArray("data");
            txtCredit1.setText(itemArray.getJSONObject(0).getString("Ten_goi"));
            txtCredit2.setText(itemArray.getJSONObject(1).getString("Ten_goi"));
            txtCredit3.setText(itemArray.getJSONObject(2).getString("Ten_goi"));
            txtCredit4.setText(itemArray.getJSONObject(3).getString("Ten_goi"));
        } catch (JSONException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
