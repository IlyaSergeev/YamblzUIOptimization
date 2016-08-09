package com.yamblz.uioptimizationsample.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.squareup.picasso.Transformation;
import com.yamblz.uioptimizationsample.R;

/**
 * Created by Александр on 09.08.2016.
 */

public class ShadowTransform implements Transformation {
    public static final String TAG = "ShadowTransform";

    public final Drawable shadow;

    public ShadowTransform(Context context) {
        shadow = ContextCompat.getDrawable(context, R.drawable.fade_shadow);
    }

    @Override
    public Bitmap transform(Bitmap source) {
        Bitmap copy = source.copy(source.getConfig(), true);
        Canvas canvas = new Canvas(copy);
        shadow.setBounds(0, (int) (source.getHeight() - source.getHeight()* 0.3), source.getWidth(), source.getWidth());
        shadow.draw(canvas);
        source.recycle();
        return copy;
    }

    @Override
    public String key() {
        return TAG;
    }
}
