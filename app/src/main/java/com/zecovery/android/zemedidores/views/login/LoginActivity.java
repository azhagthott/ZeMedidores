package com.zecovery.android.zemedidores.views.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.ResultCodes;
import com.google.firebase.FirebaseApp;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.views.turno.TurnoActivity;

import java.util.Collections;

public class LoginActivity extends AppCompatActivity implements
        LoginValidationCallback, LoginAuthorizationCallback {

    private static final int RC_SIGN_IN = 1111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FirebaseApp.initializeApp(this);

        new LoginValidation(this).init();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == ResultCodes.OK) {
                FirebaseCrash.log("Login iniciaTurnoOk: " + new CurrentUser().uid());
                logged();

            } else {
                if (response == null) {
                    FirebaseCrash.log("pressed at login: " + new CurrentUser().name());
                } else {
                    if (response.getErrorCode() == ErrorCodes.NO_NETWORK) {
                        FirebaseCrash.report(new Exception("Attempt to login without connection"));
                    }

                    if (response.getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                        FirebaseCrash.report(new Exception("Unknown iniciaTurnoError at login / sign in failed"));
                    }
                }
            }
        }
    }

    @Override
    public void logged() {
        new LoginAuthorization(this).init();
    }

    @Override
    public void signIn() {
        /*startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                        .setTheme(R.style.AppTheme_Login)
                        .build(),
                RC_SIGN_IN);
*/
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(
                                Collections.singletonList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                        .setTheme(R.style.AppTheme_Login)
                        .setIsSmartLockEnabled(true, true)
                        .build(),
                RC_SIGN_IN);
    }

    @Override
    public void autorizado() {
        startActivity(new Intent(this, TurnoActivity.class));
        finish();
    }

    @Override
    public void noAutorizado() {
        startActivity(new Intent(this, NoAutorizadoActivity.class));
        finish();
    }
}