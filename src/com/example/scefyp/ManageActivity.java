package com.example.scefyp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;

import com.example.database.DatabaseHandler;
import com.example.database.Drug;
import com.example.database.DrugListAdapter;

public class ManageActivity extends Activity {

	private ListView listView1;
	List<Drug> drugArray = new ArrayList<Drug>();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        
        //load drug_list
        /*
        ListView drugList = (ListView)findViewById(R.id.drug_list);
        
        DatabaseHandler dh = new DatabaseHandler(this);
        
        Cursor cursor = dh.getCursor();
        
        
        startManagingCursor(cursor);
        
        String[] from = new String[]{"_id", DatabaseHandler.NAME, 
        		DatabaseHandler.USAGE};
        
        int[] to = new int[]{R.id.text};
        Log.d("message:..","bug just next line..");
        SimpleCursorAdapter cursorAdapter =
         new SimpleCursorAdapter(this, R.layout.row, cursor, from, to);
        
        drugList.setAdapter(cursorAdapter);
      
        dh.close();
        */
        
        //load "listview1"
        DatabaseHandler dh = new DatabaseHandler(this);
        Cursor cursor = dh.getCursor();
        startManagingCursor(cursor);
        //looping
        Drug drug;
        while(cursor.moveToNext()){
        	int code = cursor.getInt(cursor.getColumnIndex("_id"));
        	String name = cursor.getString(cursor.getColumnIndex(DatabaseHandler.NAME));
        	String usage = cursor.getString(cursor.getColumnIndex(DatabaseHandler.USAGE));
        	drug = new Drug(code, name, usage);
        	drugArray.add(drug);
        }
        dh.close();

        Drug[] drugs = new Drug[drugArray.size()];
        drugs = drugArray.toArray(drugs);
        /*
         * testing data
        Drug d1 = new Drug(111,"d1","eat");
        Drug d2 = new Drug(111,"d1","eat");
        Drug d3 = new Drug(111,"d1","eat");
        Drug[] drugs = new Drug[]{d1, d2, d3};
        */
        
        DrugListAdapter adapter = new DrugListAdapter(this, 
                R.layout.listview_item_row, drugs);
        
        listView1 = (ListView)findViewById(R.id.drug_list);
        View header = (View)getLayoutInflater().inflate(R.layout.listview_header_row, null);
        
        listView1.addHeaderView(header);
        listView1.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manage, menu);
        return true;
    }
}
