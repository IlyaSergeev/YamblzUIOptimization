package com.yamblz.uioptimizationsample.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

public class GradientTransformation implements Transformation {
    @Override
    public Bitmap transform(Bitmap source) {
        int w = source.getWidth();
        int h = source.getHeight();
        Bitmap transformedBitmap = source.copy(source.getConfig(), true);
        Canvas canvas = new Canvas(transformedBitmap);
        LinearGradient linearGradient = new LinearGradient(w / 2, h / 2, w / 2, h, Color.TRANSPARENT,
                Color.BLACK, Shader.TileMode.CLAMP);
        Paint paint = new Paint(Paint.DITHER_FLAG);
        paint.setDither(true);
        paint.setFilterBitmap(true);
        paint.setShader(linearGradient);
        canvas.drawPaint(paint);
        source.recycle();
        return transformedBitmap;
    }

    @Override
    public String key() {
        return "Gradient";
    }
}
