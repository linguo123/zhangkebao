package com.example.a10146.demo2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by 10146 on 2018/4/27.
 */

public class Menu extends Activity {


    private SlidingMenu mLeftMenu ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.homepage_slide);

     //   mLeftMenu = (SlidingMenu) findViewById(R.id.left);
    }

  //  public void toggleMenu(View view)
//    {
//        mLeftMenu.toggle();
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu)
//    {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

}