package com.example1.bus;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Transit1 extends Activity {
	SQLiteDatabase db;
	Cursor cur,cur1,cur2,cur3;
	int i;
	int j;
	int k,l;
	String [][] bs=new String[200][200];
	String [][] bs1=new String[200][200];
	String srt[][]=new String[200][50];
	public void onCreate(Bundle savedInstanceState) {
		for(i=0;i<200;i++){
			for(j=0;j<50;j++)
	    	srt[i][j]=null;
	    }
		super.onCreate(savedInstanceState);
		db = openOrCreateDatabase("fbusdb.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db.setVersion(1);
	    db.setLocale(Locale.getDefault());
		
		System.out.println("tr1111");
		Intent intent = getIntent();
	
		final String[] jname = intent.getStringArrayExtra("jname");
		final String[] bus = intent.getStringArrayExtra("busno");																														
		final String[] fbs = intent.getStringArrayExtra("fbs");
		final String[] fbd = intent.getStringArrayExtra("fbd");
		final String src=intent.getStringExtra("src");
		final String des=intent.getStringExtra("des");
		//System.out.println("tr1"+jname[12]);
		i=0;
		j=0;
		while(jname[i]!=null){
			String temp=jname[i];
	     // 	System.out.println("sj"+temp);
	    String q="select Route from route_table where '"+temp+"' IN (Starting_point,stop1,stop2,stop3,stop4,stop5,stop6,stop7);";
	    cur = db.rawQuery(q,null );
	    cur.moveToFirst(); 
	    j=0;
	    while(!cur.isAfterLast()){
		      	String n = cur.getString(0).toString();
		      	srt[i][j]=n;
		    /*  	System.out.println("si"+i);
		      	System.out.println("s"+srt[i][j]);*/
		      	j++;
		    cur.moveToNext();
		    }
	       cur.close();
	    i++;
		}
		i=0;
		 j=0;
	        k=0;
	       int t=0;
	       l=0;
	       while(i<150){//retrieving bus no for the junction
	    	   l=0;
	    	   j=0;
	    	   while(srt[i][l]!=null){
	    	 String temp =srt[i][l];
	    	// System.out.println("jn"+temp);
	    	String q3="select bus_no from Bustable where '"+temp+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24,Route25,Route26,Route27,Route28,Route29);";
	          cur2=db.rawQuery(q3,null);
	          cur2.moveToFirst();
	           if(cur2!=null){
	        	  cur2.moveToFirst();
	        	  t=j;
	            while(!cur2.isAfterLast()){
	        	  String n=cur2.getString(0).toString();
	     		  bs[i][j]=n;
	     //		 System.out.println("jsstr"+i);
	     	//	System.out.println("jsstr"+j);
	      // 	System.out.println("jsstr"+bs[i][j]);
	        		  j++;
	       	  cur2.moveToNext();
	          }
	          }
	           else{
	        	   bs[i][j]=null;
	           }
	           l++;
	    	   }
	    	   l=0;
	    	   k=0;
	          while(srt[i][l]!=null){
	        	  String temp1 ="~"+srt[i][l];
	        	  String  q3="select bus_no from Bustable where '"+temp1+"' IN(Route1,Route2,Route3,Route4,Route5,Route6,Route7,Route8,Route9,Route10,Route11,Route12,Route13,Route14,Route15,Route16,Route17,Route18,Route19,Route20,Route21,Route22,Route23,Route24,Route25,Route26,Route27,Route28,Route29);";
	        	  cur2=db.rawQuery(q3,null);
		          cur2.moveToFirst();
		          if(cur2!=null){
		        	  cur2.moveToFirst();
		        	  t=k;
		        	
		             while(!cur2.isAfterLast()){
		            	 String  n=cur2.getString(0).toString();
		        	  	   bs1[i][k]=n;
		        //	/*  	 System.out.println("jsrev"+i);
		 	     //		System.out.println("jsrev"+k);
		      //  	  System.out.println("jsrev"+bs1[i][k]);
		        		  k++;
		       	  cur2.moveToNext();
		          }
		          }
		          else{
		        	  bs1[i][k]=null;
		          }
		             l++;
		        }
		       l=0;   
	    	   i++;
	            }
	       cur2.close();
	       i=0;j=0;k=0;
	       System.out.println("while ended");
	    /*while(i<153){
	    	   j=0;
	    	   k=0;
	    	   while(fbs[k]!=null){
	    		   j=0;
	    	   while(bs[i][j]!=null)
	    	   {
	    		    if((bs[i][j].equals(fbs[k]))==true)
	    		   System.out.println("jsstr"+bs[i][j]);
	    		   j++;
	    		  }
	    	   k++;
	    	   }
	    	   i++;
	       }*/
	       /*while(bs[i][j]!=null){
	    	   k=0;
	    	   while(fbs[k]!=null){
	    		   j=0;
	    		   while(bs[i][j]!=null){
	    	   	   if(fbs[k].equals(bs[i][j])==true){
	    	   		   System.out.println("tr"+bs[k]);
	    	   		   break;
	    		   }
	    	   	   j++;
	    		   }
	    		   k++;
	    	   }
	    	   j=0;
	    	   i++;
	       }*/
	       
	}
}
