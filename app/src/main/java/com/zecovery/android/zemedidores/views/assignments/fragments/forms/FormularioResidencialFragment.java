package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Residente;
import com.zecovery.android.zemedidores.views.assignments.fragments.IdInspeccionListener;

import java.util.Calendar;

import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION;

public class FormularioResidencialFragment extends Fragment implements View.OnClickListener, GuardaDatosFormularioResidencial, DatePickerDialog.OnDateSetListener {

    private static final int REQUEST_DATE = 109;
    private Button saveButton;
    private EditText nombreResidente;
    private EditText rutResidente;
    private EditText telefonoResidente;
    private EditText emailResidente;
    private TextView fechaResidente;
    private TextView poligonoSocialResidente;
    private ImageButton setFechaResidenteButton;
    private LocalDatabase db;
    private int idInspeccion;
    private IdInspeccionListener tokenCallback;

    public FormularioResidencialFragment() {

    }

    public FormularioResidencialFragment newInstance(int idInspeccion) {
        FormularioResidencialFragment residentialFormFragment = new FormularioResidencialFragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION, idInspeccion);
        residentialFormFragment.setArguments(args);
        return residentialFormFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_residencial, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        idInspeccion = getArguments().getInt(ID_INSPECCION, 0);
        db = new LocalDatabase(getContext());

        findViews(view);

        Log.d("TAG", "onViewCreated: " + idInspeccion);


        saveButton.setOnClickListener(this);

        if (idInspeccion > 0) {
            if (db.getDatosResidente(idInspeccion).getNombreResidente() != null) {
                nombreResidente.setText(db.getDatosResidente(idInspeccion).getNombreResidente());
            }

            if (db.getDatosResidente(idInspeccion).getRutResidente() != null) {
                rutResidente.setText(db.getDatosResidente(idInspeccion).getRutResidente());
            }

            if (db.getDatosResidente(idInspeccion).getTelefonoResidente() != null) {
                telefonoResidente.setText(db.getDatosResidente(idInspeccion).getTelefonoResidente());
            }

            if (db.getDatosResidente(idInspeccion).getEmailResidente() != null) {
                emailResidente.setText(db.getDatosResidente(idInspeccion).getEmailResidente());
            }

            if (db.getDatosResidente(idInspeccion).getFechaResidente() != null) {
                fechaResidente.setText(db.getDatosResidente(idInspeccion).getFechaResidente());
            }

            setFechaResidenteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new DatePickerFragment().newInstance(idInspeccion).show(getFragmentManager(), "datePicker");

                }
            });
        }
    }

    private void findViews(View view) {
        saveButton = view.findViewById(R.id.saveButton);
        nombreResidente = view.findViewById(R.id.nombreResidente);
        rutResidente = view.findViewById(R.id.rutResidente);
        telefonoResidente = view.findViewById(R.id.telefonoResidente);
        emailResidente = view.findViewById(R.id.emailResidente);
        fechaResidente = view.findViewById(R.id.fechaResidente);
        poligonoSocialResidente = view.findViewById(R.id.poligonoSocialResidente);
        setFechaResidenteButton = view.findViewById(R.id.setFechaResidenteButton);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                tokenCallback = (IdInspeccionListener) activity;
            } catch (Exception e) {
                Log.d("TAG", e.toString());
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    @Override
    public void onClick(View v) {

        boolean errorNombre;
        boolean errorRut;
        boolean errorTelefono;
        boolean errorFecha;

        if (nombreResidente.getText().length() == 0) {
            errorNombre = true;
            nombreResidente.setError("Debe ingresar el nombre del residente");
        } else {
            errorNombre = false;
        }
        if (rutResidente.getText().length() == 0) {
            errorRut = true;
            rutResidente.setError("Debe ingresar el RUT del residente");
        } else {
            errorRut = false;
        }

        if (telefonoResidente.getText().length() == 0) {
            errorTelefono = true;
            telefonoResidente.setError("Debe ingresar telefono de contacto del residente");
        } else {
            errorTelefono = false;
        }

        /*if (fechaResidente.getText().length() == 0) {
            errorFecha = true;
            fechaResidente.setError("Debe ingresar fecha desde que habita la propiedad");
        } else {
            errorFecha = false;
        }*/

        //&& !errorFecha
        if (!errorNombre && !errorRut && !errorTelefono) {
            Residente resident = new Residente();
            resident.setNombreResidente(nombreResidente.getText().toString());
            resident.setRutResidente(rutResidente.getText().toString());
            resident.setTelefonoResidente(telefonoResidente.getText().toString());
            resident.setEmailResidente(emailResidente.getText().toString());
            resident.setFechaResidente(fechaResidente.getText().toString());
            new GuardaDatosResidente(this, getContext()).guardaDatos(resident, idInspeccion);
        } else {
            Toast.makeText(getContext(), "Complete los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void guardaDatos() {
        tokenCallback.IdInspeccionFotoTest(idInspeccion);
    }

    @Override
    public void errorGuardaDatos() {
        FirebaseCrash.log("Error guardando datos residenciales");
        Toast.makeText(getContext(), "No se pudo guardar la informacion", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {


            Log.d("TAG", "onActivityResult: " + data.getStringExtra("DATE"));


        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

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
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) FormularioResidencialFragment.instantiate(getContext(),
                    FormularioResidencialFragment.class.getCanonicalName()), year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            final int realMonth = month + 1;
            LocalDatabase db = new LocalDatabase(getContext());
            db.guardaFechaResidencia(idInspeccion, year, realMonth, day);
        }
    }
}
