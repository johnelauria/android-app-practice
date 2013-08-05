/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

/**
 * @author johnmaenard
 *
 */
public class Tabs extends Activity implements OnClickListener {
	
	Button newTab, startWatch, stopWatch;
	TabHost th;
	long start, stop;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.tabs);
		setButtons();
		setTabs();
		newTab.setOnClickListener(this);
		startWatch.setOnClickListener(this);
		stopWatch.setOnClickListener(this);
	}
	
	private void setButtons() {
		newTab = (Button) findViewById(R.id.addTab);
		startWatch = (Button) findViewById(R.id.bTabStart);
		stopWatch = (Button) findViewById(R.id.bTabStop);
	}
	
	private void setTabs() {
		th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		TabSpec spec = th.newTabSpec("tab1");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Stop Watch");
		th.addTab(spec);
		spec = th.newTabSpec("tab2");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Second Tab");
		th.addTab(spec);
		spec = th.newTabSpec("tab3");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Add a tab");
		th.addTab(spec);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()) {
		case R.id.addTab:
			TabSpec ourSpec = th.newTabSpec("new tab");
			ourSpec.setContent(new TabHost.TabContentFactory() {
				
				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView text = new TextView(Tabs.this);
					text.setText("You have created a new Tab");
					return text;
				}
			});
			ourSpec.setIndicator("New");
			th.addTab(ourSpec);
			break;
		case R.id.bTabStart:
			start = System.currentTimeMillis();
			break;
		case R.id.bTabStop:
			stop = System.currentTimeMillis();
			if (start != 0) {
				TextView textResult = (TextView) findViewById(R.id.tvTabFirst);
				long result = stop - start;
				int millis = (int) result;
				int seconds = millis / 1000;
				int minutes = seconds / 60;
				millis = millis % 100;
				seconds = seconds % 60;
				textResult.setText(String.format("%d : %02d : %02d", minutes, seconds, millis));
			}
			break;
		}
	}

}
