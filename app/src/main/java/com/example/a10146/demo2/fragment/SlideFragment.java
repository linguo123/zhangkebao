package com.example.a10146.demo2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.a10146.demo2.R;
import com.example.a10146.demo2.SildeItem.FoldLineDiaActivity;
import com.example.a10146.demo2.SildeItem.GoToCollegeActivity;
import com.example.a10146.demo2.SildeItem.MyRoleActivity;
import com.example.a10146.demo2.SildeItem.TestReportActivity;
import com.example.a10146.demo2.SildeItem.VIPActivity;
import com.example.a10146.demo2.SildeItem.WroColleActivity;
import com.example.a10146.demo2.adapter.SlideMenuAdapter;
import com.example.a10146.demo2.view.XXListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SlideFragment extends Fragment implements OnClickListener {

	private XXListView lst_slide;
	private RelativeLayout rel_night;
	private LinearLayout lin_slide_wendu_location;
	private RelativeLayout rel_slide_set;

	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.fragment_slide, null);
		initView(view);
		lst_slide.setAdapter(new SlideMenuAdapter(getActivity(), getData()));
		lst_slide.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
					startActivity(new Intent().setClass(getActivity(),
							MyRoleActivity.class));
					break;
				case 1:
					startActivity(new Intent().setClass(getActivity(),
							TestReportActivity.class));
					break;
				case 2:
					startActivity(new Intent().setClass(getActivity(),
							WroColleActivity.class));
					break;
				case 3:
					startActivity(new Intent().setClass(getActivity(),
							FoldLineDiaActivity.class));
					break;
				case 4:
					startActivity(new Intent().setClass(getActivity(),
							VIPActivity.class));
					break;
				case 5:
					startActivity(new Intent().setClass(getActivity(),
							GoToCollegeActivity.class));
					break;
				}
			}
		});
		return view;
	}

	private void initView(View view) {
		lst_slide = (XXListView) view.findViewById(R.id.lst_slide);

//		rel_night = (RelativeLayout) view.findViewById(R.id.lin_night);
//		rel_night.setOnClickListener(this);

//		lin_slide_wendu_location = (LinearLayout) view
//				.findViewById(R.id.lin_slide_wendu_location);
//		lin_slide_wendu_location.setOnClickListener(this);
//
//		rel_slide_set = (RelativeLayout) view.findViewById(R.id.rel_slide_set);
//		rel_slide_set.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}


	public List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("icon", R.drawable.ic_tara);
		map.put("title", "我的角色形象");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.ic_wallet);
		map.put("title", "测评汇报");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.ic_slide_paint);
		map.put("title", "错题收藏");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.ic_slide_save);
		map.put("title", "能力变化折线表");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.ic_slide_photo);
		map.put("title", "VIP会员");
		list.add(map);

		map = new HashMap<String, Object>();
		map.put("icon", R.drawable.ic_slide_file);
		map.put("title", "直击高考");
		list.add(map);

		return list;
	}

	// 设置listview的高度 否则在scrollview中只显示一行
	public void setListViewHeightBasedOnChildren(ListView listView) {
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
			// listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
			// 计算子项View 的宽高
			listItem.measure(0, 0);
			// 统计所有子项的总高度
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
	}
}