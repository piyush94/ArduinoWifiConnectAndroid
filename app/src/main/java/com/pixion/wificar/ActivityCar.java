package com.pixion.wificar;

//FILE IMPORTS
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.os.Environment;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.annotation.SuppressLint;
import android.app.Activity;

@SuppressLint({ "SimpleDateFormat", "SetJavaScriptEnabled"})
public class ActivityCar extends Activity {
	
	WebView fw;
	WebView vf;
	Button forward,backward,left,right,log,rf,ip,brake;
	String str = null;
	boolean en = false;
	SimpleDateFormat df;
	Calendar c = Calendar.getInstance();
	EditText feed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		//make app fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    
        
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		
		fw = (WebView)findViewById(R.id.webView2);
		fw.setBackgroundColor(0x00000000);
		fw.setVerticalScrollBarEnabled(true);
		fw.setVerticalScrollbarOverlay(true);
		
		vf = (WebView)findViewById(R.id.webView1);
		vf.setBackgroundColor(0x00000000);
		vf.getSettings().setJavaScriptEnabled(true);

		df = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
		
		forward = (Button)findViewById(R.id.fd);
		backward = (Button)findViewById(R.id.bd);
		left = (Button)findViewById(R.id.lt);
		right = (Button)findViewById(R.id.rt);
		log = (Button)findViewById(R.id.log);
		rf = (Button)findViewById(R.id.rf);
		ip = (Button)findViewById(R.id.ipf);
		brake = (Button)findViewById(R.id.brk);
		
		str = "-----------------------------\n" + df.format(c.getTime()) + "\n-----------------------------";
		
		
		//FORWARD BUTTON
		forward.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction() == MotionEvent.ACTION_DOWN && en)
				{
					fw.loadUrl("http://192.168.1.4/?FORWARD");			//CALLS FORWARD FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str + "\nFORWARD";
				}
				else if(event.getAction() == MotionEvent.ACTION_UP && en)
				{
					fw.loadUrl("http://192.168.1.4/?STOP");				//CALLS STOP FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str + "\nSTOP";
				}
				if(!en)
					Toast.makeText(getApplicationContext(), "Car Not Started Yet!!", Toast.LENGTH_SHORT).show();
				
				return false;
			}
		});
		
		//BACKWARD BUTTON
		backward.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction() == MotionEvent.ACTION_DOWN && en)
				{
					fw.loadUrl("http://192.168.1.4/?BACKWARD");			//CALLS BACKWARD FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str + "\nBACKWARD";
				}
				else if(event.getAction() == MotionEvent.ACTION_UP && en)
				{
					fw.loadUrl("http://192.168.1.4/?STOP");			//CALLS STOP FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str + "\nSTOP";
				}
				if(!en)
					Toast.makeText(getApplicationContext(), "Car Not Started Yet!!", Toast.LENGTH_SHORT).show();
				
				return false;
			}
		});
		
		//LEFT BUTTON
		left.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction() == MotionEvent.ACTION_DOWN && en)
				{
					fw.loadUrl("http://192.168.1.4/?LEFT");			//CALLS LEFT FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str + "\nLEFT";
				}
				else if(event.getAction() == MotionEvent.ACTION_UP && en)
				{
					fw.loadUrl("http://192.168.1.4/?CENTER");			//CALLS CENTER FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str + "\nCENTER";
				}
				if(!en)
					Toast.makeText(getApplicationContext(), "Car Not Started Yet!!", Toast.LENGTH_SHORT).show();
				
				return false;
			}
		});
		
		//RIGHT BUTTON
		right.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				if(event.getAction() == MotionEvent.ACTION_DOWN && en)
				{
					fw.loadUrl("http://192.168.1.4/?RIGHT");			//CALLS RIGHT FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str +"\nRIGHT";
				}
				else if(event.getAction() == MotionEvent.ACTION_UP && en)
				{
					fw.loadUrl("http://192.168.1.4/?CENTER");			//CALLS CENTER FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str + "\nCENTER";
				}
				if(!en)
					Toast.makeText(getApplicationContext(), "Car Not Started Yet!!", Toast.LENGTH_SHORT).show();
				
				return false;
			}
		});
		
		//REFRESH FEEDBACK BUTTON
		rf.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(en)
				{
					fw.loadUrl("http://192.168.1.4");
					fw.setWebViewClient(new WebViewClient());
					Toast.makeText(getApplicationContext(), "FEEDBACK REFRESHED.", Toast.LENGTH_SHORT).show();
					str = str + "\nFEEDBACK REFRESHED";
				}
				if(!en)
					Toast.makeText(getApplicationContext(), "Car Not Started Yet!!", Toast.LENGTH_SHORT).show();
			}
		});
		
		//LOG BUTTON
		log.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(en)
				{
					File myFile = new File(Environment.getExternalStorageDirectory().getPath(), "log.txt");
					boolean append = myFile.exists();
					FileOutputStream fout = null;
					try {
						fout = new FileOutputStream(myFile, append);
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					
					try {
						OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fout);
						str = str + "\n\n";
						outputStreamWriter.write(str);
						Toast.makeText(getApplicationContext(), "LOG GENERATED.", Toast.LENGTH_SHORT).show();
						outputStreamWriter.flush();
			            outputStreamWriter.close();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(), "FILE NOT FOUND!", Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Toast.makeText(getApplicationContext(), "IO ERROR!", Toast.LENGTH_SHORT).show();
						e.printStackTrace();
					}
				}
				if(!en)
					Toast.makeText(getApplicationContext(), "Car Not Started Yet!!", Toast.LENGTH_SHORT).show();
			}
		});
		
		//START VIDEO FEED
		ip.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				feed = (EditText)findViewById(R.id.editText1);
				String s = feed.getText().toString();
				vf.loadUrl("http://"+s+":8080");
				vf.setWebViewClient(new WebViewClient());
			}
		});
		
		//BRAKE BUTTON
		brake.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(en)
				{
					fw.loadUrl("http://192.168.1.4/?BRAKE");			//CALLS BRAKE FUNCTION
					fw.setWebViewClient(new WebViewClient());
					str = str +"\nBRAKE";
				}
				if(!en)
					Toast.makeText(getApplicationContext(), "Car Not Started Yet!!", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	//START TOGGLE
	public void onToggleClicked(View view) {
	    // Is the toggle on?
	    boolean on = ((ToggleButton) view).isChecked();
	 
	    if (on) {
	    	fw.loadUrl("http://192.168.1.4/?INITIALIZE");			//CALLS INITIALIZE FUNCTION
	    	fw.setWebViewClient(new WebViewClient());
	    	str = str + "\nCAR INITIALIZED";
	    	en = true;
	    	Toast.makeText(getApplicationContext(), "WifiCar Initialized. Ready To Go!", Toast.LENGTH_SHORT).show();
	    }
	    else
	    {
	    	fw.loadUrl("http://192.168.1.4/?HALT");					//CALLS HALT FUNCTION
	    	fw.setWebViewClient(new WebViewClient());
	    	en = false;
	    	Toast.makeText(getApplicationContext(), "WifiCar Stopped.", Toast.LENGTH_SHORT).show();
	    	finish();
	    }
	}
	
	//LED TOGGLE
	public void onLedClicked(View view2) {
	    // Is the toggle on?
	    	boolean led = ((ToggleButton) view2).isChecked();
	    	if (led && en) {
	    		fw.loadUrl("http://192.168.1.4/?LEDSON");				//CALLS LED-ON FUNCTION
	    		fw.setWebViewClient(new WebViewClient());
	    		str = str + "\nLIGHT's ON";
	    		Toast.makeText(getApplicationContext(), "Led's turned on.", Toast.LENGTH_SHORT).show();
	    	} else if(!led && en){
		    	fw.loadUrl("http://192.168.1.4/?LEDSOFF");				//CALLS LED-OF FUNCTION
		    	fw.setWebViewClient(new WebViewClient());
		    	str = str + "\nLIGHT's OFF";
		    	Toast.makeText(getApplicationContext(), "Led's turned off.", Toast.LENGTH_SHORT).show();
	    	}
	    	if(!en)
				Toast.makeText(getApplicationContext(), "Car Not Started Yet!!", Toast.LENGTH_SHORT).show();
	}

	//HALT
	@Override
	protected void onPause()
	{
	super.onPause();
	    //Save state here
		fw.loadUrl("http://192.168.1.4/?HALT");					//CALLS HALT FUNCTION
		Toast.makeText(getApplicationContext(), "WifiCar Stopped.", Toast.LENGTH_SHORT).show();
		finish();
	}
	
}
