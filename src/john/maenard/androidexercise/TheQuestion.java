/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

/**
 * @author johnmaenard
 *
 */
public class TheQuestion extends Activity implements OnClickListener, OnCheckedChangeListener {
	Button submitAnswer;
	RadioGroup answerList;
	String answerResult = "You did not select an answer";

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.thequestion);
		initializeVariables();
		answerList.setOnCheckedChangeListener(this);
		submitAnswer.setOnClickListener(this);
	}
	
	private void initializeVariables() {
		submitAnswer = (Button) findViewById(R.id.submitAnswer);
		answerList = (RadioGroup) findViewById(R.id.answerList);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent viewResult = new Intent();
		Bundle resultOfAnswer = new Bundle();
		resultOfAnswer.putString("answerComment", answerResult);
		viewResult.putExtras(resultOfAnswer);
		setResult(RESULT_OK, viewResult);
		finish();
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId) {
		case(R.id.correctAnswer):
			answerResult = "Your answer is correct! Your IQ is good!";
			break;
		case(R.id.almostCorrect):
			answerResult = "That statement is correct but that is not the reason why  Mary's sister-in-law is Mary's brother's stepmother. Therefore, your answer is incorrect.";
			break;
		case(R.id.stupidAnswer):
			answerResult = "You could either not understand a single English word or you are simply a complete idiot. That is the worst answer of all!";
			break;
		}
	}

}
