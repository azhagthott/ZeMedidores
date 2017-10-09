package com.zecovery.android.zemedidores.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.CurrentUser;

public class NoAutorizadoActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_un_authorized);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        String email = new CurrentUser().email();

        Bundle params = new Bundle();
        params.putString("UNAUTHORIZED_EMAIL", email);
        mFirebaseAnalytics.logEvent("NO_AUTORIZADO", params);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
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
                        startActivity(new Intent(NoAutorizadoActivity.this, LoginActivity.class));
                        finish();
                    }
                });
    }
}