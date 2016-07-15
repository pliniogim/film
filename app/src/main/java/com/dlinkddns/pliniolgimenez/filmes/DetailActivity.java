package com.dlinkddns.pliniolgimenez.filmes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


/**
 * com.dlinkddns.pliniolgimenez.filmes
 * <p>
 * Filmes
 * <p>
 * Created by plinio on 11/07/2016.
 */
public class DetailActivity extends AppCompatActivity {


    private final static String thumbsUrl = "http://image.tmdb.org/t/p/w185";
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        mContext = getApplicationContext();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }

        Toast.makeText(this, "Dentro da Atividade Detail...", Toast.LENGTH_SHORT).show();

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class DetailFragment extends Fragment {

        public DetailFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();

            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {
                String indiceStr = intent.getStringExtra(Intent.EXTRA_TEXT);


                int indice = Integer.parseInt(indiceStr);
                //titulo
                ((TextView) rootView.findViewById(R.id.detail_activity_titulo)).setText(tmdbFragment.getFilmTitle(indice));
                //resenha
                ((TextView) rootView.findViewById(R.id.detail_activity_overview)).setText(tmdbFragment.getFilmOverview(indice));
                //data de lançamento
                ((TextView) rootView.findViewById(R.id.detail_activity_releasedate)).setText(getString(R.string.detail_activity_release) + " " + tmdbFragment.getFilmReleaseDate(indice));
                //imagem
                ImageView view = (ImageView) rootView.findViewById(R.id.detail_activity_imageview);
                int width = mContext.getResources().getDisplayMetrics().widthPixels;
                Picasso
                        .with(getContext())
                        .load(thumbsUrl + tmdbFragment.getFilmThumbnailUrl(indice))
                        .resize(width / 2, width * 2 / 3)
                        .error(R.drawable.samplea)
                        .into(view);

                //TODO scrollView.setBackgroundTintMode();
                ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.detail_activity_scrollview);
                scrollView.setBackgroundColor(Color.LTGRAY);
                //scrollView.setBackground(view.getDrawable());
            }
            return rootView;
        }
    }
}
