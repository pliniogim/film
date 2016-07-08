/*
 * INICIAL
 * Autor: Plinio Gimenez
 *
 * filmes
 * Copyright 2016 Plinio Gimenez
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
*/
package com.dlinkddns.pliniolgimenez.filmes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    /**
     * Constantes da API tmdb
     * Chave de acesso à api deve ser colocada no arquivo gradle.properties
     */
    public static final String urlApi = "https://api.themoviedb.org/3/";
    public static final String apiKey = BuildConfig.TMDB_API_KEY;
    public static final String urlThumbnail = "https://image.tmdb.org/t/p/";
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.tmdbcontainer, new TMDBFragment())
                    .commit();
        }
    }
}
