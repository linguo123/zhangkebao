package com.example.a10146.demo2;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 10146 on 2018/4/13.
 */

public class homePage extends AppCompatActivity implements View.OnClickListener {
    private ImageButton homePge_btn;
    private ImageButton questionTest;
    private ImageButton abilityTest;
    private ImageButton Course;
    private ImageButton personalCenter;

    private ViewPager viewPager;

    private PagerAdapter pagerAdapter;
    private List<View> mvies = new ArrayList<>();
    //TAB
    private LinearLayout  TabhomePage;
    private LinearLayout  TabquestionTest;
    private LinearLayout  TababilityTest;
    private LinearLayout  TabCourse;
    private LinearLayout  TabpersonalCenter;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.homepage);
        homePge_btn = (ImageButton) findViewById(R.id.id_tab_homepage_img);
        initView();

        initEvents();

//        setSelect(0);
  }
    private void initEvents() {
        TabhomePage.setOnClickListener(this);
        TababilityTest.setOnClickListener(this);
        TabquestionTest.setOnClickListener(this);
        TabCourse.setOnClickListener(this);
        TabpersonalCenter.setOnClickListener(this);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int arg0) {
                int currentItem =viewPager.getCurrentItem();
                resetImge();
                switch (currentItem){
                    case 0:
                        homePge_btn.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        questionTest.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        abilityTest.setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        Course.setImageResource(R.drawable.tab_settings_pressed);
                        break;
                    case 4:
                        personalCenter.setImageResource(R.drawable.tab_address_pressed);
                        break;



                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void initView() {
        //初始化
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        //tabs
        TabhomePage = (LinearLayout) findViewById(R.id.id_tab_homepage);
        TabquestionTest = (LinearLayout) findViewById(R.id.id_tab_questionTest);
        TababilityTest = (LinearLayout) findViewById(R.id.id_tab_abilityTest);
        TabCourse = (LinearLayout) findViewById(R.id.id_tab_Course);
        TabpersonalCenter = (LinearLayout) findViewById(R.id.id_tab_personalCenter);

        //ImageBUttom
        homePge_btn= (ImageButton) findViewById(R.id.id_tab_homepage_img);
        questionTest= (ImageButton) findViewById(R.id.id_tab_questionTest_img);
        abilityTest= (ImageButton) findViewById(R.id.id_tab_abilityTest_img);
        Course= (ImageButton) findViewById(R.id.id_tab_Course_mig);
        personalCenter = (ImageButton) findViewById(R.id.id_tab_personalCenter_img);

        //初始化布局
        LayoutInflater inflater = LayoutInflater.from(this);
        View tab01 = inflater.inflate(R.layout.tab01,null);
        View tab02 = inflater.inflate(R.layout.tab02,null);
        View tab03 = inflater.inflate(R.layout.tab03,null);
        View tab04 = inflater.inflate(R.layout.tab04,null);
        View tab05 = inflater.inflate(R.layout.tab05,null);

        mvies.add(tab01);
        mvies.add(tab02);
        mvies.add(tab03);
        mvies.add(tab04);
        mvies.add(tab05);

        pagerAdapter = new PagerAdapter() {
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
              container.removeView(mvies.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
              View view = mvies.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                return arg0 == arg1 ;
            }
            @Override
            public int getCount() {
                return mvies.size();
            }


        };
       viewPager.setAdapter(pagerAdapter);




    }
    @Override
    public void onClick(View v) {
        resetImge();
    switch (v.getId()){
        case R.id.id_tab_homepage:
            viewPager.setCurrentItem(0);
            homePge_btn.setImageResource(R.drawable.tab_weixin_pressed);
            break;
        case R.id.id_tab_questionTest:
            viewPager.setCurrentItem(1);
            questionTest.setImageResource(R.drawable.tab_find_frd_pressed);
            break;
        case R.id.id_tab_abilityTest:
            viewPager.setCurrentItem(2);
            abilityTest.setImageResource(R.drawable.tab_address_pressed);
            break;
        case R.id.id_tab_Course:
            viewPager.setCurrentItem(3);
            Course.setImageResource(R.drawable.tab_settings_pressed);
            break;
        case R.id.id_tab_personalCenter:
            viewPager.setCurrentItem(4);
            personalCenter.setImageResource(R.drawable.tab_address_pressed);
            break;
    }
    }
    private  void  resetImge(){
        homePge_btn.setImageResource(R.drawable.tab_weixin_normal);
        questionTest.setImageResource(R.drawable.tab_find_frd_normal);
        abilityTest.setImageResource(R.drawable.tab_address_normal);
        Course.setImageResource(R.drawable.tab_settings_normal);
        personalCenter.setImageResource(R.drawable.tab_address_normal);


    };

}
