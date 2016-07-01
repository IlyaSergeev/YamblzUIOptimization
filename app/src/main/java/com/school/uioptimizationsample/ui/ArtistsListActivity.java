package com.school.uioptimizationsample.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.school.uioptimizationsample.R;
import com.school.uioptimizationsample.model.Artist;

import java.util.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArtistsListActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Collection<Artist>>
{
    private static final int ARTISTS_LOADER_ID = 101;

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.get_info_button)
    View getInfoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artisits_list);

        ButterKnife.bind(this);

        showProgress();
        getSupportLoaderManager().initLoader(ARTISTS_LOADER_ID, null, this);
    }

    @Override
    public Loader<Collection<Artist>> onCreateLoader(int id, Bundle args)
    {
        return new ArtistsLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<Collection<Artist>> loader, Collection<Artist> data)
    {
        showContent(data);
    }

    @Override
    public void onLoaderReset(Loader<Collection<Artist>> loader)
    {

    }

    private void showProgress()
    {
        progressBar.setVisibility(View.VISIBLE);
        getInfoButton.setVisibility(View.GONE);
    }

    private void showContent(Collection<Artist> data)
    {
        progressBar.setVisibility(View.GONE);
        getInfoButton.setVisibility(View.VISIBLE);

        //TODO set data
    }
}
