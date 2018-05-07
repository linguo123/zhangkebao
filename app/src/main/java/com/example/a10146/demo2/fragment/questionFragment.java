
package com.example.a10146.demo2.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.a10146.demo2.R;
import com.example.a10146.demo2.adapter.QuestionAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class questionFragment extends Fragment{

	private ListView lst_one,lst_two;
	@Override
	@Nullable
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_question, null);
		initView(view);
		lst_one.setAdapter(new QuestionAdapter(getActivity(), getOneData()));
		setListViewHeightBasedOnChildren(lst_one);
		lst_two.setAdapter(new QuestionAdapter(getActivity(), getTwoData()));
		setListViewHeightBasedOnChildren(lst_two);
		return view;
	}
	
	private void initView(View view){
		lst_one=(ListView) view.findViewById(R.id.lst_dynamic_one);
		lst_two=(ListView) view.findViewById(R.id.lst_dynamic_two);
	}

		public List<Map<String, Object>> getOneData() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", R.drawable.ic_dynamic_game);
			map.put("title", "游戏");
			map.put("right", R.drawable.ic_next);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("icon", R.drawable.ic_dynamic_shopping);
			map.put("title", "购物");
			map.put("right", R.drawable.ic_next);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("icon", R.drawable.ic_dynamic_read);
			map.put("title", "阅读");
			map.put("right", R.drawable.ic_next);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("icon", R.drawable.ic_dynamic_music);
			map.put("title", "音乐");
			map.put("right", R.drawable.ic_next);
			list.add(map);

			return list;
		}


				public List<Map<String, Object>> getTwoData() {
					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("icon", R.drawable.ic_dynamic_group);
					map.put("title", "附近的群");
					map.put("right", R.drawable.ic_next);
					list.add(map);


					map = new HashMap<String, Object>();
					map.put("icon", R.drawable.ic_dynamic_health);
					map.put("title", "健康");
					map.put("right", R.drawable.ic_next);
					list.add(map);

					map = new HashMap<String, Object>();
					map.put("icon", R.drawable.ic_dynamic_school);
					map.put("title", "OO课堂");
					map.put("right", R.drawable.ic_next);
					list.add(map);

					return list;
				}
		

		public void setListViewHeightBasedOnChildren(ListView listView) {

			ListAdapter listAdapter = listView.getAdapter();
			if (listAdapter == null) {
				return;
			}
			int totalHeight = 0;
			for (int i = 0, len = listAdapter.getCount(); i < len; i++) {

				View listItem = listAdapter.getView(i, null, listView);

				listItem.measure(0, 0);

				totalHeight += listItem.getMeasuredHeight();
			}

			ViewGroup.LayoutParams params = listView.getLayoutParams();
			params.height = totalHeight
					+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
			listView.setLayoutParams(params);
		}
}
