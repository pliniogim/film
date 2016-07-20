package com.dlinkddns.pliniolgimenez.filmes;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

class ImageAdapter2 extends BaseAdapter {

    //contexto e string das urls dos thumbnails dos filmes
    private Context mContext;
    private String[] mresult;

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

    ImageAdapter2(Context c, String[] result) {
        mContext = c;
        mresult = result;
    }

    //conta itens
    public int getCount() {
        return mThumbIds.length;
    }

    //obtem objeto na posicao x
    public Object getItem(int position) {
        return null;
    }

    //obtem identidade do objeto na posicao x
    public long getItemId(int position) {
        return 0;
    }

    // cria um novo imageview para cada item do adapterview
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;
        if (convertView == null) {
            // se nao e reciclado pega novos parametros
            imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(2, 0, 2, 0);
        } else {
            imageView = (ImageView) convertView;
        }

        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        String thumbsUrl = tmdbActivity.getThumbsUrl();

        Picasso
                .with(mContext)
                .load(thumbsUrl + mresult[position])
                .resize(width / 6, width / 4)
                .error(R.drawable.samplea)
                .into(imageView);

        return imageView;
    }
}