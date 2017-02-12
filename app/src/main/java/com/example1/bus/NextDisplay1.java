package com.example1.bus;

import java.util.LinkedHashSet;


import java.util.Locale;
import java.util.Set;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class NextDisplay1 extends Activity {
	SQLiteDatabase db;
	Cursor cur,cur1,cur2,cur3;
	public void onCreate(Bundle savedInstanceState) {
		int i;
		String [] sr=new String[50];
		String [] dr=new String[50];
		String [] bs=new String[500];
		String [] fbs=new String[250];
		String [] bs1=new String[500];
		String [] fbs1=new String[250];
		String [] bd=new String[500];
		String [] fbd=new String[250];
		String [] bd1=new String[500];
		String [] fbd1=new String[250];
		String [] rsrc=new String[500];
		String [] busno=new String[50];
		String [] rdes=new String[500];
		String [][] srtn=new String[50][50];
		String [][] drtn=new String[50][50];
		String n;
    db = openOrCreateDatabase("fbusdb1.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
    db.setVersion(1);
    db.setLocale(Locale.getDefault());
		super.onCreate(savedInstanceState);
		for(i=0;i<150;i++){
	    	bs[i]=null;
	    }
	    for(i=0;i<150;i++){
	    	bs1[i]=null;
	    }
	    for(i=0;i<150;i++){
	    	bd[i]=null;
	    }
	    for(i=0;i<150;i++){
	    	bd1[i]=null;
	    }
	    for(i=0;i<200;i++){
	    	rsrc[i]=null;
	    }
	    for(i=0;i<200;i++){
	    	rdes[i]=null;
	    }
	    i=0;
	/*	setTheme(android.R.style.Theme);
		setContentView(R.layout.next);
		String[] myarray =getResources().getStringArray(R.array.src);
		final AutoCompleteTextView sEdit = (AutoCompleteTextView) findViewById(R.id.editText2);
		final AutoCompleteTextView dEdit = (AutoCompleteTextView) findViewById(R.id.editText1);
	    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	            android.R.layout.select_dialog_item,myarray);
	    sEdit.setThreshold(1);
	    dEdit.setThreshold(1);
	    sEdit.setAdapter(adapter);
	    dEdit.setAdapter(adapter);*/
				Intent intent = getIntent();
		  final String[] srt = intent.getStringArrayExtra("srt");
		  final String[] drt = intent.getStringArrayExtra("drt");
		  final String src=intent.getStringExtra("src");
		  final String des=intent.getStringExtra("des");
		  i=0;
		  //System.out.println("recvd"+drt[i]);
		  i=0;
		  int j=0;
	       int k=0;
	       int t=0;
	       while(srt[i]!=null){//retrieving bus no for the source
	    	   //System.out.println("pp"+srt[i]);
	    	 String temp =srt[i];
	    	 //temp="R2";
	          String q3="select bus_no from Bustable where '"+temp+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24,Route25,Route26,Route27,Route28,Route29);";
	          cur2=db.rawQuery(q3,null);
	          cur2.moveToFirst();
	          int c1=cur2.getCount();
	          //System.out.println(c1);
	          if(cur2!=null){
	        	  cur2.moveToFirst();
	        	  t=j;
	            while(!cur2.isAfterLast()){
	        	   n=cur2.getString(0).toString();
  	     		  bs[j]=n;
	        	  //System.out.println("sstr"+bs[j]);
	        		  j++;
	       	  cur2.moveToNext();
	          }
	          }
	          //i++;
	  		//System.out.println("nd1");

	          String temp1 ="~"+srt[i];
		    	// temp="R2";	
		           q3="select bus_no from Bustable where '"+temp1+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24,Route25,Route26,Route27,Route28,Route29);";
		          cur2=db.rawQuery(q3,null);
		          cur2.moveToFirst();
		          cur2.getCount();
		          //System.out.println(c1);
		          if(cur2!=null){
		        	  cur2.moveToFirst();
		        	  t=k;
		        	  
		             while(!cur2.isAfterLast()){
		            	  n=cur2.getString(0).toString();
		        	  	   bs1[k]=n;
		        	//  System.out.println("srev"+bs1[k]);
		        		  k++;
		       	  cur2.moveToNext();
		          }
		          }
		          i++;
	            }
	       cur2.close();
	       i=0;
	       j=0;
	       k=0;
	       while(drt[i]!=null){//retrieving bus no for the destination
	    	  // System.out.println("pp"+drt[i]);
	    	 String temp =drt[i];
	    	 //temp="R2";
	          String q3="select bus_no from Bustable where '"+temp+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24,Route25,Route26,Route27,Route28,Route29);";
	          cur3=db.rawQuery(q3,null);
	          cur3.moveToFirst();
	          int c1=cur3.getCount();
	          //System.out.println(c1);
	          if(cur3!=null){
	          cur3.moveToFirst();
	             while(!cur3.isAfterLast()){
	        	  n=cur3.getString(0).toString();
	        	   bd[j]=n;
	        	  //System.out.println("dstr"+bd[j]);
	        		  j++;
	       	  cur3.moveToNext();
	          }
	          }
	          //i++;
	          String temp1 ="~"+drt[i];
		    	// temp="R2";	
		           q3="select bus_no from Bustable where '"+temp1+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24,Route25,Route26,Route27,Route28,Route29);";
		          cur3=db.rawQuery(q3,null);
		          cur3.moveToFirst();
		          cur3.getCount();
		          //System.out.println(c1);
		          if(cur3!=null){
		          cur3.moveToFirst();
		   
		             while(!cur3.isAfterLast()){
		        	   n=cur3.getString(0).toString();
		        	   bd1[k]=n;
		        	//  System.out.println("drev"+bd1[k]);
		        		  k++;
		       	  cur3.moveToNext();
		          }
		          }
		          i++;
	            }
	       cur3.close();
	       j=0;
	     for(i=0;bs[i]!=null;i++)
	     {
	    	 rsrc[j]=bs[i];
	    	 j++;
	     }
	     for(i=0;bs1[i]!=null;i++){
	    	 rsrc[j]=bs1[i];
	    	 j++;
	     }
	     for(i=0;rsrc[i]!=null;i++)
	     {
	    	// System.out.println("final"+rsrc[i]);
	     }
	     /*int i = 0; //removing repetition in source bus numbers
	     Set<String> tmp = new LinkedHashSet<String>();
	       for (String each : rsrc) {
	           tmp.add(each);
	       }
	       for (String each : tmp) {
	           fbs[i++] = each;
	       }
	       for(i=0;fbs[i]!=null;i++)
	       System.out.println("sr"+fbs[i]);*/
	       
	       j=0;
		     for(i=0;bd[i]!=null;i++)
		     {
		    	 rdes[j]=bd[i];
		    	 j++;
		     }
		     for(i=0;bd1[i]!=null;i++){
		    	 rdes[j]=bd1[i];
		    	 j++;
		     }
		     for(i=0;rdes[i]!=null;i++)
		     {
		    	// System.out.println("final"+rdes[i]);
		     }
	/*	      i = 0;//removing repetition in destination bus numbers
		     Set<String> tmp1 = new LinkedHashSet<String>();
		       for (String each : rdes) {
		           tmp1.add(each);
		       }
		       for (String each : tmp1) {
		           fbd[i++] = each;
		       }
		       for(i=0;fbd[i]!=null;i++)
		       System.out.println("dr"+fbd[i]);
		    k=0;  
      for(i=0;fbs[i]!=null;i++){
   	 for(j=0;fbd[j]!=null;j++){
  		   if(fbs[i].equals(fbd[j])==true){
  			   busno[k]=fbs[i];
		  	     System.out.println("b"+busno[k]);
		  	   k++;
		   		   }
		   	   }
}*/
		     db.close();
      Intent intent1 = new Intent(getBaseContext(),NextDisplay2.class);
      intent1.putExtra("rsrc",rsrc);
      intent1.putExtra("rdes",rdes);
      intent1.putExtra("src",src);
      intent1.putExtra("des",des);
      startActivity(intent1);
}
}