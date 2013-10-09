package com.example.projectattempt1;

import com.example.projectattempt1.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class About extends Activity {	
	@Override
	protected void onCreate(Bundle BOX) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(BOX);
		setContentView(R.layout.about);
	}
}