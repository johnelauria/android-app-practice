package john.maenard.androidexercise;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HttpJson extends Activity implements OnClickListener {
	
	Button searchHoroscope;
	TextView forecast;
	EditText horoscopeToSearch;
	final static String URL = "http://widgets.fabulously40.com/horoscope.json?sign=";
	HttpClient client = new DefaultHttpClient();
	JSONObject json;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpjson);
		setVars();
		searchHoroscope.setOnClickListener(this);
	}
	
	public void setVars() {
		searchHoroscope = (Button)findViewById(R.id.bHoroscope);
		forecast = (TextView)findViewById(R.id.horoscopeForecast);
		horoscopeToSearch = (EditText)findViewById(R.id.etHoroscope);
		
	}
	
	@Override
	public void onClick(View v) {
		String horoscopeRequested = horoscopeToSearch.getText().toString();
		new Read().execute("horoscope", horoscopeRequested);
		forecast.setText("Loading data please wait...");
	}
	
	public JSONObject horoscope(String hor) throws ClientProtocolException, IOException, JSONException {
		StringBuilder url = new StringBuilder(URL);
		url.append(hor);
		HttpGet get = new HttpGet(url.toString());
		HttpResponse r = client.execute(get);
		int status = r.getStatusLine().getStatusCode();
		if (status == 200) {
			HttpEntity e = r.getEntity();
			String data = EntityUtils.toString(e);
			JSONObject last = new JSONObject(data).getJSONObject("horoscope");
			return last;
		} else {
			Toast.makeText(HttpJson.this, "Couldn't retrieve data from server", Toast.LENGTH_SHORT).show();
			return null;
		}
	}
	
	public class Read extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				json = horoscope(params[1]);
				return json.getString(params[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			forecast.setText(result);
		}
		
	}

}
