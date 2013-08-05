/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;

/**
 * @author johnmaenard
 *
 */
public class SharedPrefs extends Activity implements OnClickListener {
	
	Button save, load;
	EditText saveString;
	SharedPreferences stringData;
	TextView showData;
	public static String filename = "myPreference";

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.sharedprefs);
		setWidgets();
		save.setOnClickListener(this);
		load.setOnClickListener(this);
	}
	
	private void setWidgets() {
		save = (Button) findViewById(R.id.savePref);
		load = (Button) findViewById(R.id.loadPref);
		saveString = (EditText) findViewById(R.id.stringToSave);
		showData = (TextView) findViewById(R.id.loadedPref);
		stringData = getSharedPreferences(filename, 0);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.savePref:
			String saveToPref = saveString.getText().toString();
			SharedPreferences.Editor editor = stringData.edit();
			editor.putString("sharedString", saveToPref);
			editor.commit();
			break;
		case R.id.loadPref:
			stringData = getSharedPreferences(filename, 0);
			String resultOfLoaded = stringData.getString("sharedString", "Couldn't load the data");
			showData.setText(resultOfLoaded);
			break;
		}
	}

}
