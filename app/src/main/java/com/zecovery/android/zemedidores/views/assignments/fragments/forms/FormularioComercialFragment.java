package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zecovery.android.zemedidores.R;

public class FormularioComercialFragment extends Fragment {

    public FormularioComercialFragment() {

    }

    public FormularioComercialFragment newInstance() {
        return new FormularioComercialFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", "FormularioComercialFragment onCreateView");
        return inflater.inflate(R.layout.fragment_formulario_comercial, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG", "FormularioComercialFragment onViewCreated");
    }
}