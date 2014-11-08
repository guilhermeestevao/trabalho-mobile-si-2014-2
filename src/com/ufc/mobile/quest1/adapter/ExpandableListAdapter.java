package com.ufc.mobile.quest1.adapter;

import java.util.ArrayList;
import java.util.List;

import com.ufc.mobile.quest1.R;
import com.ufc.mobile.quest1.model.Local;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private LayoutInflater inflater;
	private List<Local> locais;
	
	public ExpandableListAdapter(Activity activity, List<Local> locais) {
		this.activity = activity;
		this.inflater = activity.getLayoutInflater();
		this.locais = locais;		
	}
	
	@Override
	public int getGroupCount() {
		return locais.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		return locais.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return locais.get(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		if(convertView == null)
			convertView = inflater.inflate(R.layout.list_group, null);
		Local group = (Local) getGroup(groupPosition);
		((CheckedTextView) convertView).setText(group.getName());
		((CheckedTextView) convertView).setChecked(isExpanded);
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Local children = (Local) getChild(groupPosition, childPosition);
		if(convertView == null)
			convertView = inflater.inflate(R.layout.list_itens, null);
		
		TextView idLocal = (TextView) convertView.findViewById(R.id.tv_id_local_list_itens);
		idLocal.setText(String.valueOf(children.getDistancia()));
		
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}

}
