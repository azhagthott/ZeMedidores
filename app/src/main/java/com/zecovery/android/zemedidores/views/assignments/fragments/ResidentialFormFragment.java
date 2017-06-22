package com.zecovery.android.zemedidores.views.assignments.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.zecovery.android.zemedidores.R;

public class ResidentialFormFragment extends Fragment implements View.OnClickListener {

    public ResidentialFormFragment() {

    }

    public ResidentialFormFragment newInstance() {
        return new ResidentialFormFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_residential_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button saveButton = (Button) view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        Log.d("TAG", "ResidentialFormFragment loaded");

    }

    @Override
    public void onClick(View v) {
        NegotiationFragment negotiationFragment = new NegotiationFragment();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction().replace(R.id.content_assignment, negotiationFragment);
        ft.addToBackStack(null);
        ft.commit();
    }
}
