
package com.example.fuzzylogicmodule;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuzzylogicmodule.Model.FIS_System;
import com.example.fuzzylogicmodule.Model.Variable;

public class FIS_Editor extends Activity {
	Context mContext = null;
    Button next = null;
    Button back =null;
    Button InputAdd = null;
    Button InputDel = null;
    Button OutputAdd = null;
    Button OutputDel = null;
    ListView InputListView = null;
    ListView OutputListView = null;
    TextView Notif = null;
    ListView MethodlistView = null;
    FIS_System mFIS_System = null;
    ArrayList<Variable> Inputs = null;
    ArrayList<Variable> Outputs = null;
    String [] methodValueTexts = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fis__editor_gyani);
        mContext = this;
        next = (Button)findViewById(R.id.Next_FISEditor);
        //back = (Button)findViewById(R.id.Back_FISEditor);
        InputAdd = (Button)findViewById(R.id.InputAdd);
        InputDel = (Button)findViewById(R.id.InputDel);
        OutputAdd = (Button)findViewById(R.id.OutputAdd);
        OutputDel = (Button)findViewById(R.id.OutputDel);
        //Notif = (TextView)findViewById(R.id.Notif_FISEditor);
        
        //back.setClickable(false);
        //back.setBackgroundColor(Color.parseColor("#808080"));
        Variable DefaultInput1 = new Variable("Default Input 1", 1, 0, 1);
        Variable DefaultInput2 = new Variable("Default Input 2", 1, 0, 1);
        Variable DefaultOutput1 = new Variable("Default Output 1", 1, 0, 1);
        Variable DefaultOutput2 = new Variable("Default Output 2", 1, 0, 1);
        Inputs = new ArrayList<Variable>();
        Inputs.add(DefaultInput1);
        Inputs.add(DefaultInput2);
        Outputs = new ArrayList<Variable>();
        Outputs.add(DefaultOutput1);
        Outputs.add(DefaultOutput2);
        //Populate the Input and Outptut ListViews by default vars.
        InputListView = (ListView)findViewById(R.id.InputlistView);        
        OutputListView = (ListView)findViewById(R.id.OutputlistView);
        final List<String> InputButtons = new ArrayList<String>();
        final List<String> OutputButtons = new ArrayList<String>();
        for(Variable var : Inputs){
            InputButtons.add(var.getVariableName());
        }
        final ArrayAdapter<String> inputListAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, InputButtons);
        InputListView.setAdapter(inputListAdapter);
        InputListView.setBackgroundColor(Color.GRAY);
        
        for(Variable var : Outputs){
            OutputButtons.add(var.getVariableName());
        }
        final ArrayAdapter<String> outputListAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, OutputButtons);
        OutputListView.setAdapter(outputListAdapter);
        OutputListView.setBackgroundColor(Color.GRAY);
        
        InputListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(mContext);
				dialog.setContentView(R.layout.activity_variable_detail);
				dialog.setTitle("Variable Details");
				TextView name = (TextView) dialog.findViewById(R.id.name);
				name.setText("Name : ");
				TextView range = (TextView) dialog.findViewById(R.id.range);
				range.setText("Range : ");
				TextView type = (TextView) dialog.findViewById(R.id.type);
				type.setText("Type : ");
				EditText typeVal = (EditText) dialog.findViewById(R.id.var_detail_type);
				typeVal.setText("Input");
				typeVal.setFocusable(false);
				EditText nameVal = (EditText) dialog.findViewById(R.id.var_detail_name);
				nameVal.setText(Inputs.get(position).getVariableName());
				Button OkButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
				// if button is clicked, close the custom dialog
				OkButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				Button CancelButton = (Button) dialog.findViewById(R.id.dialogButtonCancel);
				// if button is clicked, close the custom dialog
				CancelButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				
				dialog.show();
			}
		});
        OutputListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				final Dialog dialog = new Dialog(mContext);
				dialog.setContentView(R.layout.activity_variable_detail);
				dialog.setTitle("Variable Details");
				TextView name = (TextView) dialog.findViewById(R.id.name);
				name.setText("Name : ");
				TextView range = (TextView) dialog.findViewById(R.id.range);
				range.setText("Range : ");
				TextView type = (TextView) dialog.findViewById(R.id.type);
				type.setText("Type : ");
				EditText nameVal = (EditText) dialog.findViewById(R.id.var_detail_name);
				nameVal.setText(Outputs.get(position).getVariableName());
				EditText typeVal = (EditText) dialog.findViewById(R.id.var_detail_type);
				typeVal.setText("Output");
				typeVal.setFocusable(false);
				Button OkButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
				// if button is clicked, close the custom dialog
				OkButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				Button CancelButton = (Button) dialog.findViewById(R.id.dialogButtonCancel);
				// if button is clicked, close the custom dialog
				CancelButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				
				dialog.show();
				
			}
		});
        
        InputAdd.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Variable TempInput = new Variable("Default Input ", 1, 0, 1);
                Inputs.add(TempInput);
                InputButtons.add(TempInput.getVariableName());
                inputListAdapter.notifyDataSetChanged();
            }
        });
        OutputAdd.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Variable TempOutput = new Variable("Default Output ", 1, 0, 1);
                Outputs.add(TempOutput);
                OutputButtons.add(TempOutput.getVariableName());
                outputListAdapter.notifyDataSetChanged();
            }
        });
        
        InputDel.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(Inputs.size() <= 0)
                    Toast.makeText(getApplicationContext(), "No Input variables to Delete", Toast.LENGTH_LONG).show();
                else{
                    InputButtons.remove(0);
                    Inputs.remove(0);
                    inputListAdapter.notifyDataSetChanged();
                    
                }
            }
        });
        OutputDel.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(Outputs.size() <= 0)
                    Toast.makeText(getApplicationContext(), "No Output variables to Delete", Toast.LENGTH_LONG).show();
                else{
                    OutputButtons.remove(0);
                    Outputs.remove(0);
                    outputListAdapter.notifyDataSetChanged();
                    
                }
            }
        });
        //back.getBackground().setColorFilter(Color.GRAY, PorterDuff.Mode.MULTIPLY);
        
        MethodlistView = (ListView)findViewById(R.id.MethodlistView);
        methodValueTexts = new String[] {"And", "Or", "Aggregate", "Implication", "Defuzzyfication"};
        MethodRowAdapter mMethodAdapter = new MethodRowAdapter(getApplicationContext(), methodValueTexts);
        MethodlistView.setAdapter(mMethodAdapter);
        MethodlistView.setBackgroundColor(Color.GRAY);
    }    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fis__editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
