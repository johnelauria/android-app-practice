package john.maenard.androidexercise;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLCubeExample extends Activity {
	
	GLSurfaceView cube;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		cube = new GLSurfaceView(this);
		cube.setRenderer(new GLCubeRenderer());
		setContentView(cube);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		cube.onPause();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		cube.onResume();
	}

}
