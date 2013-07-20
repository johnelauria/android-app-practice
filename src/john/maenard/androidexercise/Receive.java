/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.os.Bundle;

/**
 * @author johnmaenard
 *
 */
public class Receive extends Activity implements OnCheckedChangeListener {
	Button done;
	RadioGroup answer;
	TextView username, CommentInMovie;
	String gotBread;
	String myComment;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.receive);
		initializeVariables();
		Bundle gotBasket = getIntent().getExtras();
		gotBread = gotBasket.getString("nameOfUser");
		username.setText("Hello " + gotBread);
		answer.setOnCheckedChangeListener(this);
		
	}
	
	private void initializeVariables() {
		done = (Button) findViewById(R.id.RButtonSubmit);
		answer = (RadioGroup) findViewById(R.id.selectedMovie);
		username = (TextView) findViewById(R.id.receivedString);
		CommentInMovie = (TextView) findViewById(R.id.movieComment);
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		switch(checkedId) {
		case R.id.harryPooter:
			myComment = "Ah! The English Nerdy Male Witch";
			break;
		case R.id.ironMan:
			myComment = "Genius, Billionaire, Playboy, Philanthropist";
			break;
		case R.id.pacificRim:
			myComment = "First Generation Gundam. The american version. Fighting against alien monsters";
			break;
		case R.id.starWars:
			myComment = "Galactic Conquest";
			break;
		}
		CommentInMovie.setText(myComment);
	}

}
