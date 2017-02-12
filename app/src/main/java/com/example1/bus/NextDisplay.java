package com.example1.bus;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NextDisplay extends Activity {
	SQLiteDatabase db;
	String src;
	String des;
	Cursor cur;
	String n="a";
public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.next);
		Button mButton = (Button)findViewById(R.id.button1);
		    final EditText sEdit = (EditText)findViewById(R.id.editText2);
		    final EditText dEdit = (EditText)findViewById(R.id.editText1);
		    mButton.setOnClickListener(
		        new View.OnClickListener()
		        {
		            public void onClick(View view)
		            {
		            	String src=sEdit.getText().toString().trim();
		        		System.out.println(src);
		        		String des=dEdit.getText().toString().trim();
		        		System.out.println(des);
		            	//Log.v("EditText", mEdit.getText().toString());
		            }
		      });
		    db = openOrCreateDatabase("busdetails.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
		    db.setVersion(1);
		    db.setLocale(Locale.getDefault());
		    //String q1="select bus_no from bus_det where bus_no ='"+src+"'";
		    String q="select bus_no from bus_det where '"+src+"' IN (starting_point,stop1,stop2)";
		    cur = db.rawQuery(q,null );
		    cur.moveToFirst();
		    while(!cur.isAfterLast()){
			      String n = cur.getString(1).toString();
			    cur.moveToNext();
			    System.out.println("aa");

			    }
		    db.close();
		    cur.close();
}
}
