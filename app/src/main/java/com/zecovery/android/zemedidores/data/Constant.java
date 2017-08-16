package com.zecovery.android.zemedidores.data;

/**
 * Created by fbarrios80 on 15-05-17.
 */

public class Constant {

    public static final String ZE = "1";
    public static final String AA = "2";

    public static final String TAG = "LOG:::";

    public static final String RESIDENTIAL = "1";
    public static final String COMMERCIAL = "2";
    public static final String BUSINESS = "3";

    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String ADDRESS = "ADDRESS";
    public static final String ID_ASSIGNMENT = "ID_ASSIGNMENT";
    public static final String ID_ASSIGNMENT_EXECUTE_TEST_1 = "ID_ASSIGNMENT_EXECUTE_TEST_1";
    public static final String ID_ASSIGNMENT_EXECUTE_TEST_2 = "ID_ASSIGNMENT_EXECUTE_TEST_2";
    public static final String ID_ASSIGNMENT_EXECUTE_TEST_3 = "ID_ASSIGNMENT_EXECUTE_TEST_3";
    public static final String ID_ASSIGNMENT_NEGOTIATION = "ID_ASSIGNMENT_NEGOTIATION";
    public static final String ID_ASSIGNMENT_PHOTO = "ID_ASSIGNMENT_PHOTO";

    public static final String SOCIAL_POLYGON = "si";

    /* test, options and results
    public static final String TEST_RESULT_SELECT = "Seleccione opción";
    public static final String TEST_RESULT_OK = "SI";
    public static final String TEST_RESULT_NOK = "NO";

    public static final String TEST_RESULT_OK_0 = "Seleccione opción";
    public static final String TEST_RESULT_OK_1 = "Uso imanes";
    public static final String TEST_RESULT_OK_2 = "Invertir tomas";
    public static final String TEST_RESULT_OK_3 = "Perforar cúpula";
    public static final String TEST_RESULT_OK_4 = "Cortar engranajes";
    public static final String TEST_RESULT_OK_5 = "Uso de alabre";
    public static final String TEST_RESULT_OK_6 = "Prensado";
    public static final String TEST_RESULT_OK_7 = "Otro (especificar)";
*/
    public static final int RESIZE_PHOTO_PIXELS_PERCENTAGE = 30;
    public static final String FOLDER_ZE_MEDIDORES = "ZE_MEDIDORES";
    public static final String FIREBASE_STORAGE_BASE_URL = "gs://zemedidores.appspot.com/";

    /* rejections reasons */
    public static final String SELECT_OPTION = "Seleccione una opción";
    public static final String EMPTY_PLACE = "El lugar se encuentra deshabitado";
    public static final String UNWELCOME = "No se recibe al personal técnico";
    public static final String WRONG_DIRECTION = "Dirección errada";
    public static final String ANOTHER_REASON = "Otro (indique)";

    /* property type */
    public static final String TYPE_1 = "Casa";
    public static final String TYPE_2 = "Departamentp";

    /* payment method */
    public static final String PAYMENT_METHOD_1 = "EFECTIVO";
    public static final String PAYMENT_METHOD_2 = "CHEQUE";
    public static final String PAYMENT_METHOD_3 = "TC";
    public static final String PAYMENT_METHOD_4 = "TRANS";


    /* Retrofit */
    public static final String URL_BASE = "http://medidores.desa.zecovery.com";

    /* local db */
    public static final String DB_MANE = "zemedidores.db";
    public static final String TABLE_ASSIGNMENT = "assignment";

    /* assignment table */
    public static final String ASSIGNMENT_ID_KEY = "id";
    public static final String ASSIGNMENT_NAME = "name";
    public static final String ASSIGNMENT_DESCRIPTION = "description";
    public static final String ASSIGNMENT_ORIGIN = "origin";
    public static final String ASSIGNMENT_ADDRESS = "address";
    public static final String ASSIGNMENT_DATE = "date";
    public static final String ASSIGNMENT_UID = "uid";
    public static final String ASSIGNMENT_EMAIL = "email";
    public static final String ASSIGNMENT_METER_LOCATION = "meter_location";
    public static final String ASSIGNMENT_METER_STATUS = "meter_status";
    public static final String ASSIGNMENT_TYPE = "assignment_type";
    public static final String ASSIGNMENT_SOCIAL_RISK = "social_risk";
    public static final String ASSIGNMENT_ZONE = "zone";
    public static final String ASSIGNMENT_SECTOR = "sector";
    public static final boolean ASSIGNMENT_VISIBIITY = true;
    public static final double ASSIGNMENT_LAT = 0;
    public static final double ASSIGNMENT_LNG = 0;
}
