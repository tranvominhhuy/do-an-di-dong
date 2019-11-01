package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;

public class TrangGame extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private Button btnLinhVuc1,btnLinhVuc2,btnLinhVuc3,btnLinhVuc4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_game);
        btnLinhVuc1 = findViewById(R.id.btnLinhVuc1);
        btnLinhVuc2 = findViewById(R.id.btnLinhVuc2);
        btnLinhVuc3 = findViewById(R.id.btnLinhVuc3);
        btnLinhVuc4 = findViewById(R.id.btnLinhVuc4);

        TextView textView = findViewById(R.id.txtTenDangNhap);

        if(getSupportLoaderManager().getLoader(0)!=null)
        {
            getSupportLoaderManager().initLoader(0,null,this);

        }
        getSupportLoaderManager().restartLoader(0,null,this);

    }

    public void ChonLinhVuc1(View view) {
        Intent intent = new Intent(this,TrangTraLoiCauHoi.class);
        startActivity(intent);
    }

    public void ChonLinhVuc2(View view) {
        Intent intent = new Intent(this,TrangTraLoiCauHoi.class);
        startActivity(intent);
    }

    public void ChonLinhVuc3(View view) {
        Intent intent = new Intent(this,TrangTraLoiCauHoi.class);
        startActivity(intent);
    }

    public void ChonLinhVuc4(View view) {
        Intent intent = new Intent(this,TrangTraLoiCauHoi.class);
        startActivity(intent);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new LinhVucLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemArray = jsonObject.getJSONArray("dsLinhVuc");
            btnLinhVuc1.setText(itemArray.getJSONObject(0).getString("ten_linh_vuc"));
            btnLinhVuc2.setText(itemArray.getJSONObject(1).getString("ten_linh_vuc"));
            btnLinhVuc3.setText(itemArray.getJSONObject(2).getString("ten_linh_vuc"));
            btnLinhVuc4.setText(itemArray.getJSONObject(3).getString("ten_linh_vuc"));
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
