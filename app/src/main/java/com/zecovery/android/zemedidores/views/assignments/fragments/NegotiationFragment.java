package com.zecovery.android.zemedidores.views.assignments.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zecovery.android.zemedidores.R;

public class NegotiationFragment extends Fragment {

    public NegotiationFragment() {
    }

    public NegotiationFragment newInstance() {
        return new NegotiationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_negotiation, container, false);
    }

}
