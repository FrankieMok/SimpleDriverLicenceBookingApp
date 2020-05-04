package com.example.isc7424s12020_a1_1518687;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
	private ArrayList<String> date;
	private ArrayList<String> timeSlot;


	public CustomAdapter(Context c, ArrayList<String> d, ArrayList<String> t) {
		date = d;
		timeSlot = t;
		mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return date.size();
	}

	@Override
	public Object getItem(int position) {
		return date.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = mInflater.inflate(R.layout.lic_listview, null);
		TextView dateTextView = (TextView) view.findViewById(R.id.dateTextView);
		TextView timeSlotTextView = (TextView) view.findViewById(R.id.timeSlotTextView);

		String dateOf = date.get(position);
		String timeOf = timeSlot.get(position);

		dateTextView.setText(dateOf);
		timeSlotTextView.setText(timeOf);
		return view;
	}
}
