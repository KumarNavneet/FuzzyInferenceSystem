
package com.example.fuzzylogicmodule;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuzzylogicmodule.Model.FIS_System;
import com.example.fuzzylogicmodule.Model.Variable;

import java.util.ArrayList;
import java.util.List;

public class FIS_Editor extends Activity {

    Button next = null;
    Button back =null;
    Button InputAdd = null;
    Button InputDel = null;
    Button OutputAdd = null;
    Button OutputDel = null;
    ListView InputListView = null;
    ListView OutputListView = null;
    TextView Notif = null;
    
    FIS_System mFIS_System = null;
    ArrayList<Variable> Inputs = null;
    ArrayList<Variable> Outputs = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fis__editor_gyani);
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
        final List<String> InputButtons = new ArrayList<>();
        final List<String> OutputButtons = new ArrayList<>();
        for(Variable var : Inputs){
            InputButtons.add(var.getVariableName());
        }
        final ArrayAdapter<String> inputListAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, InputButtons);
        InputListView.setAdapter(inputListAdapter);
        InputListView.setBackgroundColor(Color.GRAY);
        
        for(Variable var : Outputs){
            OutputButtons.add(var.getVariableName());
        }
        final ArrayAdapter<String> outputListAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, OutputButtons);
        OutputListView.setAdapter(outputListAdapter);
        OutputListView.setBackgroundColor(Color.GRAY);
        
        InputAdd.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Variable TempInput = new Variable("Default Input ", 1, 0, 1);
                Inputs.add(TempInput);
//                Log.d("Navneet",  "The length of Inputs is "+Inputs.size());
//                Toast.makeText(getApplicationContext(), "The length of Inputs is "+Inputs.size(), Toast.LENGTH_LONG).show();
                InputButtons.add(TempInput.getVariableName());
//                for(Variable var : Inputs){
//                    InputButtons.add(var.getVariableName());
//                }
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
//                for(Variable var : Outputs){
//                    OutputButtons.add(var.getVariableName());
//                }
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
