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
import com.zecovery.android.zemedidores.views.assignments.fragments.IdInspeccionListener;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION_FORMULARIO_FOTOS;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;

public class FotosTestFragment extends Fragment implements View.OnClickListener {

    private ImageButton photoMeterReading;
    private ImageButton photoMeterNumber;
    private ImageButton photoMeterPanoramic;
    private ImageButton photoPropertyNumber;
    private ImageButton photoFrontageProperty;
    private ImageButton brokenMeterPhoto;
    private RadioButton positiveRadioButton;
    private RadioButton negativeRadioButton;
    private Button continueTestButton;
    private EditText meterLocationEditText;
    private EditText brokenMeterCommentEditText;
    private EditText numeroMedidorEditText;
    private EditText diametroMedidorEditText;
    private EditText lecturaMedidorEditText;
    private CircleImageView imagePreviewMedidorRoto;
    private CircleImageView imagePreviewLecturaMedidor;
    private CircleImageView imagePreviewNumeroMedidor;
    private CircleImageView imagePreviewPanoMedidor;
    private CircleImageView imagePreviewNumeroPropiedad;
    private CircleImageView imagePreviewFachadaPropiedad;

    private LinearLayout datosMedidorLinerLayout;
    private LinearLayout brokenMeterLinearLayout;
    private LinearLayout mandatoryPhotosLinearLayout;

    private MagicalCamera magicalCamera;

    private IdInspeccionListener callback;
    private LocalDatabase db;

    private int idInspeccion;
    private String photoName;
    private String localPath;

    public FotosTestFragment() {
    }

    public FotosTestFragment newInstance(int idInspeccion) {


        Log.d("TAG", "idInspeccion: " + idInspeccion);

        FotosTestFragment photosTestFragment = new FotosTestFragment();
        Bundle args = new Bundle();
        args.putInt(ID_INSPECCION_FORMULARIO_FOTOS, idInspeccion);
        photosTestFragment.setArguments(args);
        return photosTestFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_formulario_fotos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idInspeccion = getArguments().getInt(ID_INSPECCION_FORMULARIO_FOTOS);
        findViews(view);

        datosMedidorLinerLayout.setVisibility(View.GONE);

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
                    datosMedidorLinerLayout.setVisibility(View.GONE);
                } else {
                    datosMedidorLinerLayout.setVisibility(View.VISIBLE);
                    brokenMeterLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        negativeRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    datosMedidorLinerLayout.setVisibility(View.VISIBLE);
                    mandatoryPhotosLinearLayout.setVisibility(View.VISIBLE);
                } else {
                    datosMedidorLinerLayout.setVisibility(View.GONE);
                    mandatoryPhotosLinearLayout.setVisibility(View.GONE);
                }
            }
        });

        if (db.getFotos(idInspeccion).getFallaMedidor() != null) {
            imagePreviewMedidorRoto.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getFallaMedidor()).into(imagePreviewMedidorRoto);
        }

        if (db.getFotos(idInspeccion).getLecturaMedidor() != null) {
            imagePreviewLecturaMedidor.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getLecturaMedidor()).into(imagePreviewLecturaMedidor);
        }

        if (db.getFotos(idInspeccion).getNumeroMedidor() != null) {
            imagePreviewNumeroMedidor.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getNumeroMedidor()).into(imagePreviewNumeroMedidor);
        }

        if (db.getFotos(idInspeccion).getPanoramicaMedidor() != null) {
            imagePreviewPanoMedidor.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getPanoramicaMedidor()).into(imagePreviewPanoMedidor);
        }

        if (db.getFotos(idInspeccion).getNumeroPropiedad() != null) {
            imagePreviewNumeroPropiedad.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getNumeroPropiedad()).into(imagePreviewNumeroPropiedad);
        }

        if (db.getFotos(idInspeccion).getFachadaPropiedad() != null) {
            imagePreviewFachadaPropiedad.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getFachadaPropiedad()).into(imagePreviewFachadaPropiedad);
        }
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
        numeroMedidorEditText = view.findViewById(R.id.numeroMedidorEditText);
        diametroMedidorEditText = view.findViewById(R.id.diametroMedidorEditText);
        lecturaMedidorEditText = view.findViewById(R.id.lecturaMedidorEditText);
        datosMedidorLinerLayout = view.findViewById(R.id.datosMedidorLinerLayout);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof Activity) {

            try {
                Activity activity = (Activity) context;
                callback = (IdInspeccionListener) activity;
            } catch (Exception e) {
                Log.d("TAG", e.toString());
                throw new ClassCastException(context.toString()
                        + " must implement OnHeadlineSelectedListener");
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


        Log.d("TAG", "requestCode: " + requestCode);
        Log.d("TAG", "resultCode: " + resultCode);


        magicalCamera.resultPhoto(requestCode, resultCode, data);

        try {

            localPath = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), photoName, idInspeccion + "/" + FOLDER_ZE_MEDIDORES, MagicalCamera.PNG, true);
            String localPathParts[] = localPath.split("/");
            //localPhotoName = localPathParts[7];

            Foto foto = new Foto();

            Log.d("TAG", "photoName: " + photoName);

            switch (photoName) {

                case "medidor_descompuesto":
                    foto.setFallaMedidor(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewMedidorRoto.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getFallaMedidor()).into(imagePreviewMedidorRoto);
                    break;
                case "lectura_medidor":
                    foto.setLecturaMedidor(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewLecturaMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getLecturaMedidor()).into(imagePreviewLecturaMedidor);
                    break;
                case "numero_medidor":
                    foto.setNumeroMedidor(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewNumeroMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getNumeroMedidor()).into(imagePreviewNumeroMedidor);
                    break;
                case "panoramica_medidor":
                    foto.setPanoramicaMedidor(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewPanoMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getPanoramicaMedidor()).into(imagePreviewPanoMedidor);
                    break;
                case "numero_propiedad":
                    foto.setNumeroPropiedad(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewNumeroPropiedad.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getNumeroPropiedad()).into(imagePreviewNumeroPropiedad);
                    break;
                case "fachada_propiedad":
                    foto.setFachadaPropiedad(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewFachadaPropiedad.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getFachadaPropiedad()).into(imagePreviewFachadaPropiedad);
                    break;
            }
        } catch (Exception e) {
            FirebaseCrash.log("Error: " + e);
        }
    }

    @Override
    public void onClick(View v) {

        boolean ubicacionMedidor;
        boolean medidorDescompuesto;

        boolean numeroMedidorBool = false;
        boolean diametroMedidorBool = false;
        boolean lecturaMedidorBool = false;

        int id = v.getId();

        if (meterLocationEditText.getText().length() == 0) {
            ubicacionMedidor = true;
            meterLocationEditText.setError("Debe especificar la ubicacion del medidor");
        } else {
            ubicacionMedidor = false;
        }

        if (numeroMedidorEditText.getText().length() == 0) {
            numeroMedidorBool = true;
            numeroMedidorEditText.setError("Debe especificar el numero de medidor");
        } else {
            numeroMedidorBool = false;
        }

        if (diametroMedidorEditText.getText().length() == 0) {
            diametroMedidorBool = true;
            diametroMedidorEditText.setError("Debe especificar el diametro del medidor");
        } else {
            diametroMedidorBool = false;
        }

        if (lecturaMedidorEditText.getText().length() == 0) {
            lecturaMedidorBool = true;
            lecturaMedidorEditText.setError("Debe especificar la lectura del medidor");
        } else {
            lecturaMedidorBool = false;
        }

        if (positiveRadioButton.isChecked()) {

            if (brokenMeterCommentEditText.getText().length() == 0) {
                medidorDescompuesto = true;
                brokenMeterCommentEditText.setError("Debe especificar la falla del medidor");
            } else {
                medidorDescompuesto = false;
            }

            if (id == R.id.brokenMeterPhoto) {
                callCamera("medidor_descompuesto");
            }

            numeroMedidorBool = false;
            diametroMedidorBool = false;
            lecturaMedidorBool = false;

        } else if (negativeRadioButton.isChecked()) {

            medidorDescompuesto = false;

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
            }

        } else {
            medidorDescompuesto = false;
            Toast.makeText(getContext(), "Debe indicar si el medidor está descompuesto o no", Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.continueTestButton) {

            String fotoFalla = db.getFotos(idInspeccion).getFallaMedidor();
            String fotoLectura = db.getFotos(idInspeccion).getLecturaMedidor();
            String fotoNumeroMedidor = db.getFotos(idInspeccion).getNumeroMedidor();
            String fotoPanoramica = db.getFotos(idInspeccion).getPanoramicaMedidor();
            String fotoNumeroPropiedad = db.getFotos(idInspeccion).getNumeroPropiedad();
            String fotoFachada = db.getFotos(idInspeccion).getFachadaPropiedad();


            if (positiveRadioButton.isChecked() &&
                    !ubicacionMedidor &&
                    !medidorDescompuesto &&
                    fotoFalla != null) {

                String failureComment = brokenMeterCommentEditText.getText().toString();
                String meterLocation = meterLocationEditText.getText().toString();

                Medidor medidor = new Medidor();
                medidor.setUbicacion(meterLocation);
                medidor.setEstado("si");
                medidor.setDescripcionFalla(failureComment);

                db.guardaDatosMedidor(medidor, idInspeccion);

                callback.IdInspeccionEjecutaTestParte1(idInspeccion);
            }

            if (negativeRadioButton.isChecked() &&
                    !ubicacionMedidor &&
                    !numeroMedidorBool &&
                    !diametroMedidorBool &&
                    !lecturaMedidorBool &&
                    fotoLectura != null &&
                    fotoNumeroMedidor != null &&
                    fotoPanoramica != null &&
                    fotoNumeroPropiedad != null &&
                    fotoFachada != null
                    ) {

                String ubicacion = meterLocationEditText.getText().toString();
                String numero = numeroMedidorEditText.getText().toString();
                String diametro = diametroMedidorEditText.getText().toString();
                String lectura = lecturaMedidorEditText.getText().toString();

                Medidor medidor = new Medidor();
                medidor.setUbicacion(ubicacion);
                medidor.setNumeroMedidor(numero);
                medidor.setDiametroMedidor(diametro);
                medidor.setLecturaMedidor(lectura);
                medidor.setEstado("si");
                medidor.setDescripcionFalla("");
                db.guardaDatosMedidor(medidor, idInspeccion);

                callback.IdInspeccionEjecutaTestParte1(idInspeccion);
            }
        }
    }
}