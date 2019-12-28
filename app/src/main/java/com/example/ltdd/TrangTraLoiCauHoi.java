package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrangTraLoiCauHoi extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String token;

    private final static String FILE_NAME_SHAREREF = "com.example.ltdd";

    private TextView txtDe_TL;
    private Button btnDapAnA_TL,btnDapAnB_TL,btnDapAnC_TL,btnDapAnD_TL;
    private int idLinhVuc;
    private String dapAn;

    private SQLiteHelper sqLiteHelper;
    private CauHoi cauHoi;

    private int position = 0;
    private int stt = 1;
    private final static int DIEM = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_tra_loi_cau_hoi);

        btnDapAnA_TL= findViewById(R.id.btnDapAnA_TL);
        btnDapAnB_TL= findViewById(R.id.btnDapAnB_TL);
        btnDapAnC_TL= findViewById(R.id.btnDapAnC_TL);
        btnDapAnD_TL= findViewById(R.id.btnDapAnD_TL);
        txtDe_TL= findViewById(R.id.txtDe_TL);
        sqLiteHelper = new SQLiteHelper(this);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF,MODE_PRIVATE);
        editor = sharedPreferences.edit();

        token= sharedPreferences.getString("TOKEN","");
        Log.d("TOKEN",token);
        if (token==""){
            finish();
        }

        Intent intent = getIntent();
        idLinhVuc = intent.getIntExtra("linh_vuc_id",1);
        Log.d("linhvucid",idLinhVuc+"");


        if(getSupportLoaderManager().getLoader(0)!=null)
        {
            getSupportLoaderManager().initLoader(0,null,this);

        }
        getSupportLoaderManager().restartLoader(0,null,this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Nhấn để bắt đầu trò chơi").setTitle("Thông báo");
        builder.setPositiveButton("Start", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnDapAnA_TL.setEnabled(true);
                btnDapAnB_TL.setEnabled(true);
                btnDapAnC_TL.setEnabled(true);
                btnDapAnD_TL.setEnabled(true);
                //mTimeTraLoiCauHoi.start();
            }
        });
        builder.create().show();


    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new CauHoiLoader(this,token,idLinhVuc);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject object = new JSONObject(data);
            Log.d("cauhoi", data);
            JSONArray itemArrays = object.getJSONArray("data");
            if (idLinhVuc != sqLiteHelper.KtraIdLinhVucDaTonTai(idLinhVuc)) {
                for (int i = 0; i < itemArrays.length(); i++) {
                    JSONObject vitri = itemArrays.getJSONObject(i);
                    String noidung = vitri.getString("noi_dung");
                    int linh_vuc_id = vitri.getInt("linh_vuc_id");
                    String a = vitri.getString("phuong_an_A");
                    String b = vitri.getString("phuong_an_B");
                    String c = vitri.getString("phuong_an_C");
                    String d = vitri.getString("phuong_an_D");
                    String dap_an = vitri.getString("dap_an");
                    cauHoi = new CauHoi(noidung, linh_vuc_id, a, b, c, d, dap_an);
                    sqLiteHelper.themMoiCauHoi(cauHoi);
                }
            }
            hienCauHoi(position, stt);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
    public void hienCauHoi(int i, int stt)
    {
        ArrayList<CauHoi> mListCauHoi = sqLiteHelper.layDanhSach(idLinhVuc);
        if(i < mListCauHoi.size()) {
            //txtSttCauHoi.setText("Câu hỏi số "+ stt + ": ");
            txtDe_TL.setText(mListCauHoi.get(i).getNoi_dung());
            btnDapAnA_TL.setText(mListCauHoi.get(i).getPhuong_an_a());
            btnDapAnB_TL.setText(mListCauHoi.get(i).getPhuong_an_b());
            btnDapAnC_TL.setText(mListCauHoi.get(i).getPhuong_an_c());
            btnDapAnD_TL.setText(mListCauHoi.get(i).getPhuong_an_d());
            dapAn = mListCauHoi.get(i).getDap_an();
        }
        else
        {
            taoThongBao("Thông báo","Trò chơi đã kết thúc").show();
            //mTimeTraLoiCauHoi.cancel();
        }
    }

    public AlertDialog taoThongBao(String tieuDe, String thongBao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(thongBao).setTitle(tieuDe);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        return builder.create();
    }
    public int idDapAn (String dapAn)
    {
        switch (dapAn)
        {
            case "A": return btnDapAnA_TL.getId();
            case "B": return btnDapAnB_TL.getId();
            case "C": return btnDapAnC_TL.getId();
            case "D": return btnDapAnD_TL.getId();
        }
        return 0;
    }

    public void EnableBtnPhuongAn (Boolean b)
    {
        btnDapAnA_TL.setEnabled(b);
        btnDapAnB_TL.setEnabled(b);
        btnDapAnC_TL.setEnabled(b);
        btnDapAnD_TL.setEnabled(b);
    }

    public void traLoiCauHoi(View view) {
        EnableBtnPhuongAn(false);
        //mTimeTraLoiCauHoi.cancel();
        if(view.getId() == idDapAn(dapAn))
        {
            view.setBackgroundResource(R.drawable.tra_loi_dung);
//            int diem = Integer.parseInt(txtDiem.getText().toString());
//            diem += DIEM;
//            txtDiem.setText(diem+"");
        }
        else {
            if (btnDapAnA_TL.getId() == idDapAn(dapAn)) {
                btnDapAnA_TL.setBackgroundResource(R.drawable.tra_loi_dung);
                view.setBackgroundResource(R.drawable.tra_loi_sai);
            } else if (btnDapAnB_TL.getId() == idDapAn(dapAn)) {
                btnDapAnB_TL.setBackgroundResource(R.drawable.tra_loi_dung);
                view.setBackgroundResource(R.drawable.tra_loi_sai);
            } else if (btnDapAnC_TL.getId() == idDapAn(dapAn)) {
                btnDapAnC_TL.setBackgroundResource(R.drawable.tra_loi_dung);
                view.setBackgroundResource(R.drawable.tra_loi_sai);
            } else {
                btnDapAnD_TL.setBackgroundResource(R.drawable.tra_loi_dung);
                view.setBackgroundResource(R.drawable.tra_loi_sai);
            }
            //txtSoMang.setText(Integer.parseInt(txtSoMang.getText().toString()) - 1 +"");

        }
        CountDownTimer countDownTimer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                Reset();
                hienCauHoi(position++,stt++);
                //mTimeTraLoiCauHoi.start();
            }
        }.start();
    }
    public void Reset() {
        EnableBtnPhuongAn(true);
        btnDapAnA_TL.setBackgroundResource(R.drawable.tra_loi);
        btnDapAnB_TL.setBackgroundResource(R.drawable.tra_loi);
        btnDapAnC_TL.setBackgroundResource(R.drawable.tra_loi);
        btnDapAnD_TL.setBackgroundResource(R.drawable.tra_loi);
    }
}
