package com.zecovery.android.zemedidores.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.zecovery.android.zemedidores.R;

import static com.zecovery.android.zemedidores.data.LocalDatabase.DB_VERSION;

public class AboutActivity extends AppCompatActivity {

    private TextView versionDatabaseTextView;
    private TextView versionCompilacionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        versionDatabaseTextView = findViewById(R.id.versionDatabaseTextView);
        versionCompilacionTextView = findViewById(R.id.versionCompilacionTextView);

        versionDatabaseTextView.setText(String.valueOf(DB_VERSION));
        versionCompilacionTextView.setText(String.valueOf(1));
    }

}
