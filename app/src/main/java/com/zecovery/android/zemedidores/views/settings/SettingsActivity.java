package com.zecovery.android.zemedidores.views.settings;

import android.os.Bundle;
import android.preference.PreferenceActivity;

/**
 * Created by moe on 22-09-17.
 */

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }
}
