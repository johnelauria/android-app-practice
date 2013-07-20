/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.os.Bundle;

/**
 * @author johnmaenard
 *
 */
public class Send extends Activity implements OnClickListener {
	Button send;
	EditText username;
	TextView sendLabel;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.send);
		initializeVariables();
		send.setOnClickListener(this);
	}
	
	private void initializeVariables() {
		send = (Button) findViewById(R.id.sendBSend);
		username = (EditText) findViewById(R.id.etSend);
		sendLabel = (TextView) findViewById(R.id.tvSend);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.sendBSend:
			String bread = username.getText().toString();
			Bundle basket = new Bundle();
			basket.putString("nameOfUser", bread);
			Intent a = new Intent(Send.this, Receive.class);
			a.putExtras(basket);
			startActivity(a);
			break;
		}
	}

}
