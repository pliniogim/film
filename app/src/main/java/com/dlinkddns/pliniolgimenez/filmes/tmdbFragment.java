package com.dlinkddns.pliniolgimenez.filmes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class tmdbFragment extends Fragment {

    //Total de respostas JSON
    private final static int RESPONSE_TOTAL = 20;

    //booleano para controle de conteúdo http válido
    private static boolean valid_http_data = false;

    //booleano para controle de troca da ordem da lista
    private static String old_list_order = "";
    //acesso às variaveis ja decodificadas no JSON
    private static String[] filmThumbnailUrl = new String[RESPONSE_TOTAL];
    private static String[] filmTitle = new String[RESPONSE_TOTAL];


    private static String[] filmReleaseDate = new String[RESPONSE_TOTAL];
    private static String[] filmOverview = new String[RESPONSE_TOTAL];
    private static String[] filmAdult = new String[RESPONSE_TOTAL];
    private static String[] filmPopularity = new String[RESPONSE_TOTAL];
    private static String[] filmVotes = new String[RESPONSE_TOTAL];
    private static String[] filmVotesAvg = new String[RESPONSE_TOTAL];


    View rootView;
    private ImageAdapter mAdapter;
    private GridView gridview;

    // construtor publico obrigatorio
    public tmdbFragment() {
    }

    static String getFilmTitle(int indice) {
        return filmTitle[indice];
    }

    static String getFilmOverview(int indice) {
        return filmOverview[indice];
    }

    static String getFilmThumbnailUrl(int indice) {
        return filmThumbnailUrl[indice];
    }

    static String getFilmReleaseDate(int indice) {
        return filmReleaseDate[indice];
    }

    static String getFilmAdult(int indice) {
        return filmAdult[indice];
    }

    static String getFilmPopularity(int indice) {
        return filmPopularity[indice];
    }

    static String getFilmVotes(int indice) {
        return filmVotes[indice];
    }

    static String getFilmVotesAvg(int indice) {
        return filmVotesAvg[indice];
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        //armazena a vista para melhorar pesquisa a partir de rootView
        rootView = inflater.inflate(R.layout.fragment_tmdb, container, false);
        gridview = (GridView) rootView.findViewById(R.id.gridview);
        //retorna a vista
        return rootView;
    }


    @Override
    public void onStart() {
        //chama supermetodo
        super.onStart();

        //pega a preferencia
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String orderList = prefs.getString(getString(R.string.pref_initial_key), getString(R.string.pref_initial_popular));

        //classe para alimentar o listView Adapter
        if (old_list_order.equals(orderList)) {
            if (valid_http_data) {
                mAdapter = new ImageAdapter(getActivity(), filmThumbnailUrl);
                gridview.setAdapter(mAdapter);
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        String indice = String.valueOf(position);
                        Intent intent = new Intent(getActivity(), DetailActivity.class)
                                .putExtra(Intent.EXTRA_TEXT, indice);
                        startActivity(intent);
                    }
                });


            } else {
                updateTMDB(orderList);
            }
        } else {
            old_list_order = orderList;
            updateTMDB(orderList);
        }

        Bitmap bmp = null;

        for (int i = 0; i < filmThumbnailUrl.length; i++) {

            final String filename = filmThumbnailUrl[i];


            Target target = new Target() {
                private final static String thumbsUrl = "http://image.tmdb.org/t/p/w185";

                @Override
                public void onPrepareLoad(Drawable arg0) {
                    return;
                }

                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom arg1) {

                    try {
                        FileOutputStream ostream = getContext().openFileOutput(filename, getContext().MODE_PRIVATE);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                        ostream.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onBitmapFailed(Drawable arg0) {
                    return;
                }
            };
            int width = getContext().getResources().getDisplayMetrics().widthPixels;

            Picasso.with(this.getContext())
                    .load("http://image.tmdb.org/t/p/w185" + filmThumbnailUrl[i])
                    .resize(width / 2, width * 2 / 3)
                    .error(R.drawable.samplea)
                    .into(target);


            //String filename = i+".jpg";
            //bmp = BitmapFactory.decodeByteArray());
            //FileOutputStream out;
            //String uriString = (out.getAbsolutePath());
            //try {
            //    out = getActivity().openFileOutput(filename, getActivity().MODE_PRIVATE);
            //    bmp.compress(Bitmap.CompressFormat.PNG, 100, out);
            //} catch (FileNotFoundException e) {
            //    e.printStackTrace();
            //}
        }
    }

    @Override
    public void onResume() {

        super.onResume();
        //pega a preferencia
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String orderList = prefs.getString(getString(R.string.pref_initial_key), getString(R.string.pref_initial_popular));

        //classe para alimentar o listView Adapter
        if (old_list_order.equals(orderList)) {
            if (valid_http_data) {
                gridview.setAdapter(new ImageAdapter(getActivity(), filmThumbnailUrl));
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        String indice = String.valueOf(position);
                        Intent intent = new Intent(getActivity(), DetailActivity.class)
                                .putExtra(Intent.EXTRA_TEXT, indice);
                        startActivity(intent);
                    }
                });


            } else {
                updateTMDB(orderList);
            }
        } else {
            old_list_order = orderList;
            updateTMDB(orderList);
        }

    }

    private void updateTMDB(String orderList) {
        FetchTMDB TMDBTask = new FetchTMDB();
        TMDBTask.execute(orderList);
    }


    private class FetchTMDB extends AsyncTask<String, Void, String[]> {
        private final String LOG_TAG = FetchTMDB.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... params) {
            Log.i(LOG_TAG, "Dentro da rotina doInBackground");

            // Checa se existe parâmetro recebido
            if (params.length == 0) {
                return null;
            }

            // Estabelecer uma conexão de HTTP
            // para obter os dados de tmdb
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // vai conter o retorno raw da requisicao
            String tmdb_json_str = null;

            try {
                /**
                 * Constantes da API tmdb
                 * Chave de acesso à api deve ser colocada no arquivo gradle.properties
                 */

                //final String TMDBurlApi = "https://api.themoviedb.org/3/";
                //final String apiKey = BuildConfig.TMDB_API_KEY;
                //final String urlThumbnail = "https://image.tmdb.org/t/p/";

                final String TMDBurlApiPopular = "https://api.themoviedb.org/3/movie/popular?";
                final String TMDBurlApiTopRated = "https://api.themoviedb.org/3/movie/top_rated?";
                final String APPID_PARAM = "api_key";

                //ordem da lista (popular ou rated)
                String baseUrl;
                if (params[0].equals(getString(R.string.popular))) {
                    baseUrl = TMDBurlApiPopular;
                } else {
                    baseUrl = TMDBurlApiTopRated;
                }

                //cria a URL
                Uri builtUri = Uri.parse(baseUrl).buildUpon()
                        .appendQueryParameter(APPID_PARAM, BuildConfig.TMDB_API_KEY)
                        .build();

                URL url = new URL(builtUri.toString());

                // cria a requisicao com o site tmdb
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                //le o fluxo de entrada em um String
                InputStream inputStream = urlConnection.getInputStream();

                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // erro de dados...
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // nova linha para efeitos de debug (não altera parse JSON)
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                tmdb_json_str = buffer.toString();

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                // código não retornou dados
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                Log.d(LOG_TAG, tmdb_json_str);
                return getTMDBDataFromJson(tmdb_json_str);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }

            // This will only happen if there was an error getting or parsing the forecast.
            return null;
        }


        @Override
        protected void onPostExecute(String[] strings) {
            if (strings != null) {
                valid_http_data = true;
                gridview.setAdapter(new ImageAdapter(getActivity(), strings));
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        String indice = String.valueOf(position);
                        Intent intent = new Intent(getActivity(), DetailActivity.class)
                                .putExtra(Intent.EXTRA_TEXT, indice);
                        startActivity(intent);
                    }
                });
            }
        }


        private String[] getTMDBDataFromJson(String params) throws JSONException {

            Log.i(LOG_TAG, "Dentro da rotina getTMDBDataFromJson");

            // nomes para decode JSON
            final String TMDB_RESULT = "results";
            final String TMDB_POSTER_PATH = "poster_path";
            final String TMDB_TITLE = "title";
            final String TMDB_OVERVIEW = "overview";
            final String TMDB_RELEASEDATE = "release_date";
            final String TMDB_ADULT = "adult";
            final String TMDB_POPULARITY = "popularity";
            final String TMDB_VOTES = "vote_count";
            final String TMDB_VOTESAVG = "vote_average";


            //pega a lista de resultados e coloca em um array
            JSONObject tmdb_json = new JSONObject(params);
            JSONArray filmsArray = tmdb_json.getJSONArray(TMDB_RESULT);

            String poster_path;
            String title;
            String overview;
            String releaseDate;
            String adult;
            String popularity;
            String votes;
            String votesAvg;


            for (int i = 0; i < filmsArray.length(); i++) {
                JSONObject films = filmsArray.getJSONObject(i);

                poster_path = films.getString(TMDB_POSTER_PATH);
                title = films.getString(TMDB_TITLE);
                overview = films.getString(TMDB_OVERVIEW);
                releaseDate = films.getString(TMDB_RELEASEDATE);
                adult = films.getString(TMDB_ADULT);
                popularity = films.getString(TMDB_POPULARITY);
                votes = films.getString(TMDB_VOTES);
                votesAvg = films.getString(TMDB_VOTESAVG);

                //para acesso pelas outras classes
                filmTitle[i] = title;
                filmThumbnailUrl[i] = poster_path;
                filmOverview[i] = overview;
                filmReleaseDate[i] = releaseDate;
                filmAdult[i] = adult;
                filmPopularity[i] = popularity;
                filmVotes[i] = votes;
                filmVotesAvg[i] = votesAvg;
            }
            return filmThumbnailUrl;
        }
    }
}
