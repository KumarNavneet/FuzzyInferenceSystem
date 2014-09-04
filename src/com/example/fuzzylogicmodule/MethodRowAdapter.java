package com.example.fuzzylogicmodule;

import java.util.HashMap;

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
	HashMap<String, String[]> methodVar = null;
	String And[] = new String[]{
		"Min1", "Min2"
	};
	public MethodRowAdapter(Context context, String[] objects) {
		super(context, R.layout.method_layout_row, objects);
		// TODO Auto-generated constructor stub
		this.mContext = context;
		this.mValues = objects;
		methodVar = new HashMap<String, String[]>();
		for(String s : objects){
			if(s.equals("And"))
				methodVar.put(s, new String[]{"Min1", "Min2", "Min3"});
			if(s.equals("Or"))
				methodVar.put(s, new String[]{"Max1", "Max2", "Max3"});
			if(s.equals("Implication"))
				methodVar.put(s, new String[]{"Imp1", "Imp2", "Imp3"});
			if(s.equals("Aggregate"))
				methodVar.put(s, new String[]{"Agg1", "Agg2", "Agg3"});
			if(s.equals("Defuzzyfication"))
				methodVar.put(s, new String[]{"Defuzz1", "Defuzz2", "Defuzz3"});
		}
	}	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 5;
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
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item, methodVar.get(mValues[position]));
			// Specify the layout to use when the list of choices appears
		 adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			// Apply the adapter to the spinner
		 spinner.setAdapter(adapter);
		 textView.setText(mValues[position]);
		return rowView;
	}

}
