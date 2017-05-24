package com.zecovery.android.zemedidores.views.intro;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.paolorotolo.appintro.AppIntro2;
import com.zecovery.android.zemedidores.R;

public class IntroActivity extends AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

}
