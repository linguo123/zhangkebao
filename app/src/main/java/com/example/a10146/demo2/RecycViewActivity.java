package com.example.a10146.demo2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.a10146.demo2.adapter.demotestadapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by 10146 on 2018/5/16.
 */

public class RecycViewActivity extends AppCompatActivity{
   private demotestadapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testrecycler);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler01);
        List<String> list = new ArrayList<>();
        for (int i =0;i<10;i++) {
            list.add(String.format(Locale.CHINA, "第%03d", i));
        }
     //   adapter = new demotestadapter(this, list);
        recyclerView.setAdapter(adapter);

    }
}
