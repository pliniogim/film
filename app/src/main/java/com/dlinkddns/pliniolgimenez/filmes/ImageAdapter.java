package com.dlinkddns.pliniolgimenez.filmes;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


class ImageAdapter extends BaseAdapter {
    private static String thumbsUrl;

    private final int DISK_CACHE_SIZE = 2048;
    ImageView imageView;
    private Context mContext;
    private String[] mresultado;

    //referencias das imagens
    private Integer[] mThumbIds = {
            R.drawable.samplea, R.drawable.sampleb,
            R.drawable.samplec, R.drawable.sampled,
            R.drawable.samplee, R.drawable.samplef,
            R.drawable.sampleg, R.drawable.sampleh,
            R.drawable.samplei, R.drawable.samplej,
            R.drawable.samplek, R.drawable.samplel,
            R.drawable.samplem, R.drawable.samplen,
            R.drawable.sampleo, R.drawable.samplep,
            R.drawable.sampleq, R.drawable.samples,
            R.drawable.samples, R.drawable.samplet
    };

    ImageAdapter(Context c, String[] resultado) {
        mContext = c;
        mresultado = resultado;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }


    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(4, 2, 4, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        thumbsUrl = tmdbActivity.getThumbsUrl();

        Picasso
                .with(mContext)
                .load(thumbsUrl + mresultado[position])
                //.resize(width / 3, width * 1 / 2)
                .error(R.drawable.samplea)
                .into(imageView);

        return imageView;
    }
}