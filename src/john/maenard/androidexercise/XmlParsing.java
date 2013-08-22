package john.maenard.androidexercise;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class XmlParsing extends Activity implements OnClickListener {
	
	static final String BaseURL = "http://api.openweathermap.org/data/2.5/weather?q=";
	Button acquireForecast;
	TextView forecastResult;
	EditText cityName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xmlparsing);
		setVariables();
		acquireForecast.setOnClickListener(this);
	}
	
	public void setVariables() {
		acquireForecast = (Button)findViewById(R.id.bGetWeatherForecast);
		forecastResult = (TextView)findViewById(R.id.tvWeatherForecast);
		cityName = (EditText)findViewById(R.id.etWeather);
	}

	@Override
	public void onClick(View v) {
		String c = cityName.getText().toString();
		new parseXmlAsync().execute(c);
	}
	
	public class parseXmlAsync extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... arg0) {
			StringBuilder URL = new StringBuilder(BaseURL);
			URL.append(arg0[0] + "&mode=xml");
			String fullUrl = URL.toString();
			try {
				URL website = new URL(fullUrl);
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();
				XMLReader xr = sp.getXMLReader();
				HandlingXmlStuff doingWork = new HandlingXmlStuff();
				xr.setContentHandler(doingWork);
				xr.parse(new InputSource(website.openStream()));
				String information = doingWork.getInformation();
				return information;
			}catch (Exception e) {
				forecastResult.setText("Failed to load weather forecast from " + fullUrl);
				e.printStackTrace();
				return null;
			}
		}

		@Override
		protected void onPostExecute(String result) {
			forecastResult.setText(result);
		}
		
	}

}
