package john.maenard.androidexercise;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;

public class SqliteDatabase extends Activity implements OnClickListener {

	Button save, load;
	EditText drink, rate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlitedatabase);
		drink = (EditText) findViewById(R.id.etDrink);
		rate = (EditText) findViewById(R.id.etRateDrink);
		save = (Button) findViewById(R.id.bSaveDrink);
		load = (Button) findViewById(R.id.bLoadDrinks);
		load.setOnClickListener(this);
		save.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.bSaveDrink:
			String bebida = drink.getText().toString();
			String rango = rate.getText().toString();

			boolean didItWork = true;
			try {
				RateDrink entry = new RateDrink(SqliteDatabase.this);
				entry.open();
				entry.createEntry(bebida, rango);
				entry.close();
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Something went horribly wrong!");
				TextView t = new TextView(this);
				t.setText(error);
				d.setContentView(t);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Hell yea!");
					TextView t = new TextView(this);
					t.setText("Successfully Saved the data!");
					d.setContentView(t);
					d.show();
				}
			}
			break;
		case R.id.bLoadDrinks:
			Intent loadSqlData = new Intent("john.maenard.androidexercise.SQLVIEW");
			startActivity(loadSqlData);
			break;
		}
	}

}
