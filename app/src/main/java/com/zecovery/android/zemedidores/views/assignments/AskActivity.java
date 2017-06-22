package com.zecovery.android.zemedidores.views.assignments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.zecovery.android.zemedidores.R;

import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;

public class AskActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button affirmative = (Button) findViewById(R.id.affirmative);
        Button negative = (Button) findViewById(R.id.negative);

        affirmative.setOnClickListener(this);
        negative.setOnClickListener(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void assignmentType() {

        Bundle extra = getIntent().getExtras();

        if (extra != null) {

            Intent intent = new Intent(AskActivity.this, AssignmentActivity.class);

            Log.d("TAG", "assignmentType: " + getIntent().getStringExtra(ASSIGNMENT_TYPE));

            switch (getIntent().getStringExtra(ASSIGNMENT_TYPE)) {

                case RESIDENTIAL:
                    intent.putExtra(ASSIGNMENT_TYPE, RESIDENTIAL);
                    startActivity(intent);
                    break;
                case COMMERCIAL:
                    intent.putExtra(ASSIGNMENT_TYPE, COMMERCIAL);
                    startActivity(intent);
                    break;
                case BUSINESS:
                    intent.putExtra(ASSIGNMENT_TYPE, BUSINESS);
                    startActivity(intent);
                    break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.affirmative:
                assignmentType();
                break;
            case R.id.negative:
                startActivity(new Intent(AskActivity.this, RejectedActivity.class));
                break;
        }
    }
}
