package timothyyudi.ituneslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by root on 12/21/2016.
 */

public class SongAdapter extends BaseAdapter {

    ArrayList<SongModel> mSongs;
    Context mCtx;

    public SongAdapter(Context mCtx,ArrayList<SongModel> mSongs) {
        this.mCtx = mCtx;
        this.mSongs = mSongs;
    }

    @Override
    public int getCount() {
        return mSongs.size();
    }

    @Override
    public Object getItem(int i) {
        return mSongs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) mCtx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row_song, viewGroup, false);
        ImageView ivSongAlbum = (ImageView) rowView.findViewById(R.id.ivSongAlbum);
        String url = mSongs.get(i).imageURL;
        TextView tvSongTitle = (TextView) rowView.findViewById(R.id.tvSongTitle);
        tvSongTitle.setText(mSongs.get(i).songTitle);
        TextView tvSongAlbum = (TextView) rowView.findViewById(R.id.tvSongAlbum);
        tvSongAlbum.setText(mSongs.get(i).songAlbum);

        Glide
                .with(mCtx)
                .load(url)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .crossFade()
                .into(ivSongAlbum);

        return rowView;
    }
}
