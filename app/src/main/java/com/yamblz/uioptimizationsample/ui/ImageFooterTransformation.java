package com.yamblz.uioptimizationsample.ui;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

import com.squareup.picasso.Transformation;

/**
 * Created by dalexiv on 8/9/16.
 */
public class ImageFooterTransformation implements Transformation{
    @Override
    public Bitmap transform(Bitmap source) {
        int height = source.getHeight();
        int width = source.getWidth();
        LinearGradient linearGradient = new LinearGradient(0, 0, 0, 300,
                Color.TRANSPARENT, Color.BLACK, Shader.TileMode.MIRROR);

        Paint paint = new Paint();
        paint.setShader(linearGradient);
        Bitmap mutableBitmap = source.copy(Bitmap.Config.ARGB_8888, true);
        Canvas c = new Canvas(mutableBitmap);
        c.drawRect(0, height - 300, width, height, paint);
        source.recycle();
        return mutableBitmap;
    }

    @Override
    public String key() {
        return "gradient";
    }
}
