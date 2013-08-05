package john.maenard.androidexercise;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.os.AsyncTask;
import android.os.Bundle;

public class InternalStorage extends Activity implements OnClickListener {

	Button loadInternalData, saveInternalData;
	EditText dataString;
	TextView showLoadedData;
	String FILENAME = "myInternalStorage";
	FileOutputStream fos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedprefs);
		initializeWidgets();
	}

	private void initializeWidgets() {
		saveInternalData = (Button) findViewById(R.id.savePref);
		loadInternalData = (Button) findViewById(R.id.loadPref);
		dataString = (EditText) findViewById(R.id.stringToSave);
		showLoadedData = (TextView) findViewById(R.id.loadedPref);
		saveInternalData.setOnClickListener(this);
		loadInternalData.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.savePref:
			if (dataString.getText().toString().isEmpty()) {
				showLoadedData.setText("Cannot save an empty string");
			} else {
				try {
					fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
					fos.write(dataString.getText().toString().getBytes());
					fos.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		case R.id.loadPref:
			new loadSomeStuff().execute(FILENAME);
			break;
		}
	}

	public class loadSomeStuff extends AsyncTask<String, Integer, String> {

		ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = new ProgressDialog(InternalStorage.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMessage("Loading Data...");
			dialog.setMax(100);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			FileInputStream fis = null;
			String collectedData = null;
			
			for (int c = 0; c < 20; c++) {
				publishProgress(5);
				try {
					Thread.sleep(90);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			dialog.dismiss();
			try {
				fis = openFileInput(FILENAME);
				byte[] dataArray = new byte[fis.available()];
				while (fis.read(dataArray) != -1) {
					collectedData = new String(dataArray);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					fis.close();
					return collectedData;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return null;
		}

		protected void onProgressUpdate(Integer... progress) {
			dialog.incrementProgressBy(progress[0]);
		}

		protected void onPostExecute(String Result) {
			showLoadedData.setText(Result);
		}
	}

}
