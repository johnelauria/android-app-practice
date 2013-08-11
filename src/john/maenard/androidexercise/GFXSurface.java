/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * @author johnmaenard
 *
 */
public class GFXSurface extends Activity implements OnTouchListener {
	
	MyBringBackSurface theView;
	Bitmap testBall, plus;
	float x, y, sX, sY, fX, fY, dX, dY, aniX, aniY, scaleX, scaleY;
	SoundPool sp;
	int popSound = 0;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		testBall = BitmapFactory.decodeResource(getResources(), R.drawable.s_ball);
		plus = BitmapFactory.decodeResource(getResources(), R.drawable.addbutton);
		x = 0;
		y = 0;
		sX = 0;
		sY = 0;
		fX = 0;
		fY = 0;
		dX = dY = aniX = aniY = scaleX = scaleY = 0;
		theView = new MyBringBackSurface(this);
		theView.setOnTouchListener(this);
		sp = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
		popSound = sp.load(this, R.raw.facebook_ringtone_pop, 1);
		setContentView(theView);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		theView.pause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		theView.resume();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		x = event.getX();
		y = event.getY();
		
		switch(event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			sX = event.getX();
			sY = event.getY();
			dX = dY = aniX = aniY = scaleX = scaleY = 0;
			break;
		case MotionEvent.ACTION_UP:
			if (popSound != 0)
				sp.play(popSound, 1, 1, 0, 0, 1);
			fX = event.getX();
			fY = event.getY();
			dX = fX - sX;
			dY = fY - sY;
			scaleX = dX / 30;
			scaleY = dY / 30;
			break;
		}
		return true;
	}
	
	public class MyBringBackSurface extends SurfaceView implements Runnable {
		
		Thread ourThread = null;
		Boolean isRunning = true;

		public MyBringBackSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
		}
		
		public void pause() {
			isRunning = false;
			while(true) {
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
			ourThread = new Thread(this);
			ourThread.start();
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning) {
				if(!getHolder().getSurface().isValid())
					continue;
				
				Canvas canvas = getHolder().lockCanvas();
				canvas.drawRGB(250, 250, 250);
				if (x != 0 && y != 0) {
					canvas.drawBitmap(testBall, x-(testBall.getWidth()/2), y-(testBall.getHeight()/2), null);
				}
				if (sX != 0 && sY != 0) {
					canvas.drawBitmap(plus, sX-(plus.getWidth()/2), sY-(plus.getHeight()/2), null);
				}
				if (fX != 0 && fY != 0) {
					canvas.drawBitmap(testBall, x-(testBall.getWidth()/2)-aniX, y-(testBall.getHeight()/2)-aniY, null);
					canvas.drawBitmap(plus, fX-(plus.getWidth()/2), fY-(plus.getHeight()/2), null);
				}
				aniX = aniX + scaleX;
				aniY = aniY + scaleY;			
				getHolder().unlockCanvasAndPost(canvas);
			}
		}

	}

}
