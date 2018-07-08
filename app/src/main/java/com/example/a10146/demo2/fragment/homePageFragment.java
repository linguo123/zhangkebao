package com.example.a10146.demo2.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a10146.demo2.MPAndroidChart.BarChartManager;
import com.example.a10146.demo2.Progress.ProgressView;
import com.example.a10146.demo2.R;
import com.example.a10146.demo2.view.RefreshableView;
import com.example.a10146.demo2.view.RefreshableView.RefreshListener;
import com.github.mikephil.charting.charts.BarChart;

import java.util.ArrayList;
import java.util.List;

public class homePageFragment extends Fragment implements RefreshListener{

	private static final int PROGRESS=0X56;
	private static final int PROGRESS2=0X57;

	//  private Button mButton;
	private ProgressView mMyArcProgress;
	private ProgressView mMyArcProgress2;

	private int mProgress;
	private int mProgress2;


	private  int  progressurl = 60;
	private  int  progressurl2 = 50;


	private RefreshableView mRefreshableView;

      private BarChartManager barChartManager1;





	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_homepage, null);
		initView(view);

		return view;
	}

	private void initView(View view) {
		// TODO Auto-generated method stub
		mRefreshableView = (RefreshableView) view.findViewById(R.id.refresh_homepage);
		mMyArcProgress=(ProgressView) view.findViewById(R.id.arc_progress1);
		mMyArcProgress2=(ProgressView) view.findViewById(R.id.arc_progress2);

		BarChart barChart1 = (BarChart) view.findViewById(R.id.bar_chart1);
		barChartManager1 = new BarChartManager(barChart1);
		barchar();




		initData();
	}
	private void initData() {
		mRefreshableView.setRefreshListener(this);


	}

	@Override
	public void onStart() {

	   Progress();

		super.onStart();
	}

	public void onRefresh(RefreshableView view) {
			handler.sendEmptyMessageDelayed(1, 2000);


	}

	public  void  Progress(){
		mHandler.sendEmptyMessageDelayed(PROGRESS, 100);
		mHandler.sendEmptyMessageDelayed(PROGRESS2, 100);

	}

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

	Handler handler = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			mRefreshableView.finishRefresh();
			Toast.makeText(getActivity(), R.string.toast_text, Toast.LENGTH_SHORT).show();
		};
	};

	private void barchar(){


		ArrayList<Float> xValues = new ArrayList<>();
		for (int i = 0; i <= 10; i++) {
			xValues.add((float) i);
		}


		List<List<Float>> yValues = new ArrayList<>();
		for (int i = 0; i < 4; i++) {
			List<Float> yValue = new ArrayList<>();
			for (int j = 0; j <= 10; j++) {
				yValue.add((float) (Math.random() * 80));
			}
			yValues.add(yValue);
		}


		List<Integer> colours = new ArrayList<>();
		colours.add(Color.GREEN);
		colours.add(Color.BLUE);
		colours.add(Color.RED);
		colours.add(Color.CYAN);
		barChartManager1.showBarChart(xValues, yValues.get(0),null, colours.get(3));

	}
}
