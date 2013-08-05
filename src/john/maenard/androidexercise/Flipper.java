/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;

/**
 * @author johnmaenard
 *
 */
public class Flipper extends Activity implements OnClickListener {
	
	ViewFlipper flippy;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.flipper);
		flippy = (ViewFlipper) findViewById(R.id.viewFlipper1);
		flippy.setOnClickListener(this);
		flippy.setFlipInterval(1000);
		flippy.startFlipping();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		flippy.showNext();
	}

}
