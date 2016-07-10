package com.dlinkddns.pliniolgimenez.filmes;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


public class ImageAdapter extends BaseAdapter {

    private Context mContext;


    public String[] getResultado() {
        return mresultado;
    }

    public void setResultado(String[] resultado) {
        this.mresultado = resultado;
    }

    private String[] mresultado;

    public ImageAdapter(Context c) {
        mContext = c;
    }

    public ImageAdapter(Context c, String[] resultado) {
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
            imageView.setLayoutParams(new GridView.LayoutParams(300, 400));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(5, 5, 5, 5);
        } else {
            imageView = (ImageView) convertView;
        }

        //imageView.setImageResource(mThumbIds[position]);
        Picasso.with(mContext).load("http://image.tmdb.org/t/p/w185"+mresultado[position]).into(imageView);
        return imageView;
    }


    // references to our images
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
}