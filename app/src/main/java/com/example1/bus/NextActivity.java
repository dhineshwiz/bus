package com.example1.bus;
 

import android.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
 
public class NextActivity extends Activity {
 
	Button button1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.next);
		addListenerOnButton();
	}
	public void addListenerOnButton() {
		final Context context = this;	
	button1 = (Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
 			@Override
			public void onClick(View arg0) {
 			    Intent intent = new Intent(context,NextDisplay.class);
                            startActivity(intent);   
 			}
 		});
	}
 
}