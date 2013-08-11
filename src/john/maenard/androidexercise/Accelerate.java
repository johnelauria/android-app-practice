package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener {
	
	MyBringBackSurface accelerometerView;
	SensorManager sm;
	Bitmap ball;
	float x, y, sensorX, sensorY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (sm.getSensorList(Sensor.TYPE_ACCELEROMETER).size() != 0) {
			Sensor s = sm.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			sm.registerListener(this, s, SensorManager.SENSOR_DELAY_NORMAL);
		}
		x = y = sensorX = sensorY = 0;
		ball = BitmapFactory.decodeResource(getResources(), R.drawable.s_ball);
		accelerometerView = new MyBringBackSurface(this);
		accelerometerView.resume();
		setContentView(accelerometerView);
	}

	@Override
	protected void onPause() {
		sm.unregisterListener(this);
		super.onPause();
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		sensorX = event.values[0];
		sensorY = event.values[1];
	}
	
public class MyBringBackSurface extends SurfaceView implements Runnable {
		
		Thread ourThread = null;
		Boolean isRunning = false;

		public MyBringBackSurface(Context context) {
			super(context);
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

		@Override
		public void run() {
			while(isRunning) {
				if(!getHolder().getSurface().isValid())
					continue;
				
				Canvas canvas = getHolder().lockCanvas();
				canvas.drawRGB(250, 250, 250);
				float centerX = canvas.getWidth()/2;
				float centerY = (canvas.getHeight()/2) - 40;
				canvas.drawBitmap(ball, centerX - (sensorX*45), centerY + (sensorY*45), null);
				
				getHolder().unlockCanvasAndPost(canvas);
			}
		}

	}

}
