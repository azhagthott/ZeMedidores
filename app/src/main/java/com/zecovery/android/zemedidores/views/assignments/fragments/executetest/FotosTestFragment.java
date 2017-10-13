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

import static com.zecovery.android.zemedidores.data.Constant.FACHADA_PROPIEDAD;
import static com.zecovery.android.zemedidores.data.Constant.FOLDER_ZE_MEDIDORES;
import static com.zecovery.android.zemedidores.data.Constant.ID_INSPECCION_FORMULARIO_FOTOS;
import static com.zecovery.android.zemedidores.data.Constant.LECTURA_MEDIDOR;
import static com.zecovery.android.zemedidores.data.Constant.MEDIDOR_DESCOMPUESTO;
import static com.zecovery.android.zemedidores.data.Constant.NUMERO_MEDIDOR;
import static com.zecovery.android.zemedidores.data.Constant.NUMERO_PROPIEDAD;
import static com.zecovery.android.zemedidores.data.Constant.PANORAMICA_MEDIDOR;
import static com.zecovery.android.zemedidores.data.Constant.RESIZE_PHOTO_PIXELS_PERCENTAGE;
import static com.zecovery.android.zemedidores.data.Constant.RESPONDE_NO;
import static com.zecovery.android.zemedidores.data.Constant.RESPONDE_SI;

public class FotosTestFragment extends Fragment implements View.OnClickListener {

    private ImageButton photoMeterReading;
    private ImageButton photoMeterNumber;
    private ImageButton photoMeterPanoramic;
    private ImageButton photoPropertyNumber;
    private ImageButton photoFrontageProperty;
    private ImageButton medidorDescompuestoFoto;
    private RadioButton positiveRadioButton;
    private RadioButton negativeRadioButton;
    private Button continueTestButton;

    private EditText ubicacionMedidorEditText;
    private EditText fallaMedidorEditText;
    private EditText numeroMedidorEditText;
    private EditText diametroMedidorEditText;
    private EditText lecturaMedidorEditText;

    private CircleImageView imagePreviewMedidorDescompuesto;
    private CircleImageView imagePreviewLecturaMedidor;
    private CircleImageView imagePreviewNumeroMedidor;
    private CircleImageView imagePreviewPanoMedidor;
    private CircleImageView imagePreviewNumeroPropiedad;
    private CircleImageView imagePreviewFachadaPropiedad;

    private LinearLayout datosMedidorLinerLayout;
    private LinearLayout medidorRotoLinearLayout;
    private LinearLayout mandatoryPhotosLinearLayout;

    private MagicalCamera magicalCamera;

    private IdInspeccionListener callback;
    private LocalDatabase db;

    private int idInspeccion;
    private String nombreFoto;
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

        db = new LocalDatabase(getContext());

        continueTestButton.setOnClickListener(this);
        photoMeterReading.setOnClickListener(this);
        photoMeterNumber.setOnClickListener(this);
        photoMeterPanoramic.setOnClickListener(this);
        photoPropertyNumber.setOnClickListener(this);
        photoFrontageProperty.setOnClickListener(this);
        medidorDescompuestoFoto.setOnClickListener(this);

        positiveRadioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    medidorRotoLinearLayout.setVisibility(View.VISIBLE);
                    datosMedidorLinerLayout.setVisibility(View.GONE);
                } else {
                    datosMedidorLinerLayout.setVisibility(View.VISIBLE);
                    medidorRotoLinearLayout.setVisibility(View.GONE);
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
            imagePreviewMedidorDescompuesto.setVisibility(View.VISIBLE);
            Glide.with(getContext()).load(db.getFotos(idInspeccion).getFallaMedidor()).into(imagePreviewMedidorDescompuesto);
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

        if (db.getDatosMedidor(idInspeccion).getUbicacion() != null && !db.getDatosMedidor(idInspeccion).getUbicacion().equals("null")) {
            ubicacionMedidorEditText.setText(db.getDatosMedidor(idInspeccion).getUbicacion());
        } else {
            ubicacionMedidorEditText.setText("");
        }

        if (db.getDatosMedidor(idInspeccion).getDescripcionFalla() != null && !db.getDatosMedidor(idInspeccion).getDescripcionFalla().equals("null")) {
            fallaMedidorEditText.setText(db.getDatosMedidor(idInspeccion).getDescripcionFalla());
        } else {
            fallaMedidorEditText.setText("");
        }

        if (db.getDatosMedidor(idInspeccion).getNumeroMedidor() != null && !db.getDatosMedidor(idInspeccion).getNumeroMedidor().equals("null")) {
            numeroMedidorEditText.setText(db.getDatosMedidor(idInspeccion).getNumeroMedidor());
        } else {
            numeroMedidorEditText.setText("");
        }

        if (db.getDatosMedidor(idInspeccion).getDiametroMedidor() != null && !db.getDatosMedidor(idInspeccion).getDiametroMedidor().equals("null")) {
            diametroMedidorEditText.setText(db.getDatosMedidor(idInspeccion).getDiametroMedidor());
        } else {
            diametroMedidorEditText.setText("");
        }

        if (db.getDatosMedidor(idInspeccion).getLecturaMedidor() != null && !db.getDatosMedidor(idInspeccion).getLecturaMedidor().equals("null")) {
            lecturaMedidorEditText.setText(db.getDatosMedidor(idInspeccion).getLecturaMedidor());
        } else {
            lecturaMedidorEditText.setText("");
        }

        if (db.getDatosMedidor(idInspeccion).getEstado() != null) {
            if (db.getDatosMedidor(idInspeccion).getEstado().equals(RESPONDE_SI)) {
                positiveRadioButton.setChecked(true);
                medidorRotoLinearLayout.setVisibility(View.VISIBLE);
            } else {
                negativeRadioButton.setChecked(true);
                datosMedidorLinerLayout.setVisibility(View.VISIBLE);
            }
        }
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

    private Intent callCamera(String nombreFoto) {

        this.nombreFoto = nombreFoto;

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

            localPath = magicalCamera.savePhotoInMemoryDevice(magicalCamera.getPhoto(), nombreFoto, idInspeccion + "/" + FOLDER_ZE_MEDIDORES, MagicalCamera.PNG, true);

            Foto foto = new Foto();

            Log.d("TAG", "nombreFoto: " + nombreFoto);

            switch (nombreFoto) {

                case MEDIDOR_DESCOMPUESTO:
                    foto.setFallaMedidor(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewMedidorDescompuesto.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getFallaMedidor()).into(imagePreviewMedidorDescompuesto);
                    break;
                case LECTURA_MEDIDOR:
                    foto.setLecturaMedidor(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewLecturaMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getLecturaMedidor()).into(imagePreviewLecturaMedidor);
                    break;
                case NUMERO_MEDIDOR:
                    foto.setNumeroMedidor(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewNumeroMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getNumeroMedidor()).into(imagePreviewNumeroMedidor);
                    break;
                case PANORAMICA_MEDIDOR:
                    foto.setPanoramicaMedidor(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewPanoMedidor.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getPanoramicaMedidor()).into(imagePreviewPanoMedidor);
                    break;
                case NUMERO_PROPIEDAD:
                    foto.setNumeroPropiedad(localPath);
                    db.guardaFoto(foto, idInspeccion);
                    imagePreviewNumeroPropiedad.setVisibility(View.VISIBLE);
                    Glide.with(getContext()).load(db.getFotos(idInspeccion).getNumeroPropiedad()).into(imagePreviewNumeroPropiedad);
                    break;
                case FACHADA_PROPIEDAD:
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

        String fotoFalla = db.getFotos(idInspeccion).getFallaMedidor();
        String fotoLectura = db.getFotos(idInspeccion).getLecturaMedidor();
        String fotoNumeroMedidor = db.getFotos(idInspeccion).getNumeroMedidor();
        String fotoPanoramica = db.getFotos(idInspeccion).getPanoramicaMedidor();
        String fotoNumeroPropiedad = db.getFotos(idInspeccion).getNumeroPropiedad();
        String fotoFachada = db.getFotos(idInspeccion).getFachadaPropiedad();

        String descripcionFalla = fallaMedidorEditText.getText().toString();
        String meterLocation = ubicacionMedidorEditText.getText().toString();

        String ubicacion = ubicacionMedidorEditText.getText().toString();
        String numero = numeroMedidorEditText.getText().toString();
        String diametro = diametroMedidorEditText.getText().toString();
        String lectura = lecturaMedidorEditText.getText().toString();


        /* saca fotos */

        int id = v.getId();
        switch (id) {
            case R.id.medidorDescompuestoFoto:
                callCamera(MEDIDOR_DESCOMPUESTO);
                break;
            case R.id.photoMeterReading:
                nombreFoto = LECTURA_MEDIDOR;
                callCamera(nombreFoto);
                break;
            case R.id.photoMeterNumber:
                nombreFoto = NUMERO_MEDIDOR;
                callCamera(nombreFoto);
                break;
            case R.id.photoMeterPanoramic:
                nombreFoto = PANORAMICA_MEDIDOR;
                callCamera(nombreFoto);
                break;
            case R.id.photoPropertyNumber:
                nombreFoto = NUMERO_PROPIEDAD;
                callCamera(nombreFoto);
                break;
            case R.id.photoFrontageProperty:
                nombreFoto = FACHADA_PROPIEDAD;
                callCamera(nombreFoto);
                break;
        }

        /* valida largo del texto */
        if (ubicacionMedidorEditText.getText().toString().trim().length() == 0) {
            ubicacionMedidor = true;
            ubicacionMedidorEditText.setError(getResources().getString(R.string.validacion_ubicacion_medidor));
        } else {
            ubicacionMedidor = false;
        }

        /* Medidor descompuesto*/
        if (positiveRadioButton.isChecked()) {

            if (fallaMedidorEditText.getText().toString().trim().length() == 0) {
                medidorDescompuesto = true;
                fallaMedidorEditText.setError(getResources().getString(R.string.validacion_falla_medidor));
            } else {
                medidorDescompuesto = false;
            }

            if (fotoFalla == null) {
                imagePreviewMedidorDescompuesto.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load("").error(R.drawable.ic_error_outline).into(imagePreviewMedidorDescompuesto);
            }

            /* Medidor NO descompuesto*/
        } else if (negativeRadioButton.isChecked()) {

            medidorDescompuesto = false;

            if (numeroMedidorEditText.getText().toString().trim().length() == 0) {
                numeroMedidorBool = true;
                numeroMedidorEditText.setError(getResources().getString(R.string.validacion_numero_medidor));
            } else {
                numeroMedidorBool = false;
            }

            if (diametroMedidorEditText.getText().toString().trim().length() == 0) {
                diametroMedidorBool = true;
                diametroMedidorEditText.setError(getResources().getString(R.string.validacion_diametro_medidor));
            } else {
                diametroMedidorBool = false;
            }

            if (lecturaMedidorEditText.getText().toString().trim().length() == 0) {
                lecturaMedidorBool = true;
                lecturaMedidorEditText.setError(getResources().getString(R.string.validacion_lectura_medidor));
            } else {
                lecturaMedidorBool = false;
            }


            int faltaFotoLecturaMedidor = 0;
            int faltaFotoNumeroMedidor = 0;
            int faltaFotoPanoramicaMedidor = 0;
            int faltaFotoNumeroPropiedad = 0;
            int faltaFotoFachadaPropiedad = 0;

            if (fotoLectura == null) {
                imagePreviewLecturaMedidor.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load("").error(R.drawable.ic_error_outline).into(imagePreviewLecturaMedidor);
                faltaFotoLecturaMedidor = 1;
            } else {
                faltaFotoLecturaMedidor = 0;
            }

            if (fotoNumeroMedidor == null) {
                imagePreviewNumeroMedidor.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load("").error(R.drawable.ic_error_outline).into(imagePreviewNumeroMedidor);
                faltaFotoNumeroMedidor = 1;
            } else {
                faltaFotoNumeroMedidor = 0;
            }

            if (fotoPanoramica == null) {
                imagePreviewPanoMedidor.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load("").error(R.drawable.ic_error_outline).into(imagePreviewPanoMedidor);
                faltaFotoPanoramicaMedidor = 1;
            } else {
                faltaFotoPanoramicaMedidor = 0;
            }

            if (fotoNumeroPropiedad == null) {
                imagePreviewNumeroPropiedad.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load("").error(R.drawable.ic_error_outline).into(imagePreviewNumeroPropiedad);
                faltaFotoNumeroPropiedad = 1;
            } else {
                faltaFotoNumeroPropiedad = 0;
            }

            if (fotoFachada == null) {
                imagePreviewFachadaPropiedad.setVisibility(View.VISIBLE);
                Glide.with(getContext())
                        .load("").error(R.drawable.ic_error_outline).into(imagePreviewFachadaPropiedad);
                faltaFotoFachadaPropiedad = 1;
            } else {
                faltaFotoFachadaPropiedad = 0;
            }


        } else {
            medidorDescompuesto = false;
            Toast.makeText(getContext(), R.string.validacion_estado_medidor, Toast.LENGTH_SHORT).show();
        }

        if (id == R.id.continueTestButton) {

            if (positiveRadioButton.isChecked() && !ubicacionMedidor && !medidorDescompuesto && fotoFalla != null) {
                Medidor medidor = new Medidor();
                medidor.setUbicacion(meterLocation);
                medidor.setEstado(RESPONDE_SI);
                medidor.setDescripcionFalla(descripcionFalla);
                db.guardaDatosMedidor(medidor, idInspeccion);
                callback.IdInspeccionEjecutaTestParte1(idInspeccion);
            }

            if (negativeRadioButton.isChecked() && !ubicacionMedidor && !numeroMedidorBool && !diametroMedidorBool &&
                    !lecturaMedidorBool && fotoLectura != null && fotoNumeroMedidor != null && fotoPanoramica != null &&
                    fotoNumeroPropiedad != null && fotoFachada != null) {
                Medidor medidor = new Medidor();
                medidor.setUbicacion(ubicacion);
                medidor.setNumeroMedidor(numero);
                medidor.setDiametroMedidor(diametro);
                medidor.setLecturaMedidor(lectura);
                medidor.setEstado(RESPONDE_NO);
                medidor.setDescripcionFalla("");
                db.guardaDatosMedidor(medidor, idInspeccion);
                callback.IdInspeccionEjecutaTestParte1(idInspeccion);
            }
        }
    }

    private void findViews(View view) {
        photoMeterReading = view.findViewById(R.id.photoMeterReading);
        photoMeterNumber = view.findViewById(R.id.photoMeterNumber);
        photoMeterPanoramic = view.findViewById(R.id.photoMeterPanoramic);
        photoPropertyNumber = view.findViewById(R.id.photoPropertyNumber);
        photoFrontageProperty = view.findViewById(R.id.photoFrontageProperty);
        medidorDescompuestoFoto = view.findViewById(R.id.medidorDescompuestoFoto);
        positiveRadioButton = view.findViewById(R.id.positiveRadioButton);
        negativeRadioButton = view.findViewById(R.id.negativeRadioButton);
        medidorRotoLinearLayout = view.findViewById(R.id.medidorRotoLinearLayout);
        mandatoryPhotosLinearLayout = view.findViewById(R.id.mandatoryPhotosLinearLayout);
        ubicacionMedidorEditText = view.findViewById(R.id.ubicacionMedidorEditText);
        fallaMedidorEditText = view.findViewById(R.id.fallaMedidorEditText);
        continueTestButton = view.findViewById(R.id.continueTestButton);
        imagePreviewMedidorDescompuesto = view.findViewById(R.id.imagePreviewMedidorDescompuesto);
        imagePreviewLecturaMedidor = view.findViewById(R.id.imagePreviewLecturaMedidor);
        imagePreviewNumeroMedidor = view.findViewById(R.id.imagePreviewNumeroMedidor);
        imagePreviewPanoMedidor = view.findViewById(R.id.imagePreviewPanoMedidor);
        imagePreviewNumeroPropiedad = view.findViewById(R.id.imagePreviewNumeroPropiedad);
        imagePreviewFachadaPropiedad = view.findViewById(R.id.imagePreviewFachadaPropiedad);
        numeroMedidorEditText = view.findViewById(R.id.numeroMedidorEditText);
        diametroMedidorEditText = view.findViewById(R.id.diametroMedidorEditText);
        lecturaMedidorEditText = view.findViewById(R.id.lecturaMedidorEditText);
        datosMedidorLinerLayout = view.findViewById(R.id.datosMedidorLinerLayout);

        datosMedidorLinerLayout.setVisibility(View.GONE);
        medidorRotoLinearLayout.setVisibility(View.GONE);
        mandatoryPhotosLinearLayout.setVisibility(View.GONE);

    }
}