package com.application.bris.ikurma_nos_konsumer.util;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.appcompat.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by suminiwl on 25/06/2019.
 */

public class BitmapImageViewRounded extends AppCompatImageView {
    public static float radius = 18.0f;

    public BitmapImageViewRounded(Context context) {
        super(context);
    }

    public BitmapImageViewRounded(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BitmapImageViewRounded(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //float radius = 36.0f;
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);
    }
}