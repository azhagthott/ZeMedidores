package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import com.google.firebase.auth.FirebaseAuth;
import com.zecovery.android.zemedidores.data.CurrentUser;
import com.zecovery.android.zemedidores.data.Nodes;

/**
 * Created by fbarrios80 on 03-07-17.
 */

public class SendTestResults {

    private TestResultsCallback callback;

    public SendTestResults(TestResultsCallback callback) {
        this.callback = callback;
    }

    public void save(String token) {

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            if (token != null) {

                Nodes nodes = new Nodes();
                /*nodes.testResults(new CurrentUser().uid()).child(pushKey).child("test1").setValue(test1);
                nodes.testResults(new CurrentUser().uid()).child(pushKey).child("test2").setValue(test2);
                nodes.testResults(new CurrentUser().uid()).child(pushKey).child("test3").setValue(test3);*/
                callback.resultsSaved();
            } else {
                callback.resultsNotSaved();
            }
        }
    }
}
