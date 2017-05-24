package com.zecovery.android.zemedidores.views;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zecovery.android.zemedidores.R;

/**
 * Created by fbarrios80 on 22-05-17.
 */

public class AlertDialogFragment extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dialog_fragment, container, false);
        getDialog().setTitle("Razon de rechazo");



        return rootView;
    }


}
