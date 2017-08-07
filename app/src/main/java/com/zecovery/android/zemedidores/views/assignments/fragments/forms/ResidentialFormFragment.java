package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.Nodes;
import com.zecovery.android.zemedidores.views.assignments.fragments.PushKeyListener;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT;
import static com.zecovery.android.zemedidores.data.Constant.SOCIAL_POLYGON;

public class ResidentialFormFragment extends Fragment implements View.OnClickListener,
        ValueEventListener {

    private EditText residentName;
    private EditText residentRut;
    private EditText residentPhone;
    private EditText residentEmail;
    private EditText residentDate;
    private TextView polygonTextView;

    private String pushKey;
    private PushKeyListener callback;

    public ResidentialFormFragment() {

    }

    public ResidentialFormFragment newInstance(String pushKey) {
        ResidentialFormFragment residentialFormFragment = new ResidentialFormFragment();
        Bundle args = new Bundle();
        args.putString(ID_ASSIGNMENT, pushKey);
        residentialFormFragment.setArguments(args);
        return residentialFormFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_residential_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button saveButton = (Button) view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(this);

        residentName = (EditText) view.findViewById(R.id.residentName);
        residentRut = (EditText) view.findViewById(R.id.residentRut);
        residentPhone = (EditText) view.findViewById(R.id.residentPhone);
        residentEmail = (EditText) view.findViewById(R.id.residentEmail);
        residentDate = (EditText) view.findViewById(R.id.residentDate);
        polygonTextView = (TextView) view.findViewById(R.id.polygonTextView);

        pushKey = getArguments().getString(ID_ASSIGNMENT);

        new Nodes().preLoadedData(pushKey).addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {

        residentName.setText(dataSnapshot.child("name_resident").getValue().toString());
        residentRut.setText(dataSnapshot.child("rut").getValue().toString());
        residentPhone.setText(dataSnapshot.child("telephone").getValue().toString());
        residentEmail.setText(dataSnapshot.child("email_resident").getValue().toString());
        residentDate.setText(dataSnapshot.child("date_resident").getValue().toString());

        String polygon = dataSnapshot.child("polygon").getValue().toString();

        if (polygon.equals(SOCIAL_POLYGON)) {
            polygonTextView.setVisibility(View.VISIBLE);
        } else {
            polygonTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                callback = (PushKeyListener) activity;
            } catch (Exception e) {
                Log.d("TAG", e.toString());
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Toast.makeText(getContext(), R.string.rejection_error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        callback.pushKeyToPhotoTest(pushKey);
    }
}
