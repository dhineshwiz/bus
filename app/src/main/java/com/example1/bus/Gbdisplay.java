package com.example1.bus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class Gbdisplay extends Activity{
	int i;
	ListView list;
	final ArrayList<String> list1 = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gb_display);
		TextView textView = (TextView) this.findViewById(R.id.textView1);
		TextView textView1 = (TextView) this.findViewById(R.id.textView2);
		Intent intent = getIntent();
		System.out.println("gb");
		  String[] bus = intent.getStringArrayExtra("busno");
		  final String src=intent.getStringExtra("src");
		  final String des=intent.getStringExtra("des");
		     String sw[]=intent.getStringArrayExtra("sw");
		     System.out.println("s"+sw[0]);
		     System.out.println("s"+sw[1]);
		  
		     /*for(i=0;i<10;i++)
		     {
		    	 
		    	 if(sw[i].equals("no")!=true){
			   System.out.println("same"+sw[i]);
		  }
		     }*/
		list = (ListView)findViewById(R.id.listView1);
		list.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		list.setOnItemClickListener(new OnItemClickListener() {
						public void onItemClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
			//	View parentView = (View) arg1.getParent();
				//parentView.setBackgroundColor(Color.BLUE);
			//	((ViewGroup) parentView).getChildAt(0).setBackgroundColor(Color.BLUE);
				// TODO Auto-generated method stub
				int sel=0;
			 sel=list.getCheckedItemPosition();
			//System.out.println("pono"+sel);
			int frst=list.getFirstVisiblePosition();
			sel=sel-frst;
			//System.out.println("pos"+sel);
				 String s =((TextView) list.getChildAt(sel)).getText().toString();
				 System.out.println("cl"+s);
				if(s.charAt(0)=='S'){
					System.out.println("irf");
				}
					//System.out.println("poano"+s);
				 
				//Intent intent = new Intent(getBaseContext(),Places_listener.class);
               // startActivity(intent);  
				if(s.charAt(1)!='w'){
				 Intent myIntent = new Intent(arg0.getContext(),Bus_Display.class);
				 	myIntent.putExtra("search",s);
	 				myIntent.putExtra("src",src);
	 		       myIntent.putExtra("des",des);
	 				startActivityForResult(myIntent, 0);
				}
            }
			});
			  for(i=0;bus[i]!=null;i++){
			  list1.add(bus[i]);
			  //System.out.println("new"+bus[i]);
			  }
			  
			  for(i=0;i<10;i++)
			  {
				  if(sw[i]!=null){
				  if(sw[i].equals("no")!=true){
			  list1.add(sw[i]);
				  }
			  }
			  }
		  textView.setText("Source: "+String.valueOf(src));
		  textView1.setText("Destination: "+String.valueOf(des));
		  final StableArrayAdapter adapter = new StableArrayAdapter(this,
		            android.R.layout.simple_list_item_1,list1);
		        list.setAdapter(adapter);
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
	 public boolean onKeyDown(int keyCode, KeyEvent event){
		    if(keyCode == KeyEvent.KEYCODE_BACK) {
		            Intent Act2Intent = new Intent(this, NextDisplay.class);
		            Act2Intent.setFlags(Act2Intent.FLAG_ACTIVITY_CLEAR_TOP);
		            startActivity(Act2Intent);          
		            finish();
		            return true;
		    }
		    return false;
		}
}
