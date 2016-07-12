package com.dlinkddns.pliniolgimenez.filmes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * com.dlinkddns.pliniolgimenez.filmes
 * <p>
 * Filmes
 * <p>
 * Created by plinio on 11/07/2016.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

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
                ((TextView) rootView.findViewById(R.id.detail_activity_textview))
                        .setText(tmdbFragment.getFilmTitle(indice) +
                                " - " + tmdbFragment.getFilmThumbnailUrl(indice) +
                                " - " + tmdbFragment.getFilmAdult(indice) +
                                " - " + tmdbFragment.getFilmOverview(indice) +
                                " - " + tmdbFragment.getFilmPopularity(indice) +
                                " - " + tmdbFragment.getFilmVotes(indice) +
                                " - " + tmdbFragment.getFilmVotesAvg(indice));
            }
            return rootView;
        }
    }
}
