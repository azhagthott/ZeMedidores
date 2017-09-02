package com.zecovery.android.zemedidores.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.zecovery.android.zemedidores.models.Assignment;
import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.models.Resident;

import java.util.List;

/**
 * Created by fbarrios80 on 15-08-17.
 */

public class LocalDatabase extends SQLiteOpenHelper {

    /* local db */
    private static final int DB_VERSION = 104;
    private static final String DB_MANE = "zemedidores.db";
    private static final String TABLE_ASSIGNMENT = "assignment";
    private static final String TABLE_CURRENT_LOCATION = "location";
    private static final String TABLE_INSPECTION_RESULT = "result";

    //SQL comandos
    private static final String DB_CREATE_TABLE = "CREATE TABLE ";
    private static final String DB_DROP_TABLE = "DROP TABLE IF EXISTS ";
    private static final String SELECT_ALL = "SELECT * FROM ";

    /* assignment table */
    private static final String ASSIGNMENT_ID_KEY = "id";
    private static final String ASSIGNMENT_ID_INSPECTION = "id_inspection";
    private static final String ASSIGNMENT_ADDRESS = "address";
    private static final String ASSIGNMENT_NAME = "name";
    private static final String ASSIGNMENT_RUT = "rut";
    private static final String ASSIGNMENT_ORIGIN = "origin";
    private static final String ASSIGNMENT_ORDEN = "orden";
    private static final String ASSIGNMENT_COMUNA = "comuna";
    private static final String ASSIGNMENT_MEDIDOR = "medidor";
    private static final String ASSIGNMENT_DATE = "date";
    private static final String ASSIGNMENT_TYPE = "assignment_type";
    private static final String ASSIGNMENT_SOCIAL_RISK = "social_risk";
    private static final String ASSIGNMENT_ZONE = "zone";
    private static final String ASSIGNMENT_SECTOR = "sector";
    private static final String ASSIGNMENT_LAT = "lat";
    private static final String ASSIGNMENT_LNG = "lng";

    /* location table */
    private static final String CURRENT_LOCATION_ID_KEY = "id";
    private static final String CURRENT_LOCATION_LAT = "lat";
    private static final String CURRENT_LOCATION_LNG = "lng";


    private static final String RESULT_ID_KEY = "id";
    private static final String RESULT_ID_INSPECCION = "id_isnpeccion";
    private static final String RESULT_NAME = "name";
    private static final String RESULT_RUT = "rut";
    private static final String RESULT_PHONE = "phone";
    private static final String RESULT_EMAIL = "email";
    private static final String RESULT_DATE_RES = "date_res";
    private static final String RESULT_METER_LOCATION = "meter_location";
    private static final String RESULT_METER_STATUS = "meter_status";
    private static final String RESULT_METER_OBS = "meter_obs";
    private static final String RESULT_METER_PHOTO = "meter_photo_local_path";
    private static final String RESULT_TEST_1 = "test_1";
    private static final String RESULT_TEST_2 = "test_2";
    private static final String RESULT_TEST_3 = "test_3";
    private static final String RESULT_TEST_USO_IMANES = "uso_imanes";
    private static final String RESULT_TEST_INV_TOMAS = "inv_tomas";
    private static final String RESULT_TEST_PERF_CUP = "perf_cup";
    private static final String RESULT_TEST_CORTAR_ENG = "cortar_eng";
    private static final String RESULT_TEST_USO_ALAM = "uso_alam";
    private static final String RESULT_TEST_PRENSADO = "prensado";
    private static final String RESULT_TEST_OTRO_1 = "otro_1";
    private static final String RESULT_TEST_INST_PAR = "inst_par";
    private static final String RESULT_TEST_BYPASS = "bypass";
    private static final String RESULT_TEST_OTRO_2 = "otro_2";
    private static final String RESULT_TEST_CAT = "categoria";
    private static final String RESULT_TEST_YEAR = "year";
    private static final String RESULT_TEST_MARCA = "marca";
    private static final String RESULT_TEST_REG = "registrador";
    private static final String RESULT_TEST_INST = "instalacion";
    private static final String RESULT_TEST_TRAMOS_RECTOS_ANTES = "tram_antes";
    private static final String RESULT_TEST_TRAMOS_RECTOS_DESPUES = "tram_despues";
    private static final String RESULT_TEST_OBS_1 = "observaciones_1";
    private static final String RESULT_TEST_VERTICALES = "verticales";
    private static final String RESULT_TEST_CORTES = "cortes";
    private static final String RESULT_TEST_ALTER = "alternativo";
    private static final String RESULT_TEST_TIPO = "tipo";
    private static final String RESULT_TEST_OBS_2 = "observaciones_2";
    private static final String RESULT_TEST_CONST = "construccion_nueva";
    private static final String RESULT_TEST_TIPO_PROP = "tipo_propiedad";
    private static final String RESULT_TEST_HABITANTES = "habitantes";
    private static final String RESULT_TEST_BANOS = "banos";
    private static final String RESULT_TEST_SUP = "superficie";
    private static final String RESULT_TEST_SUP_JARDIN = "superficie_jardin";
    private static final String RESULT_TEST_ACCESO = "acceso";
    private static final String RESULT_TEST_SUP_TERRENO = "superficie_terreno";
    private static final String RESULT_TEST_OBS_3 = "observaciones_3";
    private static final String RESULT_TEST_AUTO_AB = "auto_abastecimiento";
    private static final String RESULT_TEST_TIPO_FUENTE = "tipo_fuente";
    private static final String RESULT_TEST_USO = "uso";
    private static final String RESULT_TEST_ACTIVO = "activo";
    private static final String RESULT_TEST_CAPACIDAD_BOMBA = "capacidad_bomba";
    private static final String RESULT_TEST_RESOLUCION = "resolucion";
    private static final String RESULT_TEST_CAUDAL = "caudal";
    private static final String RESULT_TEST_OBS_4 = "observaciones_4";


    public LocalDatabase(Context context) {
        super(context, DB_MANE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ASSIGNMENT = DB_CREATE_TABLE + TABLE_ASSIGNMENT + "("
                + ASSIGNMENT_ID_KEY + " INTEGER PRIMARY_KEY, "
                + ASSIGNMENT_ID_INSPECTION + " TEXT, "
                + ASSIGNMENT_ADDRESS + " TEXT, "
                + ASSIGNMENT_TYPE + " TEXT, "
                + ASSIGNMENT_DATE + " TEXT, "
                + ASSIGNMENT_LAT + " TEXT, "
                + ASSIGNMENT_LNG + " TEXT, "
                + ASSIGNMENT_NAME + " TEXT, "
                + ASSIGNMENT_ORIGIN + " TEXT, "
                + ASSIGNMENT_SOCIAL_RISK + " TEXT, "
                + ASSIGNMENT_RUT + " TEXT, "
                + ASSIGNMENT_SECTOR + " TEXT, "
                + ASSIGNMENT_ZONE + " TEXT, "
                + ASSIGNMENT_ORDEN + " TEXT, "
                + ASSIGNMENT_COMUNA + " TEXT, "
                + ASSIGNMENT_MEDIDOR + " TEXT)"
                + ";";

        String CREATE_TABLE_CURRENT_LOCATION = DB_CREATE_TABLE + TABLE_CURRENT_LOCATION + "("
                + CURRENT_LOCATION_ID_KEY + " INTEGER PRIMARY_KEY, "
                + CURRENT_LOCATION_LAT + " TEXT, "
                + CURRENT_LOCATION_LNG + " TEXT)"
                + ";";

        String CREATE_TABLE_INSPECTION_RESULT = DB_CREATE_TABLE + TABLE_INSPECTION_RESULT + "("
                + RESULT_ID_KEY + " INTEGER PRIMARY_KEY, "
                + RESULT_ID_INSPECCION + " TEXT, "
                + RESULT_NAME + " TEXT, "
                + RESULT_RUT + " TEXT, "
                + RESULT_PHONE + " TEXT, "
                + RESULT_EMAIL + " TEXT, "
                + RESULT_DATE_RES + " TEXT, "
                + RESULT_METER_LOCATION + " TEXT, "
                + RESULT_METER_STATUS + " TEXT, "
                + RESULT_METER_OBS + " TEXT, "
                + RESULT_METER_PHOTO + " TEXT, "
                + RESULT_TEST_1 + " TEXT, "
                + RESULT_TEST_2 + " TEXT, "
                + RESULT_TEST_3 + " TEXT, "
                + RESULT_TEST_USO_IMANES + " TEXT, "
                + RESULT_TEST_INV_TOMAS + " TEXT, "
                + RESULT_TEST_PERF_CUP + " TEXT, "
                + RESULT_TEST_CORTAR_ENG + " TEXT, "
                + RESULT_TEST_USO_ALAM + " TEXT, "
                + RESULT_TEST_PRENSADO + " TEXT, "
                + RESULT_TEST_OTRO_1 + " TEXT, "
                + RESULT_TEST_INST_PAR + " TEXT, "
                + RESULT_TEST_BYPASS + " TEXT, "
                + RESULT_TEST_OTRO_2 + " TEXT, "
                + RESULT_TEST_CAT + " TEXT, "
                + RESULT_TEST_YEAR + " TEXT, "
                + RESULT_TEST_MARCA + " TEXT, "
                + RESULT_TEST_REG + " TEXT, "
                + RESULT_TEST_INST + " TEXT, "
                + RESULT_TEST_TRAMOS_RECTOS_ANTES + " TEXT, "
                + RESULT_TEST_TRAMOS_RECTOS_DESPUES + " TEXT, "
                + RESULT_TEST_OBS_1 + " TEXT, "
                + RESULT_TEST_VERTICALES + " TEXT, "
                + RESULT_TEST_CORTES + " TEXT, "
                + RESULT_TEST_ALTER + " TEXT, "
                + RESULT_TEST_TIPO + " TEXT, "
                + RESULT_TEST_OBS_2 + " TEXT, "
                + RESULT_TEST_CONST + " TEXT, "
                + RESULT_TEST_TIPO_PROP + " TEXT, "
                + RESULT_TEST_HABITANTES + " TEXT, "
                + RESULT_TEST_BANOS + " TEXT, "
                + RESULT_TEST_SUP + " TEXT, "
                + RESULT_TEST_SUP_JARDIN + " TEXT, "
                + RESULT_TEST_ACCESO + " TEXT, "
                + RESULT_TEST_SUP_TERRENO + " TEXT, "
                + RESULT_TEST_OBS_3 + " TEXT, "
                + RESULT_TEST_AUTO_AB + " TEXT, "
                + RESULT_TEST_TIPO_FUENTE + " TEXT, "
                + RESULT_TEST_USO + " TEXT, "
                + RESULT_TEST_ACTIVO + " TEXT, "
                + RESULT_TEST_CAPACIDAD_BOMBA + " TEXT, "
                + RESULT_TEST_RESOLUCION + " TEXT, "
                + RESULT_TEST_CAUDAL + " TEXT, "
                + RESULT_TEST_OBS_4 + " TEXT) "
                + ";";

        db.execSQL(CREATE_TABLE_ASSIGNMENT);
        db.execSQL(CREATE_TABLE_CURRENT_LOCATION);
        db.execSQL(CREATE_TABLE_INSPECTION_RESULT);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DB_DROP_TABLE + TABLE_ASSIGNMENT);
        db.execSQL(DB_DROP_TABLE + TABLE_CURRENT_LOCATION);
        db.execSQL(DB_DROP_TABLE + TABLE_INSPECTION_RESULT);
        onCreate(db);
    }

    public void saveDownloadedInspection(List<Inspection> inspections) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (Inspection inspection : inspections) {

            values.put(ASSIGNMENT_ID_INSPECTION, inspection.getId_inspeccion());
            values.put(ASSIGNMENT_ADDRESS, inspection.getAddress());
            values.put(ASSIGNMENT_TYPE, inspection.getAssigment_type());
            values.put(ASSIGNMENT_DATE, inspection.getDate());
            values.put(ASSIGNMENT_LAT, inspection.getLat());
            values.put(ASSIGNMENT_LNG, inspection.getLng());
            values.put(ASSIGNMENT_NAME, inspection.getName_resident());
            values.put(ASSIGNMENT_ORIGIN, inspection.getOrigin());
            values.put(ASSIGNMENT_SOCIAL_RISK, inspection.getPolygon());
            values.put(ASSIGNMENT_RUT, inspection.getRut());
            values.put(ASSIGNMENT_SECTOR, inspection.getSector());
            values.put(ASSIGNMENT_ZONE, inspection.getZone());
            values.put(ASSIGNMENT_ORDEN, inspection.getOrden());
            values.put(ASSIGNMENT_COMUNA, inspection.getComuna());
            values.put(ASSIGNMENT_MEDIDOR, inspection.getMedidor());
            db.insert(TABLE_ASSIGNMENT, null, values);
        }
        db.close();
    }

    public void createAssignmnet() {
    }

    public void insertAssignment() {
    }

    public void deleteAssignment() {
    }

    public void updateAssignment() {
    }

    public List<Assignment> getAllAssignment() {
        return null;
    }

    public void getAssignment(int idAssignment) {
    }

    /* Current location*/
    public void saveDbCurrentLocation(double lat, double lng) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CURRENT_LOCATION_LAT, String.valueOf(lat));
        values.put(CURRENT_LOCATION_LNG, String.valueOf(lng));
        db.insert(TABLE_CURRENT_LOCATION, null, values);
        db.close();
    }

    public LatLng getCurrentDbLocation() {

        double lat;
        double lng;

        SQLiteDatabase db = this.getReadableDatabase();
        String sqlCommand = SELECT_ALL + TABLE_CURRENT_LOCATION + " ORDER BY " + CURRENT_LOCATION_ID_KEY + " DESC LIMIT 1;";
        Cursor cursor = db.rawQuery(sqlCommand, null);

        if (cursor.moveToLast()) {
            lat = Double.valueOf(cursor.getString(1));
            lng = Double.valueOf(cursor.getString(2));
            return new LatLng(lat, lng);
        } else {
            return new LatLng(0, 0);
        }
    }


    public void saveResidentData(Resident resident, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Log.d("LOG_TAG", "nombre: " + resident.getNombre());
        Log.d("LOG_TAG", "rut: " + resident.getRut());
        Log.d("LOG_TAG", "fono: " + resident.getTelefono());
        Log.d("LOG_TAG", "email: " + resident.getEmail());
        Log.d("LOG_TAG", "data: " + resident.getFecha());

        values.put(RESULT_NAME, String.valueOf(resident.getNombre()));
        values.put(RESULT_RUT, String.valueOf(resident.getRut()));
        values.put(RESULT_PHONE, String.valueOf(resident.getTelefono()));
        values.put(RESULT_EMAIL, String.valueOf(resident.getEmail()));
        values.put(RESULT_DATE_RES, String.valueOf(resident.getFecha()));

        db.insert(TABLE_INSPECTION_RESULT, null, values);
        db.close();

    }

}
