package com.pixion.wificar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class AboutUs extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//make app fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_us);

		Button b1 = (Button)findViewById(R.id.tm);
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent abus = new Intent("com.pixion.wificar.TeamMembers");
				startActivity(abus);
			}
		});
	}
}
