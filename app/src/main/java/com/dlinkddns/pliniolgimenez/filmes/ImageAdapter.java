package com.dlinkddns.pliniolgimenez.filmes;


import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

class ImageAdapter extends BaseAdapter {

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

    ImageAdapter(Context c, String[] result) {
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
        FrameLayout view = new FrameLayout(mContext);
        ImageView imageView = new ImageView(mContext);
        TextView textView = new TextView(mContext);

        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setPadding(2, 0, 2, 0);

        //view reciclado
        if (convertView != null) {
            view = (FrameLayout) convertView;
        }

        int width = mContext.getResources().getDisplayMetrics().widthPixels;
        String thumbsUrl = tmdbActivity.getThumbsUrl();

        Picasso
                .with(mContext)
                .load(thumbsUrl + mresult[position])
                .resize(width / 2, width * 2 / 3)
                .error(R.drawable.samplea)
                .into(imageView);

        int fator = tmdbActivity.getPageNumber();
        int itemPage = 1;
        if (fator != 1) {
            itemPage = fator * 10;
        } else {
            itemPage = 0;
        }

        textView.setText(String.valueOf((itemPage + position) + 1));
        textView.setGravity(Gravity.BOTTOM | Gravity.END);
        textView.setPadding(0, 0, 20, 20);
        textView.setTextSize(24);
        textView.setTextColor(Color.WHITE);
        textView.setShadowLayer(10, 0, 0, Color.BLACK);
        view.addView(imageView);
        view.addView(textView);

        return view;
    }
}