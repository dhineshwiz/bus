package com.example1.bus;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Transit extends Activity {
	SQLiteDatabase db;
	Cursor cur,cur1,cur2,cur3;
	int j=0;
	int k=0;
	String jname[]=new String[200];
	String jbus[][]=new String[200][200];
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("transit");
		for(int i=0;i<200;i++)
		jname[i]=null;
		for(int i=0;i<200;i++){
			for(int j=0;j<200;j++)
				jbus[i][j]=null;
		}
		Intent intent = getIntent();
		final String[] busno = intent.getStringArrayExtra("busno");																														
		final String[] fbs = intent.getStringArrayExtra("fbs");
		final String[] fbd = intent.getStringArrayExtra("fbd");
		final String src=intent.getStringExtra("src");
		final String des=intent.getStringExtra("des");
		db = openOrCreateDatabase("fbusdb1.sqlite",SQLiteDatabase.CREATE_IF_NECESSARY, null);
	    db.setVersion(1);
	    db.setLocale(Locale.getDefault());
			super.onCreate(savedInstanceState);
			String q4="select * from newjunction_table";
		    cur=db.rawQuery(q4,null);
		    cur.moveToFirst();
		    System.out.println("count"+cur.getCount());
		    j=0;
		    k=0;
		    int i=0;
		    while(i<51){
		    	
		    	j=2;
		    	//if(cur.getString(1)==null)
		    		//break;
		    	//else
		    	if(cur.getString(0)!=null){
		    	jname[i] = cur.getString(0).toString();
		    //	System.out.println("jn"+jname[i]);
		    	do{
			    	jbus[i][j]=cur.getString(j);
			   // 	System.out.println("jj"+jbus[i][j]);
			    	j++;
			     }while(cur.getString(j)!=null);
		    	}
		    	i++;
		    	cur.moveToNext();
		    }
	/*	for(i=0;jname[i]!=null;i++){
		//	System.out.println("jn"+jname[i]);
			for(j=0;j<150;j++){
				if(jbus[i][j]!=null)
				System.out.println("jj"+jbus[i][j]);
			}
		}*/
		    String str[][]=new String[1000][1000];
		    String strn[]=new String[1000];
		    String dtr[][]=new String[1000][1000];
		    String dtrn[]=new String[1000];
		    int l=0;
		    /*for(k=0;fbs[k]!=null;k++)
		    System.out.println("ssame"+fbs[k]);*/
		for(i=0;i<200;i++){
			int t=0;
			for(j=0;j<200;j++){
				for(k=0;fbs[k]!=null;k++){
				if((fbs[k]!=null)&&(jbus[i][j]!=null)&&(jname[i].equals(src)!=true)){
				if(fbs[k].equals(jbus[i][j])==true){
					str[l][t]=fbs[k];
					strn[l]=jname[i];
					//System.out.println("ssame"+strn[l]);
					//System.out.println("ssame"+str[l][t]);
					t++;
						//System.out.println("ssame"+fbs[k]);
				}
				}
				}
			}
			l++;
		}
		l=0;
		for(i=0;i<200;i++){
			int t=0;
			for(j=0;j<200;j++){
				for(k=0;fbd[k]!=null;k++){
				if((fbd[k]!=null)&&(jbus[i][j]!=null)&&(jname[i].equals(des)!=true)){
				if(fbd[k].equals(jbus[i][j])==true){
					dtr[l][t]=fbd[k];
					//System.out.println("dsame"+dtr[l]);
					dtrn[l]=jname[i];
				//	System.out.println("dsame"+dtrn[l]);
				//System.out.println("dsame"+dtr[l][t]);
				
					t++;
				//	System.out.println("dsame"+fbd[k]);
				}
				}
				}
			}
			l++;
		}
		String sj[]=new String[10];
		String sjn[]=new String[10];
		String dj[]=new String[10];
		String djn[]=new String[10];
		String sw[]=new String [20];
		int t=0;	
		for(i=0;strn[i]!=null;i++){
				for(j=0;dtrn[j]!=null;j++){
				if(strn[i].equals(dtrn[j])==true){
			//		System.out.println("same"+strn[i]);
					for(k=0;str[i][k]!=null;k++){
						if(t<10){
							sj[t]=str[i][k];
							dj[t]=dtr[i][k];
							sjn[t]=strn[i];
							if((sj[t]!=null)&&(dj[t]!=null))
								sw[t]="Switch "+sj[t]+"|Junction:"+sjn[t]+"| "+dj[t];
					/*		System.out.println("same"+sj[t]);
							System.out.println("same"+dj[t]);
							System.out.println("same"+sjn[t]);*/
									t++;
						}
						
						/*System.out.println("same"+strn[i]);
						System.out.println("same"+str[i][k]);
						System.out.println("same"+dtr[i][k]);*/
						
					}
				}
		}
		}
		 //   for(i=0;i<10;i++)
		   // 	System.out.println("same"+sw[i]);
	  
		    Intent intent1 = new Intent(getBaseContext(),Gbdisplay.class);
		     intent1.putExtra("busno",busno);
		     intent1.putExtra("fbs",fbs);
		     intent1.putExtra("fbd",fbd);
		     intent1.putExtra("src",src);
		     intent1.putExtra("des",des);
		     intent1.putExtra("sw",sw);
		     startActivity(intent1);   
	}	    
	}