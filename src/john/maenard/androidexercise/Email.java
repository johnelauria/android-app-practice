/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * @author johnmaenard
 * 
 */
public class Email extends Activity implements View.OnClickListener {
	EditText recipient, subject, messageContent;
	Button sendMessage;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.email);
		setVariables();
		sendMessage.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent sendEmail = new Intent(android.content.Intent.ACTION_SEND);
		sendEmail.putExtra(android.content.Intent.EXTRA_EMAIL, recipient.getText().toString());
		sendEmail.putExtra(android.content.Intent.EXTRA_SUBJECT, subject.getText().toString());
		sendEmail.setType("plain/text");
		sendEmail.putExtra(android.content.Intent.EXTRA_TEXT, messageContent.getText().toString());
		startActivity(sendEmail);
	}
	
	private void setVariables() {
		recipient = (EditText) findViewById(R.id.emailInput);
		subject = (EditText) findViewById(R.id.subjInput);
		messageContent = (EditText) findViewById(R.id.msgContent);
		sendMessage = (Button) findViewById(R.id.sendMsg);
	}

}
