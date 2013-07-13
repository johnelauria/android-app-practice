/**
 * 
 */
package john.maenard.androidexercise;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.os.Bundle;

/**
 * @author johnmaenard
 * 
 */
public class TextPlay extends Activity implements View.OnClickListener {
	Button viewMenu, execute;
	EditText commandSent;
	TextView responseText;
	ToggleButton encryptText;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.textplay);
		viewMenu = (Button) findViewById(R.id.backMenuButton);
		responseText = (TextView) findViewById(R.id.responseText);
		execute = (Button) findViewById(R.id.executeCmd);
		commandSent = (EditText) findViewById(R.id.cmdToExec);
		encryptText = (ToggleButton) findViewById(R.id.switch1);
		
		encryptText.setOnClickListener(this);
		execute.setOnClickListener(this);
		viewMenu.setOnClickListener(this);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case (R.id.executeCmd):
			String check = commandSent.getText().toString();
			commandSent.setText("");
			if (check.contentEquals("left")) {
				responseText.setGravity(Gravity.LEFT);
				responseText.setText(check);
			} else if (check.toLowerCase().contentEquals("center")) {
				responseText.setGravity(Gravity.CENTER);
				responseText.setText(check);
			} else if (check.toLowerCase().contentEquals("right")) {
				responseText.setGravity(Gravity.RIGHT);
				responseText.setText(check);
			} else if (check.toLowerCase().contentEquals("blue")) {
				responseText.setTextColor(Color.BLUE);
				responseText.setText("This is color yellow");
			} else if (check.toLowerCase().contentEquals("red")) {
				responseText.setTextColor(Color.RED);
				responseText.setText("This is color green");
			} else if (check.toLowerCase().contentEquals("random")) {
				Random num = new Random();
				Random randColor = new Random();
				responseText.setTextSize(num.nextInt(70));
				responseText.setTextColor(Color.rgb(randColor.nextInt(250),
						randColor.nextInt(250), randColor.nextInt(250)));
				responseText.setText("WTF!!!");
				commandSent.setText(check);
			} else {
				responseText.setText("Invalid Input");
				responseText.setGravity(Gravity.CENTER);
			}
			break;
			
		case (R.id.backMenuButton):
			Intent backToMenu = new Intent(
					"john.maenard.androidexercise.MAINMENU");
			startActivity(backToMenu);
			break;
			
		case (R.id.switch1):
			if (encryptText.isChecked()) {
				commandSent.setInputType(InputType.TYPE_CLASS_TEXT
						| InputType.TYPE_TEXT_VARIATION_PASSWORD);
			} else {
				commandSent.setInputType(InputType.TYPE_CLASS_TEXT);
			}
			break;
		}
	}

}
