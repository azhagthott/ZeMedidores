package com.zecovery.android.zemedidores.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;

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

        LocalDatabase db = new LocalDatabase(this);

        versionDatabaseTextView = findViewById(R.id.versionDatabaseTextView);
        versionCompilacionTextView = findViewById(R.id.versionCompilacionTextView);




    }

}
