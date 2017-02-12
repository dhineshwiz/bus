package com.example1.bus;
// G=25D500 Dark blue=024A68 android:background="@color/white"

import java.io.IOException;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends Activity {
	Button button1,button2,button3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		DataBaseHelper myDbHelper = new DataBaseHelper(this);
        myDbHelper = new DataBaseHelper(this);
 
        try {
 
   	myDbHelper.createDataBase();
 
 	} catch (IOException ioe) {
 
 		throw new Error("Unable to create database");
 
 	}
 
 	try {

 
 	myDbHelper.openDataBase();
 
 	}catch(SQLException sqle){
 
 		throw sqle;
 
 	}
	addListenerOnButton();
}
 	public void addListenerOnButton() {

	final Context context = this;
 		button1 = (Button) findViewById(R.id.button1);
		button2= (Button) findViewById(R.id.button2);
		button3 = (Button) findViewById(R.id.button3);
        System.out.println("The system test"+button1);
		//button1.setOnClickListener(new OnClickListener() {
 			//@Override
			//public void onClick(View arg0) {
 			    Intent intent = new Intent(context,NextActivity.class);
                            startActivity(intent);   
 			//}
 		//});
		/*button2.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context,GrActivity.class);
                            startActivity(intent);   
 			}
 		});

		button3.setOnClickListener(new OnClickListener() {
			 
			@Override
			public void onClick(View arg0) {
			    Intent intent = new Intent(context,FavPlace.class);
                            startActivity(intent);   
 			}
 		});*/
 	}
 }
