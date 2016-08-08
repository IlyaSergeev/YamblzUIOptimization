package com.yamblz.uioptimizationsample.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by Aleksandra on 09/08/16.
 */

public class ShadowTransformation implements Transformation {
    private static final String KEY = "ShadowTransformation";
    private static final int[] colors = {Color.TRANSPARENT, Color.BLACK};
    private Paint paint = new Paint();

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap result = source.copy(Bitmap.Config.ARGB_8888, true);
        Canvas canvas = new Canvas(result);
        LinearGradient shadow = new LinearGradient(0, 0, 0,
                source.getHeight(), colors, null, Shader.TileMode.CLAMP);

        paint.setShader(shadow);
        canvas.drawRect(new Rect(0, 0, result.getWidth(), result.getHeight()), paint);
        source.recycle();
        source = result;
        return source;
    }

    @Override
    public String key() {
        return KEY;
    }
}
