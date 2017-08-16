package com.zecovery.android.zemedidores.views.assignments.fragments.executetest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_PHOTO;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;

public class PhotosTestFragment extends Fragment implements View.OnClickListener, UploadPhotosCallback,
        BrokenMeterCallback, MeterLocationCallback {

    private ImageButton photoMeterReading;
    private ImageButton photoMeterNumber;
    private ImageButton photoMeterPanoramic;
    private ImageButton photoPropertyNumber;
    private ImageButton photoFrontageProperty;
    private ImageButton brokenMeterPhoto;
    private RadioButton positiveRadioButton;
    private RadioButton negativeRadioButton;

    private Button continueTestButton;
    private LinearLayout brokenMeterLinearLayout;
    private LinearLayout mandatoryPhotosLinearLayout;

    private EditText meterLocationEditText;
    private EditText brokenMeterCommentEditText;

    private MagicalCamera magicalCamera;

    private TokenListener callback;
    private String token;

    private String photoName = "";
    private String remoteFolder = "";
    private String localPhotoName = "";


    public PhotosTestFragment() {
    }

    public PhotosTestFragment newInstance(String pushKey) {

        PhotosTestFragment photosTestFragment = new PhotosTestFragment();
        Bundle args = new Bundle();
        args.putString(ID_ASSIGNMENT_PHOTO, pushKey);
        photosTestFragment.setArguments(args);
        return photosTestFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photos_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        token = getArguments().getString(ID_ASSIGNMENT_PHOTO);
        findViews(view);

        continueTestButton.setOnClickListener(this);

        brokenMeterLinearLayout.setVisibility(View.GONE);
        mandatoryPhotosLinearLayout.setVisibility(View.GONE);

        positiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    brokenMeterLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    brokenMeterLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        negativeRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mandatoryPhotosLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    mandatoryPhotosLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        photoMeterReading.setOnClickListener(this);
        brokenMeterLinearLayout.setOnClickListener(this);
        photoMeterNumber.setOnClickListener(this);
        photoMeterPanoramic.setOnClickListener(this);
        photoPropertyNumber.setOnClickListener(this);
        photoFrontageProperty.setOnClickListener(this);
        brokenMeterPhoto.setOnClickListener(this);
    }

    private void findViews(View view) {
        photoMeterReading = view.findViewById(R.id.photoMeterReading);
        photoMeterNumber = view.findViewById(R.id.photoMeterNumber);
        photoMeterPanoramic = view.findViewById(R.id.photoMeterPanoramic);
        photoPropertyNumber = view.findViewById(R.id.photoPropertyNumber);
        photoFrontageProperty = view.findViewById(R.id.photoFrontageProperty);
        brokenMeterPhoto = view.findViewById(R.id.brokenMeterPhoto);
        positiveRadioButton = view.findViewById(R.id.positiveRadioButton);
        negativeRadioButton = view.findViewById(R.id.negativeRadioButton);
        brokenMeterLinearLayout = view.findViewById(R.id.brokenMeterLinearLayout);
        mandatoryPhotosLinearLayout = view.findViewById(R.id.mandatoryPhotosLinearLayout);
        meterLocationEditText = view.findViewById(R.id.meterLocationEditText);
        brokenMeterCommentEditText = view.findViewById(R.id.brokenMeterCommentEditText);
        continueTestButton = view.findViewById(R.id.continueTestButton);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                callback = (TokenListener) activity;
            } catch (Exception e) {
                Log.d("TAG", e.toString());
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
            }
        }
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (negativeRadioButton.isChecked()) {
            switch (id) {

                case R.id.photoMeterReading:
                    photoName = "lectura_medidor";
                    remoteFolder = "lectura_medidor";
                    callCamera(photoName);
                    break;
                case R.id.photoMeterNumber:
                    photoName = "numero_medidor";
                    remoteFolder = "numero_medidor";
                    callCamera(photoName);
                    break;
                case R.id.photoMeterPanoramic:
                    photoName = "panoramica_medidor";
                    remoteFolder = "panoramica_medidor";
                    callCamera(photoName);
                    break;
                case R.id.photoPropertyNumber:
                    photoName = "numero_propiedad";
                    remoteFolder = "numero_propiedad";
                    callCamera(photoName);
                    break;
                case R.id.photoFrontageProperty:
                    photoName = "fachada_propiedad";
                    remoteFolder = "fachada_propiedad";
                    callCamera(photoName);
                    break;

                case R.id.continueTestButton:

                    String failureComment = brokenMeterCommentEditText.getText().toString();
                    String meterLocation = meterLocationEditText.getText().toString();

                    if (positiveRadioButton.isChecked() && !failureComment.equals("")) {
                        new BrokenMeter(this).inform(token, failureComment, meterLocation, localPhotoName);
                    } else {
                        new MeterLocation(this).saveLocation(token, meterLocation);
                    }

                    callback.tokenToExecuteTestPart1(token);
                    break;
            }
        } else {
            switch (id) {

                case R.id.brokenMeterPhoto:
                    photoName = "medidor_descompuesto";
                    remoteFolder = "medidor_descompuesto";
                    callCamera(photoName);
                    break;

                case R.id.continueTestButton:
                    String failureComment = brokenMeterCommentEditText.getText().toString();
                    String meterLocation = meterLocationEditText.getText().toString();

                    if (positiveRadioButton.isChecked() && !failureComment.equals("")) {
                        new BrokenMeter(this).inform(token, failureComment, meterLocation, localPhotoName);
                    } else {
                        new MeterLocation(this).saveLocation(token, meterLocation);
                    }

                    callback.tokenToExecuteTestPart1(token);
                    break;
            }
        }
    }

    private Intent callCamera(String photoName) {

        this.photoName = photoName;

        String[] permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        MagicalPermissions magicalPermissions = new MagicalPermissions(getActivity(), permissions);
        magicalCamera = new MagicalCamera(getActivity(), RESIZE_PHOTO_PIXELS_PERCENTAGE, magicalPermissions);
        magicalCamera.takeFragmentPhoto(this);

        return new Intent();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        magicalCamera.resultPhoto(requestCode, resultCode, data);

        try {
            String localPath = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), photoName, token + "/" + FOLDER_ZE_MEDIDORES, MagicalCamera.PNG, true);
            String localPathParts[] = localPath.split("/");
            localPhotoName = localPathParts[7];

            new UploadPhotos(this).upload(localPhotoName, localPath, remoteFolder);

        } catch (Exception e) {
            Log.d("TAG", "onActivityResult: " + e);
        }
    }

    @Override
    public void photoUploaded() {
        //Toast.makeText(getContext(), getResources().getString(R.string.photo_uploaded), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void uploadingPhotoError() {
        //Toast.makeText(getContext(), getResources().getString(R.string.photo_no_uploaded), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void informBrokenMeter() {
        //Toast.makeText(getContext(), getResources().getString(R.string.photo_uploaded), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void informBrokenMeterError() {
        //Toast.makeText(getContext(), getResources().getString(R.string.photo_no_uploaded), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveMeterLocation() {
    }

    @Override
    public void errorSavingMeterLocation() {
    }
}