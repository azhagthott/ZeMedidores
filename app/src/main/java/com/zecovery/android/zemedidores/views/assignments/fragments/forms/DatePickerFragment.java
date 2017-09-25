package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;

/**
 * Created by fbarrios80 on 25-09-17.
 */

public class DatePickerFragment extends DialogFragment {

    OnDateSetListener onDateSet;
    private int year;
    private int month;
    private int day;

    public DatePickerFragment() {
    }

    public void setCallBack(OnDateSetListener onDate) {
        onDateSet = onDate;
    }

    public DatePickerFragment newInstance(int idInspeccion) {

        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION, idInspeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @SuppressLint("NewApi")
    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        year = args.getInt("year");
        month = args.getInt("month");
        day = args.getInt("day");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), onDateSet, year, month, day);
    }
}
