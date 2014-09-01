
package com.example.fuzzylogicmodule;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fuzzylogicmodule.Model.FIS_System;
import com.example.fuzzylogicmodule.Model.Variable;

import java.util.ArrayList;

public class FIS_Editor extends Activity {

    Button next = null;
    Button back =null;
    Button AddInput = null;
    Button DelInput = null;
    Button AddOutput = null;
    Button DelOutput = null;
    
    TextView Notif = null;
    
    FIS_System mFIS_System = null;
    ArrayList<Variable> Inputs = null;
    ArrayList<Variable> Outputs = null;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fis__editor);
        next = (Button)findViewById(R.id.Next_FISEditor);
        back = (Button)findViewById(R.id.Back_FISEditor);
        AddInput = (Button)findViewById(R.id.AddInput);
        DelInput = (Button)findViewById(R.id.DelInput);
        AddOutput = (Button)findViewById(R.id.AddOutput);
        DelOutput = (Button)findViewById(R.id.DelOutput);
        Notif = (TextView)findViewById(R.id.Notif_FISEditor);
        
        back.setClickable(false);
        back.setBackgroundColor(Color.parseColor("#808080"));
        
        AddInput.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
            }
        });
        AddOutput.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
            }
        });
        
        DelInput.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(Inputs.size() <= 0)
                    Toast.makeText(getApplicationContext(), "No Inputs to Delete", Toast.LENGTH_LONG).show();
                else{
                    
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
