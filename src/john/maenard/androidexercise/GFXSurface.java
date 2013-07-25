/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * @author johnmaenard
 *
 */
public class GFXSurface extends Activity implements OnTouchListener {
	
	MyBringBackSurface theView;
	float x, y;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		x = 0;
		y = 0;
		theView = new MyBringBackSurface(this);
		theView.setOnTouchListener(this);
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
		return true;
	}
	
	public class MyBringBackSurface extends SurfaceView implements Runnable {
		
		SurfaceHolder ourHolder;
		Thread ourThread = null;
		Boolean isRunning = true;

		public MyBringBackSurface(Context context) {
			// TODO Auto-generated constructor stub
			super(context);
			ourHolder = getHolder();
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
				if(!ourHolder.getSurface().isValid())
					continue;
				
				Canvas canvas = ourHolder.lockCanvas();
				canvas.drawRGB(250, 250, 250);
				if (x != 0 && y != 0) {
					Bitmap testBall = BitmapFactory.decodeResource(getResources(), R.drawable.s_ball);
					canvas.drawBitmap(testBall, x-(testBall.getWidth()/2), y-(testBall.getHeight()/2), null);
				}
				ourHolder.unlockCanvasAndPost(canvas);
			}
		}

	}

}
