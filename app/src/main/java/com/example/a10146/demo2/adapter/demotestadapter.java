package com.example.a10146.demo2.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a10146.demo2.Activity.smsRegister;
import com.example.a10146.demo2.R;
import com.example.a10146.demo2.professionalTest;

import java.util.List;

/**
 * Created by 10146 on 2018/5/16.
 */

public class demotestadapter extends RecyclerView.Adapter<demotestadapter.TestViewHolder>{
   

        private List<professionalTest> proTest;
        private Context context;

        public demotestadapter(List<professionalTest> proTest,Context context) {
            this.proTest = proTest;
            this.context=context;
        }




    //自定义ViewHolder类
        static class TestViewHolder extends RecyclerView.ViewHolder{

            CardView cardView;
            ImageView test_photo;
            TextView test_title;
            TextView test_desc;
            Button share;
            Button readMore;

            public TestViewHolder(final View itemView) {
                super(itemView);
                cardView= (CardView) itemView.findViewById(R.id.card_view);
                test_photo= (ImageView) itemView.findViewById(R.id.news_photo);
                test_title= (TextView) itemView.findViewById(R.id.news_title);
                test_desc= (TextView) itemView.findViewById(R.id.news_desc);
                share= (Button) itemView.findViewById(R.id.btn_share);
                readMore= (Button) itemView.findViewById(R.id.btn_more);
                //设置TextView背景为半透明
                test_title.setBackgroundColor(Color.argb(20, 0, 0, 0));

            }


        }
        @Override
        public demotestadapter.TestViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view= LayoutInflater.from(context).inflate(R.layout.testitem,viewGroup,false);
            TestViewHolder testviewholder=new TestViewHolder(view);
            return testviewholder;
        }

        @Override
        public void onBindViewHolder(demotestadapter.TestViewHolder personViewHolder, int i) {
            final int j=i;

            personViewHolder.test_photo.setImageResource(proTest.get(i).getPhotoId());
            personViewHolder.test_title.setText(proTest.get(i).getTitle());
            personViewHolder.test_desc.setText(proTest.get(i).getDesc());

            //为btn_share btn_readMore cardView设置点击事件
            personViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,smsRegister.class);
                    intent.putExtra("News",proTest.get(j));
                    context.startActivity(intent);
                }
            });

            personViewHolder.share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_SEND);
                    intent.setType("text/plain");
                    intent.putExtra(Intent.EXTRA_SUBJECT, "分享");
                    intent.putExtra(Intent.EXTRA_TEXT, proTest.get(j).getDesc());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(Intent.createChooser(intent, proTest.get(j).getTitle()));
                }
            });

            personViewHolder.readMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,smsRegister.class);
                    intent.putExtra("News",proTest.get(j));
                    context.startActivity(intent);
                }
            });


        }

        @Override
        public int getItemCount() {
            return proTest.size();
        }
    }
