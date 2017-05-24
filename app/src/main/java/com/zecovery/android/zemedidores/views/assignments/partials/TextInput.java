package com.zecovery.android.zemedidores.views.assignments.partials;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.zecovery.android.zemedidores.R;

/**
 * Created by fbarrios80 on 22-05-17.
 */

public class TextInput extends android.support.v7.widget.AppCompatEditText {

    public TextInput(Context context, String hint) {
        super(context);
        setView();
        setHint(hint);
        setTag(false);
    }

    private void setView() {
        setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                )
        );
        int padding = (int) getContext().getResources().getDimension(R.dimen.normal_padding);
        setPadding(padding, padding, padding, padding);
    }

    public void setListener(final InputListener listener) {
        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().trim().length() > 3) {
                    listener.isValid();
                    setTag(true);
                } else {
                    listener.notValid("más largo");
                    setTag(false);
                }
            }
        });
    }
}
