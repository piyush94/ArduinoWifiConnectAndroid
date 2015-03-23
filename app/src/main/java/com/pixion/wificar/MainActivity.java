package com.pixion.wificar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//make app fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button b1 = (Button)findViewById(R.id.button1);
		Button b2 = (Button)findViewById(R.id.button2);
		
		b1.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				WifiManager wm = (WifiManager)getSystemService(Context.WIFI_SERVICE);
				ConnectivityManager connManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                WifiInfo wInfo = wm.getConnectionInfo();
		    	if(wm.getWifiState() == WifiManager.WIFI_STATE_DISABLED)
		    	{
		    		Toast.makeText(getApplicationContext(), "Wifi is Disabled. Enabling WiFi", Toast.LENGTH_SHORT).show();
		    		wm.setWifiEnabled(true);
		    	}
		    	else if((wm.getWifiState() == WifiManager.WIFI_STATE_ENABLED) && (mWifi.isConnected()) && wInfo.getSSID().contains("netis_piyush"))
		    	{
		    		Toast.makeText(getApplicationContext(), "Wifi Connection Established", Toast.LENGTH_SHORT).show();
		    		Intent ac = new Intent("com.pixion.wificar.ActivityCar");
		    		startActivity(ac);
		    	}
		    	else
		    	{
		    		Toast.makeText(getApplicationContext(), "Waiting for WiFi to connect. ", Toast.LENGTH_SHORT).show();
		    	}
			}
		});
		
		b2.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent ac = new Intent("com.pixion.wificar.AboutUs");
				startActivity(ac);
				
			}
		});
	}
}
