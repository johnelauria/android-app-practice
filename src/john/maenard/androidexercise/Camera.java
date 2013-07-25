/**
 * 
 */
package john.maenard.androidexercise;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageView;

/**
 * @author johnmaenard
 * 
 */
public class Camera extends Activity implements View.OnClickListener {
	ImageView displayImage;
	ImageButton openCamera;
	Button setWallpaper;
	Intent i;
	int cameraData = 0;
	Bitmap bmp;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Auto-generated method stub
		setContentView(R.layout.camera);
		setVariables();
		openCamera.setOnClickListener(this);
		setWallpaper.setOnClickListener(this);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			bmp = (Bitmap) extras.get("data");
			displayImage.setImageBitmap(bmp);
		}
	}
	
	private void setVariables() {
		displayImage = (ImageView) findViewById(R.id.capturedImage);
		openCamera = (ImageButton) findViewById(R.id.openCamera);
		setWallpaper = (Button) findViewById(R.id.setWallpaper);
		InputStream is = getResources().openRawResource(R.drawable.ic_launcher);
		bmp = BitmapFactory.decodeStream(is);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case(R.id.openCamera):
			i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, cameraData);
			break;
			
		case(R.id.setWallpaper):
			try {
				setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

}
