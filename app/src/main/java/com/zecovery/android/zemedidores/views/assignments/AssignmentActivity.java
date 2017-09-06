package com.zecovery.android.zemedidores.views.assignments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.ExecuteTestPart1Fragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.ExecuteTestPart2Fragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.ExecuteTestPart3Fragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.executetest.PhotosTestFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.BusinessFormFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.CommercialFormFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.NegotiationFragment;
import com.zecovery.android.zemedidores.views.assignments.fragments.forms.ResidentialFormFragment;

import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.BUSINESS;
import static com.zecovery.android.zemedidores.data.Constant.COMMERCIAL;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.RESIDENTIAL;

public class AssignmentActivity extends AppCompatActivity implements TokenListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {

            int token = getIntent().getIntExtra(ID_ASSIGNMENT, 0);

            switch (getIntent().getStringExtra(ASSIGNMENT_TYPE)) {

                case RESIDENTIAL:
                    callFragment(new ResidentialFormFragment().newInstance(token));
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

    @Override
    public void tokenToPhotoTest(int token) {
        callFragment(new PhotosTestFragment().newInstance(token));
    }

    @Override
    public void tokenToExecuteTestPart1(int token) {
        callFragment(new ExecuteTestPart1Fragment().newInstance(token));
    }

    @Override
    public void tokenToExecuteTestPart2(int token) {
        callFragment(new ExecuteTestPart2Fragment().newInstance(token));
    }

    @Override
    public void tokenToExecuteTestPart3(int token) {
        callFragment(new ExecuteTestPart3Fragment().newInstance(token));
    }

    @Override
    public void tokenToNegotiation(int token) {
        callFragment(new NegotiationFragment().newInstance(token));
    }
}
