package com.example.ltdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BangXepHangAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int TYPE_VIEW_ITEM =0;
    private final static int TYPE_VIEW_LOADING =1;
    private final ArrayList<NguoiChoi> mListNguoiChoi;
    private final LayoutInflater inflater ;
    private final Context context;
    public BangXepHangAdapter(ArrayList<NguoiChoi> mListNguoiChoi, Context context) {
        this.mListNguoiChoi = mListNguoiChoi;
        this.inflater= LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==TYPE_VIEW_ITEM){
            View itemView = this.inflater.inflate(R.layout.bang_xep_hang_item_view,parent, false);
            return new NguoiChoiViewHolder(itemView,this);
        }else if(viewType == TYPE_VIEW_LOADING){
            View itemView = this.inflater.inflate(R.layout.loading_item,parent,false);
            return new LoadingViewHolder(itemView);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NguoiChoiViewHolder){
            hienThiThongTin((NguoiChoiViewHolder)holder,position);
        }else if (holder instanceof  LoadingViewHolder){
            hienThiProgressBar((LoadingViewHolder)holder);
        }
    }

    @Override
    public int getItemCount() {
        return this.mListNguoiChoi==null?0:this.mListNguoiChoi.size();
    }

    class NguoiChoiViewHolder extends RecyclerView.ViewHolder {
        private final TextView tenDangNhapTextView;
        private final TextView diemCaoNhatTextView;
        private final BangXepHangAdapter mAdapter;

        public NguoiChoiViewHolder(@NonNull View itemView, BangXepHangAdapter mAdapter) {
            super(itemView);
            this.mAdapter = mAdapter;
            this.tenDangNhapTextView = itemView.findViewById(R.id.txtTenDangNhap_BangXepHang);
            this.diemCaoNhatTextView = itemView.findViewById(R.id.txtDiem_BangXepHang);
        }
    }
    class LoadingViewHolder extends  RecyclerView.ViewHolder{
        private  final ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.progressBar=itemView.findViewById(R.id.progressBar);
        }
    }
    void hienThiThongTin(NguoiChoiViewHolder holder,int position){
        NguoiChoi nguoiChoi = this.mListNguoiChoi.get(position);
        holder.tenDangNhapTextView.setText(nguoiChoi.getTenDangNhap());
        holder.diemCaoNhatTextView.setText(nguoiChoi.getDiemCaoNhat()+"");
    }
    void hienThiProgressBar(LoadingViewHolder holder)
    {

    }
}

