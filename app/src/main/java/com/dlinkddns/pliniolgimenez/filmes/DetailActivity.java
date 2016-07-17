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
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;


/**
 * com.dlinkddns.pliniolgimenez.filmes
 * <p>
 * Filmes
 * <p>
 * Created by plinio on 11/07/2016.
 */
public class DetailActivity extends AppCompatActivity {
    private static String thumbsUrl;
    private static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);
        thumbsUrl = tmdbFragment.getThumbsUrl();
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


                int indice = Integer.parseInt(indiceStr);
                //titulo
                ((TextView) rootView.findViewById(R.id.detail_activity_titulo)).setText(tmdbFragment.getFilmTitle(indice));
                //resenha
                ((TextView) rootView.findViewById(R.id.detail_activity_overview)).setText(tmdbFragment.getFilmOverview(indice));
                //data de lancamento
                ((TextView) rootView.findViewById(R.id.detail_activity_releasedate)).setText(tmdbFragment.getFilmReleaseDate(indice).substring(0, 4));
                //tema adulto getFilmAdult(int indice)
                if (tmdbFragment.getFilmAdult(indice).equals("true")) {
                    ((TextView) rootView.findViewById(R.id.detail_activity_adult)).setText(R.string.adultTheme);
                } else {
                    ((TextView) rootView.findViewById(R.id.detail_activity_adult)).setText(" ");
                }
                //popularidade
                ((TextView) rootView.findViewById(R.id.detail_activity_popularity)).setText(getString(R.string.popularity) +" "+ tmdbFragment.getFilmPopularity(indice));
                //votos
                ((TextView) rootView.findViewById(R.id.detail_activity_votes)).setText(getString(R.string.nbrvotes)+" "+tmdbFragment.getFilmVotes(indice));
                //media de votos
                ((TextView) rootView.findViewById(R.id.detail_activity_avgvotes)).setText(getString(R.string.avgvotes)+" "+tmdbFragment.getFilmVotesAvg(indice));


                //imagem
                ImageView view = (ImageView) rootView.findViewById(R.id.detail_activity_imageview);
                //int width = mContext.getResources().getDisplayMetrics().widthPixels;
                //isFilePresent(file);
                Picasso
                        .with(MainActivity.getContexto())
                        .load(tmdbFragment.getThumbsUrl() + tmdbFragment.getFilmThumbnailUrl(indice))
                        //.resize(width / 2, width * 2 / 3)
                        .error(R.drawable.samplea)
                        .into(view);


                //TODO scrollView.setBackgroundTintMode();
                //ScrollView scrollView = (ScrollView) rootView.findViewById(R.id.detail_activity_scrollview);
                //scrollView.setBackgroundColor(Color.LTGRAY);
                //scrollView.setBackground(view.getDrawable());
            }
            return rootView;
        }

        public boolean isFilePresent(String fileName) {

            Toast.makeText(getContext(), "File:" + fileName, Toast.LENGTH_LONG).show();
            String path = getContext().getFilesDir().getAbsolutePath() + "/" + fileName;
            File file = new File(path);
            return file.exists();
        }
    }
}
