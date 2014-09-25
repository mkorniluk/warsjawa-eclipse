package com.example.crappyapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;

public class ImageView extends android.widget.ImageView {

	public ImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public ImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public ImageView(Context context) {
		super(context);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);

		GradientDrawable gradientDrawable = new GradientDrawable(
				Orientation.TOP_BOTTOM, new int[] { 0x00000000, 0xff000000 });
		gradientDrawable
				.setBounds(getLeft(), getTop()+getBottom()*2/3, getRight(), getBottom());
		gradientDrawable.draw(canvas);
		
		invalidate();
	}
}
