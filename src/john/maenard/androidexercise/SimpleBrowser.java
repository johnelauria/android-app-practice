/**
 * 
 */
package john.maenard.androidexercise;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;

/**
 * @author johnmaenard
 *
 */
public class SimpleBrowser extends Activity implements OnClickListener {
	
	Button go, back, forward, refresh, clrH;
	EditText webAddress;
	WebView ourWeb;

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.simplebrowser);
		initializeWidgets();
		go.setOnClickListener(this);
		back.setOnClickListener(this);
		forward.setOnClickListener(this);
		refresh.setOnClickListener(this);
		clrH.setOnClickListener(this);
		ourWeb.getSettings().setJavaScriptEnabled(true);
		ourWeb.getSettings().setLoadWithOverviewMode(true);
		ourWeb.getSettings().setUseWideViewPort(true);
		ourWeb.setWebViewClient(new ourWebViewClient());
		ourWeb.loadUrl("http://developer.android.com/index.html");
	}
	
	private void initializeWidgets() {
		go = (Button) findViewById(R.id.goButton);
		back = (Button) findViewById(R.id.backButton);
		forward = (Button) findViewById(R.id.forwardButton);
		refresh = (Button) findViewById(R.id.refreshWeb);
		clrH = (Button) findViewById(R.id.clrHistory);
		webAddress = (EditText) findViewById(R.id.webURL);
		ourWeb = (WebView) findViewById(R.id.webContent);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case R.id.goButton:
			String url = webAddress.getText().toString();
			ourWeb.loadUrl(url);
			InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
			imm.hideSoftInputFromWindow(webAddress.getWindowToken(), 0);
			break;
		case R.id.backButton:
			if (ourWeb.canGoBack())
				ourWeb.goBack();
			break;
		case R.id.forwardButton:
			if (ourWeb.canGoForward())
				ourWeb.goForward();
			break;
		case R.id.clrHistory:
			ourWeb.clearHistory();
			break;
		case R.id.refreshWeb:
			ourWeb.reload();
			break;
		}
	}

}
