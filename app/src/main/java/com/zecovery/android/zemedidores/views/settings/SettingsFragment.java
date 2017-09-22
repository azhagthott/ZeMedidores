package com.zecovery.android.zemedidores.views.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.zecovery.android.zemedidores.R;

/**
 * Created by moe on 22-09-17.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
