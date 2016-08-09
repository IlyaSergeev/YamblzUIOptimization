package com.yamblz.uioptimizationsample.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by SerG3z on 09.08.16.
 */
public class GradientTransformation implements Transformation {

    @Override
    public Bitmap transform(Bitmap source) {

        int y = source.getHeight();

        Bitmap grandientBitmap = source.copy(source.getConfig(), true);
        Canvas canvas = new Canvas(grandientBitmap);

        LinearGradient linearGradient =
                new LinearGradient(0, y - y / 4, 0, y,
                        new int[]{Color.TRANSPARENT, Color.BLACK, Color.BLACK},
                        new float[]{0, 0.85f, 1f},
                        Shader.TileMode.CLAMP);
        Paint p = new Paint(Paint.DITHER_FLAG);
        p.setShader(null);
        p.setDither(true);
        p.setFilterBitmap(true);
        p.setShader(linearGradient);
        canvas.drawPaint(p);
        source.recycle();
        return grandientBitmap;
    }

    @Override
    public String key() {
        return "effectGradient";
    }
}
