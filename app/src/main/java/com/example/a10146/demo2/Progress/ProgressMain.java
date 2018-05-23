package com.example.a10146.demo2.Progress;

/**
 * Created by 10146 on 2018/5/21.
 */

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.example.a10146.demo2.R;

public class ProgressMain extends Activity {
        private static final int PROGRESS=0X56;
        private static final int PROGRESS2=0X57;

      //  private Button mButton;
        private ProgressView mMyArcProgress;
        private ProgressView mMyArcProgress2;

        private int mProgress;
        private int mProgress2;


        private  int  progressurl = 60;
        private  int  progressurl2 = 50;


        private Handler mHandler=new Handler(){
            public void handleMessage(android.os.Message msg) {
                switch (msg.what) {
                    case PROGRESS:
                        mProgress++;
                        if(mProgress<=progressurl){
                            mMyArcProgress.setmProgress(mProgress);
                            mHandler.sendEmptyMessageDelayed(PROGRESS, 20);
                        }
                        break;
                    case PROGRESS2:
                        mProgress2++;
                        if(mProgress2<=progressurl2){
                            mMyArcProgress2.setmProgress(mProgress2);
                            mHandler.sendEmptyMessageDelayed(PROGRESS2, 20);
                        }
                        break;


                    default:
                        break;
                }
            };
        };

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.progressview);
            mMyArcProgress=(ProgressView) findViewById(R.id.arc_progress1);
            mMyArcProgress2=(ProgressView) findViewById(R.id.arc_progress2);

          //  mButton=(Button) findViewById(R.id.button_start);
         //   mButton.setOnClickListener(this);
            mHandler.sendEmptyMessageDelayed(PROGRESS, 100);
            mHandler.sendEmptyMessageDelayed(PROGRESS2, 100);


        }
   //     @Override
//        public void onClick(View v) {
//            switch (v.getId()) {
//                case R.id.button_start:
//                    mHandler.sendEmptyMessageDelayed(PROGRESS, 100);
//                    mHandler.sendEmptyMessageDelayed(PROGRESS2, 100);
//                    mHandler.sendEmptyMessageDelayed(PROGRESS3, 100);
//                    break;
//
//                default:
//                    break;
//            }
//
//        }


    }


