package com.yamblz.uioptimizationsample.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

public class GradientTransformation implements Transformation {

    private Paint mPaint = new Paint();
    private Canvas mCanvas = new Canvas();

    @Override
    public Bitmap transform(Bitmap source) {
        final int w = source.getWidth();
        final int h = source.getHeight();
        Bitmap transformedBitmap = source.copy(source.getConfig(), true);
        mCanvas.setBitmap(transformedBitmap);
        LinearGradient linearGradient = new LinearGradient(w / 2, h / 2, w / 2, h, Color.TRANSPARENT,
                Color.BLACK, Shader.TileMode.CLAMP);
        mPaint.reset();
        mPaint.setDither(true);
        mPaint.setFilterBitmap(true);
        mPaint.setShader(linearGradient);
        mCanvas.drawPaint(mPaint);
        source.recycle();
        return transformedBitmap;
    }

    @Override
    public String key() {
        return "Gradient";
    }
}
