package com.zecovery.android.zemedidores.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.android.gms.maps.model.LatLng;
import com.zecovery.android.zemedidores.models.Inspection;
import com.zecovery.android.zemedidores.models.Medidor;
import com.zecovery.android.zemedidores.models.Residente;
import com.zecovery.android.zemedidores.models.ResultadoInspeccion;
import com.zecovery.android.zemedidores.models.TestParte1;
import com.zecovery.android.zemedidores.models.TestParte2;
import com.zecovery.android.zemedidores.models.TestParte3;

import java.util.List;

/**
 * Created by fbarrios80 on 15-08-17.
 */

public class LocalDatabase extends SQLiteOpenHelper {

    /* local db */
    private static final int DB_VERSION = 109;
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


    /* meter, resident, tests table */
    private static final String RESULT_ID_KEY = "id";
    private static final String RESULT_ID_INSPECCION = "id_inspeccion";
    private static final String RESULT_NAME = "name";
    private static final String RESULT_RUT = "rut";
    private static final String RESULT_PHONE = "phone";
    private static final String RESULT_EMAIL = "email";
    private static final String RESULT_DATE_RES = "date_res";
    private static final String RESULT_METER_LOCATION = "meter_location";
    private static final String RESULT_METER_STATUS = "meter_status";
    private static final String RESULT_METER_OBS = "meter_obs";
    private static final String RESULT_BROKEN_METER_PHOTO = "photo_broken_meter";
    private static final String RESULT_METER_LECTURE_PHOTO = "photo_lecture_meter";
    private static final String RESULT_METER_NUMBER_PHOTO = "photo_number_meter";
    private static final String RESULT_METER_PANO_PHOTO = "photo_pano_meter";
    private static final String RESULT_METER_NUMBER_PROP_PHOTO = "photo_number_prop_meter";
    private static final String RESULT_FACE_PHOTO = "photo_face";
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
    private static final String RESULT_TEST_CLASS = "clase";
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
                + RESULT_BROKEN_METER_PHOTO + " TEXT, "
                + RESULT_METER_LECTURE_PHOTO + " TEXT, "
                + RESULT_METER_NUMBER_PHOTO + " TEXT, "
                + RESULT_METER_PANO_PHOTO + " TEXT, "
                + RESULT_METER_NUMBER_PROP_PHOTO + " TEXT, "
                + RESULT_FACE_PHOTO + " TEXT, "
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
                + RESULT_TEST_CLASS + " TEXT, "
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


    public void guardaDatosResidente(Residente residente, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESULT_ID_INSPECCION, String.valueOf(idInspeccion));
        values.put(RESULT_NAME, String.valueOf(residente.getNombre()));
        values.put(RESULT_RUT, String.valueOf(residente.getRut()));
        values.put(RESULT_PHONE, String.valueOf(residente.getTelefono()));
        values.put(RESULT_EMAIL, String.valueOf(residente.getEmail()));
        values.put(RESULT_DATE_RES, String.valueOf(residente.getFecha()));

        db.insert(TABLE_INSPECTION_RESULT, null, values);
        db.close();

    }

    public void guardaDatosMedidor(Medidor medidor, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESULT_METER_LOCATION, String.valueOf(medidor.getUbicacion()));
        values.put(RESULT_METER_STATUS, String.valueOf(medidor.getEstado()));
        values.put(RESULT_METER_OBS, String.valueOf(medidor.getDescripcionFalla()));
        values.put(RESULT_BROKEN_METER_PHOTO, String.valueOf(medidor.getFotoFalla()));
        values.put(RESULT_METER_LECTURE_PHOTO, String.valueOf(medidor.getFotoLectura()));
        values.put(RESULT_METER_NUMBER_PHOTO, String.valueOf(medidor.getFotoNumeroMedidor()));
        values.put(RESULT_METER_PANO_PHOTO, String.valueOf(medidor.getFotoPanoramica()));
        values.put(RESULT_METER_NUMBER_PROP_PHOTO, String.valueOf(medidor.getFotoNumeroPropiedad()));
        values.put(RESULT_FACE_PHOTO, String.valueOf(medidor.getFotoFachada()));

        db.update(TABLE_INSPECTION_RESULT, values, RESULT_ID_INSPECCION + " = ?", new String[]{String.valueOf(idInspeccion)});
        db.close();
    }

    public void guardaDatosTestParte1(TestParte1 test, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESULT_TEST_1, String.valueOf(test.getTest1()));
        values.put(RESULT_TEST_2, String.valueOf(test.getTest2()));
        values.put(RESULT_TEST_3, String.valueOf(test.getTest3()));
        values.put(RESULT_TEST_USO_IMANES, String.valueOf(test.getUsoImanes()));
        values.put(RESULT_TEST_INV_TOMAS, String.valueOf(test.getInvertirTomas()));
        values.put(RESULT_TEST_PERF_CUP, String.valueOf(test.getPerforaCupula()));
        values.put(RESULT_TEST_CORTAR_ENG, String.valueOf(test.getCortaEngranaje()));
        values.put(RESULT_TEST_USO_ALAM, String.valueOf(test.getUsoAlambres()));
        values.put(RESULT_TEST_PRENSADO, String.valueOf(test.getPrensado()));
        values.put(RESULT_TEST_OTRO_1, String.valueOf(test.getOtro()));
        values.put(RESULT_TEST_INST_PAR, String.valueOf(test.getInstalacionParalela()));
        values.put(RESULT_TEST_BYPASS, String.valueOf(test.getBypass()));
        values.put(RESULT_TEST_OTRO_2, String.valueOf(test.getOtro2()));

        db.update(TABLE_INSPECTION_RESULT, values, RESULT_ID_INSPECCION + " = ?", new String[]{String.valueOf(idInspeccion)});
        db.close();
    }

    public void guardaDatosTestParte2(TestParte2 test, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESULT_TEST_CLASS, String.valueOf(test.getClaseMedidor()));
        values.put(RESULT_TEST_YEAR, String.valueOf(test.getAnoMedidor()));
        values.put(RESULT_TEST_MARCA, String.valueOf(test.getMarca()));
        values.put(RESULT_TEST_REG, String.valueOf(test.getRegistrador()));
        values.put(RESULT_TEST_INST, String.valueOf(test.getInstalacion()));
        values.put(RESULT_TEST_TRAMOS_RECTOS_ANTES, String.valueOf(test.getTramoAntes()));
        values.put(RESULT_TEST_TRAMOS_RECTOS_DESPUES, String.valueOf(test.getTramoDespues()));
        values.put(RESULT_TEST_OBS_1, String.valueOf(test.getObservaciones()));
        values.put(RESULT_TEST_VERTICALES, String.valueOf(test.getEstadoVerticales()));
        values.put(RESULT_TEST_CORTES, String.valueOf(test.getEstadoCortes()));
        values.put(RESULT_TEST_ALTER, String.valueOf(test.getSuministroAlternativo()));
        values.put(RESULT_TEST_TIPO, String.valueOf(test.getCumplePlano()));
        values.put(RESULT_TEST_OBS_2, String.valueOf(test.getObservaciones2()));

        db.update(TABLE_INSPECTION_RESULT, values, RESULT_ID_INSPECCION + " = ?", new String[]{String.valueOf(idInspeccion)});
        db.close();
    }

    public void guardaDatosTestParte3(TestParte3 test, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESULT_TEST_CONST, String.valueOf(test.getConstruccionNueva()));
        values.put(RESULT_TEST_TIPO_PROP, String.valueOf(test.getTipoPropiedad()));
        values.put(RESULT_TEST_HABITANTES, String.valueOf(test.getHabitantes()));
        values.put(RESULT_TEST_BANOS, String.valueOf(test.getBanos()));
        values.put(RESULT_TEST_SUP, String.valueOf(test.getSuperficieEdificada()));
        values.put(RESULT_TEST_SUP_JARDIN, String.valueOf(test.getSuperficieJardin()));
        values.put(RESULT_TEST_ACCESO, String.valueOf(test.getAcceso()));
        values.put(RESULT_TEST_SUP_TERRENO, String.valueOf(test.getSuperficieTerreno()));
        values.put(RESULT_TEST_OBS_3, String.valueOf(test.getObservaciones1()));
        values.put(RESULT_TEST_AUTO_AB, String.valueOf(test.getAutoAbastecimiento()));
        values.put(RESULT_TEST_TIPO_FUENTE, String.valueOf(test.getTipoFuente()));
        values.put(RESULT_TEST_USO, String.valueOf(test.getUso()));
        values.put(RESULT_TEST_ACTIVO, String.valueOf(test.getActivo()));
        values.put(RESULT_TEST_CAPACIDAD_BOMBA, String.valueOf(test.getCapacidadBomba()));
        values.put(RESULT_TEST_RESOLUCION, String.valueOf(test.getResolucion()));
        values.put(RESULT_TEST_CAUDAL, String.valueOf(test.getCaudal()));
        values.put(RESULT_TEST_OBS_4, String.valueOf(test.getObservaciones2()));

        db.update(TABLE_INSPECTION_RESULT, values, RESULT_ID_INSPECCION + " = ?", new String[]{String.valueOf(idInspeccion)});
        db.close();
    }

    public ResultadoInspeccion getResultadoInspeccion(int idInspeccion) {

        ResultadoInspeccion resultado = new ResultadoInspeccion();
        TestParte1 testParte1 = new TestParte1();
        TestParte2 testParte2 = new TestParte2();
        TestParte3 testParte3 = new TestParte3();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INSPECTION_RESULT, new String[]
                        {
                                RESULT_TEST_1,
                                RESULT_TEST_2,
                                RESULT_TEST_3,
                                RESULT_TEST_USO_IMANES,
                                RESULT_TEST_INV_TOMAS,
                                RESULT_TEST_PERF_CUP,
                                RESULT_TEST_CORTAR_ENG,
                                RESULT_TEST_USO_ALAM,
                                RESULT_TEST_PRENSADO,
                                RESULT_TEST_OTRO_1,
                                RESULT_TEST_INST_PAR,
                                RESULT_TEST_BYPASS,
                                RESULT_TEST_OTRO_2,
                                RESULT_TEST_CLASS,
                                RESULT_TEST_YEAR,
                                RESULT_TEST_MARCA,
                                RESULT_TEST_REG,
                                RESULT_TEST_INST,
                                RESULT_TEST_TRAMOS_RECTOS_ANTES,
                                RESULT_TEST_TRAMOS_RECTOS_DESPUES,
                                RESULT_TEST_OBS_1,
                                RESULT_TEST_VERTICALES,
                                RESULT_TEST_CORTES,
                                RESULT_TEST_ALTER,
                                RESULT_TEST_TIPO,
                                RESULT_TEST_OBS_2,
                                RESULT_TEST_CONST,
                                RESULT_TEST_TIPO_PROP,
                                RESULT_TEST_HABITANTES,
                                RESULT_TEST_BANOS,
                                RESULT_TEST_SUP,
                                RESULT_TEST_SUP_JARDIN,
                                RESULT_TEST_ACCESO,
                                RESULT_TEST_SUP_TERRENO,
                                RESULT_TEST_OBS_3,
                                RESULT_TEST_AUTO_AB,
                                RESULT_TEST_TIPO_FUENTE,
                                RESULT_TEST_USO,
                                RESULT_TEST_ACTIVO,
                                RESULT_TEST_CAPACIDAD_BOMBA,
                                RESULT_TEST_RESOLUCION,
                                RESULT_TEST_CAUDAL,
                                RESULT_TEST_OBS_4}
                , RESULT_ID_INSPECCION + "=?",
                new String[]{String.valueOf(idInspeccion)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        testParte1.setTest1(cursor.getString(0));
        testParte1.setTest2(cursor.getString(1));
        testParte1.setTest3(cursor.getString(2));

        testParte1.setUsoImanes(cursor.getString(3));
        testParte1.setInvertirTomas(cursor.getString(4));
        testParte1.setPerforaCupula(cursor.getString(5));
        testParte1.setCortaEngranaje(cursor.getString(6));
        testParte1.setUsoAlambres(cursor.getString(7));
        testParte1.setPrensado(cursor.getString(8));
        testParte1.setOtro(cursor.getString(9));

        testParte1.setInstalacionParalela(cursor.getString(10));
        testParte1.setBypass(cursor.getString(11));
        testParte1.setOtro2(cursor.getString(12));


        resultado.setTestParte1(testParte1);
        resultado.setTestParte2(testParte2);
        resultado.setTestParte3(testParte3);

        return resultado;

    }

    public void guardaFormularioNegociacion(int idInspeccion) {

    }


}
