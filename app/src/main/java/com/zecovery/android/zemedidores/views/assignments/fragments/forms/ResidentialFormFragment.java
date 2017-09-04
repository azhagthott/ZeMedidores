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
import com.zecovery.android.zemedidores.models.Residente;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;

public class ResidentialFormFragment extends Fragment implements View.OnClickListener, SaveResidentialForm {

    private Button saveButton;
    private EditText residentName;
    private EditText residentRut;
    private EditText residentPhone;
    private EditText residentEmail;
    private EditText residentDate;
    private TextView polygonTextView;

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

        residentName = view.findViewById(R.id.residentName);
        residentRut = view.findViewById(R.id.residentRut);
        residentPhone = view.findViewById(R.id.residentPhone);
        residentEmail = view.findViewById(R.id.residentEmail);
        residentDate = view.findViewById(R.id.residentDate);
        polygonTextView = view.findViewById(R.id.polygonTextView);

        if (residentName.getText().length() == 0) {
            residentName.setError("Debe ingresar el nombre del residente");
        }
        if (residentRut.getText().length() == 0) {
            residentRut.setError("Debe ingresar el RUT del residente");
        }

        if (residentPhone.getText().length() == 0) {
            residentPhone.setError("Debe ingresar telefono de contacto del residente");
        }

        if (residentDate.getText().length() == 0) {
            residentDate.setError("Debe ingresar fecha desde que habita la propiedad");
        }

        token = getArguments().getInt(ID_ASSIGNMENT, 0);
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

        Residente resident = new Residente();
        resident.setNombre(residentName.getText().toString());
        resident.setRut(residentRut.getText().toString());
        resident.setTelefono(residentPhone.getText().toString());
        resident.setEmail(residentEmail.getText().toString());
        resident.setFecha(residentDate.getText().toString());

        new GuardaDatosResidente(this, getContext()).guardaDatos(resident, token);
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