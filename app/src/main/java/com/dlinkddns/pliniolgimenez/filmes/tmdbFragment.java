package com.dlinkddns.pliniolgimenez.filmes;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.ArrayAdapter;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class tmdbFragment extends Fragment {

    private View rootView;
    private GridView gridview;
    private String orderList;
    private String[] decodedStr;

    private ArrayAdapter<String> mtmdbAdapter;


    public tmdbFragment() {
        // construtor publico obrigatorio
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

        //chama metodo para pegar os dados da api TMDB via HTTP
        updateTMDB();
    }


    private void updateTMDB() {

        //classe para alimentar o listView Adapter
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        orderList = prefs.getString(getString(R.string.pref_initial_key), getString(R.string.pref_initial_popular));

        FetchTMDB TMDBTask = new FetchTMDB();
        TMDBTask.execute(orderList);
    }


    public class FetchTMDB extends AsyncTask<String, Void, String[]> {
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
                final String TMDBurlApi = "https://api.themoviedb.org/3/";
                final String apiKey = BuildConfig.TMDB_API_KEY;
                final String urlThumbnail = "https://image.tmdb.org/t/p/";
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

                // cria a requisição com o site tmdb
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
                decodedStr = getTMDBDataFromJson(tmdb_json_str);
                return decodedStr;
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
                gridview.setAdapter(new ImageAdapter(getActivity(), strings));
                gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent(getActivity(), DetailActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }


        /**
         * @param params
         * @return
         * @throws JSONException
         */
        private String[] getTMDBDataFromJson(String params) throws JSONException {

            Log.i(LOG_TAG, "Dentro da rotina getTMDBDataFromJson");

            // nomes para decode JSON
            final String TMDB_RESULT = "results";
            final String TMDB_POSTER_PATH = "poster_path";
            final String TMDB_TITLE = "title";

            //pega a lista de resultados e coloca em um array
            JSONObject tmdb_json = new JSONObject(params);
            JSONArray filmsArray = tmdb_json.getJSONArray(TMDB_RESULT);

            //string que será montada para retorno com
            //o mesmo tamanho de respostas do siste
            String[] resultado = new String[filmsArray.length()];

            String poster_path;
            String title;

            for (int i = 0; i < filmsArray.length(); i++) {
                JSONObject films = filmsArray.getJSONObject(i);

                poster_path = films.getString(TMDB_POSTER_PATH);
                title = films.getString(TMDB_TITLE);
                resultado[i] = poster_path;

            }
            return resultado;

        }
    }
}
