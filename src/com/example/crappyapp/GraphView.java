package com.example.crappyapp;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {
	ArrayList<Float> values = new ArrayList<Float>();
	float prevVal = 0;

	public GraphView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public GraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GraphView(Context context) {
		super(context);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		Paint paint = new Paint();
		canvas.drawColor(0x00ffffff, Mode.DST);

		float vel = new Random().nextFloat()*5-2;
		prevVal = Math.max(0, Math.min(prevVal+vel,getHeight()-1));
		values.add(prevVal);
		if (values.size() > getWidth())
			values.remove(0);

		paint.setColor(Color.RED);
		for (int i = 0; i < values.size(); i++) {
			canvas.drawPoint(i, values.get(i), paint);
		}
		
		invalidate();
	}
}
