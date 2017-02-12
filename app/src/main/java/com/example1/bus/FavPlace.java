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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
public class FavPlace  extends Activity{
	SQLiteDatabase db;
	Cursor cur;
	int count=0;
  String[] fbus;
  ListView list;
  ArrayAdapter<String> det;
   final ArrayList<String> list1 = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.places);
		
		Intent intent = getIntent();
		list = (ListView)findViewById(R.id.listView2);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		list.setOnItemClickListener(new OnItemClickListener() {
			 
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
			int sel=list.getCheckedItemPosition();
			int frst=list.getFirstVisiblePosition();
			sel=sel-frst;
			System.out.println("pos"+sel);
				
				 String s =((TextView) list.getChildAt(sel)).getText().toString();
					System.out.println("poano"+s);

				//Intent intent = new Intent(getBaseContext(),Places_listener.class);
               // startActivity(intent);  
				 Intent myIntent = new Intent(arg0.getContext(),Places_listener.class);
	 				myIntent.putExtra("busno",s);
	 				startActivityForResult(myIntent, 0);
            }
			});
		db = openOrCreateDatabase("busdetails.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db.setVersion(1);
	    db.setLocale(Locale.getDefault());
	    String q1="select bus_no from Myplaces";
	    cur = db.rawQuery(q1,null );
	    cur.moveToFirst();
	    while(!cur.isAfterLast()){
	    	count++;
	    	cur.moveToNext();
	    }
	    cur.moveToFirst();
	    int j=0;
	    /*while(!cur.isAfterLast()){
		    
			do
		    {
		    	String n = cur.getString(j).toString();
		    	 j++;
		      	 list1.add(n);
		     }while(cur.getString(j)!=null);
		     	cur.moveToNext();
		    }*/
	      //db.close();
	      //	    int j=0;
	    
	    
	     while(!cur.isAfterLast()){
					list1.add(cur.getString(0).toString());
				//	System.out.println(list1);
					
		  	  cur.moveToNext();
	    }

		    final StableArrayAdapter adapter = new StableArrayAdapter(this,
		            android.R.layout.simple_list_item_1,list1);
		        list.setAdapter(adapter);
	     //db.close();
		}
	private class StableArrayAdapter extends ArrayAdapter<String>{
		HashMap<String, Integer> mIdMap= new HashMap<String, Integer>();
		public StableArrayAdapter (Context context,int textViewResourceId,
				List<String> objects){
				super(context,textViewResourceId,objects);
				for ( int i=0;i<objects.size();++i){
					mIdMap.put(objects.get(i),i);
				}
		}
		}
}
