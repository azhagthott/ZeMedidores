package com.zecovery.android.zemedidores.views.Assignments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.login.LoginActivity;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class AssignmentDetailActivity extends AppCompatActivity implements VerticalStepperForm {

    private VerticalStepperFormLayout verticalStepperForm;
    private EditText name;
    private EditText email;
    private EditText phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        String[] mySteps = {"Name", "Email", "Phone Number"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimaryDark);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_sign_out) {
            signOut();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(AssignmentDetailActivity.this, LoginActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;

        switch (stepNumber) {
            case 0:
                view = createNameStep();
                break;
            case 1:
                view = createEmailStep();
                break;
            case 2:
                view = createPhoneNumerStep();
                break;
        }
        return view;

    }

    private View createNameStep() {
        name = new EditText(this);
        name.setSingleLine(true);
        name.setHint("Name");
        return name;
    }

    private View createEmailStep() {
        email = new EditText(this);
        email.setSingleLine(true);
        email.setHint("Email");
        return email;
    }

    private View createPhoneNumerStep() {
        phoneNumber = new EditText(this);
        phoneNumber.setSingleLine(true);
        phoneNumber.setHint("Phone number");
        return phoneNumber;
    }


    @Override
    public void onStepOpening(int stepNumber) {

        switch (stepNumber) {
            case 0:
                checkName();
                break;
            case 1:
                checkEmail();
                break;
            case 2:
                checkPhoneNumber();
                break;
        }
    }

    private void checkName() {
        if (name.length() >= 3 && name.length() <= 40) {
            verticalStepperForm.setActiveStepAsCompleted();
        }
    }

    private void checkEmail() {
        if (name.length() >= 3 && name.length() <= 40) {
            verticalStepperForm.setActiveStepAsCompleted();
        }
    }

    private void checkPhoneNumber() {
        if (name.length() >= 3 && name.length() <= 40) {
            verticalStepperForm.setActiveStepAsCompleted();
        }
    }

    @Override
    public void sendData() {
        //sendData
    }
}
