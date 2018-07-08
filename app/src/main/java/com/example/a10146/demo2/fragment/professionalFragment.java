package com.example.a10146.demo2.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a10146.demo2.HttpConnectionUtil;
import com.example.a10146.demo2.JsonContainer;
import com.example.a10146.demo2.ProQuestion.ProQueActivity;
import com.example.a10146.demo2.R;
import com.example.a10146.demo2.Recyclertest.demotestadapter;
import com.example.a10146.demo2.TesQuestion.DownloadUtil;
import com.example.a10146.demo2.TesQuestion.TestQueActivity;
import com.example.a10146.demo2.adapter.ProfessionalAdpter;
import com.example.a10146.demo2.professionalTest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.ButterKnife;

public class professionalFragment extends Fragment {
    ProfessionalAdpter madapter;

   private RelativeLayout tabArticle;
   private RelativeLayout tabActive;
   private TextView tabArticleTv;
   private  View tabArticleLine;
   private  TextView tabActiveTv;
   private  View tabActiveLine;

    LinearLayout  tipscontent;
    RecyclerView recyclerView;

    private List<professionalTest> newsList;

    private demotestadapter readapter;
    private String postdataUrl;
    String dataversion;
    String version="20180707";

    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // TODO Auto-generated method stub

          View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_profession, null);
            ButterKnife.bind(getActivity(), view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());



       recyclerView= (RecyclerView) view.findViewById(R.id.profssion_recyclerView);
//职业测评tab的切换
        tabArticle = (RelativeLayout) view.findViewById(R.id.tab_article);
        tabActive =(RelativeLayout)view.findViewById(R.id.tab_active);
        tabArticleTv = (TextView) view.findViewById(R.id.tab_article_tv);
        tabArticleLine =view.findViewById(R.id.tab_article_line);
        tabActiveTv = (TextView) view.findViewById(R.id.tab_active_tv);
        tabActiveLine = view.findViewById(R.id.tab_active_line);

        initPersonData();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        readapter=new demotestadapter(newsList,getActivity());
        recyclerView.setAdapter(readapter);
        radapterClicklistener();
        init();
        return view;
    }
    //切换页面的初始化
    private void init() {
        initTab();
   //    initViewpager();
                }
    private void initTab() {
                    tabArticleTv.setText("能力测试");
                    tabArticleTv.setTextColor(Color.parseColor("#c0c0c0"));
                    tabArticleLine.setVisibility(View.VISIBLE);
                    tabActiveTv.setText("脑力训练");
                    tabActiveTv.setTextColor(Color.parseColor("#c0c0c0"));
                    tabActiveLine.setVisibility(View.GONE);
                    tabArticleTv.setOnClickListener(new tabClick(0));
                    tabActiveTv.setOnClickListener(new tabClick(1));
                }

    private class tabClick implements View.OnClickListener {
        private int index = 0;
        public tabClick(int i) {
            index = i;
        }
        public void onClick(View v) {
            switch (index) {
                case 0:
                    tabArticleTv.setTextColor(Color.parseColor("#000000"));
                    tabActiveTv.setTextColor(Color.parseColor("#c0c0c0"));
                    tabArticleLine.setVisibility(View.VISIBLE);
                    tabActiveLine.setVisibility(View.GONE);
                    recyclerView.setVisibility(recyclerView.VISIBLE);
                    break;
                case 1:
                    tabArticleTv.setTextColor(Color.parseColor("#c0c0c0"));
                    tabActiveTv.setTextColor(Color.parseColor("#000000"));
                    tabArticleLine.setVisibility(View.GONE);
                    tabActiveLine.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(recyclerView.GONE);
                    break;
            }
          //  viewPager.setCurrentItem(index);
        }
    }

//Recycleiew的点击事件监听
    public void  radapterClicklistener(){
        readapter.setOnItemClickListener(new demotestadapter.OnItemClickListener(){
        @Override
        public void onClick(int position) {
            Log.d("position", String.valueOf(position));
            switch (position){
                case 0:
                    ProVersion(00);
                    break;
                case 1:
                    ProVersion(01);
                    break;
                case 2:
                    ProVersion(02);
                    break;
                case 3:
                    ProVersion(03);
                    break;
            }
        }
    });
    }
    @Override
    public void onStart() {

        super.onStart();

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void initPersonData() {
        newsList =new ArrayList<>();

        newsList.add(new professionalTest(getString(R.string.news_one_title),
                getString(R.string.news_one_desc),R.mipmap.news_one));

        newsList.add(new professionalTest(getString(R.string.news_two_title),
                getString(R.string.news_two_desc),R.mipmap.news_two));

        newsList.add(new professionalTest(getString(R.string.news_three_title),
                getString(R.string.news_three_desc),R.mipmap.news_three));

        newsList.add(new professionalTest(getString(R.string.news_four_title),
                getString(R.string.news_four_desc),R.mipmap.news_four));
    }
 public  void  ProVersion(final int value){
     postdataUrl = "https://extlife.xyz/ability/getversion";
     String posthttp = HttpConnectionUtil.getHttp().getRequset(postdataUrl,null);
     JsonContainer<String> rootModel = new JsonContainer<String>();
     Gson gson = new Gson();
     rootModel = gson.fromJson(posthttp, new TypeToken<JsonContainer<String>>() {
     }.getType());
     if (rootModel.getStatus().equals("Success")) {
         dataversion= rootModel.getData();

         if (!Objects.equals(dataversion, version)){
             String url="https://extlife.xyz/ability/getcache";
             String path ="/data/data/com.example.a10146.demo2/databases/";
             String name =String.format("question%02d.db",value);
             DownloadUtil.get().download(url,path,name,new DownloadUtil.OnDownloadListener() {
                 @Override
                 public void onDownloadSuccess(File file) {
                     Log.e("DownloadSuccess","Success");
                     Intent intent = new Intent();
                     intent.putExtra("question", value);
                     intent.setClass(getActivity(), ProQueActivity.class);
                     professionalFragment.this.startActivity(intent);

                 }
                 @Override
                 public void onDownloading(int progress) {}
                 @Override
                 public void onDownloadFailed(Exception e) {
                     Log.e("downloadFailed","fail");
                 }
             });
         }else {
             Intent intent = new Intent();
             intent.putExtra("question", value);
             intent.setClass(getActivity(), TestQueActivity.class);
             professionalFragment.this.startActivity(intent);
         }


     }


 }
}