package com.example1.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Places_listener  extends Activity{
	  ListView list;
	  final ArrayList<String> list1 = new ArrayList<String>();
		SQLiteDatabase db;
		Cursor cur;
		int count=0;
		String s;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gr_display);
		TextView textView = (TextView) this.findViewById(R.id.textView1);
		final CheckBox favButton = (CheckBox)findViewById( R.id.fav);
		favButton.setVisibility(8);
		Intent intent = getIntent();
		String s = intent.getStringExtra("busno");
		db = openOrCreateDatabase("busdetails.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db.setVersion(1);
	    db.setLocale(Locale.getDefault());
		list = (ListView)findViewById(R.id.listView1);
		System.out.println("no"+s);
		String q1="select * from bus_det where bus_no ='"+s+"'";
	    cur = db.rawQuery(q1,null );
	    cur.moveToFirst();
	    while(!cur.isAfterLast()){
	    	count++;
	    	cur.moveToNext();
	    }
	    cur.moveToFirst();
	    int i = 0;
	    int j=2;
	     String bus_no="Bus:"+cur.getString(1);
	    while(!cur.isAfterLast()){
	    do
	    {
	    	String n = cur.getString(j).toString();
	    	 j++;
	      	 list1.add(n);
	     }while(cur.getString(j)!=null);
	     	cur.moveToNext();
	    }
	   
	    textView.setText(String.valueOf(bus_no));
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	            android.R.layout.simple_list_item_1,list1);
	        list.setAdapter(adapter);
	      //db.close();
	
	}
	 private class StableArrayAdapter extends ArrayAdapter<String> {
		    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();
		    public StableArrayAdapter(Context context, int textViewResourceId,
		        List<String> objects) {
		      super(context, textViewResourceId, objects);
		      for (int i = 0; i < objects.size(); ++i) {
		        mIdMap.put(objects.get(i), i);
		      }
		    }
	 }
}
