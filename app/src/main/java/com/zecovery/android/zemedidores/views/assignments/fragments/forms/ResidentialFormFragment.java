package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Residente;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;

public class ResidentialFormFragment extends Fragment implements View.OnClickListener, SaveResidentialForm {

    private Button saveButton;
    private EditText nombreResidente;
    private EditText rutResidente;
    private EditText telefonoResidente;
    private EditText emailResidente;
    private EditText fechaResidente;
    private TextView poligonoSocialResidente;

    private LocalDatabase db;

    private int token;
    private TokenListener tokenCallback;

    public ResidentialFormFragment() {

    }

    public ResidentialFormFragment newInstance(int token) {
        ResidentialFormFragment residentialFormFragment = new ResidentialFormFragment();
        Bundle args = new Bundle();
        args.putInt(ID_ASSIGNMENT, token);
        residentialFormFragment.setArguments(args);
        return residentialFormFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_residential_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveButton = view.findViewById(R.id.saveButton);
        saveButton.setOnClickListener(this);

        nombreResidente = view.findViewById(R.id.nombreResidente);
        rutResidente = view.findViewById(R.id.rutResidente);
        telefonoResidente = view.findViewById(R.id.telefonoResidente);
        emailResidente = view.findViewById(R.id.emailResidente);
        fechaResidente = view.findViewById(R.id.fechaResidente);
        poligonoSocialResidente = view.findViewById(R.id.poligonoSocialResidente);
        token = getArguments().getInt(ID_ASSIGNMENT, 0);

        db = new LocalDatabase(getContext());

        if (db.getDatosResidente(token).getNombreResidente() != null) {
            nombreResidente.setText(db.getDatosResidente(token).getNombreResidente());
        }

        if (db.getDatosResidente(token).getRutResidente() != null) {
            rutResidente.setText(db.getDatosResidente(token).getRutResidente());
        }

        if (db.getDatosResidente(token).getTelefonoResidente() != null) {
            telefonoResidente.setText(db.getDatosResidente(token).getTelefonoResidente());
        }

        if (db.getDatosResidente(token).getEmailResidente() != null) {
            emailResidente.setText(db.getDatosResidente(token).getEmailResidente());
        }

        if (db.getDatosResidente(token).getFechaResidente() != null) {
            fechaResidente.setText(db.getDatosResidente(token).getEmailResidente());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                tokenCallback = (TokenListener) activity;
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

        if (fechaResidente.getText().length() == 0) {
            errorFecha = true;
            fechaResidente.setError("Debe ingresar fecha desde que habita la propiedad");
        } else {
            errorFecha = false;
        }

        if (!errorNombre && !errorRut && !errorTelefono && !errorFecha) {
            Residente resident = new Residente();
            resident.setNombreResidente(nombreResidente.getText().toString());
            resident.setRutResidente(rutResidente.getText().toString());
            resident.setTelefonoResidente(telefonoResidente.getText().toString());
            resident.setEmailResidente(emailResidente.getText().toString());
            resident.setFechaResidente(fechaResidente.getText().toString());
            new GuardaDatosResidente(this, getContext()).guardaDatos(resident, token);
        } else {
            Toast.makeText(getContext(), "Complete los campos obligatorios", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void save() {
        tokenCallback.tokenToPhotoTest(token);
    }

    @Override
    public void error() {
        Toast.makeText(getContext(), "No se pudo guardar la informacion", Toast.LENGTH_SHORT).show();
    }
}