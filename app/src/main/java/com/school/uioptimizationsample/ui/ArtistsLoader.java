package com.school.uioptimizationsample.ui;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.school.uioptimizationsample.communication.Api;
import com.school.uioptimizationsample.model.Artist;

import java.util.Collection;

/**
 * Created by i-sergeev on 01.07.16
 */
public class ArtistsLoader extends AsyncTaskLoader<Collection<Artist>>
{
    private final Api api;

    public ArtistsLoader(Context context)
    {
        super(context);
        api = new Api();
    }

    @Override
    public Collection<Artist> loadInBackground()
    {
        return api.getArtists();
    }
}
