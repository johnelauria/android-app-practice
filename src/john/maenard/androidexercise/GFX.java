/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

/**
 * @author johnmaenard
 *
 */
public class GFX extends Activity {
	
	MyBringBack ourView;
	WakeLock wL;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Keep Screen awake at all time during this activity
		PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wL = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK, "whatever");
		super.onCreate(savedInstanceState);
		wL.acquire();
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}

	@Override
	protected void onPause() {
		super.onPause();
		wL.release();
	}

}
