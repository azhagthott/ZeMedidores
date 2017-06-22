package com.zecovery.android.zemedidores.views.assignments.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zecovery.android.zemedidores.R;

public class BusinessFormFragment extends Fragment {

    public BusinessFormFragment() {

    }

    public BusinessFormFragment newInstance() {
        return new BusinessFormFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_business_form, container, false);
    }

}
