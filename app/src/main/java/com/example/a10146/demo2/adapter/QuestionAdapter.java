package com.example.a10146.demo2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a10146.demo2.R;

import java.util.List;
import java.util.Map;

public class  QuestionAdapter extends BaseAdapter {

	private Context context;
	private List<Map<String, Object>> list;
	public QuestionAdapter(Context context, List<Map<String, Object>> list){
		this.context=context;
		this.list=list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(convertView==null){
			holder = new ViewHolder();
			LayoutInflater mInflater = LayoutInflater
					.from(context);
			convertView = mInflater.inflate(R.layout.item_lst_question,
					null);
			
			holder.img_me_icon=(ImageView) convertView.findViewById(R.id.item_img_question);
			holder.txt_me_content=(TextView) convertView.findViewById(R.id.item_txt_question);
			holder.img_right=(ImageView) convertView.findViewById(R.id.item_img_right);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		int icon=(Integer) list.get(position).get("icon");
		String title=(String) list.get(position).get("title");
		int right=(Integer) list.get(position).get("right");
		
		holder.img_me_icon.setBackgroundResource(icon);
		holder.txt_me_content.setText(title);
		holder.img_right.setBackgroundResource(right);
		
		return convertView;
	}
	
	static class ViewHolder {
		public ImageView img_me_icon;
		public TextView txt_me_content;
		public ImageView img_right;
	}
}
