package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.zecovery.android.zemedidores.R;

/**
 * Created by moe on 08-10-17.
 */

public class ProgressAlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        View view = getActivity().getLayoutInflater().inflate(R.layout.alert_dialog_enviadno_inspeccion, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view);
        builder.setCancelable(false);
        return builder.create();
    }
}
