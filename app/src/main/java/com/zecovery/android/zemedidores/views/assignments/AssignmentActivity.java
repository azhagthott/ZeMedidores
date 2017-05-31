package com.zecovery.android.zemedidores.views.assignments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.fragments.BusinessFormFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.CommercialFormFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.RejectionFormFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.ResidentialFormFragment;

import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.EMPTY;
import static com.zecovery.android.zemedidores.data.Constant.REJECTED;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;

public class AssignmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        if (getIntent().getStringExtra("EXTRA") != null) {

            String extra = getIntent().getStringExtra("EXTRA");

            Log.d("LOG_TAG", "extra: " + extra);

            switch (extra) {
                case RESIDENTIAL:
                    setFragment(new ResidentialFormFragment());
                    break;
                case COMMERCIAL:
                    setFragment(new CommercialFormFragment());
                    break;
                case BUSINESS:
                    setFragment(new BusinessFormFragment());
                    break;
                case REJECTED:
                    setFragment(new RejectionFormFragment());
                    break;
                case EMPTY:
                    setFragment(new ResidentialFormFragment());
                    break;
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    protected void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, fragment);
        fragmentTransaction.commit();
    }

}
