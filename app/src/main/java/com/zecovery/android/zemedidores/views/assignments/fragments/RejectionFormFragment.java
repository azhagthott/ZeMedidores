package com.zecovery.android.zemedidores.views.assignments.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;

import com.zecovery.android.zemedidores.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class RejectionFormFragment extends Fragment implements View.OnClickListener {

    private FloatingActionButton fab;
    private Spinner rejectionsSpinner;
    private EditText rejectionReasonEditText;

    public RejectionFormFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_rejection, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
    }

    private void findViews(View view) {
        rejectionReasonEditText = (EditText) view.findViewById(R.id.rejectionReasonEditText);
        rejectionsSpinner = (Spinner) view.findViewById(R.id.rejectionsSpinner);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
    }

    @Override
    public void onClick(View view) {

    }
}
