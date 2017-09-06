package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zecovery.android.zemedidores.R;

public class CommercialFormFragment extends Fragment {

    public CommercialFormFragment() {

    }

    public CommercialFormFragment newInstance() {
        return new CommercialFormFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", "CommercialFormFragment onCreateView");
        return inflater.inflate(R.layout.fragment_commercial_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG", "CommercialFormFragment onViewCreated");
    }
}