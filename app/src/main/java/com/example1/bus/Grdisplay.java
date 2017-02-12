package com.example1.bus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
public class Grdisplay extends Activity {
    public static final String KEY_TITLE = "bus_no";
    private static final String DATABASE_TABLE = "Myplaces";
	Button button,button2;
	Cursor cur,cur2;
	ListView list;
	String[] array=new String[1];
	int count=0;
	ArrayAdapter<String> det;
	 final ArrayList<String> list1 = new ArrayList<String>();
	SQLiteDatabase db;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gr_display);
		Intent intent = getIntent();
		final CheckBox favButton = (CheckBox)findViewById( R.id.fav);
	   TextView textView = (TextView) this.findViewById(R.id.textView1);
		final String s = intent.getStringExtra("search");
		
		list = (ListView)findViewById(R.id.listView1);
	    db = openOrCreateDatabase("busdetails.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db.setVersion(1);
	    db.setLocale(Locale.getDefault());
	    String q1="select * from bus_det where bus_no ='"+s+"'";
	    cur = db.rawQuery(q1,null );
	     cur.moveToFirst();
	     if(cur!=null)
	    	 System.out.println("not null");
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
	      db.close();
	      cur.moveToFirst();
	      
	      favButton.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View arg0) {
	                if(favButton.isChecked()){
	                	Toast.makeText(getBaseContext(),"Added to My Places", 
	 							Toast.LENGTH_SHORT).show();
	                	db = openOrCreateDatabase("busdetails.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	                    ContentValues values = new ContentValues();
	                    values.put(KEY_TITLE,cur.getString(1));
	                	db.insert(DATABASE_TABLE,null,values);
	    			    //Intent intent = new Intent(getBaseContext(),FavPlace.class);
	                	}
	                else
	                	System.out.println("no");
	            }
	        });
	      db.close();
	      //addListenerOnButton();
	  		}
	/*public void addListenerOnButton() {
		button2= (Button) findViewById(R.id.imageButton1);
		button2.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View arg0) {
 			    Intent intent = new Intent(getBaseContext(),MapActivity.class);
                            startActivity(intent);   
 			}
 		});
	}*/
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
	