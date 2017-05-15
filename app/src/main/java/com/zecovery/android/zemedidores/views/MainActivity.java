package com.zecovery.android.zemedidores.views;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.events.DecoEvent;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.adapters.AssignmentAdapter;
import com.zecovery.android.zemedidores.adapters.AssignmentUpdater;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.views.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements AssignmentUpdater {

    private RecyclerView mainRecyclerView;
    private DecoView main, done, undone;
    //private CollapsingToolbarLayout toolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViews();

        toolbar.setTitle("");
        //toolbarLayout.setTitle("");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainRecyclerView.setLayoutManager(linearLayoutManager);
        AssignmentAdapter adapter = new AssignmentAdapter(new CurrentUser().uid(), MainActivity.this);
        mainRecyclerView.setAdapter(adapter);

        graph();
    }


    private void graph() {
        main.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(12f)
                .build());

        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(12f)
                .setSpinDuration(2000)
                .build();

        int series1Index = main.addSeries(seriesItem1);

        main.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(500)
                .setDuration(1000)
                .build());

        main.addEvent(new DecoEvent.Builder(25)
                .setIndex(series1Index)
                .setDelay(4000)
                .build());

    }

    private void findViews() {


        //toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        mainRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        main = (DecoView) findViewById(R.id.mainProgress);
        done = (DecoView) findViewById(R.id.done);
        undone = (DecoView) findViewById(R.id.undone);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            return true;
        }
        if (id == R.id.action_sign_out) {
            signOut();
            return true;
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

    @Override
    public void update() {
        Toast.makeText(this, "update", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setAsDone() {
        Toast.makeText(this, "setAsDone", Toast.LENGTH_SHORT).show();
    }
}
