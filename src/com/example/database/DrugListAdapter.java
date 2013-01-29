package com.example.database;

import com.example.scefyp.R;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DrugListAdapter extends ArrayAdapter<Drug>{

    Context context; 
    int layoutResourceId;    
    Drug data[] = null;
    
    public DrugListAdapter(Context context, int layoutResourceId, Drug[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        DrugHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new DrugHolder();
            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
            
            row.setTag(holder);
        }
        else
        {
            holder = (DrugHolder)row.getTag();
        }
        
        Drug drug = data[position];
        holder.txtTitle.setText(drug.getName());
        holder.imgIcon.setImageResource(R.drawable.pill);
        
        return row;
    }
    
    static class DrugHolder
    {
        ImageView imgIcon;
        TextView txtTitle;
    }
}