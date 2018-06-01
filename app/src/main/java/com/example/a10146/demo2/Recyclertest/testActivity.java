package com.example.a10146.demo2.Recyclertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a10146.demo2.R;
import com.example.a10146.demo2.professionalTest;

/**
 * Created by 10146 on 2018/5/17.
 */

    public class testActivity extends AppCompatActivity {
        private ImageView testPhoto;
        private TextView testTitle;
        private TextView testDesc;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_test);

            testPhoto= (ImageView) findViewById(R.id.news_photo);
            testTitle= (TextView) findViewById(R.id.news_title);
            testDesc= (TextView) findViewById(R.id.news_desc);

            Intent intent=getIntent();

            professionalTest item= (professionalTest) intent.getSerializableExtra("News");
            testPhoto.setImageResource(item.getPhotoId());
            testTitle.setText(item.getTitle());
            testDesc.setText(item.getDesc());

        }
    }

