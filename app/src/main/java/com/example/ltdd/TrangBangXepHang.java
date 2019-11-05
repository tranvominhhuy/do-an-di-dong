package com.example.ltdd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import java.util.ArrayList;

public class TrangBangXepHang extends AppCompatActivity  implements LoaderManager.LoaderCallbacks<String> {
    private final static ArrayList<NguoiChoi> mListNguoiChoi = new ArrayList<>();
    private BangXepHangAdapter bangXepHangAdapter;
    private RecyclerView mRecyclerView;
    private static final int PAGE_SIZE = 25;
    private int currentPage = 1;
    private int totalPage;
    private boolean isLastPage = false;
    private boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_bang_xep_hang);
        this.mRecyclerView = findViewById(R.id.rcv_bang_xep_hang);
        this.bangXepHangAdapter = new BangXepHangAdapter(mListNguoiChoi,this);
        this.mRecyclerView.setAdapter(bangXepHangAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        loadData(null);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager=(LinearLayoutManager)mRecyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if(!isLoading && !isLastPage){
                    if((visibleItemCount + firstVisibleItemPosition)>= totalItemCount
                            && firstVisibleItemPosition >=0
                            && totalItemCount >= PAGE_SIZE){
                        isLoading=true;
                        currentPage++;
                        mListNguoiChoi.add(null);
                        bangXepHangAdapter.notifyItemInserted(mListNguoiChoi.size() -1);

                        Bundle data = new Bundle();
                        data.putInt("page",currentPage);
                        data.putInt("limit",PAGE_SIZE);
                        loadData(data);
                    }
                }
            }
        });

    }
    private void loadData(@Nullable Bundle data){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo= null;
        if(connMgr !=null){
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if(networkInfo!=null && networkInfo.isConnected()){
            if(getSupportLoaderManager().getLoader(0)!=null){
                getSupportLoaderManager().restartLoader(0,data,this);
            }
            getSupportLoaderManager().initLoader(0,data,this);

        } else {
            taoThongBao("Không thể kết nối Internet").show();
        }
    }
    private AlertDialog taoThongBao(String s){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(s).setTitle("Lỗi");
        builder.setPositiveButton("Đồng Ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        return builder.create();
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
