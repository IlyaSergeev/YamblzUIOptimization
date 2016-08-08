package com.yamblz.uioptimizationsample.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.yamblz.uioptimizationsample.R;
import com.yamblz.uioptimizationsample.model.Artist;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by i-sergeev on 01.07.16
 */
public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.ArtistVH> {
    @NonNull
    private final Artist[] artists;

    @NonNull
    private final Picasso picasso;

    @NonNull
    private final Resources resources;

    public ArtistsAdapter(@Nullable Artist[] artists,
                          @NonNull Picasso picasso,
                          @NonNull Resources resources) {
        this.picasso = picasso;
        this.resources = resources;
        if (artists == null) {
            artists = new Artist[0];
        }
        this.artists = artists;
    }

    @Override
    public ArtistVH onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.artist_card, parent, false);
        return new ArtistVH(view);
    }

    @Override
    public void onBindViewHolder(ArtistVH holder, int position) {
        holder.bind(artists[position]);
    }

    @Override
    public int getItemCount() {
        return artists.length;
    }

    public class ArtistVH extends RecyclerView.ViewHolder {
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

        public ArtistVH(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(@NonNull Artist artist) {
            picasso
                    .load(artist.getCover().getBigImageUrl())
                    .into(
                            new Target() {
                                final int GRADIENT_HEIGHT = 320;

                                @Override
                                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                    int w = bitmap.getWidth();
                                    int h = bitmap.getHeight();
                                    Bitmap overlay = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
                                    Canvas canvas = new Canvas(overlay);

                                    canvas.drawBitmap(bitmap, 0, 0, null);

                                    Paint paint = new Paint();
                                    LinearGradient shader = new LinearGradient(0, h - GRADIENT_HEIGHT, 0, h, 0xFFFFFFFF, 0x00FFFFFF, Shader.TileMode.CLAMP);
                                    paint.setShader(shader);
                                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
                                    canvas.drawRect(0, h - GRADIENT_HEIGHT, w, h, paint);
                                    posterImageView.setImageBitmap(overlay);
                                }

                                @Override
                                public void onBitmapFailed(Drawable errorDrawable) {
                                }

                                @Override
                                public void onPrepareLoad(Drawable placeHolderDrawable) {
                                    posterImageView.setImageDrawable(resources.getDrawable(android.R.color.holo_red_dark));
                                }
                            }
                    );
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
