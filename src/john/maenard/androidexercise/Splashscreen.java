/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

/**
 * @author johnmaenard
 *
 */
public class Splashscreen extends Activity {
	Intent startMain = new Intent("john.maenard.androidexercise.MAINMENU");

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.splashscreen);
		Thread timer = new Thread() {
			public void run() {
				try {
					SharedPreferences checkSplash = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
					Boolean startSplash = checkSplash.getBoolean("checkBoxPref", true);
					if (startSplash == true)
						sleep(3000);
					else
						sleep(0);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					startActivity(startMain);
				}
			};
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

}
