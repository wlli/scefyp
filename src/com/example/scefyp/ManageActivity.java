package com.example.scefyp;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TableLayout;

import com.example.database.DatabaseHandler;

public class ManageActivity extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        
        //load drug_list
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

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_manage, menu);
        return true;
    }
}
