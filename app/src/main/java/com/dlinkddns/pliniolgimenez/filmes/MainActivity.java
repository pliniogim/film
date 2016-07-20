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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    //private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //inicializa vista
        setContentView(R.layout.framelayout_activity_main);

        if (savedInstanceState == null) {
            //inicializa fragment
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.tmdbcontainer, new tmdbActivity.tmdbFragment())
                    .commit();
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
        // para o actionbar lidar com os eventos e tambem com
        // os botoes home/up desde que a atividade esteja associada
        // com parent activity no manifest
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }

        //return para supermetodo
        return true;
    }
}
