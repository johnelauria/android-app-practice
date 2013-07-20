/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;

/**
 * @author johnmaenard
 *
 */
public class StartQuestion extends Activity implements OnClickListener {
	Button startQuestion;
	TextView answerResult;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.startquestion);
		initializeVariables();
		startQuestion.setOnClickListener(this);
		
	}
	
	private void initializeVariables() {
		startQuestion = (Button) findViewById(R.id.startQuestion);
		answerResult = (TextView) findViewById(R.id.resultToAnswer);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent openQuestion = new Intent(StartQuestion.this, TheQuestion.class);
		startActivityForResult(openQuestion, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle getResult = data.getExtras();
			String theResult = getResult.getString("answerComment");
			answerResult.setText(theResult);
		}
	}

}
