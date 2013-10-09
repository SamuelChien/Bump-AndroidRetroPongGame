package com.example.projectattempt1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import android.R.bool;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.graphics.Paint.Align;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.renderscript.Type;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;

public class GTXSurface extends Activity implements OnTouchListener {

	MediaPlayer gameMusic;

	MyBringBackSurface ourSurfaceView;
	float x, y, x1, y1;
	Context sharedContext;

	public static int running = 1;

	Random rand = new Random();

	// Display display = getWindowManager().getDefaultDisplay();
	// @SuppressWarnings("deprecation")
	// int iWidth = display.getWidth(), iHeight = display.getHeight();

	int iWidth = 720, iHeight = 1280;

	int levelnum = 1, enemy_rows = 1, enemy_speed = 1,
			enemy_shoot_percentage = 0, forcefield_height = 1;

	Alien[][] aliens = null;

	@Override
	protected void onPause() {
		gameMusic.stop();
		super.onPause();
		ourSurfaceView.pause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		ourSurfaceView.resume();
	}

	@Override
	protected void onRestart() {
		gameMusic = MediaPlayer.create(GTXSurface.this, R.raw.game_music);
		gameMusic.start();
		gameMusic.setLooping(true);
		super.onRestart();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		sharedContext = this;
		ourSurfaceView = new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x = 0;
		y = 0;
		setContentView(ourSurfaceView);

		gameMusic = MediaPlayer.create(GTXSurface.this, R.raw.game_music);
		gameMusic.start();
		gameMusic.setLooping(true);
	}

	public boolean onTouch(View v, MotionEvent event) {
		x = event.getX();
		y = event.getY();
		x1 = 0;
		y1 = 0;

		int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			if (x >= 0 && x < 75 && y >= 0 && y < 75) {
				ourSurfaceView.runPause();
			}
			break;
		}
		return true;
	}

	@Override
	public void onBackPressed() {
		ourSurfaceView.runPause();
	}

	public class MyBringBackSurface extends SurfaceView implements Runnable {
		SurfaceHolder ourHolder;
		Thread ourThread = null;
		boolean isRunning = false;
		boolean launched = false;

		Ball startingBall = new Ball(true, 0, 0, 0, 0);

		int count_left = 0;
		int count_right = 0;
		int aim = 1;
		boolean initialxandy = true;
		boolean initaldrag = true;
		float bar_x = 0;
		float bar_y = 0;
		float drag_x = 0;
		float drag_y = 0;

		boolean heart1 = true, heart2 = true, heart3 = true, heart4 = true,
				heart5 = true;
		boolean heartArray[] = { heart1, heart2, heart3, heart4, heart5 };

		Bitmap gameBG = BitmapFactory.decodeResource(getResources(),
				R.drawable.game_bg);
		Bitmap left_button = BitmapFactory.decodeResource(getResources(),
				R.drawable.control_left_press1);
		Bitmap right_button = BitmapFactory.decodeResource(getResources(),
				R.drawable.control_right_press1);
		Bitmap heartDead = BitmapFactory.decodeResource(getResources(),
				R.drawable.heart_live_single);
		Bitmap heartAlive = BitmapFactory.decodeResource(getResources(),
				R.drawable.heart_live_red);
		Bitmap pause = BitmapFactory.decodeResource(getResources(),
				R.drawable.pause);
		Bitmap bluebar = BitmapFactory.decodeResource(getResources(),
				R.drawable.bluebar);
		Bitmap greybar = BitmapFactory.decodeResource(getResources(),
				R.drawable.paddle);
		Bitmap drag_Go = BitmapFactory.decodeResource(getResources(),
				R.drawable.drag_go);
		Bitmap ball = BitmapFactory.decodeResource(getResources(),
				R.drawable.ball_scaled);
		Bitmap pointer_left = BitmapFactory.decodeResource(getResources(),
				R.drawable.pointer_left);
		Bitmap pointer_up = BitmapFactory.decodeResource(getResources(),
				R.drawable.pointer_up);
		Bitmap pointer_right = BitmapFactory.decodeResource(getResources(),
				R.drawable.pointer_right);
		Bitmap drag_move = BitmapFactory.decodeResource(getResources(),
				R.drawable.drag_move);
		Bitmap drag_end = BitmapFactory.decodeResource(getResources(),
				R.drawable.drag_bar_ends);
		Bitmap drag_bar = BitmapFactory.decodeResource(getResources(),
				R.drawable.drag_bar);
		Bitmap enemy1 = BitmapFactory.decodeResource(getResources(),
				R.drawable.enemy1);
		Bitmap enemy2 = BitmapFactory.decodeResource(getResources(),
				R.drawable.enemy2);
		Bitmap enemy3 = BitmapFactory.decodeResource(getResources(),
				R.drawable.enemy3);

		public MyBringBackSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder();
			ourThread = new Thread(this);
			ourThread.start();

			initializeAliens();
		}

		public void initializeAliens() {
			InputStreamReader is = new InputStreamReader(getResources()
					.openRawResource(R.raw.l1));
			BufferedReader br = new BufferedReader(is);

			try {
				enemy_rows = Integer.parseInt(br.readLine(), 10);
				enemy_speed = Integer.parseInt(br.readLine(), 10);
				enemy_shoot_percentage = Integer.parseInt(br.readLine(), 10);
				forcefield_height = Integer.parseInt(br.readLine(), 10);

				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			aliens = new Alien[enemy_rows][8];

			int shooters = enemy_shoot_percentage / 100 * (enemy_rows * 8);

			// Fill aliens array
			for (int y = 0; y < enemy_rows; y++) {
				for (int x = 0; x < 8; x++) {
					int randomInt = rand.nextInt(3), randomTime = rand
							.nextInt(80);

					if (y * x < shooters) {
						aliens[y][x] = new Alien(randomInt, true, randomTime,
								iWidth / 8 * x, iWidth / 8 * y);
					} else {
						aliens[y][x] = new Alien(randomInt, true, randomTime,
								iWidth / 8 * x, iWidth / 8 * y);
					}
				}
			}
		}

		public void pause() {
			isRunning = false;
			while (true) {
				try {
					ourThread.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			ourThread = null;
		}

		public void resume() {
			isRunning = true;
			ourThread = new Thread(this);
			ourThread.start();
		}

		public void runPause() {
			Intent openStartingPoint = new Intent("android.intent.action.PAUSE");
			startActivity(openStartingPoint);
		}

		public void run() {
			while (isRunning) {
				if (!ourHolder.getSurface().isValid())
					continue;
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawBitmap(gameBG, 0, 0, null);

				// Draw spaceships
				for (int i = 0; i < 8; i++) {
					for (int j = 0; j < enemy_rows; j++) {
						if (aliens[j][i].color == 0 && aliens[j][i].isAlive)
							canvas.drawBitmap(enemy1, aliens[j][i].xPos,
									aliens[j][i].yPos + 70, null);
						else if (aliens[j][i].color == 1
								&& aliens[j][i].isAlive)
							canvas.drawBitmap(enemy2, aliens[j][i].xPos,
									aliens[j][i].yPos + 70, null);
						else if (aliens[j][i].color == 2
								&& aliens[j][i].isAlive)
							canvas.drawBitmap(enemy3, aliens[j][i].xPos,
									aliens[j][i].yPos + 70, null);
					}
				}

				// this is for the score
				Typeface font = Typeface.createFromAsset(
						sharedContext.getAssets(), "VISITOR1.TTF");
				Paint textPaint = new Paint();
				textPaint.setARGB(1000, 255, 255, 255);
				textPaint.setTextAlign(Align.CENTER);
				textPaint.setTextSize(50);
				textPaint.setTypeface(font);
				int score = 69 * startingBall.aliendied - 92
						* startingBall.persondied;
				String strSCORE = Integer.toString(score);
				float width = textPaint.measureText(strSCORE);
				canvas.drawText(strSCORE, canvas.getWidth() - width / 2, 50,
						textPaint);

				// this is for the left and right click button
				if (!launched) {
					canvas.drawBitmap(left_button, 50, 1150, null);
					canvas.drawBitmap(right_button, canvas.getWidth()
							- right_button.getWidth() - 50, 1150, null);
					canvas.drawBitmap(drag_Go,
							canvas.getWidth() / 2 - drag_Go.getWidth() / 2,
							1100, null);
					canvas.drawBitmap(ball,
							canvas.getWidth() / 2 - ball.getWidth() / 2, 1015,
							null);
					startingBall.xPos = canvas.getWidth() / 2 - ball.getWidth()
							/ 2;
					startingBall.yPos = 1015;
					startingBall.maxX = canvas.getWidth() - ball.getWidth();
					startingBall.maxY = canvas.getHeight() - ball.getHeight();

					if (x > 50 && x < 50 + left_button.getWidth() && y > 1150
							&& y < 1150 + left_button.getHeight() && x1 != x
							&& y1 != y
							&& Math.abs(x1 - x) + Math.abs(y1 - y) > 1250) {
						// button go black
						aim -= 1;
						count_left += 1;
						x1 = x;
						y1 = y;
						if (aim < 0)
							aim = 0;
						else if (aim > 2)
							aim = 2;
					}

					if (x > canvas.getWidth() - right_button.getWidth() - 50
							&& x < canvas.getWidth() - 50
									+ left_button.getWidth() && y > 1150
							&& y < 1150 + left_button.getHeight() && x1 != x
							&& y1 != y
							&& Math.abs(x1 - x) + Math.abs(y1 - y) > 1250) {
						aim += 1;
						count_right += 1;
						x1 = x;
						y1 = y;
						if (aim < 0)
							aim = 0;
						else if (aim > 2)
							aim = 2;
					}

					if (aim == 2 && count_right < 3) {
						count_left = 0;
						canvas.drawBitmap(pointer_right, canvas.getWidth() / 2,
								920, null);
					}

					else if (aim == 1 || count_left > 2 && count_left < 5) {
						count_right = 0;
						canvas.drawBitmap(pointer_up, canvas.getWidth() / 2,
								870, null);
					} else if (aim == 1 || count_right > 2 && count_right < 5) {
						count_left = 0;
						canvas.drawBitmap(pointer_up, canvas.getWidth() / 2,
								870, null);
					}

					else if (aim == 0 && count_left < 3) {
						count_right = 0;
						canvas.drawBitmap(pointer_left, canvas.getWidth() / 2
								- pointer_left.getWidth(), 920, null);
					}

					else {
						aim = 1;
						count_left = 0;
						count_right = 0;
						x1 = 0;
						y1 = 0;
					}

					if (x > canvas.getWidth() / 2 - drag_Go.getWidth() / 2
							&& x < canvas.getWidth() / 2 + drag_Go.getWidth()
									/ 2 && y > 1100
							&& y < 1100 + drag_Go.getHeight()) {
						startingBall.launched(aim);
						launched = true;
					}
				} else {
					// drag track
					int widthTrack = greybar.getWidth() / 2;
					while (widthTrack < canvas.getWidth() - greybar.getWidth()
							/ 2) {
						if (!(widthTrack + drag_bar.getWidth() > drag_x && widthTrack < drag_move
								.getWidth() + drag_x))
							canvas.drawBitmap(drag_bar, widthTrack,
									1100 + drag_move.getHeight() / 2, null);
						widthTrack += 1;
					}

					// drag track end
					canvas.drawBitmap(drag_end, greybar.getWidth() / 2 - 20,
							1100 + 50, null);
					canvas.drawBitmap(drag_end,
							canvas.getWidth() - greybar.getWidth() / 2 - 20,
							1100 + 50, null);

					// the new drag button
					if (initaldrag) {
						drag_x = canvas.getWidth() / 2 - drag_move.getWidth()
								/ 2;
						drag_y = 1100;
						initaldrag = false;
					}

					if (x > bar_x && x < bar_x + greybar.getWidth()
							&& y > drag_y && y < drag_y + drag_move.getHeight()
							&& x + greybar.getWidth() / 2 < canvas.getWidth()
							&& x - greybar.getWidth() / 2 > 0) {
						drag_x = x - drag_move.getWidth() / 2;
						bar_x = x - greybar.getWidth() / 2;
					}

					if (startingBall.bag[0] == null)
						startingBall.add(new MovingBar(bar_x, bar_y));

					((MovingBar) startingBall.bag[0]).x = bar_x;
					((MovingBar) startingBall.bag[0]).y = bar_y;
					canvas.drawBitmap(drag_move, drag_x, drag_y, null);

					for (int i = 0; i < aliens.length; i++)
						for (Alien obj : aliens[i])
							startingBall.add(obj);

					startingBall.update();
					canvas.drawBitmap(ball, startingBall.xPos,
							startingBall.yPos, null);

					if (startingBall.reset) {
						launched = false;
						startingBall.reset = false;
						initialxandy = true;
					}
				}

				// heart
				double initialIncrement = 0 - heartAlive.getWidth() * 5;

				for (boolean heart : heartArray) {
					if (heart) {
						canvas.drawBitmap(
								heartAlive,
								(float) (canvas.getWidth() / 2 + initialIncrement),
								30, null);
						initialIncrement += heartAlive.getWidth() * 2;
					} else {
						canvas.drawBitmap(
								heartDead,
								(float) (canvas.getWidth() / 2 + initialIncrement),
								30, null);
						initialIncrement += heartAlive.getWidth() * 2;
					}
				}

				// pause sign
				canvas.drawBitmap(pause, pause.getWidth() * 1, 30, null);

				if (running == 0)
					finish();

				// paddle bar
				if (initialxandy) {
					bar_x = canvas.getWidth() / 2 - greybar.getWidth() / 2;
					bar_y = 1080;
					initialxandy = false;
				}

				canvas.drawBitmap(greybar, bar_x, bar_y, null);

				// this is dividing line
				int widthLoop = 0;
				while (widthLoop < canvas.getWidth()) {
					if (!(widthLoop + bluebar.getWidth() > bar_x && widthLoop < bar_x
							+ greybar.getWidth()))
						canvas.drawBitmap(bluebar, widthLoop,
								bar_y + greybar.getHeight() / 2, null);
					widthLoop += 1;
				}

				ourHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
}