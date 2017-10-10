package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.content.Context;
import android.view.View;
import android.widget.RadioButton;

import com.bumptech.glide.Glide;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by moe on 10-10-17.
 */

public class Validaciones {

    public Validaciones() {
    }

    public boolean validaFotos(Context context, RadioButton radioButton, CircleImageView preview, int idInspeccion) {

        LocalDatabase db = new LocalDatabase(context);

        switch (radioButton.getId()) {
            case R.id.usoImanesPositivoRb:

                if (db.getFotos(idInspeccion).getUsoImanes1() == null) {
                    if (radioButton.isChecked()) {
                        preview.setVisibility(View.VISIBLE);
                        Glide.with(context).load("").error(R.drawable.ic_error_outline).into(preview);
                        return true;
                    } else {
                        return false;
                    }
                }
        }
        return true;
    }
}
