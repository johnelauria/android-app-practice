package john.maenard.androidexercise;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangle {
	
	private float vertices[] = {
		0f, 1f,
		1f, -1f,
		-1f, -1f
	};
	
	private float rgbaVals[] = {
		1, 1, 0, .5f,
		.25f, 0, .85f, 1,
		0, 1, 1, 1
	};
	
	private FloatBuffer colorBuff;
	private short[] pIndex = {0, 1, 2};
	private FloatBuffer vertBuff;
	private ShortBuffer pBuff;
	
	public GLTriangle() {
		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);
		
		ByteBuffer pbBuff = ByteBuffer.allocateDirect(pIndex.length * 2);
		pbBuff.order(ByteOrder.nativeOrder());
		pBuff = pbBuff.asShortBuffer();
		pBuff.put(pIndex);
		pBuff.position(0);
		
		ByteBuffer cBuff = ByteBuffer.allocateDirect(rgbaVals.length * 4);
		cBuff.order(ByteOrder.nativeOrder());
		colorBuff = cBuff.asFloatBuffer();
		colorBuff.put(rgbaVals);
		colorBuff.position(0);
	}
	
	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glEnableClientState(GL10.GL_COLOR_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
		gl.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_COLOR_ARRAY);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}

}
