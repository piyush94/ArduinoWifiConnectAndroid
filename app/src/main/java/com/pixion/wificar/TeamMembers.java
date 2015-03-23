package com.pixion.wificar;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;

public class TeamMembers extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//make app fullscreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team_members);
}
}
