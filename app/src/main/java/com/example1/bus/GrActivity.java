package com.example1.bus;
 

/*
 *  
        android:id="@+id/grt"
        android:layout_width="300dp"
        android:layout_height="wrap_content"
        android:layout_marginLeft="14dp"
        android:layout_marginTop="22dp"
        android:ems="10"
        android:hint="@string/grt" />
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;
 
public class GrActivity extends Activity {
	
 public String search=null;
	Spinner sp;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gr);
        addListenerOnButton();
        sp = (Spinner) findViewById(R.id.spinner1);
        
        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
 
		public void onItemSelected(AdapterView<?> arg0, View arg1,
				int arg2, long arg3) {
			// TODO Auto-generated method stub
				search=sp.getSelectedItem().toString();
				/*Toast.makeText(getBaseContext(),"Select a bus number", 
							Toast.LENGTH_SHORT).show();*/
		}
 
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
				
		}
	});
    }
 
 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.gr, menu);
        return true;
    }*/
    public void addListenerOnButton() {
     	final Context context = this;
 		Button button1 = (Button) findViewById(R.id.gbutton1);
		button1.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View arg0) {
 			    //Intent intent = new Intent(context,Grdisplay.class);
                  //          startActivity(intent);  
 				if((search.equals("Bus number")!=true))
 				{
 					Toast.makeText(getBaseContext(), sp.getSelectedItem().toString(), 
 							Toast.LENGTH_SHORT).show();
    			    Intent intent = new Intent(getBaseContext(),FavPlace.class);
 					Intent myIntent = new Intent(arg0.getContext(), Grdisplay.class);
 				myIntent.putExtra("search",search);
 				startActivityForResult(myIntent, 0);
 				}
 				else
 				{
 					Toast.makeText(getBaseContext(),"Select a bus number", 
 							Toast.LENGTH_SHORT).show();
 				}
 			}
 		});
    }
}

