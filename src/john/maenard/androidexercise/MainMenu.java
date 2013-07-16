/**
 * 
 */
package john.maenard.androidexercise;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
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
			"example4", "example5" };

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

}
