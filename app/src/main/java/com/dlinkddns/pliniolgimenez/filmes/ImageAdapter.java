package com.dlinkddns.pliniolgimenez.filmes;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


class ImageAdapter extends BaseAdapter {

    private final static String thumbsUrl = "http://image.tmdb.org/t/p/w185";
    private Context mContext;
    private String[] mresultado;
    //referencias das imagens
    private Integer[] mThumbIds = {
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1,
            R.drawable.sample1, R.drawable.sample1
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
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(4, 2, 4, 2);
        } else {
            imageView = (ImageView) convertView;
        }

        int width = mContext.getResources().getDisplayMetrics().widthPixels;

        //com.squareup.picasso.Picasso
        //        .with(context)
        //        .load("some url")
        //        .centerCrop().resize(width/2,width/2)
        //        .error(R.drawable.placeholder)
        //        .placeholder(R.drawable.placeholder)
        //        .into(item.drawableId);
        //imageView.setImageResource(mThumbIds[position]);

        Picasso
                .with(mContext)
                .load(thumbsUrl + mresultado[position])
                .resize(width / 2, width * 2 / 3)
                .error(R.drawable.sample1)
                .into(imageView);

        return imageView;
    }
}