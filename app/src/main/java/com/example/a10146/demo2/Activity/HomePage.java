package com.example.a10146.demo2.Activity;


import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a10146.demo2.R;
import com.example.a10146.demo2.fragment.SlideFragment;
import com.example.a10146.demo2.fragment.courseFragment;
import com.example.a10146.demo2.fragment.homePageFragment;
import com.example.a10146.demo2.fragment.professionalFragment;
import com.example.a10146.demo2.fragment.questionFragment;
import com.example.a10146.demo2.utils.FragmentUtil;
import com.example.a10146.demo2.view.AddPupopWindow;

import java.util.ArrayList;
import java.util.List;

import edu.zx.slidingmenu.SlidingMenu;
import edu.zx.slidingmenu.SlidingMenu.CanvasTransformer;

/**
 * Created by 10146 on 2018/5/2.
 */

public class HomePage extends BaseActivity implements OnClickListener {
    private SlidingMenu mSlidingMenu;// 侧滑栏
    private RelativeLayout nav_HomePage,nav_QuestionTest,nav_ProfessionalTest,nav_CourseSelection;

    private TextView txt_HomePage,txt_QuestionTest,txt_ProfessionalTest,txt_CourseSelection;
    private ImageView image_HomePage,image_QuestionTest,image_ProfessionalTest,image_CourseSelection;
    private FragmentManager manager;// Fragment碎片管理器
    private FragmentTransaction transaction;
    private List<Fragment> fragmentArry;

    private int tabPressedColor;
    private int tabNormalColor;
    private int oldTabIndex = FragmentUtil.TabIndex.TAB_HomePage;// 上次选中的tab索引
    private int newTabIndex = FragmentUtil.TabIndex.TAB_HomePage;// 新选中的tab索引

    // 这个是声明页面上的头像的那个image控件的ID
    private ImageView imgClick;

    private ImageView img_head_right;

    private CanvasTransformer mTransformer;
    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;

    private AddPupopWindow menuWindow;


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_slide);
        initSlideMenu();

        mTransformer = new CanvasTransformer() {
            @Override
            public void transformCanvas(Canvas canvas, float percentOpen) {
 /*               float scale = (float) (percentOpen * 0.25 + 0.75);
                canvas.scale(scale, scale, canvas.getWidth() / 2,
                        canvas.getHeight() / 2); */   //未来可更改成放大效果
            }
        };
        mSlidingMenu.setBehindCanvasTransformer(mTransformer);

        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1, 0);
        alphaAnimation.setDuration(500);
        animationSet.addAnimation(alphaAnimation);
        // 创建一个AnimationSet对象，参数为Boolean型，
        // true表示使用Animation的interpolator，false则是使用自己的
        AnimationSet animationSet1 = new AnimationSet(true);
        // 创建一个AlphaAnimation对象，参数从完全的透明度，到完全的不透明
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0, 1);
        // 设置动画执行的时间
        alphaAnimation1.setDuration(500);
        // 将alphaAnimation对象添加到AnimationSet当中
        animationSet1.addAnimation(alphaAnimation);

        tabPressedColor = getResources().getColor(R.color.tab_select_color);
        tabNormalColor = getResources().getColor(R.color.tab_uncelect_color);
        initView();


        }

    private void initSlideMenu() {
        int width = getWindow().getWindowManager().getDefaultDisplay()
                .getWidth();

        mSlidingMenu = new SlidingMenu(this);
        mSlidingMenu.setBackgroundResource(R.drawable.ic_slidemenu_bg);
        // 关联
        mSlidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);// 设置样式和内容平行的显示
        mSlidingMenu.setBehindOffset(width / 4);// 距离后面屏幕的距离
        // 设置菜单的模式
        mSlidingMenu.setMode(SlidingMenu.LEFT);
        // 设置左边的
        mSlidingMenu.setMenu(R.layout.left_slide);

        mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
       // mSlidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);// 设置触摸的模式
        // 添加左边的菜单
        FragmentTransaction frans = getSupportFragmentManager()
                .beginTransaction();
        frans.add(R.id.left_slide, new SlideFragment());
        frans.commit();// 提交
    }

    private void initView() {
        imgClick = (ImageView) findViewById(R.id.img_click);
        imgClick.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlidingMenu.toggle(true);
            }
        });

        img_head_right=(ImageView) findViewById(R.id.img_head_right);
        img_head_right.setOnClickListener(this);

        img_head_right.setBackgroundResource(R.mipmap.sign);
        img_head_right.setVisibility(View.VISIBLE);

        nav_HomePage = (RelativeLayout) findViewById(R.id.rel_homePage);
        nav_QuestionTest = (RelativeLayout) findViewById(R.id.rel_question);
        nav_ProfessionalTest = (RelativeLayout) findViewById(R.id.rel_ProfessionalTest);
        nav_CourseSelection = (RelativeLayout) findViewById(R.id.rel_courseSelection);


        nav_HomePage.setOnClickListener(this);
        nav_QuestionTest.setOnClickListener(this);
        nav_ProfessionalTest.setOnClickListener(this);
        nav_CourseSelection.setOnClickListener(this);

        image_HomePage = (ImageView) findViewById(R.id.img_homePage);
        image_QuestionTest = (ImageView) findViewById(R.id.img_question);
        image_ProfessionalTest = (ImageView) findViewById(R.id.img_ProfessionalTest);
        image_CourseSelection =(ImageView) findViewById(R.id.img_courseSelection);

        txt_HomePage = (TextView) findViewById(R.id.txt_homePage);
        txt_QuestionTest = (TextView) findViewById(R.id.txt_question);
        txt_ProfessionalTest = (TextView) findViewById(R.id.txt_ProfessionalTest);
        txt_CourseSelection =(TextView) findViewById(R.id.txt_courseSelection);

        // 初始化Fragment碎片管理
        fragmentArry = new ArrayList<Fragment>();
        fragmentArry.add(new homePageFragment());
        fragmentArry.add(new questionFragment());
        fragmentArry.add(new professionalFragment());
        fragmentArry.add(new courseFragment());

        manager = getSupportFragmentManager();// 获取Fragment管理器
        transaction = manager.beginTransaction();// 从管理器中得到一个Fragment事务
        transaction.add(R.id.mainFragment,
                fragmentArry.get(FragmentUtil.TabIndex.TAB_HomePage));// 将得到的fragment替换当前的viewGroup内
        transaction.commit();

    }
    /**
     * 切换Fragment视图
     *
     * @param contentId
     *            容器ID
     * @param fragment
     *            切换对象
     * @param index
     *            tab索引位置
     */
    private void replace(int contentId, Fragment fragment, int index) {
        if (!fragment.isAdded()) {
            changeTabState(index);
            System.out.println("newIndex:" + newTabIndex + ",oldIndex:"
                    + oldTabIndex);

            FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();

            fragmentTransaction.replace(contentId, fragment).commit();

            oldTabIndex = newTabIndex;
        }
    }

    /**
     * 改变Tab状态
     */
        private void changeTabState(int index) {
        newTabIndex = index;
        switch (index) {
            case FragmentUtil.TabIndex.TAB_HomePage:
                txt_HomePage.setTextColor(tabPressedColor);
                txt_QuestionTest.setTextColor(tabNormalColor);
                txt_ProfessionalTest.setTextColor(tabNormalColor);
                txt_CourseSelection.setTextColor(tabNormalColor);

                image_HomePage.setBackgroundResource(R.drawable.ic_main_homepage_select);
                image_QuestionTest.setBackgroundResource(R.drawable.ic_main_question_unselect);
                image_ProfessionalTest.setBackgroundResource(R.drawable.ic_main_personal_unselect);
                image_CourseSelection.setBackgroundResource(R.drawable.ic_main_personal_unselect);//这里图片没有更换后期ui出来后再改


                img_head_right.setVisibility(View.VISIBLE);
                img_head_right.setBackgroundResource(R.mipmap.sign);


                break;
            case FragmentUtil.TabIndex.TAB_QuestionTest:
                txt_HomePage.setTextColor(tabNormalColor);
                txt_QuestionTest.setTextColor(tabPressedColor);
                txt_ProfessionalTest.setTextColor(tabNormalColor);
                txt_CourseSelection.setTextColor(tabNormalColor);

                image_HomePage.setBackgroundResource(R.drawable.ic_main_homepage_unselect);
                image_QuestionTest.setBackgroundResource(R.drawable.ic_main_question_select);
                image_ProfessionalTest.setBackgroundResource(R.drawable.ic_main_personal_unselect);
                image_CourseSelection.setBackgroundResource(R.drawable.ic_main_personal_unselect);

                img_head_right.setVisibility(View.GONE);
                break;
            case FragmentUtil.TabIndex.TAB_ProfessionalTest:

                txt_HomePage.setTextColor(tabNormalColor);
                txt_QuestionTest.setTextColor(tabNormalColor);
                txt_ProfessionalTest.setTextColor(tabPressedColor);
                txt_CourseSelection.setTextColor(tabNormalColor);

                image_HomePage.setBackgroundResource(R.drawable.ic_main_homepage_unselect);
                image_QuestionTest.setBackgroundResource(R.drawable.ic_main_question_unselect);
                image_ProfessionalTest.setBackgroundResource(R.drawable.ic_main_personal_select);
                image_CourseSelection.setBackgroundResource(R.drawable.ic_main_personal_unselect);

                img_head_right.setVisibility(View.GONE);
                break;
            case  FragmentUtil.TabIndex.TAB_CourseSelect:
                txt_HomePage.setTextColor(tabNormalColor);
                txt_QuestionTest.setTextColor(tabNormalColor);
                txt_ProfessionalTest.setTextColor(tabNormalColor);
                txt_CourseSelection.setTextColor(tabPressedColor);

                image_HomePage.setBackgroundResource(R.drawable.ic_main_homepage_unselect);
                image_QuestionTest.setBackgroundResource(R.drawable.ic_main_question_unselect);
                image_ProfessionalTest.setBackgroundResource(R.drawable.ic_main_personal_unselect);
                image_CourseSelection.setBackgroundResource(R.drawable.ic_main_personal_select);




        }
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.rel_homePage:// 切换到首页的fragment
                replace(R.id.mainFragment,
                        fragmentArry.get(FragmentUtil.TabIndex.TAB_HomePage),
                        FragmentUtil.TabIndex.TAB_HomePage);

                break;
            case R.id.rel_question:// 切换到题海测试的fragment
                replace(R.id.mainFragment,
                        fragmentArry.get(FragmentUtil.TabIndex.TAB_QuestionTest),
                        FragmentUtil.TabIndex.TAB_QuestionTest);
                break;
            case R.id.rel_ProfessionalTest:// 切换到职业测试的fragment
                replace(R.id.mainFragment,
                        fragmentArry.get(FragmentUtil.TabIndex.TAB_ProfessionalTest),
                        FragmentUtil.TabIndex.TAB_ProfessionalTest);
                break;
            case R.id.rel_courseSelection:// 切换到职业测试的fragment
                replace(R.id.mainFragment,
                        fragmentArry.get(FragmentUtil.TabIndex.TAB_CourseSelect),
                        FragmentUtil.TabIndex.TAB_CourseSelect);
                break;

            case R.id.img_click:// 个人信息
                mSlidingMenu.toggle(true);
                break;
            case R.id.img_head_right://签到
//                menuWindow = new AddPupopWindow(HomePage.this, itemsOnClick);
//                menuWindow.showAtLocation(
//                        findViewById(R.id.find_password),
//                        Gravity.FILL , 0, 0);
                break;
        }
    }

    // 点击两次退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 3000);
        } else {
            finish();
            System.exit(0);
        }
    }
}
