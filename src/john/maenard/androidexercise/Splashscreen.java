/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author johnmaenard
 *
 */
public class Splashscreen extends Activity {

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
					sleep(3000);
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
				finally {
					Intent startMain = new Intent("john.maenard.androidexercise.MAINMENU");
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
