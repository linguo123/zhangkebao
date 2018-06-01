package com.example.a10146.demo2.Recyclertest;

/**
 * Created by 10146 on 2018/5/17.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.a10146.demo2.R;
import com.example.a10146.demo2.professionalTest;

import java.util.ArrayList;
import java.util.List;

public class testActivityMain extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<professionalTest> newsList;

    private demotestadapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_main);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView= (RecyclerView) findViewById(R.id.profssion_recyclerView);

        initPersonData();

        adapter=new demotestadapter(newsList,testActivityMain.this);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);

    }



    private void initPersonData() {
        newsList =new ArrayList<>();

        newsList.add(new professionalTest(getString(R.string.news_one_title),
                getString(R.string.news_one_desc),R.mipmap.news_one));

        newsList.add(new professionalTest(getString(R.string.news_two_title),
                getString(R.string.news_two_desc),R.mipmap.news_two));

        newsList.add(new professionalTest(getString(R.string.news_three_title),
                getString(R.string.news_three_desc),R.mipmap.news_three));

        newsList.add(new professionalTest(getString(R.string.news_four_title),getString(R.string.news_four_desc),R.mipmap.news_four));
    }


}