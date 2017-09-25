package com.zecovery.android.zemedidores.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.adapters.InspeccionAdapter;
import com.zecovery.android.zemedidores.background.InspeccionParams;
import com.zecovery.android.zemedidores.background.ListaInspecciones;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.views.login.LoginActivity;
import com.zecovery.android.zemedidores.views.map.MapActivity;
import com.zecovery.android.zemedidores.views.settings.SettingsActivity;

import java.util.List;
import java.util.Random;

import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;
import static com.zecovery.android.zemedidores.data.Constant.NUEVA;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;
    private LocalDatabase db;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mainRecyclerView = findViewById(R.id.mainRecyclerView);

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Random r = new Random();
                int idInspeccion = -1 * (r.nextInt(99999999 - 10000000) + 10000000);

                Log.d("MainActivity", "idInspeccion: " + idInspeccion);

                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                intent.putExtra(ASSIGNMENT_TYPE, NUEVA);
                intent.putExtra(ID_INSPECCION, idInspeccion);
                startActivity(intent);
            }
        });

        db = new LocalDatabase(this);

        new GetInspecciones(new InspeccionParams(123, new CurrentUser().email())).execute();
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_sign_out:
                signOut();
                break;
            case R.id.action_about:
                startActivity(new Intent(this, AboutActivity.class));
                break;
            case R.id.action_preference:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
                    }
                });
    }

    public class GetInspecciones extends ListaInspecciones {

        public GetInspecciones(InspeccionParams params) {
            super(params);
        }

        @Override
        protected void onPostExecute(List<Inspection> inspections) {
            super.onPostExecute(inspections);

            db.guardaInspeccionesDescargadas(inspections);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mainRecyclerView.setLayoutManager(linearLayoutManager);
            InspeccionAdapter adapter = new InspeccionAdapter(inspections);
            mainRecyclerView.setAdapter(adapter);
        }
    }
}