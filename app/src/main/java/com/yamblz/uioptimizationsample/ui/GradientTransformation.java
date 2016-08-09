package com.yamblz.uioptimizationsample.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;


public class GradientTransformation implements Transformation {
    private Paint paint;

    public GradientTransformation() {
        paint = new Paint();
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap bitmap = source.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(bitmap);
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        LinearGradient gradient = new LinearGradient(0, height, 0, height / 2, Color.BLACK, Color.TRANSPARENT, Shader.TileMode.CLAMP);
        paint.setShader(gradient);
        canvas.drawRect(0, height / 2, width, height, paint);
        canvas.save();
        source.recycle();
        return bitmap;
    }

    @Override
    public String key() {
        return "GradientTransformation";
    }
}
