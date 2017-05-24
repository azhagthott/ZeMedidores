package com.zecovery.android.zemedidores.views.assignments;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.partials.InputListener;
import com.zecovery.android.zemedidores.views.assignments.partials.TextInput;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class AssignmentDetailActivity extends AppCompatActivity implements VerticalStepperForm, InputListener {

    private VerticalStepperFormLayout verticalStepperForm;
    private EditText name;
    private EditText email;
    private EditText phoneNumber;
    private TextInput nameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String[] mySteps = {"Nombre", "Correl", "Phone Number"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .showVerticalLineWhenStepsAreCollapsed(true)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();
    }


    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;

        switch (stepNumber) {
            case 0:
                nameInput = new TextInput(this, "nombre");
                nameInput.setListener(this);
                view = nameInput;
                break;
            case 1:
                view = createEmailStep();
                break;
            case 2:
                view = createPhoneNumberStep();
                break;
        }
        return view;

    }

    private View createNameStep() {
        name = new EditText(this);
        name.setSingleLine(true);
        name.setHint("Name");
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 3) {
                    verticalStepperForm.setActiveStepAsCompleted();
                }
            }
        });
        return name;
    }

    private View createEmailStep() {
        email = new EditText(this);
        email.setSingleLine(true);
        email.setHint("Email");
        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                verticalStepperForm.goToNextStep();
                return false;
            }
        });

        return email;
    }

    private View createPhoneNumberStep() {
        phoneNumber = new EditText(this);
        phoneNumber.setSingleLine(true);
        phoneNumber.setHint("Phone number");
        phoneNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                verticalStepperForm.goToNextStep();
                return false;
            }
        });

        return phoneNumber;
    }


    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case 0:
                if ((boolean) nameInput.getTag()) {
                    verticalStepperForm.setActiveStepAsCompleted();
                } else {
                    verticalStepperForm.setActiveStepAsUncompleted();
                }
                break;
            case 1:
                checkEmail();
                break;
            case 2:

                checkPhoneNumber();
                break;
        }
    }

    private void checkEmail() {
        if (email.length() >= 3 && email.length() <= 40) {
            verticalStepperForm.setActiveStepAsCompleted();
        }
    }

    private void checkPhoneNumber() {
        if (phoneNumber.length() >= 3 && phoneNumber.length() <= 40) {
            verticalStepperForm.setActiveStepAsCompleted();
        }
    }

    @Override
    public void sendData() {
        //sendData
    }

    @Override
    public void isValid() {
        verticalStepperForm.setActiveStepAsCompleted();
    }

    @Override
    public void notValid(String error) {
        verticalStepperForm.setActiveStepAsUncompleted(error);
    }
}
