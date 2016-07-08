package com.dlinkddns.pliniolgimenez.filmes;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TMDBFragment extends Fragment {

    private ArrayAdapter<String> mtmdbAdapter;

    public TMDBFragment() {
        // construtor publico obrigatorio
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**
         * adaptador matriz preenche os dados da listview
         * ao qual esta conectado
         */
        mtmdbAdapter = new ArrayAdapter<String>(
                getActivity(),                  // contexto corrente
                R.layout.list_item_tmdb,        // ID do layout
                R.id.list_item_tmdb_textview,   // ID do TextID para popular
                new ArrayList<String>());

        /**
         * armazena a vista para melhorar pesquisa a partir de rootView
         */
        View rootView = inflater.inflate(R.layout.fragment_tmdb, container, false);

        /**
         * pega referencia da listView
         */
        ListView listView = (ListView) rootView.findViewById(R.id.listview_tmdb);

        /**
         * seta o adaptador para a vista
         */
        listView.setAdapter(mtmdbAdapter);

        /**
         * retorna a vista
         */
        return rootView;
    }

    @Override
    public void onStart() {
        /**
         * chama supermetodo
         */
        super.onStart();

        /**
         * chama metodo para pegar os dados da api TMDB via HTTP
         */
        updateTMDB();
    }


    /**
     *
     */
    private void updateTMDB() {
        /**
         * classe para alimentar o listView Adapter
         */
        FetchTMDB TMDBTask = new FetchTMDB();
        TMDBTask.execute("teste");
    }

    public class FetchTMDB extends AsyncTask<String, Void, String[]> {
        private final String LOG_TAG = FetchTMDB.class.getSimpleName();

        @Override
        protected String[] doInBackground(String... params) {
            Log.i(LOG_TAG, "Dentro da rotina doInBackground");
            String debug[] = {"teste"};

            // If there's no zip code, there's nothing to look up.  Verify size of params.
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
                getTMDBDataFromJson(debug);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return debug;
        }

        @Override
        protected void onPostExecute(String[] strings) {
            if (strings != null) {
                mtmdbAdapter.clear();
                for (String dayForecastStr : strings) {
                    mtmdbAdapter.add(dayForecastStr);
                }
                // New data is back from the server.  Hooray!
            }
        }

        private String[] getTMDBDataFromJson(String[] params) throws JSONException {

            //params[0] = "passed";
            Log.i(LOG_TAG, "Dentro da rotina getTMDBDataFromJson");

            //constroi a url para tmdb
            // para os parametros consute:
            // https://www.themoviedb.org/faq/api

            final String FORECAST_BASE_URL =
                    "http://api.themoviedb.org/3/movie";
            final String QUERY_PARAM = "id";
            final String FORMAT_PARAM = "mode";
            final String UNITS_PARAM = "units";
            final String DAYS_PARAM = "cnt";
            final String APPID_PARAM = "APPID";

            return params;

        }
    }
}
