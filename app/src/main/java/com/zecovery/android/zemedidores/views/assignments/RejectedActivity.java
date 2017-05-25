package com.zecovery.android.zemedidores.views.assignments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.partials.InputListener;
import com.zecovery.android.zemedidores.views.assignments.partials.TextInput;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class RejectedActivity extends AppCompatActivity implements VerticalStepperForm, InputListener {

    private VerticalStepperFormLayout rejectedVSF;
    private TextInput nameInput, emailInput, phoneNumberInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejected);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String[] mySteps = {"Nombre", "Correo Electronico", "Numero de telefono"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        rejectedVSF = (VerticalStepperFormLayout) findViewById(R.id.rejectedVSF);


        VerticalStepperFormLayout.Builder.newInstance(rejectedVSF, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .showVerticalLineWhenStepsAreCollapsed(true)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;

        switch (stepNumber) {
            case 0:
                nameInput = new TextInput(this, "Nombre residente");
                nameInput.setListener(this);
                view = nameInput;
                break;
            case 1:
                emailInput = new TextInput(this, "RUT");
                emailInput.setListener(this);
                view = emailInput;
                break;
            case 2:
                phoneNumberInput = new TextInput(this, "Telefono");
                phoneNumberInput.setListener(this);
                view = phoneNumberInput;
                break;
        }
        return view;

    }


    @Override
    public void onStepOpening(int stepNumber) {

    }

    @Override
    public void sendData() {

    }

    @Override
    public void isValid() {

    }

    @Override
    public void notValid(String error) {

    }
}
