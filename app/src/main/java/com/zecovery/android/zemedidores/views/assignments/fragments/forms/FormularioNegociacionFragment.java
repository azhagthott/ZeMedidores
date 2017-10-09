package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.network.NegociacionCallback;
import com.zecovery.android.zemedidores.views.MainActivity;

import java.util.ArrayList;
import java.util.List;

import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION_FORMULARIO_NEGOCIACION;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_1;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_2;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_3;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_4;
import static com.zecovery.android.zemedidores.data.Constant.SELECCIONAR_OPCION;

public class FormularioNegociacionFragment extends Fragment implements NegociacionCallback, View.OnClickListener {

    private TextView socialRiskTextView;
    private TextView totalDebtTextView;
    private TextView arrangementTextView;
    private EditText commentEditText;

    private RadioButton negotiationAcceptedRadioButton;
    private RadioButton negotiationRejectedRadioButton;

    private Spinner paymentMethodSpinner;

    private ImageButton paymentMethodImageButton;
    private ImageButton personalIdImageButton;

    private Button endInspectionButton;

    private LocalDatabase db;

    private int token;


    public FormularioNegociacionFragment() {
    }

    public FormularioNegociacionFragment newInstance(int token) {

        FormularioNegociacionFragment negotiationFragment = new FormularioNegociacionFragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION_FORMULARIO_NEGOCIACION, token);
        negotiationFragment.setArguments(args);
        return negotiationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_negociacion, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        token = getArguments().getInt(ID_INSPECCION_FORMULARIO_NEGOCIACION);
        findViews(view);
        db = new LocalDatabase(getContext());

        Log.d("TAG", "FormularioNegociacionFragment");

        List<String> paymentMethod = new ArrayList<>();

        paymentMethod.add(SELECCIONAR_OPCION);
        paymentMethod.add(PAYMENT_METHOD_1);
        paymentMethod.add(PAYMENT_METHOD_2);
        paymentMethod.add(PAYMENT_METHOD_3);
        paymentMethod.add(PAYMENT_METHOD_4);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, paymentMethod);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        paymentMethodSpinner.setAdapter(adapter);

        endInspectionButton.setOnClickListener(this);
    }

    private void findViews(View view) {
        socialRiskTextView = view.findViewById(R.id.socialRiskTextView);
        totalDebtTextView = view.findViewById(R.id.totalDebtTextView);
        arrangementTextView = view.findViewById(R.id.arrangementTextView);
        commentEditText = view.findViewById(R.id.commentEditText);
        negotiationAcceptedRadioButton = view.findViewById(R.id.negotiationAcceptedRadioButton);
        negotiationRejectedRadioButton = view.findViewById(R.id.negotiationRejectedRadioButton);
        paymentMethodSpinner = view.findViewById(R.id.paymentMethodSpinner);
        paymentMethodImageButton = view.findViewById(R.id.paymentMethodImageButton);
        personalIdImageButton = view.findViewById(R.id.personalIdImageButton);
        endInspectionButton = view.findViewById(R.id.endInspectionButton);
    }

    @Override
    public void guardaFormularioNegociacion() {
        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void errorFormularioNegociacion() {
        Toast.makeText(getContext(), "No se pudo guarda la informacion", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {

        Log.d("FormularioNegociacionFragment", "onClick");

        String negotiationAccepted;
        String paymentMethod;

        if (negotiationAcceptedRadioButton.isChecked()) {
            negotiationAccepted = "SI";
        } else if (negotiationRejectedRadioButton.isChecked()) {
            negotiationAccepted = "NO";
        } else {
            negotiationAccepted = "-";
        }

        paymentMethod = paymentMethodSpinner.getSelectedItem().toString();


    }
}
