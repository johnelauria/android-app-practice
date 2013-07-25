/**
 * 
 */
package john.maenard.androidexercise;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * @author johnmaenard
 *
 */
public class Prefs extends PreferenceActivity {

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		addPreferencesFromResource(R.xml.prefs);
	}

}
