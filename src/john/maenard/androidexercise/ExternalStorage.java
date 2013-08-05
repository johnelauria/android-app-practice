package john.maenard.androidexercise;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import android.os.Environment;
import android.widget.TextView;

public class ExternalStorage extends Activity implements
		OnItemSelectedListener, OnClickListener {
	TextView canRead, canWrite, showFilePathTo;
	Boolean canR, canW;
	Button confirm, save;
	EditText etFilename;
	String state;
	String[] paths = { "Music", "Images", "Downloads" };
	Spinner spinner;
	File path;
	File file = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externalstorage);
		setWidgets();
		checkState();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				ExternalStorage.this, android.R.layout.simple_spinner_item,
				paths);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);
		confirm.setOnClickListener(this);
		save.setOnClickListener(this);
	}

	private void checkState() {
		// TODO Auto-generated method stub
		state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {
			canRead.setText("TRUE");
			canWrite.setText("TRUE");
			canR = true;
			canW = true;
		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			canRead.setText("TRUE");
			canWrite.setText("FALSE");
			canR = true;
			canW = false;
		} else {
			canRead.setText("FALSE");
			canWrite.setText("FALSE");
			canR = false;
			canW = false;
		}
	}

	private void setWidgets() {
		canRead = (TextView) findViewById(R.id.EDCanRead);
		canWrite = (TextView) findViewById(R.id.EDCanWrite);
		spinner = (Spinner) findViewById(R.id.spinner1);
		etFilename = (EditText) findViewById(R.id.etSaveAs);
		confirm = (Button) findViewById(R.id.bConfirmSave);
		save = (Button) findViewById(R.id.bSaveAs);
		showFilePathTo = (TextView) findViewById(R.id.tvSavedTo);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinner.getSelectedItemPosition();
		switch (position) {
		case 0:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path = Environment
					.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bConfirmSave:
			save.setVisibility(View.VISIBLE);
			break;
		case R.id.bSaveAs:
			String f = etFilename.getText().toString();
			file = new File(path, f + ".png");
			try {
				InputStream is = getResources().openRawResource(
						R.drawable.s_ball);
				OutputStream os = new FileOutputStream(file);
				byte[] data = new byte[is.available()];
				is.read(data);
				os.write(data);
				is.close();
				os.close();

				Toast t = Toast.makeText(ExternalStorage.this,
						"Data has been saved", Toast.LENGTH_LONG);
				t.show();

				MediaScannerConnection.scanFile(ExternalStorage.this,
						new String[] { file.toString() }, null,
						new MediaScannerConnection.OnScanCompletedListener() {

							@Override
							public void onScanCompleted(String path, Uri uri) {
								// TODO Auto-generated method stub
								Toast t = Toast.makeText(ExternalStorage.this,
										"Scan Completed", Toast.LENGTH_LONG);
								t.show();
								showFilePathTo.setText(path);
							}
						});

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

}
