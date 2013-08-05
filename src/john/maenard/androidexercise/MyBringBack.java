package john.maenard.androidexercise;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.view.View;

public class MyBringBack extends View {
	
	Bitmap sBall;
	float yAxis;
	Paint lightBlue;
	Paint textPaint;
	Rect middleRect = new Rect();;
	//Typeface font; Include Custom Font

	public MyBringBack(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		sBall = BitmapFactory.decodeResource(getResources(), R.drawable.s_ball);
		yAxis = 0;
		lightBlue = new Paint();
		textPaint = new Paint();
		//font = Typeface.createFromAsset(context.getAssets(), "Font Name"); Include Custom Font
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		textPaint.setARGB(50, 10, 195, 254);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		//textPaint.setTypeface(typeface); For Custom font. Put custom font to asset folder
		canvas.drawText("Animations", canvas.getWidth()/2, 200, textPaint);
		
		canvas.drawBitmap(sBall, (canvas.getWidth() / 2), yAxis, null);
		if (yAxis < canvas.getHeight())
			yAxis += 10;
		else
			yAxis = 0;
		
		middleRect.set(0, canvas.getHeight()/2, canvas.getWidth(), (canvas.getHeight()/2)+140);
		lightBlue.setColor(Color.CYAN);
		canvas.drawRect(middleRect, lightBlue);
		invalidate();
	}
	
}
