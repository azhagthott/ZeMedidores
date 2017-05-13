package com.zecovery.android.zemedidores.views;

import android.content.Intent;
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
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.adapters.AssignmentAdapter;
import com.zecovery.android.zemedidores.adapters.AssignmentUpdater;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.views.login.LoginActivity;

public class MainActivity extends AppCompatActivity implements AssignmentUpdater {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mainRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mainRecyclerView.setLayoutManager(linearLayoutManager);
        AssignmentAdapter adapter = new AssignmentAdapter(new CurrentUser().uid(), MainActivity.this);
        mainRecyclerView.setAdapter(adapter);

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
