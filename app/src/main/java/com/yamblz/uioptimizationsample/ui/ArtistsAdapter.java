package com.yamblz.uioptimizationsample.ui;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.yamblz.uioptimizationsample.R;
import com.yamblz.uioptimizationsample.model.Artist;

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

    public ArtistsAdapter(@Nullable Artist[] artists,
                          @NonNull Picasso picasso,
                          @NonNull Resources resources)
    {
        this.picasso = picasso;
        this.resources = resources;
        if (artists == null)
        {
            artists = new Artist[0];
        }
        this.artists = artists;
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
                    .placeholder(R.drawable.window_background)
                    .transform(new Transformation() {
                @Override
                public Bitmap transform(Bitmap source) {
                    int x = source.getWidth();
                    int y = source.getHeight();

                    Bitmap grandientBitmap = source.copy(source.getConfig(), true);
                    Canvas canvas = new Canvas(grandientBitmap);
                    LinearGradient grad =
                            new LinearGradient(0, y, 0, y - 400, Color.BLACK, Color.WHITE, Shader.TileMode.CLAMP);
                    Paint paint = new Paint();
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
                    paint.setShader(grad);
                    canvas.drawRect(0, y - 400, x, y, paint);
                    source.recycle();
                    return grandientBitmap;
                }

                @Override
                public String key() {
                    return "key for gradient";
                }
            }).into(posterImageView);

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
