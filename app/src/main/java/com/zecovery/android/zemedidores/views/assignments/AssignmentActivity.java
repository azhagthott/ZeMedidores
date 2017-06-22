package com.zecovery.android.zemedidores.views.assignments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.fragments.BusinessFormFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.CommercialFormFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.ResidentialFormFragment;

import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;

public class AssignmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {

            switch (getIntent().getStringExtra(ASSIGNMENT_TYPE)) {
                case RESIDENTIAL:
                    callFragment(new ResidentialFormFragment());
                    break;
                case COMMERCIAL:
                    callFragment(new CommercialFormFragment());
                    break;
                case BUSINESS:
                    callFragment(new BusinessFormFragment());
                    break;
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void callFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_assignment, fragment).commit();
    }
}