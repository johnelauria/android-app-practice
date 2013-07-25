/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.os.Bundle;

/**
 * @author johnmaenard
 *
 */
public class GFX extends Activity {
	
	MyBringBack ourView;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		ourView = new MyBringBack(this);
		setContentView(ourView);
	}

}
