package com.example.a10146.demo2.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a10146.demo2.R;
import com.example.a10146.demo2.view.RefreshableView;
import com.example.a10146.demo2.view.RefreshableView.RefreshListener;

public class homePageFragment extends Fragment implements
RefreshListener{

	private RefreshableView mRefreshableView;
	Handler handler = new Handler() {
		public void handleMessage(Message message) {
			super.handleMessage(message);
			mRefreshableView.finishRefresh();
			Toast.makeText(getActivity(), R.string.toast_text, Toast.LENGTH_SHORT).show();
		};
	};
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
		initData();
	}
	private void initData() {
		mRefreshableView.setRefreshListener(this);
		
	}
	

		public void onRefresh(RefreshableView view) {

			handler.sendEmptyMessageDelayed(1, 2000);
			
		}
}
