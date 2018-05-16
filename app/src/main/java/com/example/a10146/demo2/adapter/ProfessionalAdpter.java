package com.example.a10146.demo2.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a10146.demo2.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//自己写的adapter当中有问题还没找出来
public class ProfessionalAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//    public ProfessionalAdpter(LayoutInflater mLayoutInflater, Context mContext) {
//        this.mLayoutInflater = mLayoutInflater;
//        this.mContext = mContext;
//    }vv

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;
    public ProfessionalAdpter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_selecttest, parent, false));

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ImageViewHolder) holder).mTextView.setText(mTitles[position]);

    }

    @Override
    public int getItemViewType(int position) {
        return position ;
    }

    @Override
    public int getItemCount() {
        return  mTitles.length;
    }


    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view)
        TextView mTextView;
        @BindView(R.id.image_view)
        ImageView mImageView;

        ImageViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.cv_item)
        void onItemClick() {
            Log.d("ImageViewHolder", "onClick--> position = " + getPosition());
        }
    }
}



