
package com.example.a10146.demo2.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.a10146.demo2.HttpConnectionUtil;
import com.example.a10146.demo2.JsonContainer;
import com.example.a10146.demo2.R;
import com.example.a10146.demo2.TesQuestion.DownloadUtil;
import com.example.a10146.demo2.TesQuestion.TestQueActivity;
import com.example.a10146.demo2.adapter.QuestionAdapter;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class questionFragment extends Fragment{
   private LineChart mLineChart;
	private ListView lst_one,lst_two;

	private String postdataUrl;
	String dataversion;
	String version="20180707";
    private DownloadUtil  downloadUtil;
	@Nullable
	public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view= LayoutInflater.from(getActivity()).inflate(R.layout.fragment_question, null);
		mLineChart = (LineChart) view.findViewById(R.id.lineChart);
		linebar();
		initView(view);

		lst_one.setAdapter(new QuestionAdapter(getActivity(), getOneData()));
		setListViewHeightBasedOnChildren(lst_one);
		lst_two.setAdapter(new QuestionAdapter(getActivity(), getTwoData()));
		setListViewHeightBasedOnChildren(lst_two);
		lst_one.setOnItemClickListener(new AdapterView.OnItemClickListener() {


			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
				// TODO Auto-generated method stub
				switch (position) {
					case 0:
						setVersion(00);
						break;
					case 1:
						setVersion(01);
						break;
					case 2:
						setVersion(02);
						break;
					case 3:
						setVersion(03);
						break;

				}


			}
		});


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
			map.put("title", "语文");
			map.put("right", R.drawable.ic_next);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("icon", R.drawable.ic_dynamic_shopping);
			map.put("title", "数学");
			map.put("right", R.drawable.ic_next);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("icon", R.drawable.ic_dynamic_read);
			map.put("title", "英语");
			map.put("right", R.drawable.ic_next);
			list.add(map);



			return list;
		}

				public List<Map<String, Object>> getTwoData() {

					List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("icon", R.drawable.ic_dynamic_group);
					map.put("title", "物理");
					map.put("right", R.drawable.ic_next);
					list.add(map);

					map = new HashMap<String, Object>();
					map.put("icon", R.drawable.ic_dynamic_music);
					map.put("title", "化学");
					map.put("right", R.drawable.ic_next);
					list.add(map);

					map = new HashMap<String, Object>();
					map.put("icon", R.drawable.ic_dynamic_health);
					map.put("title", "生物");
					map.put("right", R.drawable.ic_next);
					list.add(map);

					map = new HashMap<String, Object>();
					map.put("icon", R.drawable.ic_dynamic_school);
					map.put("title", "地理");
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
		private void linebar(){
			XAxis xAxis = mLineChart.getXAxis();
			xAxis.setGranularity(1f);
			xAxis.setLabelCount(12, false);
			YAxis leftYAxis = mLineChart.getAxisLeft();
			YAxis rightYAxis = mLineChart.getAxisRight();
			rightYAxis.setEnabled(false);


			Legend legend = mLineChart.getLegend();
			legend.setEnabled(false);
			//显示边界
			mLineChart.setDrawBorders(true);
			//设置数据
			List<Entry> entries = new ArrayList<>();
			for (int i = 0; i < 12; i++) {
				entries.add(new Entry(i+1, (float) (Math.random()) * 50+50));
			}
			//一个LineDataSet就是一条线
			LineDataSet lineDataSet = new LineDataSet(entries, "成绩");
			xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
			LineData data = new LineData(lineDataSet);
			mLineChart.setData(data);

		}
		public  void  setVersion(final int value){
			postdataUrl = "https://extlife.xyz/ability/getversion";
			String posthttp = HttpConnectionUtil.getHttp().getRequset(postdataUrl,null);
			JsonContainer<String> rootModel = new JsonContainer<String>();
			Gson gson = new Gson();
			rootModel = gson.fromJson(posthttp, new TypeToken<JsonContainer<String>>() {
			}.getType());
			if (rootModel.getStatus().equals("Success")) {
				dataversion= rootModel.getData();
//            String version = dataversion+"版本";
				if (!Objects.equals(dataversion, version)){
					String url="https://extlife.xyz/ability/getcache";
					String path ="/data/data/com.example.a10146.demo2/databases/";
					String name =String.format("question%02d.db",value);
					DownloadUtil.get().download(url,path,name,new DownloadUtil.OnDownloadListener() {

						@Override
						public void onDownloadSuccess(File file) {
							Log.e("DownloadSuccess","Success");
							Intent intent = new Intent();
							intent.putExtra("question", value);

							intent.setClass(getActivity(), TestQueActivity.class);
							questionFragment.this.startActivity(intent);


						}

						@Override
						public void onDownloading(int progress) {

						}

						@Override
						public void onDownloadFailed(Exception e) {
                           Log.e("downloadFailed","fail");
						}
					});

				}else {
					Intent intent = new Intent();
					intent.putExtra("question", value);

					intent.setClass(getActivity(), TestQueActivity.class);
					questionFragment.this.startActivity(intent);

				}


			}


		}
}
