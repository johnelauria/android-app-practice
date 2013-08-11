package john.maenard.androidexercise;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class HttpExample extends Activity {
	
	TextView returnedHttp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexample);
		returnedHttp = (TextView)findViewById(R.id.tvHttpExample);
		GetMethodEx test = new GetMethodEx();
		try {
			String returned = test.getInternetData();
			returnedHttp.setText(returned);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
