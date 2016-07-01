package com.school.uioptimizationsample.communication;

import com.school.uioptimizationsample.model.Artist;

import java.util.Collection;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by i-sergeev on 01.07.16
 */
public class Api
{
    public Observable<Collection<Artist>> getArtistsObservable() {
        return Observable.create(new Observable.OnSubscribe<Collection<Artist>>()
        {
            @Override
            public void call(Subscriber<? super Collection<Artist>> subscriber)
            {
                //TODO parse artists
            }
        });
    }
}
