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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.frosquivel.magicalcamera.MagicalCamera;
import com.frosquivel.magicalcamera.MagicalPermissions;
import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.Foto;
import com.zecovery.android.zemedidores.models.Medidor;
import com.zecovery.android.zemedidores.views.assignments.fragments.TokenListener;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_PHOTO;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;

public class PhotosTestFragment extends Fragment implements View.OnClickListener {

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

    private CircleImageView imagePreviewMedidorRoto;
    private CircleImageView imagePreviewLecturaMedidor;
    private CircleImageView imagePreviewNumeroMedidor;
    private CircleImageView imagePreviewPanoMedidor;
    private CircleImageView imagePreviewNumeroPropiedad;
    private CircleImageView imagePreviewFachadaPropiedad;

    private MagicalCamera magicalCamera;

    private TokenListener callback;
    private LocalDatabase db;

    private int token;
    private String photoName;
    private String localPath;

    public PhotosTestFragment() {
    }

    public PhotosTestFragment newInstance(int token) {

        PhotosTestFragment photosTestFragment = new PhotosTestFragment();
        Bundle args = new Bundle();
        args.putInt(ID_ASSIGNMENT_PHOTO, token);
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
        token = getArguments().getInt(ID_ASSIGNMENT_PHOTO);
        findViews(view);

        continueTestButton.setVisibility(View.GONE);
        continueTestButton.setOnClickListener(this);

        db = new LocalDatabase(getContext());

        brokenMeterLinearLayout.setVisibility(View.GONE);
        mandatoryPhotosLinearLayout.setVisibility(View.GONE);

        photoMeterReading.setOnClickListener(this);
        brokenMeterLinearLayout.setOnClickListener(this);
        photoMeterNumber.setOnClickListener(this);
        photoMeterPanoramic.setOnClickListener(this);
        photoPropertyNumber.setOnClickListener(this);
        photoFrontageProperty.setOnClickListener(this);
        brokenMeterPhoto.setOnClickListener(this);

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
        imagePreviewMedidorRoto = view.findViewById(R.id.imagePreviewMedidorRoto);
        imagePreviewLecturaMedidor = view.findViewById(R.id.imagePreviewLecturaMedidor);
        imagePreviewNumeroMedidor = view.findViewById(R.id.imagePreviewNumeroMedidor);
        imagePreviewPanoMedidor = view.findViewById(R.id.imagePreviewPanoMedidor);
        imagePreviewNumeroPropiedad = view.findViewById(R.id.imagePreviewNumeroPropiedad);
        imagePreviewFachadaPropiedad = view.findViewById(R.id.imagePreviewFachadaPropiedad);
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
        if (meterLocationEditText.getText().length() == 0) {
            meterLocationEditText.setError("Debe especificar laubicacion del medidor");
        }

        int id = v.getId();

        if (negativeRadioButton.isChecked()) {

            switch (id) {

                case R.id.photoMeterReading:
                    photoName = "lectura_medidor";
                    callCamera(photoName);
                    break;
                case R.id.photoMeterNumber:
                    photoName = "numero_medidor";
                    callCamera(photoName);
                    break;
                case R.id.photoMeterPanoramic:
                    photoName = "panoramica_medidor";
                    callCamera(photoName);
                    break;
                case R.id.photoPropertyNumber:
                    photoName = "numero_propiedad";
                    callCamera(photoName);
                    break;
                case R.id.photoFrontageProperty:
                    photoName = "fachada_propiedad";
                    callCamera(photoName);
                    break;

                case R.id.continueTestButton:
                    String meterLocation = meterLocationEditText.getText().toString();
                    Medidor medidor = new Medidor();
                    medidor.setUbicacion(meterLocation);
                    db.guardaDatosMedidor(medidor, token);
                    callback.tokenToExecuteTestPart1(token);
                    break;
            }

        } else if (positiveRadioButton.isChecked()) {

            switch (id) {

                case R.id.brokenMeterPhoto:

                    photoName = "medidor_descompuesto";
                    callCamera(photoName);
                    break;

                case R.id.continueTestButton:

                    String failureComment = brokenMeterCommentEditText.getText().toString();
                    String meterLocation = meterLocationEditText.getText().toString();

                    Medidor medidor = new Medidor();
                    medidor.setUbicacion(meterLocation);
                    medidor.setEstado("si");
                    medidor.setDescripcionFalla(failureComment);

                    db.guardaDatosMedidor(medidor, token);

                    callback.tokenToExecuteTestPart1(token);
                    break;
            }
        } else {
            continueTestButton.setVisibility(View.GONE);
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

            localPath = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), photoName, token + "/" + FOLDER_ZE_MEDIDORES, MagicalCamera.PNG, true);
            String localPathParts[] = localPath.split("/");
            //localPhotoName = localPathParts[7];

            Foto foto = new Foto();

            switch (photoName) {

                case "medidor_descompuesto":
                    foto.setFallaMedidor(localPath);
                    db.guardaFoto(foto, token);
                    imagePreviewMedidorRoto.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(token).getFallaMedidor()).into(imagePreviewMedidorRoto);
                    break;
                case "lectura_medidor":
                    foto.setLecturaMedidor(localPath);
                    db.guardaFoto(foto, token);
                    imagePreviewLecturaMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(token).getLecturaMedidor()).into(imagePreviewLecturaMedidor);
                    break;
                case "numero_medidor":
                    foto.setNumeroMedidor(localPath);
                    db.guardaFoto(foto, token);
                    imagePreviewNumeroMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(token).getNumeroMedidor()).into(imagePreviewNumeroMedidor);
                    break;
                case "panoramica_medidor":
                    foto.setPanoramicaMedidor(localPath);
                    db.guardaFoto(foto, token);
                    imagePreviewPanoMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(token).getPanoramicaMedidor()).into(imagePreviewPanoMedidor);
                    break;
                case "numero_propiedad":
                    foto.setNumeroPropiedad(localPath);
                    db.guardaFoto(foto, token);
                    imagePreviewNumeroPropiedad.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(token).getNumeroPropiedad()).into(imagePreviewNumeroPropiedad);
                    break;
                case "fachada_propiedad":
                    foto.setFachadaPropiedad(localPath);
                    db.guardaFoto(foto, token);
                    imagePreviewFachadaPropiedad.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(token).getFachadaPropiedad()).into(imagePreviewFachadaPropiedad);
                    break;
            }
        } catch (Exception e) {
            FirebaseCrash.log("Error: " + e);
        }

        if (positiveRadioButton.isChecked() && db.getFotos(token).getFallaMedidor() != null &&
                brokenMeterCommentEditText.getText().toString().trim().length() != 0 && meterLocationEditText.getText().toString().trim().length()!=0) {

            continueTestButton.setVisibility(View.VISIBLE);



        } else if (negativeRadioButton.isChecked() &&
                db.getFotos(token).getLecturaMedidor() != null &&
                db.getFotos(token).getNumeroMedidor() != null &&
                db.getFotos(token).getPanoramicaMedidor() != null &&
                db.getFotos(token).getNumeroPropiedad() != null &&
                db.getFotos(token).getFachadaPropiedad() != null &&
                brokenMeterCommentEditText.getText().toString().trim().length() != 0) {

            continueTestButton.setVisibility(View.VISIBLE);

        } else {
            Toast.makeText(getContext(), "Asegurese de sacar las fotos que corresponden", Toast.LENGTH_SHORT).show();
        }
    }
}