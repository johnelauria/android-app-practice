package john.maenard.androidexercise;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Dashboard extends Activity {
	int counter;
	Button add, subtract;
	TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        counter = 0;
        add = (Button) findViewById(R.id.addOne);
        subtract = (Button) findViewById(R.id.subtractOne);
        totalText = (TextView) findViewById(R.id.totalView);
        add.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				counter++;
				totalText.setText("The number is now " + counter);
			}
		});
        subtract.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				counter--;
				totalText.setText("The number is now " + counter);
			}
		});
    }


    @Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }
    
}
