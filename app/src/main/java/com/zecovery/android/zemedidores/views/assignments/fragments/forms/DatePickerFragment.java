package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.DatePicker;

import com.zecovery.android.zemedidores.data.LocalDatabase;

import java.util.Calendar;

import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;

/**
 * Created by fbarrios80 on 25-09-17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int idInspeccion;

    public DatePickerFragment() {
    }

    public DatePickerFragment newInstance(int idInspeccion) {

        DatePickerFragment fragment = new DatePickerFragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION, idInspeccion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        idInspeccion = getArguments().getInt(ID_INSPECCION, 0);

        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);


        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), FormularioInspeccionNuevaFragment, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        Log.d("TAG", "year: " + year);
        Log.d("TAG", "month: " + month);
        Log.d("TAG", "day: " + day);

        LocalDatabase db = new LocalDatabase(getContext());
        db.guardaFechaResidencia(idInspeccion, year, month + 1, day);
    }
}
