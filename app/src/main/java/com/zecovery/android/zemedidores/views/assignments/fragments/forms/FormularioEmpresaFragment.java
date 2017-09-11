package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zecovery.android.zemedidores.R;

public class FormularioEmpresaFragment extends Fragment {

    public FormularioEmpresaFragment() {

    }

    public FormularioEmpresaFragment newInstance() {
        return new FormularioEmpresaFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("TAG", "FormularioEmpresaFragment onCreateView");
        return inflater.inflate(R.layout.fragment_formulario_empresa, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("TAG", "FormularioEmpresaFragment onViewCreated");
    }
}