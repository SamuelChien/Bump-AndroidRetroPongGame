package com.example.projectattempt1;

import com.example.projectattempt1.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Gameover extends Activity {
	
	Button cont, quit;
	
	@Override
	protected void onCreate(Bundle BOX) {

		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		super.onCreate(BOX);
		setContentView(R.layout.gameover);
		
        quit = (Button) findViewById(R.id.quitbutton);
        
        quit.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {

				Intent openStartingPoint = new Intent("android.intent.action.TRYTHIS");
				finish();
				startActivity(openStartingPoint);
			}
		});
	}
}