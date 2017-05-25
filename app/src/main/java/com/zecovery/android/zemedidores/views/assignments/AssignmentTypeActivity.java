package com.zecovery.android.zemedidores.views.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.MainActivity;

public class AssignmentTypeActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_type);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button residential = (Button) findViewById(R.id.residentialButton);
        Button business = (Button) findViewById(R.id.businessButton);

        residential.setOnClickListener(this);
        business.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.residentialButton:
                startActivity(new Intent(AssignmentTypeActivity.this, MainActivity.class));
                break;
            case R.id.businessButton:
                startActivity(new Intent(AssignmentTypeActivity.this, MainActivity.class));
                break;
        }
    }
}
