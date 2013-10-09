package com.example.projectattempt1;

import java.util.List;

import com.example.projectattempt1.R;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Pause extends Activity {
	
	AnimationDrawable pauseFrame;
	Button cont, quit;
	
	@Override
	protected void onCreate(Bundle BOX) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(BOX);
		setContentView(R.layout.pause);
		
		ImageView background = (ImageView)findViewById(R.id.pause);
        background.setImageResource(R.drawable.pausemenu);
        pauseFrame = (AnimationDrawable) background.getDrawable();
        pauseFrame.start();
        
        cont = (Button) findViewById(R.id.contbutton);
        
        cont.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				finish();
			}
		});
        
        quit = (Button) findViewById(R.id.quitbutton);
        
        quit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent openStartingPoint = new Intent("android.intent.action.TRYTHIS");
				GTXSurface.running = 0;
				finish();
				startActivity(openStartingPoint);
			}
		});;
	}
}