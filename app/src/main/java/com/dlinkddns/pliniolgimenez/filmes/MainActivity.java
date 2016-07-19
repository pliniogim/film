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

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main" ;
    public static Context contexto;
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    public static Context getContexto() {
        return contexto;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        contexto = getApplicationContext();

        if (savedInstanceState != null) {
            return;
        } else {

            //inicializa fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.tmdbcontainer, new tmdbActivity.tmdbFragment())
                    .commit();

            //String layout_choice = getString(R.string.selected_configuration);

            //if (layout_choice.equals("large")) {
            //    getSupportFragmentManager()
            //            .beginTransaction()
            //            .add(R.id.container, new DetailActivity.DetailFragment())
            //            .commit();
            //}
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //cria o menu a partir do xml
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        //return para supermetodo
        return true;
    }
}
