package john.maenard.androidexercise;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;

public class SqliteDatabase extends Activity implements OnClickListener {

	Button save, load, show, modify, delete;
	EditText drink, rate, searchDrinkId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlitedatabase);
		drink = (EditText) findViewById(R.id.etDrink);
		searchDrinkId = (EditText)findViewById(R.id.etDrinkId);
		show = (Button)findViewById(R.id.bGetFromSql);
		modify = (Button)findViewById(R.id.bModifySql);
		delete = (Button)findViewById(R.id.bDeleteSql);
		rate = (EditText) findViewById(R.id.etRateDrink);
		save = (Button) findViewById(R.id.bSaveDrink);
		load = (Button) findViewById(R.id.bLoadDrinks);
		show.setOnClickListener(this);
		modify.setOnClickListener(this);
		delete.setOnClickListener(this);
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
		case R.id.bGetFromSql:
			String drinkId = searchDrinkId.getText().toString();
			long drinkIdLong = Long.parseLong(drinkId);
			RateDrink rd = new RateDrink(SqliteDatabase.this);
			rd.open();
			try {
				String returnedDrink = rd.getDrink(drinkIdLong);
				String returnedRate = rd.getRate(drinkIdLong);
				drink.setText(returnedDrink);
				rate.setText(returnedRate);
			} catch (Exception e) {
				e.printStackTrace();
				Dialog errorDialog = new Dialog(this);
				errorDialog.setTitle("Failed To Load Data");
				TextView errorMessage = new TextView(this);
				errorMessage.setText("ID is not found in the Database");
				errorDialog.setContentView(errorMessage);
				errorDialog.show();
			} finally {
				rd.close();
			}
			break;
		case R.id.bModifySql:
			String idUpdate = searchDrinkId.getText().toString();
			long idToUpdate = Long.parseLong(idUpdate);
			String drinkToUpdate = drink.getText().toString();
			String rateToUpdate = rate.getText().toString();
			RateDrink drinkUpdate = new RateDrink(this);
			drinkUpdate.open();
			try {
				drinkUpdate.updateDrink(idToUpdate, drinkToUpdate, rateToUpdate);
			} catch(Exception e) {
				e.printStackTrace();
				Dialog errorMessage = new Dialog(this);
				errorMessage.setTitle("Failed to Update");
				TextView errorContent = new TextView(this);
				errorContent.setText("ID not found in the Database");
				errorMessage.show();
			} finally {
				drinkUpdate.close();
			}
			break;
		case R.id.bDeleteSql:
			final AlertDialog alert = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT).create();
			alert.setTitle("Alert");
			alert.setMessage("You are about to delete this data. Are you sure? Have you made up your mind? Have you asked your dog about this decision? Think about your children.");
			alert.setButton(AlertDialog.BUTTON_POSITIVE, "Yes", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					String idDelete = searchDrinkId.getText().toString();
					long drinkIdToDelete = Long.parseLong(idDelete);
					RateDrink deleteDrink = new RateDrink(SqliteDatabase.this);
					deleteDrink.open();
					try {
						deleteDrink.deleteDrink(drinkIdToDelete);
					} catch(Exception e) {
						e.printStackTrace();
						Dialog errorMessage = new Dialog(SqliteDatabase.this);
						errorMessage.setTitle("Failed to delete");
						TextView errorContent = new TextView(SqliteDatabase.this);
						errorContent.setText("ID not found in the Database");
						errorMessage.show();
					} finally {
						deleteDrink.close();
					}
					deleteDrink.close();
				}
			});
			alert.setButton(AlertDialog.BUTTON_NEGATIVE, "No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					alert.cancel();
				}
			});
			alert.show();
			
			break;
		}
	}

}
