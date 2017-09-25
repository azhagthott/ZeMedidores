package com.zecovery.android.zemedidores.views.assignments.fragments.forms;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class FormularioInspeccionNuevaFragment extends Fragment implements View.OnClickListener,
        FormularioResidencialCallback {

    private Button saveButton;
    private EditText nuevaInspeccionDireccion;
    private EditText nuevaInspeccionNumeroCliente;
    private EditText nuevoResidenteNombre;
    private EditText nuevoResidenteRut;
    private EditText nuevoResidenteTelefono;
    private EditText nuevoResidenteEmail;
    private TextView fechaResidente;
    private ImageButton setFechaResidenteButton;

    private DatePickerDialog.OnDateSetListener onDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            int realMonth = month + 1;
            fechaResidente.setText(year + "-" + realMonth + "-" + day);
        }
    };

    private IdInspeccionListener idInspeccionCallback;
    private LocalDatabase db;
    private int idInspeccion;

    public FormularioInspeccionNuevaFragment() {
    }

    public FormularioInspeccionNuevaFragment newInstance(int idInspeccion) {
        FormularioInspeccionNuevaFragment inspeccionNuevaFragment = new FormularioInspeccionNuevaFragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION, idInspeccion);
        inspeccionNuevaFragment.setArguments(args);
        return inspeccionNuevaFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_inspeccion_nueva, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        findViews(view);

        idInspeccion = getArguments().getInt(ID_INSPECCION, 0);

        db = new LocalDatabase(getContext());

        saveButton.setOnClickListener(this);

        setFechaResidenteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });

        if (db.getFechaResidencia(idInspeccion) != null) {
            fechaResidente.setText(db.getFechaResidencia(idInspeccion));
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                idInspeccionCallback = (IdInspeccionListener) activity;
            } catch (Exception e) {
                Log.d("TAG", e.toString());
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    private void findViews(View view) {
        nuevaInspeccionDireccion = view.findViewById(R.id.nuevaInspeccionDireccion);
        nuevaInspeccionNumeroCliente = view.findViewById(R.id.nuevaInspeccionNumeroCliente);
        nuevoResidenteNombre = view.findViewById(R.id.nuevoResidenteNombre);
        nuevoResidenteRut = view.findViewById(R.id.nuevoResidenteRut);
        nuevoResidenteTelefono = view.findViewById(R.id.nuevoResidenteTelefono);
        nuevoResidenteEmail = view.findViewById(R.id.nuevoResidenteEmail);
        saveButton = view.findViewById(R.id.saveButton);
        setFechaResidenteButton = view.findViewById(R.id.setFechaResidenteButton);
        fechaResidente = view.findViewById(R.id.fechaResidente);
    }

    @Override
    public void onClick(View view) {

        boolean errorDireccion;
        boolean errorNumMedidor;
        boolean errorNombre;
        boolean errorRut;
        boolean errorTelefono;
        boolean errorFecha;


        if (nuevaInspeccionDireccion.getText().length() == 0) {
            errorDireccion = true;
            nuevaInspeccionDireccion.setError("Debe ingresar el nombre del residente");
        } else {
            errorDireccion = false;
        }
        if (nuevaInspeccionNumeroCliente.getText().length() == 0) {
            errorNumMedidor = true;
            nuevaInspeccionNumeroCliente.setError("Debe ingresar el nombre del residente");
        } else {
            errorNumMedidor = false;
        }


        if (nuevoResidenteNombre.getText().length() == 0) {
            errorNombre = true;
            nuevoResidenteNombre.setError("Debe ingresar el nombre del residente");
        } else {
            errorNombre = false;
        }
        if (nuevoResidenteRut.getText().length() == 0) {
            errorRut = true;
            nuevoResidenteRut.setError("Debe ingresar el RUT del residente");
        } else {
            errorRut = false;
        }
        if (nuevoResidenteTelefono.getText().length() == 0) {
            errorTelefono = true;
            nuevoResidenteTelefono.setError("Debe ingresar telefono de contacto del residente");
        } else {
            errorTelefono = false;
        }
        if (fechaResidente.getText().length() == 0) {
            errorFecha = true;
            fechaResidente.setError("Debe ingresar fecha desde cuando habita");
        } else {
            errorFecha = false;
        }

        if (!errorDireccion && !errorNumMedidor && !errorNombre && !errorRut && !errorTelefono && !errorFecha) {

            Residente resident = new Residente();

            resident.setDireccionResidente(nuevaInspeccionDireccion.getText().toString());
            resident.setNumeroCliente(nuevaInspeccionNumeroCliente.getText().toString());
            resident.setNombreResidente(nuevoResidenteNombre.getText().toString());
            resident.setRutResidente(nuevoResidenteRut.getText().toString());
            resident.setTelefonoResidente(nuevoResidenteTelefono.getText().toString());
            resident.setEmailResidente(nuevoResidenteEmail.getText().toString());
            resident.setFechaResidente(fechaResidente.getText().toString());

            new GuardaDatosResidente(this, getContext()).guardaDatos(resident, idInspeccion);

        } else {
            Toast.makeText(getContext(), "Complete los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void guardaDatos() {
        idInspeccionCallback.IdInspeccionFotoTest(idInspeccion);
    }

    @Override
    public void errorGuardaDatos() {
        FirebaseCrash.log("Error guardando datos residenciales");
        Toast.makeText(getContext(), "No se pudo guardar la informacion", Toast.LENGTH_SHORT).show();
    }

    private void showDatePicker() {
        DatePickerFragment date = new DatePickerFragment();
        final Calendar c = Calendar.getInstance();
        Bundle args = new Bundle();
        args.putInt("year", c.get(Calendar.YEAR));
        args.putInt("month", c.get(Calendar.MONTH));
        args.putInt("day", c.get(Calendar.DAY_OF_MONTH));
        date.setArguments(args);
        date.setCallBack(onDate);
        date.show(getFragmentManager(), "Date Picker");
    }
}
