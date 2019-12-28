package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    private int idLV1, idLV2, idLV3, idLV4;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String token;

    private final static String FILE_NAME_SHAREREF = "com.example.ltdd";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_game);
        btnLinhVuc1 = findViewById(R.id.btnLinhVuc1);
        btnLinhVuc2 = findViewById(R.id.btnLinhVuc2);
        btnLinhVuc3 = findViewById(R.id.btnLinhVuc3);
        btnLinhVuc4 = findViewById(R.id.btnLinhVuc4);

        TextView textView = findViewById(R.id.txtTenDangNhap);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        token= sharedPreferences.getString("TOKEN","");
        Log.d("TOKEN",token);
        if (token==""){
            finish();
        }

        if(getSupportLoaderManager().getLoader(0)!=null)
        {
            getSupportLoaderManager().initLoader(0,null,this);

        }
        getSupportLoaderManager().restartLoader(0,null,this);



    }

    public void ChonLinhVuc1(View view) {
        Intent intent = new Intent(this,TrangTraLoiCauHoi.class);
        intent.putExtra("linh_vuc_id",idLV1);
        startActivity(intent);
    }

    public void ChonLinhVuc2(View view) {
        Intent intent = new Intent(this,TrangTraLoiCauHoi.class);
        intent.putExtra("linh_vuc_id",idLV2);
        startActivity(intent);
    }

    public void ChonLinhVuc3(View view) {
        Intent intent = new Intent(this,TrangTraLoiCauHoi.class);
        intent.putExtra("linh_vuc_id",idLV3);
        startActivity(intent);
    }

    public void ChonLinhVuc4(View view) {
        Intent intent = new Intent(this,TrangTraLoiCauHoi.class);
        intent.putExtra("linh_vuc_id",idLV4);
        startActivity(intent);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new LinhVucLoader(this,token);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {

        try {
            JSONObject jsonObject = new JSONObject(data);
            Log.d("linhvuc",data);
            JSONArray itemArray = jsonObject.getJSONArray("data");
            btnLinhVuc1.setText(itemArray.getJSONObject(0).getString("ten_linh_vuc"));
            idLV1 = itemArray.getJSONObject(0).getInt("id");
            btnLinhVuc2.setText(itemArray.getJSONObject(1).getString("ten_linh_vuc"));
            idLV2 = itemArray.getJSONObject(1).getInt("id");
            btnLinhVuc3.setText(itemArray.getJSONObject(2).getString("ten_linh_vuc"));
            idLV3 = itemArray.getJSONObject(2).getInt("id");
            btnLinhVuc4.setText(itemArray.getJSONObject(3).getString("ten_linh_vuc"));
            idLV4 = itemArray.getJSONObject(3).getInt("id");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
