package com.yamblz.uioptimizationsample.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by Austry on 09/08/16.
 */
public class GradientTransformation implements Transformation {

    private int gradientOffset;
    public GradientTransformation(int gradientOffset){
        this.gradientOffset = gradientOffset;
    }

    @Override
    public Bitmap transform(Bitmap source) {
        int x = source.getWidth();
        int y = source.getHeight();

        Bitmap grandientBitmap = source.copy(source.getConfig(), true);
        Canvas canvas = new Canvas(grandientBitmap);
        LinearGradient grad =
                new LinearGradient(0, y, 0, y - gradientOffset, Color.BLACK, Color.WHITE, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        paint.setShader(grad);
        canvas.drawRect(0, y - gradientOffset, x, y, paint);
        source.recycle();
        return grandientBitmap;
    }

    @Override
    public String key() {
        return "Gradient";
    }
}
