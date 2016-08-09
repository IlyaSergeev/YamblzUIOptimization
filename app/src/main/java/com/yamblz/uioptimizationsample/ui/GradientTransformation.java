package com.yamblz.uioptimizationsample.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

public class GradientTransformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {
        int width = source.getWidth();
        int height = source.getHeight();

        int gradientHeight = height>>2;

        Paint paint = new Paint();
        LinearGradient gradient = new LinearGradient(0, height - gradientHeight, 0, height, 0x20000000, 0xFF000000, Shader.TileMode.CLAMP);
        paint.setShader(gradient);

        Bitmap mutableSource = source.copy(Bitmap.Config.ARGB_8888, true);
        Canvas mutableSourceCanvas = new Canvas(mutableSource);
        mutableSourceCanvas.drawRect(0, height-gradientHeight, width, height, paint);

        source.recycle();

        return mutableSource;
    }

    @Override
    public String key() {
        return "gradient_transformation";
    }

}
