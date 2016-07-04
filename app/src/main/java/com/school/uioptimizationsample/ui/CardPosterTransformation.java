package com.school.uioptimizationsample.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;

import com.school.uioptimizationsample.R;
import com.squareup.picasso.Transformation;

/**
 * Created by i-sergeev on 04.07.16
 */
public class CardPosterTransformation implements Transformation
{
    @NonNull
    private final Context context;

    public CardPosterTransformation(@NonNull Context context)
    {
        this.context = context;
    }

    @Override
    public Bitmap transform(Bitmap source)
    {
        final int sourceHeight = source.getHeight();
        final int sourceWidth = source.getWidth();

        Bitmap bmOverlay = Bitmap.createBitmap(sourceWidth, sourceHeight, source.getConfig());
        Canvas canvas = new Canvas(bmOverlay);
        canvas.drawBitmap(source, new Matrix(), null);

        source.recycle();

        LayerDrawable layerDrawable = (LayerDrawable) context.getResources().getDrawable(R.drawable.poster_vignetting);
        if (layerDrawable != null)
        {
            layerDrawable.setBounds(0, 0, sourceWidth, sourceHeight);
            layerDrawable.draw(canvas);
        }

        return bmOverlay;
    }

    @Override
    public String key()
    {
        return "CardPosterTransformation";
    }
}
