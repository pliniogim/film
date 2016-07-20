package com.dlinkddns.pliniolgimenez.filmes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * {@link Fragment} subclass.
 * Executa a montagem da tela
 * (gridview com o thumbnail dos filmes)
 */

public class tmdbActivity2 extends tmdbActivity {

    //on save
    private final static String ARG_POSITION = "position";

    /**
     * VARIAVEIS DE CLASSE
     */

    //posição do foco do gridview
    private static int viewFocus = 0;

    public static class tmdbFragment extends Fragment {

        //gridview de referencia
        private GridView gridview;

        //construtor publico obrigatorio
        public tmdbFragment() {
        }

        //ONCREATEVIEW
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            //armazena a vista para melhorar pesquisa a partir de rootView
            View rootView = inflater.inflate(R.layout.grid_fragment_tmdb, container, false);
            gridview = (GridView) rootView.findViewById(R.id.gridview);

            //retorna a vista
            return rootView;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            // Save the current article selection in case we need to recreate the fragment
            outState.putInt(ARG_POSITION, viewFocus);
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            if (savedInstanceState != null) {
                viewFocus = savedInstanceState.getInt(ARG_POSITION);
            }
            super.onCreate(savedInstanceState);
        }

        //ONSTART
        @Override
        public void onStart() {

            //chama supermetodo
            super.onStart();

            //cria adaptador
            setAdapter();
        }

        //ONRESUME
        @Override
        public void onResume() {

            //chama supermetodo
            super.onResume();

            //cria adaptador
            setAdapter();
        }


        /**
         * INICIO ROTINAS
         * DE APOIO
         */

        //seta adaptador de imagens
        void setAdapter() {

            //seta o foco
            gridview.setSelection(viewFocus);
            gridview.setFocusable(true);

            //seta o adaptador
            gridview.setAdapter(new ImageAdapter2(getActivity(), tmdbActivity.getFilmThumbnailUrl()));
            setAdapterListener();
        }

        //listener do adaptador
        private void setAdapterListener() {
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    viewFocus = position;
                    String indice = String.valueOf(position);
                    Intent intent = new Intent(getActivity(), DetailActivity.class)
                            .putExtra(Intent.EXTRA_TEXT, indice);
                    startActivity(intent);
                }
            });
        }
    }
}


