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

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.adapters.InspectionAdapter;
import com.zecovery.android.zemedidores.background.InspectionParams;
import com.zecovery.android.zemedidores.background.InspectionList;
import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.views.login.LoginActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mainRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        mainRecyclerView = findViewById(R.id.mainRecyclerView);
        new GetInspections(new InspectionParams(123, "")).execute();
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

    public class GetInspections extends InspectionList {


        public GetInspections(InspectionParams params) {
            super(params);
        }

        @Override
        protected void onPostExecute(List<Inspection> inspections) {
            super.onPostExecute(inspections);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            mainRecyclerView.setLayoutManager(linearLayoutManager);
            InspectionAdapter adapter = new InspectionAdapter(MainActivity.this, inspections);
            mainRecyclerView.setAdapter(adapter);
        }
    }
}