package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.MainActivity;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_NEGOTIATION;

public class NegotiationFragment extends Fragment implements NegotiationCallback, View.OnClickListener {

    private String token;

    public NegotiationFragment() {
    }

    public NegotiationFragment newInstance(String pushKey) {

        NegotiationFragment negotiationFragment = new NegotiationFragment();
        Bundle args = new Bundle();
        args.putString(ID_ASSIGNMENT_NEGOTIATION, pushKey);
        negotiationFragment.setArguments(args);
        return negotiationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_negotiation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        token = getArguments().getString(ID_ASSIGNMENT_NEGOTIATION);

    }

    @Override
    public void saveNegotiationForm() {

    }

    @Override
    public void negotiationFormError() {

    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), MainActivity.class));
    }
}
