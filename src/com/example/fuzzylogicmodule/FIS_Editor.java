
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
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuzzylogicmodule.Model.FIS_System;
import com.example.fuzzylogicmodule.Model.MembershipFunction;
import com.example.fuzzylogicmodule.Model.Variable;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.LineGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;

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
    //FrameLayout mframeLayout = null;
    Dialog dialog = null;
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
        dialog = new Dialog(mContext);
        //mframeLayout = (FrameLayout) dialog.findViewById(R.id.graphFrame);
        final MembershipFunction sampleMF = new MembershipFunction("Triangular", 6, "0,0,0.5,1,1,0");
        ArrayList<MembershipFunction> defaultMFList = new ArrayList<MembershipFunction>();
        defaultMFList.add(sampleMF);
        Variable DefaultInput1 = new Variable("Default Input 1", 1, 0, 1, defaultMFList, defaultMFList.size());
        Variable DefaultInput2 = new Variable("Default Input 2", 1, 0, 1, defaultMFList, defaultMFList.size());
        Variable DefaultOutput1 = new Variable("Default Output 1", 2, 0, 1, defaultMFList, defaultMFList.size());
        Variable DefaultOutput2 = new Variable("Default Output 2", 2, 0, 1, defaultMFList, defaultMFList.size());
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
			public void onItemClick(AdapterView<?> parent, View view,final int position, long id) {
				// TODO Auto-generated method stub
				//dialog.setContentView(R.layout.test_var_layout);
				dialog.setContentView(R.layout.variable_details_layout);
				dialog.setTitle("Input Variable Details...");
				TextView name = (TextView) dialog.findViewById(R.id.name);
				name.setText("Name : ");
				TextView range = (TextView) dialog.findViewById(R.id.range);
				range.setText("Range : ");
				TextView type = (TextView) dialog.findViewById(R.id.type);
				type.setText("Type : ");
				TextView typeVal = (TextView) dialog.findViewById(R.id.var_detail_type);
				typeVal.setText("Input");
				typeVal.setFocusable(false);
				final EditText nameVal = (EditText) dialog.findViewById(R.id.var_detail_name);
				nameVal.setText(Inputs.get(position).getVariableName());
				final EditText rangell_val = (EditText) dialog.findViewById(R.id.var_detail_lr);
				final EditText rangeul_val = (EditText) dialog.findViewById(R.id.var_detail_ur);
				rangell_val.setText(Inputs.get(position).getLowerLimit()+"");
				rangeul_val.setText(Inputs.get(position).getUpperLimit()+"");
				Spinner memFuncSpinner = (Spinner)dialog.findViewById(R.id.membershipSpinner);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,  new String[]{"Triangular", "Trapezoidal", "Normal"});
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				memFuncSpinner.setAdapter(adapter);
				Spinner memFuncSpinnerCount = (Spinner)dialog.findViewById(R.id.mf_count_spinner);
                ArrayAdapter<String> countAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,  new String[]{"1", "2", "3", "4"});
                countAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                memFuncSpinnerCount.setAdapter(countAdapter);
				// init example series data
				GraphViewSeries exampleSeries = new GraphViewSeries(new GraphViewData[] {
				    new GraphViewData(Inputs.get(position).getLowerLimit(), 0.0d)
				    , new GraphViewData((Inputs.get(position).getLowerLimit() + Inputs.get(position).getUpperLimit())/2.0, 1.0d)
				    , new GraphViewData(Inputs.get(position).getUpperLimit(), 0.0d)
				    //, new GraphViewData(4, 1.0d)
				});
				 
				GraphView graphView = new LineGraphView(mContext, "Demo");
				graphView.addSeries(exampleSeries); // data
				graphView.setScalable(true);
				graphView.setScrollable(true);
				FrameLayout mframeLayout = (FrameLayout) dialog.findViewById(R.id.graphFrame);
				mframeLayout.addView(graphView);
				Button OkButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
				OkButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String newNameVal = nameVal.getText().toString();
						Inputs.get(position).setVariableName(newNameVal);
						Inputs.get(position).setLowerLimit(Integer.parseInt(rangell_val.getText().toString()));
						Inputs.get(position).setUpperLimit(Integer.parseInt(rangeul_val.getText().toString()));
						InputButtons.remove(position);
						InputButtons.add(position, newNameVal);
						inputListAdapter.notifyDataSetChanged();
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
					final int position, long id) {
				// TODO Auto-generated method stub
				//final Dialog dialog = new Dialog(mContext);
				dialog.setContentView(R.layout.variable_details_layout);
				dialog.setTitle("Output Variable Details...");
				TextView name = (TextView) dialog.findViewById(R.id.name);
				name.setText("Name : ");
				TextView range = (TextView) dialog.findViewById(R.id.range);
				range.setText("Range : ");
				TextView type = (TextView) dialog.findViewById(R.id.type);
				type.setText("Type : ");
				final EditText nameVal = (EditText) dialog.findViewById(R.id.var_detail_name);
				nameVal.setText(Outputs.get(position).getVariableName());
				TextView typeVal = (TextView) dialog.findViewById(R.id.var_detail_type);
				typeVal.setText("Output");
				typeVal.setFocusable(false);
				final EditText rangell_val = (EditText) dialog.findViewById(R.id.var_detail_lr);
				final EditText rangeul_val = (EditText) dialog.findViewById(R.id.var_detail_ur);
				rangell_val.setText(Outputs.get(position).getLowerLimit()+"");
				rangeul_val.setText(Outputs.get(position).getUpperLimit()+"");
				Spinner memFuncSpinner = (Spinner)dialog.findViewById(R.id.membershipSpinner);
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,  new String[]{"Triangular", "Trapezoidal", "Normal"});
				adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				memFuncSpinner.setAdapter(adapter);
				final Spinner memFuncSpinnerCount = (Spinner)dialog.findViewById(R.id.mf_count_spinner);
                ArrayAdapter<String> countAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_spinner_item,  new String[]{"1", "2", "3", "4"});
                countAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                memFuncSpinnerCount.setAdapter(countAdapter);
                MembershipFunction mMF = Outputs.get(position).getMembershipFuncs().get(0);
                String paramAsString = "";                
                for(String s : mMF.getMemfuncParams())
                	paramAsString+= s+", ";
                String GraphTitle = mMF.getMemfuncName()+" MF with "+paramAsString+" as end points";
                GraphData mGraphData = new GraphData(mMF, paramAsString);
                GraphViewData[] gvd = mGraphData.generate();
                GraphViewSeries exampleSeries = new GraphViewSeries(gvd);
                GraphView graphView = new LineGraphView(mContext, GraphTitle);
				graphView.addSeries(exampleSeries); // data
				graphView.setScalable(true);
				graphView.setScrollable(true);
				final FrameLayout mframeLayout = (FrameLayout) dialog.findViewById(R.id.graphFrame);
				mframeLayout.addView(graphView);
				
				memFuncSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position1, long id) {
						// TODO Auto-generated method stub
						//if(position != 0){
							String mfSelected = parent.getItemAtPosition(position1).toString();
							if(memFuncSpinnerCount.getSelectedItemPosition() == 0){
								MembershipFunction existing = Outputs.get(position).getMembershipFuncs().get(0);
								existing.setMemfuncName(mfSelected);
								if(mfSelected.equals("Triangular")){
									existing.setParamCount(3);
									existing.setMemfuncParams("0,0,.5,1,1,0");
									String GraphTitle = existing.getMemfuncName()+" MF with 0,0,.5,1,1,0 as end points";
					                GraphData mGraphData = new GraphData(existing, "0,0,.5,1,1,0");
					                GraphViewData[] gvd = mGraphData.generate();
					                GraphViewSeries exampleSeries = new GraphViewSeries(gvd);
					                GraphView graphView = new LineGraphView(mContext, GraphTitle);
									graphView.addSeries(exampleSeries); // data
									graphView.setScalable(true);
									graphView.setScrollable(true);
									mframeLayout.removeAllViews();
									mframeLayout.addView(graphView);
								}
								else if(mfSelected.equals("Trapezoidal")){
									existing.setParamCount(4);
									existing.setMemfuncParams("0,0,.25,1,.75,1,1,0");
									String GraphTitle = existing.getMemfuncName()+" MF with 0,0,.25,1,.75,1,1,0 as end points";
					                GraphData mGraphData = new GraphData(existing, "0,0,.25,1,.75,1,1,0");
					                GraphViewData[] gvd = mGraphData.generate();
					                GraphViewSeries exampleSeries = new GraphViewSeries(gvd);
					                GraphView graphView = new LineGraphView(mContext, GraphTitle);
									graphView.addSeries(exampleSeries); // data
									graphView.setScalable(true);
									graphView.setScrollable(true);
									mframeLayout.removeAllViews();
									mframeLayout.addView(graphView);
								}
								else if(mfSelected.equals("Normal")){
									existing.setParamCount(2);
									existing.setMemfuncParams("0,1");
									String GraphTitle = existing.getMemfuncName()+" MF with 0, 1 as mew and sigma ";
					                GraphData mGraphData = new GraphData(existing, "0,1");
					                GraphViewData[] gvd = mGraphData.generate();
					                GraphViewSeries exampleSeries = new GraphViewSeries(gvd);
					                GraphView graphView = new LineGraphView(mContext, GraphTitle);
									graphView.addSeries(exampleSeries); // data
									graphView.setScalable(true);
									graphView.setScrollable(true);
									//graphView.setsetViewPort(2, 40);
									mframeLayout.removeAllViews();
									mframeLayout.addView(graphView);
								}
							}
						//}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						// TODO Auto-generated method stub
						
					}
				});
				
				Button OkButton = (Button) dialog.findViewById(R.id.dialogButtonOK);

				OkButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String newNameVal = nameVal.getText().toString();
						Outputs.get(position).setVariableName(newNameVal);
						Outputs.get(position).setLowerLimit(Integer.parseInt(rangell_val.getText().toString()));
						Outputs.get(position).setUpperLimit(Integer.parseInt(rangeul_val.getText().toString()));
						OutputButtons.remove(position);
						OutputButtons.add(position, newNameVal);
						outputListAdapter.notifyDataSetChanged();
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
            	final MembershipFunction sampleMF = new MembershipFunction("Triangular", 6, "0,0,0.5,1,1,0");
                ArrayList<MembershipFunction> defaultMFList = new ArrayList<MembershipFunction>();
                defaultMFList.add(sampleMF);
                Variable TempInput = new Variable("Default Input ", 1, 0, 1,defaultMFList, defaultMFList.size());
                Inputs.add(TempInput);
                InputButtons.add(TempInput.getVariableName());
                inputListAdapter.notifyDataSetChanged();
            }
        });
        OutputAdd.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
            	final MembershipFunction sampleMF = new MembershipFunction("Triangular", 6, "0,0,0.5,1,1,0");
                ArrayList<MembershipFunction> defaultMFList = new ArrayList<MembershipFunction>();
                defaultMFList.add(sampleMF);
                Variable TempOutput = new Variable("Default Output ", 1, 0, 1,defaultMFList, defaultMFList.size());
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
