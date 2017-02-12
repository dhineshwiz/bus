package com.example1.bus;

import java.util.LinkedHashSet;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class NextDisplay2 extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
		String [] fbs=new String[500];
		String [] fbd=new String[500];
		String [] busno=new String[100];
		int j,k;
		super.onCreate(savedInstanceState);
		//System.out.println("next2");
		/*setTheme(android.R.style.Theme);
		setContentView(R.layout.next);
		String[] myarray =getResources().getStringArray(R.array.src);
		final AutoCompleteTextView dEdit = (AutoCompleteTextView) findViewById(R.id.editText1);
		final AutoCompleteTextView sEdit = (AutoCompleteTextView) findViewById(R.id.editText2);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.select_dialog_item,myarray);
	    sEdit.setThreshold(1);
	    dEdit.setThreshold(1);
	    sEdit.setAdapter(adapter);
	    dEdit.setAdapter(adapter);*/
		Intent intent = getIntent();
		  String[] rsrc = intent.getStringArrayExtra("rsrc");
		  String[] rdes = intent.getStringArrayExtra("rdes");
		  final String src=intent.getStringExtra("src");
		  final String des=intent.getStringExtra("des");
		  int m=3;
		  
		  //System.out.println("recvd2"+rdes[m]);
		
		 int i = 0; //removing repetition in source bus numbers
	     Set<String> tmp = new LinkedHashSet<String>();
	       for (String each : rsrc) {
	           tmp.add(each);
	       }
	       for (String each : tmp) {
	           fbs[i++] = each;
	       }
	    /*   for(i=0;fbs[i]!=null;i++)
	       System.out.println("sr"+fbs[i]);*/
	    	          i = 0;//removing repetition in destination bus numbers
		     Set<String> tmp1 = new LinkedHashSet<String>();
		       for (String each : rdes) {
		           tmp1.add(each);
		       }
		       for (String each : tmp1) {
		           fbd[i++] = each;
		       }
		      // for(i=0;fbd[i]!=null;i++)
		       //System.out.println("dr"+fbd[i]);
		    k=0;  
     for(i=0;fbs[i]!=null;i++){
  	 for(j=0;fbd[j]!=null;j++){
 		   if(fbs[i].equals(fbd[j])==true){
 			   
 			   busno[k]=fbs[i];
		  	     //System.out.println("b"+busno[k]);
		  	   k++;
		   		   }
		   	   }
     }
     int lt;
     lt=k;
     System.out.println("busltgh"+k);
     
   //  System.out.println("nd2");
     if(lt>5){
    	 String sw[]=new String[20];
    	 for(i=0;i<20;i++){
    		 sw[i]="no";
    	 }
     Intent intent1 = new Intent(getBaseContext(),Gbdisplay.class);
     intent1.putExtra("busno",busno);
     intent1.putExtra("fbs",fbs);
     intent1.putExtra("fbd",fbd);
     intent1.putExtra("src",src);
     intent1.putExtra("sw",sw);
     intent1.putExtra("des",des);
     startActivity(intent1);
     }
     else if(lt<=5){
    	 Intent intent2 = new Intent(getBaseContext(),Transit.class);
         intent2.putExtra("busno",busno);
         intent2.putExtra("fbs",fbs);
         intent2.putExtra("fbd",fbd);
         intent2.putExtra("src",src);
         intent2.putExtra("des",des);
         startActivity(intent2);
     }
	}
	
}
