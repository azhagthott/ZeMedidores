package com.zecovery.android.zemedidores.views.assignments.fragments.forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.crash.FirebaseCrash;
import com.zecovery.android.zemedidores.R;
import com.zecovery.android.zemedidores.data.LocalDatabase;
import com.zecovery.android.zemedidores.models.ResultadoInspeccion;
import com.zecovery.android.zemedidores.network.NegociacionCallback;
import com.zecovery.android.zemedidores.network.PostResult;
import com.zecovery.android.zemedidores.views.MainActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.zecovery.android.zemedidores.data.Constant.ID_ASSIGNMENT_NEGOTIATION;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_1;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_2;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_3;
import static com.zecovery.android.zemedidores.data.Constant.PAYMENT_METHOD_4;
import static com.zecovery.android.zemedidores.data.Constant.SELECT_OPTION;
import static com.zecovery.android.zemedidores.data.Constant.URL_BASE_DESA;

public class NegotiationFragment extends Fragment implements NegociacionCallback, View.OnClickListener {

    private TextView socialRiskTextView;
    private TextView totalDebtTextView;
    private TextView arrangementTextView;
    private EditText commentEditText;

    private RadioButton negotiationAcceptedRadioButton;
    private RadioButton negotiationRejectedRadioButton;

    private Spinner paymentMethodSpinner;

    private ImageButton paymentMethodImageButton;
    private ImageButton personalIdImageButton;

    private Button endInspectionButton;

    private LocalDatabase db;

    private int token;

    public NegotiationFragment() {
    }

    public NegotiationFragment newInstance(int token) {

        NegotiationFragment negotiationFragment = new NegotiationFragment();
        Bundle args = new Bundle();
        args.putInt(ID_ASSIGNMENT_NEGOTIATION, token);
        negotiationFragment.setArguments(args);
        return negotiationFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_negotiation, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        token = getArguments().getInt(ID_ASSIGNMENT_NEGOTIATION);
        findViews(view);

        db = new LocalDatabase(getContext());


        Log.d("TAG", "NegotiationFragment");

        List<String> paymentMethod = new ArrayList<>();

        paymentMethod.add(SELECT_OPTION);
        paymentMethod.add(PAYMENT_METHOD_1);
        paymentMethod.add(PAYMENT_METHOD_2);
        paymentMethod.add(PAYMENT_METHOD_3);
        paymentMethod.add(PAYMENT_METHOD_4);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, paymentMethod);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        paymentMethodSpinner.setAdapter(adapter);

        endInspectionButton.setOnClickListener(this);

    }

    private void findViews(View view) {
        socialRiskTextView = view.findViewById(R.id.socialRiskTextView);
        totalDebtTextView = view.findViewById(R.id.totalDebtTextView);
        arrangementTextView = view.findViewById(R.id.arrangementTextView);
        commentEditText = view.findViewById(R.id.commentEditText);
        negotiationAcceptedRadioButton = view.findViewById(R.id.negotiationAcceptedRadioButton);
        negotiationRejectedRadioButton = view.findViewById(R.id.negotiationRejectedRadioButton);
        paymentMethodSpinner = view.findViewById(R.id.paymentMethodSpinner);
        paymentMethodImageButton = view.findViewById(R.id.paymentMethodImageButton);
        personalIdImageButton = view.findViewById(R.id.personalIdImageButton);
        endInspectionButton = view.findViewById(R.id.endInspectionButton);
    }

    @Override
    public void saveNegotiationForm() {


        startActivity(new Intent(getContext(), MainActivity.class));
    }

    @Override
    public void negotiationFormError() {
        Toast.makeText(getContext(), "No se pudo guarda la informacion", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        post(token);
        Log.d("NegotiationFragment", "onClick");

        String negotiationAccepted;
        String paymentMethod;

        if (negotiationAcceptedRadioButton.isChecked()) {
            negotiationAccepted = "SI";
        } else if (negotiationRejectedRadioButton.isChecked()) {
            negotiationAccepted = "NO";
        } else {
            negotiationAccepted = "-";
        }

        paymentMethod = paymentMethodSpinner.getSelectedItem().toString();



    }


    private void post(int token) {

        ResultadoInspeccion resultado = db.getResultadoInspeccion(token);

        //File file = new File("");

        RequestBody lat = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().latitude));
        RequestBody lng = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(db.getCurrentDbLocation().longitude));

        Calendar rightNow = Calendar.getInstance();
        int fechaAcutal = (int) (rightNow.getTimeInMillis() / 1000);
        RequestBody fecha = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(fechaAcutal));

        String test1 = resultado.getTestParte1().getTest1();
        String test2 = resultado.getTestParte1().getTest2();
        String test3 = resultado.getTestParte1().getTest3();
        String usoImanes = resultado.getTestParte1().getUsoImanes();
        String invertirTomas = resultado.getTestParte1().getInvertirTomas();
        String perforaCupula = resultado.getTestParte1().getPerforaCupula();
        String cortaEngranaje = resultado.getTestParte1().getCortaEngranaje();
        String usoAlambres = resultado.getTestParte1().getUsoAlambres();
        String prensado = resultado.getTestParte1().getPrensado();
        String otro = resultado.getTestParte1().getOtro();
        String instalacionParalela = resultado.getTestParte1().getInstalacionParalela();
        String bypass = resultado.getTestParte1().getBypass();
        String otro2 = resultado.getTestParte1().getOtro2();

        RequestBody test1Body = RequestBody.create(MediaType.parse("multipart/form-data"), test1);
        RequestBody test2Body = RequestBody.create(MediaType.parse("multipart/form-data"), test2);
        RequestBody test3Body = RequestBody.create(MediaType.parse("multipart/form-data"), test3);
        RequestBody usoImanesBody = RequestBody.create(MediaType.parse("multipart/form-data"), usoImanes);
        RequestBody invertirTomasBody = RequestBody.create(MediaType.parse("multipart/form-data"), invertirTomas);
        RequestBody perforaCupulaBody = RequestBody.create(MediaType.parse("multipart/form-data"), perforaCupula);
        RequestBody cortaEngranajeBody = RequestBody.create(MediaType.parse("multipart/form-data"), cortaEngranaje);
        RequestBody usoAlambresBody = RequestBody.create(MediaType.parse("multipart/form-data"), usoAlambres);
        RequestBody prensadoBody = RequestBody.create(MediaType.parse("multipart/form-data"), prensado);
        RequestBody otroBody = RequestBody.create(MediaType.parse("multipart/form-data"), otro);
        RequestBody instalacionParalelaBody = RequestBody.create(MediaType.parse("multipart/form-data"), instalacionParalela);
        RequestBody bypassBody = RequestBody.create(MediaType.parse("multipart/form-data"), bypass);
        RequestBody otro2Body = RequestBody.create(MediaType.parse("multipart/form-data"), otro2);

        /*RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fotoBody = MultipartBody.Part.createFormData("foto_rechazo", "", requestFile);*/

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL_BASE_DESA).build();
        PostResult service = retrofit.create(PostResult.class);

        Call<ResponseBody> call = service.postResultadoTest(
                lat, lng, fecha,
                test1Body, test2Body, test3Body,
                usoImanesBody,
                invertirTomasBody,
                perforaCupulaBody,
                cortaEngranajeBody,
                usoAlambresBody,
                prensadoBody, otroBody, instalacionParalelaBody, bypassBody, otro2Body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("NegotiationFragment", "onResponse");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                FirebaseCrash.log("Exception: " + t.getMessage());

            }
        });


    }


}
