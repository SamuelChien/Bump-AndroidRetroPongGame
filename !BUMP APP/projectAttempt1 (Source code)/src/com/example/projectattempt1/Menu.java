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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

public class Menu extends Activity {

	Button about, start, back;
	AnimationDrawable menuFrame;
	MediaPlayer menuSong, startSong;
	
	@Override
    protected void onCreate(Bundle BOX) {
	 	this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(BOX);
        
        setContentView(R.layout.menu);
        
        ImageView background = (ImageView)findViewById(R.id.menu);
        background.setImageResource(R.drawable.menuscreen);
        menuFrame = (AnimationDrawable) background.getDrawable();
        menuFrame.start();
        
        menuSong = MediaPlayer.create(Menu.this, R.raw.menu_music_1);
        menuSong.setLooping(true);
        menuSong.start();
        
        startSong = MediaPlayer.create(Menu.this, R.raw.menu_clicks);
        
        about = (Button) findViewById(R.id.button2);
        start = (Button) findViewById(R.id.button1);
        
        about.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent openStartingPoint = new Intent("android.intent.action.ABOUT");
				startActivity(openStartingPoint);
			}
		});
        
        start.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent openStartingPoint = new Intent("android.intent.action.GTXSURFACE");
				GTXSurface.running = 1;
				finish();
				startActivity(openStartingPoint);
			}
		});
	}
	
	@Override
	protected void onStop() {
	    if (this.isFinishing()) {
	    	menuSong.stop();
	    }
	    Context context = getApplicationContext();
	    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
	    if (!taskInfo.isEmpty()) {
	    	ComponentName topActivity = taskInfo.get(0).topActivity; 
	    	if (!topActivity.getPackageName().equals(context.getPackageName())) {
	    		menuSong.stop();
	    	}
	    }
	    super.onStop();
	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		if (menuSong.isPlaying() == false) {
			menuSong = MediaPlayer.create(Menu.this, R.raw.menu_music_1);
			menuSong.start();
			menuSong.setLooping(true);
		}
	}
}
