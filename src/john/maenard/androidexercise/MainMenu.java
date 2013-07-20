/**
 * 
 */
package john.maenard.androidexercise;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * @author johnmaenard
 * 
 */
public class MainMenu extends ListActivity {
	String menus[] = { "Dashboard", "TextPlay", "Email", "Camera",
			"Send", "StartQuestion" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(MainMenu.this,
				android.R.layout.simple_list_item_1, menus));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		String chosenMenu = menus[position];
		super.onListItemClick(l, v, position, id);
		try {
			Class ourClass = Class.forName("john.maenard.androidexercise." + chosenMenu);
			Intent ourIntent = new Intent(MainMenu.this, ourClass);
			startActivity(ourIntent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()) {
		case(R.id.aboutUsMenu):
			Intent aboutUsPopup = new Intent("john.maenard.androidexercise.ABOUTUS");
			startActivity(aboutUsPopup);
			break;
		case(R.id.aboutDeveloper):
			Intent aboutDevPopup = new Intent("john.maenard.androidexercise.ABOUTDEVELOPER");
			startActivity(aboutDevPopup);
			break;
		case(R.id.preferencesMenu):
			
			break;
		case(R.id.exitApp):
			
			break;
		}
		return false;
	}

}
