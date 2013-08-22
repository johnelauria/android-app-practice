package john.maenard.androidexercise;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLCube {
	
	private float vertices[] = {
		1, 1, -1, // point 0 top front right
		1, -1, -1, //point 1 bottom front right
		-1, -1, -1, //point 2 bottom front left
		-1, 1, -1, //point 3 top front left
		1, 1, 1, // point 4 top back right
		1, -1, 1, //point 5 bottom back right
		-1, -1, 1, //point 6 bottom back left
		-1, 1, 1, //point 7 top back left
	};
	private short pIndex[] = {
		3,4,0, 0,4,1, 3,0,1,
		3,7,4, 7,6,4, 7,3,6,
		3,1,2, 1,6,2, 6,3,2,
		1,4,5, 5,6,1, 6,5,4
	};
	private FloatBuffer vertBuff;
	private ShortBuffer pBuff;
	
	public GLCube() {
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
	}
	
	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnable(GL10.GL_CULL_FACE);
		gl.glCullFace(GL10.GL_BACK);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length, GL10.GL_UNSIGNED_SHORT, pBuff);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glDisable(GL10.GL_CULL_FACE);
	}

}
