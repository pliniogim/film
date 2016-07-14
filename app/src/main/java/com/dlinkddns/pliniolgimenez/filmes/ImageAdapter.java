package com.dlinkddns.pliniolgimenez.filmes;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


class ImageAdapter extends BaseAdapter {

    private final static String thumbsUrl = "http://image.tmdb.org/t/p/w185";
    ImageView imageView;
    private Context mContext;
    private String[] mresultado;
    //referencias das imagens
    private Integer[] mThumbIds = {
            R.drawable.sample1, R.drawable.sample2,
            R.drawable.sample3, R.drawable.sample4,
            R.drawable.sample5, R.drawable.sample6,
            R.drawable.sample7, R.drawable.sample8,
            R.drawable.sample9, R.drawable.sample10,
            R.drawable.sample11, R.drawable.sample12,
            R.drawable.sample13, R.drawable.sample14,
            R.drawable.sample15, R.drawable.sample16,
            R.drawable.sample17, R.drawable.sample19,
            R.drawable.sample19, R.drawable.sample20
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

    public View getIndView(int position) {
        return (View) this.imageView.findViewById(1300 + position);
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setId(1300 + position);
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

        //checa se existe em disco

        //se existe em disco, carrega imagem de disco

        //senao carrega imagem do site

        Picasso
                .with(mContext)
                .load(thumbsUrl + mresultado[position])
                .resize(width / 2, width * 2 / 3)
                .error(R.drawable.sample1)
                .into(imageView);
        return imageView;

    }

}