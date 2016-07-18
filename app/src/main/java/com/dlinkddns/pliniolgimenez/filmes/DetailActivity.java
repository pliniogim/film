package com.dlinkddns.pliniolgimenez.filmes;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * com.dlinkddns.pliniolgimenez.filmes
 * <p>
 * Filmes
 * <p>
 * Created by plinio on 11/07/2016.
 */
public class DetailActivity extends AppCompatActivity {

    private static int indice;
    private static String thumbsUrl;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        thumbsUrl = tmdbActivity.getThumbsUrl();
        mContext = getApplicationContext();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment())
                    .commit();
        }
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


                indice = Integer.parseInt(indiceStr);
                //titulo
                ((TextView) rootView.findViewById(R.id.detail_activity_titulo)).setText(tmdbActivity.getFilmTitle(indice));
                //resenha
                ((TextView) rootView.findViewById(R.id.detail_activity_overview)).setText(tmdbActivity.getFilmOverview(indice));
                //data de lancamento
                ((TextView) rootView.findViewById(R.id.detail_activity_releasedate)).setText(tmdbActivity.getFilmReleaseDate(indice).substring(0, 4));
                //tema adulto getFilmAdult(int indice)
                if (tmdbActivity.getFilmAdult(indice).equals("true")) {
                    ((TextView) rootView.findViewById(R.id.detail_activity_adult)).setText(R.string.adultTheme);
                } else {
                    ((TextView) rootView.findViewById(R.id.detail_activity_adult)).setText(" ");
                }
                //popularidade
                ((TextView) rootView.findViewById(R.id.detail_activity_popularity)).setText(getString(R.string.popularity) + " " + tmdbActivity.getFilmPopularity(indice));
                //votos
                ((TextView) rootView.findViewById(R.id.detail_activity_votes)).setText(getString(R.string.nbrvotes) + " " + tmdbActivity.getFilmVotes(indice));
                //media de votos
                ((TextView) rootView.findViewById(R.id.detail_activity_avgvotes)).setText(getString(R.string.avgvotes) + " " + tmdbActivity.getFilmVotesAvg(indice));


                //imagem
                ImageView view = (ImageView) rootView.findViewById(R.id.detail_activity_imageview);
                Picasso
                        .with(MainActivity.getContexto())
                        .load(tmdbActivity.getThumbsUrl() + tmdbActivity.getFilmThumbnailUrl(indice))
                        //.resize(width / 2, width * 2 / 3)
                        .error(R.drawable.samplea)
                        .into(view);
            } else {

                rootView = inflater.inflate(R.layout.fragment_detail, container, false);
                indice = 0;
                //titulo
                ((TextView) rootView.findViewById(R.id.detail_activity_titulo)).setText("1");
                //resenha
                ((TextView) rootView.findViewById(R.id.detail_activity_overview)).setText("2");
                //data de lancamento
                ((TextView) rootView.findViewById(R.id.detail_activity_releasedate)).setText("3");
                //tema adulto getFilmAdult(int indice)
                ((TextView) rootView.findViewById(R.id.detail_activity_adult)).setText("4");
                //popularidade
                ((TextView) rootView.findViewById(R.id.detail_activity_popularity)).setText("5");
                //votos
                ((TextView) rootView.findViewById(R.id.detail_activity_votes)).setText("6");
                //media de votos
                ((TextView) rootView.findViewById(R.id.detail_activity_avgvotes)).setText("7");


                //imagem
                //ImageView view = (ImageView) rootView.findViewById(R.id.detail_activity_imageview);
                //Picasso
                //        .with(MainActivity.getContexto())
                //        .load(R.drawable.samplea)
                //        //.resize(width / 2, width * 2 / 3)
                //        .error(R.drawable.samplea)
                //        .into(view);
            }
            return rootView;
        }

    }

}


