package john.maenard.androidexercise;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;

public class SQLView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// READ ALL DATA FROM DATABASE
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView loadView = (TextView) findViewById(R.id.loadSql);
		RateDrink info = new RateDrink(SQLView.this);
		info.open();
		String data = info.readData();
		info.close();
		loadView.setText(data);
	}

}
