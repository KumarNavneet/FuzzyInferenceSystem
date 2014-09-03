package com.example.fuzzylogicmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MethodRowAdapter extends ArrayAdapter<String> {
	private Context mContext = null;
	private String[] mValues = null;
	public MethodRowAdapter(Context context, String[] objects) {
		super(context, R.layout.method_layout_row, objects);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.mValues = objects;
	}	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getItem(int position) {
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
		 LayoutInflater inflater = (LayoutInflater) mContext
			        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 View rowView = inflater.inflate(R.layout.method_layout_row, parent, false);
		 TextView textView = (TextView) rowView.findViewById(R.id.MethodRowText);
		 Spinner spinner = (Spinner) rowView.findViewById(R.id.MethodRowSpinner);
		 textView.setText(mValues[position]);
		return rowView;
	}

}
