package com.dlinkddns.pliniolgimenez.filmes;

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
 *
 * Filmes
 *
 * Created by plinio on 11/07/2016.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailActivity.DetailFragment())
                    .commit();

            String layout_choice = getString(R.string.selected_configuration);

            if (layout_choice.equals("large")) {
                //usar o suporte para fragmento para iniciar outra atividade
                //no mesmo pane
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.tmdbcontainer, new tmdbActivity2.tmdbFragment())
                        .commit();
            }
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

            //infla vista
            View rootView = inflater.inflate(R.layout.scrollview_fragment_detail, container, false);

            //pega os parametros passados para a atividade
            Intent intent = getActivity().getIntent();

            if (intent != null && intent.hasExtra(Intent.EXTRA_TEXT)) {

                //indice do onclicklistener passado para a atividade
                String indiceStr = intent.getStringExtra(Intent.EXTRA_TEXT);
                int indice = Integer.parseInt(indiceStr);

                //titulo
                ((TextView) rootView.findViewById(R.id.detail_activity_titulo))
                        .setText(tmdbActivity.getFilmTitle(indice));

                //resenha
                ((TextView) rootView.findViewById(R.id.detail_activity_overview))
                        .setText(tmdbActivity.getFilmOverview(indice));

                //data de lancamento
                ((TextView) rootView.findViewById(R.id.detail_activity_releasedate))
                        .setText(tmdbActivity.getFilmReleaseDate(indice).substring(0, 4));

                //tema adulto getFilmAdult(int indice)
                if (tmdbActivity.getFilmAdult(indice).equals("true")) {
                    ((TextView) rootView.findViewById(R.id.detail_activity_adult))
                            .setText(R.string.adultTheme);
                } else {
                    ((TextView) rootView.findViewById(R.id.detail_activity_adult))
                            .setText(" ");
                }

                //popularidade
                ((TextView) rootView.findViewById(R.id.detail_activity_popularity))
                        .setText(getString(R.string.popularity) + " " + tmdbActivity.getFilmPopularity(indice));

                //votos
                ((TextView) rootView.findViewById(R.id.detail_activity_votes))
                        .setText(getString(R.string.nbrvotes) + " " + tmdbActivity.getFilmVotes(indice));

                //media de votos
                ((TextView) rootView.findViewById(R.id.detail_activity_avgvotes))
                        .setText(getString(R.string.avgvotes) + " " + tmdbActivity.getFilmVotesAvg(indice));

                //imagem
                ImageView view = (ImageView) rootView.findViewById(R.id.detail_activity_imageview);

                //download ou cache para imageview
                Picasso
                        .with(getActivity().getApplicationContext())
                        .load(tmdbActivity.getThumbsUrl() + tmdbActivity.getFilmThumbnailUrl(indice))
                        .error(R.drawable.samplea)
                        .into(view);
            }
            return rootView;
        }

    }

}


