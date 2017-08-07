package com.zecovery.android.zemedidores.views.assignments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.fragments.PushKeyListener;
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

public class AssignmentActivity extends AppCompatActivity implements PushKeyListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {

            String pushKey = getIntent().getStringExtra(ID_ASSIGNMENT);

            switch (getIntent().getStringExtra(ASSIGNMENT_TYPE)) {

                case RESIDENTIAL:
                    callFragment(new ResidentialFormFragment().newInstance(pushKey));
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
    public void pushKeyToPhotoTest(String pushKey) {
        callFragment(new PhotosTestFragment().newInstance(pushKey));
    }

    @Override
    public void pushKeyToExecuteTestPart1(String pushKey) {
        callFragment(new ExecuteTestPart1Fragment().newInstance(pushKey));
    }

    @Override
    public void pushKeyToExecuteTestPart2(String pushKey) {
        callFragment(new ExecuteTestPart2Fragment().newInstance(pushKey));
    }

    @Override
    public void pushKeyToExecuteTestPart3(String pushKey) {
        callFragment(new ExecuteTestPart3Fragment().newInstance(pushKey));
    }

    @Override
    public void pushKeyToNegotiation(String pushKey) {
        callFragment(new NegotiationFragment().newInstance(pushKey));
    }
}
