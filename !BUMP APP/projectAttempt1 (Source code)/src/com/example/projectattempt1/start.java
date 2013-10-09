package com.example.projectattempt1;

import com.example.projectattempt1.R;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class start extends Activity{

	MediaPlayer ourSong;
	
	@Override
	protected void onCreate(Bundle BOX) {
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(BOX);
		setContentView(R.layout.start);
		
		//ourSong = MediaPlayer.create(start.this, R.raw.splashsound);
		
		//ourSong.start();
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(4000);					
				} catch (InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent("android.intent.action.TRYTHIS");
					//ourSong.release();
					finish();
					startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}

	@Override
    protected void onPause() {
		super.onPause();
		//ourSong.release();
    }
}
