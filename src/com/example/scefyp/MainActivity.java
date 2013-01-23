package com.example.scefyp;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.example.database.DatabaseHandler;
import com.example.database.Drug;


public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.appspot.scefyp";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //testing code below
        Drug drug = new Drug(101,"Panadol Max", "3times1day");
        DatabaseHandler dh = new DatabaseHandler(this);
        //dh.updateDrug(drug);
        
        List<Drug> drugs = dh.getAllDrugs();
        for(Drug d : drugs){
        	Log.d("Drug info: ", d.getName()+ "," + d.getCode() + "," + d.getUsage());
        }
        dh.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void sendMessage(View view){
    	Intent intent = new Intent(this, DisplayMessageActivity.class);
    	EditText editText = (EditText)findViewById(R.id.edit_message);
    	String message = editText.getText().toString();
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
    
    public void capture(View view){
    	Intent intent = new Intent(this, CaptureActivity.class);
    	startActivity(intent);
    }
    
    public void manage(View view){
    	Intent intent = new Intent(this, ManageActivity.class);
    	startActivity(intent);
    }
}
