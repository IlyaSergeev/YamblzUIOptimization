package com.school.uioptimizationsample.ui;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.school.uioptimizationsample.R;
import com.school.uioptimizationsample.model.Artist;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by i-sergeev on 01.07.16
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistVH>
{
    @NonNull
    private final Artist[] artists;

    @NonNull
    private final Picasso picasso;

    @NonNull
    private final Resources resources;

    private final CardPosterTransformation posterTransformation;
    private final int posterHeight;

    public ArtistsAdapter(@Nullable Artist[] artists,
                          @NonNull Context context)
    {
        this.picasso = Picasso.with(context);
        this.resources = context.getResources();
        if (artists == null)
        {
            artists = new Artist[0];
        }
        this.artists = artists;
        this.posterTransformation = new CardPosterTransformation(context);
        this.posterHeight = context.getResources().getDimensionPixelSize(R.dimen.poster_height);
    }

    @Override
    public ArtistVH onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.artist_card, parent, false);
        return new ArtistVH(view);
    }

    @Override
    public void onBindViewHolder(ArtistVH holder, int position)
    {
        holder.bind(artists[position]);
    }

    @Override
    public int getItemCount()
    {
        return artists.length;
    }

    public class ArtistVH extends RecyclerView.ViewHolder
    {
        @BindView(R.id.artist_poster)
        ImageView posterImageView;

        @BindView(R.id.artist_name)
        TextView nameTextView;

        @BindView(R.id.artist_albums)
        TextView albumsTextView;

        @BindView(R.id.artist_songs)
        TextView songsTextView;

        @BindView(R.id.artist_description)
        TextView descriptionTextView;

        public ArtistVH(View itemView)
        {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull Artist artist)
        {
            picasso.load(artist.getCover().getBigImageUrl())
                   .placeholder(R.drawable.poster_placeholder)
                   .resize(0, posterHeight)
                   .transform(posterTransformation)
                   .into(posterImageView);
            nameTextView.setText(artist.getName());
            descriptionTextView.setText(artist.getDescription());
            albumsTextView.setText(resources.getQuantityString(R.plurals.artistAlbums,
                                                               artist.getAlbumsCount(),
                                                               artist.getAlbumsCount()));
            songsTextView.setText(resources.getQuantityString(R.plurals.artistTracks,
                                                              artist.getTracksCount(),
                                                              artist.getTracksCount()));
        }
    }
}
