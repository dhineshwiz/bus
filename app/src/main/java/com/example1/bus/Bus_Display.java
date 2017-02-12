package com.example1.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import android.R.color;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Bus_Display extends Activity {
   public static final String KEY_TITLE = "bus_no";
    private static final String DATABASE_TABLE = "Myplaces";
	SQLiteDatabase db,db1;
	Bundle b=new Bundle();
	String[] rno_arr=new String[50];
	String[][] rname_arr=new String[50][50];
	String[] route=new String[50];
	Integer[] distance=new Integer[50];
	Integer tot_dist;
	float td;
	String key;
	Cursor cur,cur1,cur2,cur3;
	int count=0;
	int t,bt;
	int cd;
	ListView list;
	final ArrayList<String> list1 = new ArrayList<String>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gr_display);
		list = (ListView)findViewById(R.id.listView1);
		TextView textView = (TextView) this.findViewById(R.id.textView1);
		TextView textView1 = (TextView) this.findViewById(R.id.textView2);
	//	TextView textView2 = (TextView) this.findViewById(R.id.textView3);
		//Button cost = (Button) this.findViewById(R.id.cost);
		/*ImageButton gmap = (ImageButton) this.findViewById(R.id.imageButton1);
		gmap.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(getBaseContext(),Map.class);
                            startActivity(intent);   
 			}
 		});*/
		final CheckBox favButton = (CheckBox)findViewById( R.id.fav);
		Intent intent = getIntent();
		final String s = intent.getStringExtra("search");
	//	System.out.println("bus"+s);
		final String src=intent.getStringExtra("src");
		final String des=intent.getStringExtra("des");
		//String s="11A";
		db = openOrCreateDatabase("fbusdb1.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db.setVersion(1);
	    db.setLocale(Locale.getDefault());
	    //my places checking 
	    String q="select * from Myplaces where bus_no ='"+s+"'"; 
	    cur2=db.rawQuery(q,null);
	    int c=cur2.getCount();
	    if(c!=0)
	    {
	    	favButton.setChecked(true);
	    }
	    
	    cur2.close();
	    int j=2;
	    //distance extraction
	    /*if((src==null)&&(des==null)){
	    	cost.setVisibility(View.VISIBLE);
	    }*/
	    if((src!=null)&&(des!=null)){
	     String q4="select * from distance_new where bus_no ='"+s+"'";
	    cur3=db.rawQuery(q4,null);
	    int d=0;
	    cd=cur3.getCount();
	    if(cd!=0){
	    cur3.moveToFirst();
	    do{
	    	distance[d]=cur3.getInt(j);
	    	//System.out.println(+d+"d"+distance[d]);
	    	j++;
	    	d++;
	    }while(cur3.getString(j)!=null);
	    }
	    }
	    cur3.close();
	    String q1="select * from Bustable where bus_no ='"+s+"'";
	    cur = db.rawQuery(q1,null );
	    cur.moveToFirst();
	     j=2;
	     int i=0;
	     int k=0;
	     //retrieving route number from the bus no;
	    do
	    {
	    	 rno_arr[i]= cur.getString(j).toString();
	    	// System.out.println("rt"+rno_arr[i]);
	    	  j++;
	    	 i++;
	    }while(cur.getString(j)!=null);
	    db.close();
	    cur.close();
	    db = openOrCreateDatabase("fbusdb1.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db.setVersion(1);
	    db.setLocale(Locale.getDefault());
	  	    i=0;
	    while(rno_arr[i]!=null){ //retrieving route name from the bus no;
	    	k=0;
	    	String temp=rno_arr[i];
	    	if(temp.charAt(0)=='~')
	    	{
	    		temp=temp.replaceAll("[~]","");
	    		//System.out.println(temp);
	    		String q2="select * from route_table where Route='"+temp+"'";
	    		cur1=db.rawQuery(q2,null);
	    		cur1.moveToFirst();
	    		int p = cur1.getColumnCount();
	    		p--;
	    		cur1.moveToFirst();
	    	 while(p>0){
	    			 if(cur1.getString(p)!=null)
	    			 {
	    				 String r= cur1.getString(p).toString();
    			    	 rname_arr[i][k]=r;
    			     //System.out.println(rname_arr[i][k]);
    			    	 k++;
    				 }
	    			 p--;
	    		 }
	    		 }
	    	else
	    	{
	    		//System.out.println(temp);
	    		String q2="select * from route_table where Route='"+temp+"'";
	    		cur1=db.rawQuery(q2,null);
	    		cur1.moveToFirst();
	    		j=1;
	    		do
	    	    {
	    	    	 String r= cur1.getString(j).toString();
	    	    	 rname_arr[i][k]=r;
	    	    	//System.out.println(rname_arr[i][k]);
	    	    	 k++;	 
	    	    	 j++;
	    	     }while(cur1.getString(j)!=null);
	    	}
	      	i++;
	   }
	    i=0;
	    k=0;
	    j=0;
	/* for(;rname_arr[i][j]!=null;i++){
		  for(k=0;rname_arr[i][k]!=null;k++){
			  System.out.println("i"+i);
			  System.out.println("k"+k);
			  System.out.println(rname_arr[i][k]);
		  }
	  }*/
	  int l;
	  route[0]=rname_arr[0][0];
	  int r=1;
	    for(i=1,j=0;rname_arr[i][j]!=null;i++){//removing repetition of junction points
	    	for(l=i-1,k=1;rname_arr[l][k]!=null;k++){
	    		/*System.out.println("l"+l);
	    		System.out.println("k"+k);
	    		System.out.println("in"+rname_arr[l][k]);
	    		System.out.println("i"+i);
	    		System.out.println("j"+j);
	    		System.out.println("out"+rname_arr[i][j]);*/
	    		//rname_arr[l][k].equals(rname_arr[i][j]);
	    		if(rname_arr[l][k].equals(rname_arr[i][j])==true){
	    			
	    			route[r]=rname_arr[l][k];
	    			//System.out.println("same"+route[r]);
	    			r++;
	    		}
	    	
	    		else if(rname_arr[l][k].equals(rname_arr[i][j])==false)
	    		{
	    			route[r]=rname_arr[l][k];
	    			//System.out.println("bb"+route[r]);
	    			r++;
	    		}
   	    	}
	    }
	    i--;
	    j++;
	    while(rname_arr[i][j]!=null){
	    	route[r]=rname_arr[i][j];
	    	r++;
	    	j++;
	    }
	    
	    i=0;
	    int si=0;
	    int di=0;
	    //Intent i1=new Intent(getBaseContext(),Grdisplay.class);
	    for(;route[i]!=null;i++){
			//System.out.println(route[i]);
			String n=route[i];
//			b.putStringArray(key, new String[]{route[i]});
			if((src!=null)&&(des!=null)){
				//cost.setVisibility(View.VISIBLE);
			if(src.equals(n)==true){
				 si=i;
				}
			if(des.equals(n)==true){
				 di=i;
			}
			}
			/*if(si>di){
				ct=si-di;
			}
			if(si<di){
				ct=si-di;
			}*/
			
		//	 System.out.println("sindex"+si);
		//	System.out.println("dindex"+di);
			list1.add(n);
			
		 }
	    i=0;
	    
	    //t=si+1;
	    td=0;
	    if(cd!=0){
	    if(si>di){
	    t=si;
		bt=di;
	    }
	    else if(si<di){
	    	t=di;
	    	bt=si;
	    }
		
		while(t>bt){
			td+=distance[t];
			t--;
		}
				td=td/1000;
		//System.out.println("distance"+td);
		textView1.setText("Km: "+String.valueOf(td));
		//textView2.setText("Cost: ");
	    }
	     if(si>di){
	    	
			Collections.reverse(list1);
		}
	    
	//    i1.putExtras(b);
	  //  startActivity(i1);
	    favButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if(favButton.isChecked()){
                	Toast.makeText(getBaseContext(),"Added to My Places", 
 							Toast.LENGTH_SHORT).show();
                	db = openOrCreateDatabase("fbusdb1.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    ContentValues values = new ContentValues();
                    values.put(KEY_TITLE,s);
                	db.insert(DATABASE_TABLE,null,values);
    			    //Intent intent = new Intent(getBaseContext(),FavPlace.class);
                	}
                if(!favButton.isChecked()){
                	db = openOrCreateDatabase("fbusdb1.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
                    ContentValues values = new ContentValues();
                    //values.put(KEY_TITLE,s);
                	db.delete(DATABASE_TABLE,KEY_TITLE +"="+s,null);
                }
                	//System.out.println("no");
            }
        });
	    db.close();
	   textView.setText("Bus no: "+String.valueOf(s));
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
	 
}