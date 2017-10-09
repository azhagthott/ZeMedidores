package com.zecovery.android.zemedidores.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.zecovery.android.zemedidores.models.Foto;
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
    public static final int DB_VERSION = 166;
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
    private static final String ASSIGNMENT_INSPECTOR = "inspector";
    private static final String ASSIGNMENT_ID_INSPECTION = "id_inspection";
    private static final String ASSIGNMENT_ADDRESS = "address";
    private static final String ASSIGNMENT_TYPE = "assignment_type";
    private static final String ASSIGNMENT_DATE = "date";
    private static final String ASSIGNMENT_LAT = "lat";
    private static final String ASSIGNMENT_LNG = "lng";
    private static final String ASSIGNMENT_NAME = "nombre";
    private static final String ASSIGNMENT_EMAIL = "email";
    private static final String ASSIGNMENT_FECHA_INI = "fecha_residencia";
    private static final String ASSIGNMENT_MARCA_MEDIDOR = "marca_medidor";
    private static final String ASSIGNMENT_NUMERO_MEDIDOR = "numero_medidor";
    private static final String ASSIGNMENT_ORIGIN = "origin";
    private static final String ASSIGNMENT_SOCIAL_RISK = "social_risk";
    private static final String ASSIGNMENT_TELEFONO = "telefono";
    private static final String ASSIGNMENT_RUT = "rut";
    private static final String ASSIGNMENT_ZONE = "zone";
    private static final String ASSIGNMENT_SECTOR = "sector";
    private static final String ASSIGNMENT_ORDEN = "orden";
    private static final String ASSIGNMENT_COMUNA = "comuna";
    private static final String ASSIGNMENT_MEDIDOR = "medidor";

    /* location table */
    private static final String CURRENT_LOCATION_ID_KEY = "id";
    private static final String CURRENT_LOCATION_LAT = "lat";
    private static final String CURRENT_LOCATION_LNG = "lng";

    /* meter, resident, tests table */
    private static final String RESULT_ID_KEY = "id";
    private static final String RESULT_ID_INSPECCION = "id_inspeccion";
    private static final String RESULT_MEDIDOR_UBICACION = "medidor_ubicacion";
    private static final String RESULT_MEDIDOR_ESTADO = "medidor_estado";
    private static final String RESULT_MEDIDOR_OBS = "meiddor_obs";

    /* Tests */
    private static final String RESULT_TEST_1 = "test_1";
    private static final String RESULT_TEST_2 = "test_2";
    private static final String RESULT_TEST_3 = "test_3";
    private static final String RESULT_TEST_4 = "test_4";
    private static final String RESULT_TEST_5 = "test_5";
    private static final String RESULT_TEST_USO_IMANES = "uso_imanes";
    private static final String RESULT_TEST_INV_TOMAS = "inv_tomas";
    private static final String RESULT_TEST_PERF_CUP = "perf_cup";
    private static final String RESULT_TEST_CORTAR_ENG = "cortar_eng";
    private static final String RESULT_TEST_USO_ALAM = "uso_alam";
    private static final String RESULT_TEST_PRENSADO = "prensado";
    private static final String RESULT_TEST_OTRO_1 = "otro_1";
    private static final String RESULT_TEST_OTRO_1_TEXT = "otro_1_text";
    private static final String RESULT_TEST_INST_PAR = "inst_par";
    private static final String RESULT_TEST_BYPASS = "bypass";
    private static final String RESULT_TEST_OTRO_2 = "otro_2";
    private static final String RESULT_TEST_OTRO_2_TEXT = "otro_2_text";
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
    private static final String RESULT_TEST_NOMBRE_RESIDENTE = "nombre_residente";
    private static final String RESULT_TEST_RUT_RESIDENTE = "rut_residente";
    private static final String RESULT_TEST_TELEFONO_RESIDENTE = "telefono_residente";
    private static final String RESULT_TEST_EMAIL_RESIDENTE = "email_residente";
    private static final String RESULT_TEST_FECHA_RESIDENTE = "fecha_residente";

    /* Fotos */
    private static final String RESULT_FOTO_MEDIDOR_ROTO = "photo_broken_meter";
    private static final String RESULT_FOTO_LECTURA_MEDIDOR = "photo_lecture_meter";
    private static final String RESULT_FOTO_NUMERO_MEDIDOR = "photo_number_meter";
    private static final String RESULT_METER_PANO_PHOTO = "photo_pano_meter";
    private static final String RESULT_METER_NUMBER_PROP_PHOTO = "photo_number_prop_meter";
    private static final String RESULT_FACE_PHOTO = "photo_face";

    private static final String RESULT_TEST_FOTO_RECHAZO = "foto_rechazo_inspeccion";
    private static final String RESULT_TEST_FOTO_FALLA_MEDIDOR = "foto_falla_medidor";
    private static final String RESULT_TEST_FOTO_LECTURA_MEDIDOR = "foto_lectura_medidor";
    private static final String RESULT_TEST_FOTO_NUMERO_MEDIDOR = "foto_numero_medidor";
    private static final String RESULT_TEST_FOTO_PANO_MEDIDOR = "foto_pano_medidor";
    private static final String RESULT_TEST_FOTO_NUMERO_PROPIEDAD = "foto_numero_propiedad";
    private static final String RESULT_TEST_FOTO_FACHADA_PROPIEDAD = "foto_fachada_propiedad";
    private static final String RESULT_TEST_FOTO_USO_IMANES_1 = "foto_uso_imanes_1";
    private static final String RESULT_TEST_FOTO_USO_IMANES_2 = "foto_uso_imanes_2";
    private static final String RESULT_TEST_FOTO_USO_IMANES_3 = "foto_uso_imanes_3";
    private static final String RESULT_TEST_FOTO_INV_TOMAS_1 = "foto_inv_tomas_1";
    private static final String RESULT_TEST_FOTO_INV_TOMAS_2 = "foto_inv_tomas_2";
    private static final String RESULT_TEST_FOTO_INV_TOMAS_3 = "foto_inv_tomas_3";
    private static final String RESULT_TEST_FOTO_PERF_CUPULA_1 = "foto_perf_cupula_1";
    private static final String RESULT_TEST_FOTO_PERF_CUPULA_2 = "foto_perf_cupula_2";
    private static final String RESULT_TEST_FOTO_PERF_CUPULA_3 = "foto_perf_cupula_3";
    private static final String RESULT_TEST_FOTO_CORTA_ENGRANAJE_1 = "foto_corta_engranaje_1";
    private static final String RESULT_TEST_FOTO_CORTA_ENGRANAJE_2 = "foto_corta_engranaje_2";
    private static final String RESULT_TEST_FOTO_CORTA_ENGRANAJE_3 = "foto_corta_engranaje_3";
    private static final String RESULT_TEST_FOTO_USO_ALAMBRE_1 = "foto_uso_alambre_1";
    private static final String RESULT_TEST_FOTO_USO_ALAMBRE_2 = "foto_uso_alambre_2";
    private static final String RESULT_TEST_FOTO_USO_ALAMBRE_3 = "foto_uso_alambre_3";
    private static final String RESULT_TEST_FOTO_PRENSADO_1 = "foto_prensado_1";
    private static final String RESULT_TEST_FOTO_PRENSADO_2 = "foto_prensado_2";
    private static final String RESULT_TEST_FOTO_PRENSADO_3 = "foto_prensado_3";
    private static final String RESULT_TEST_FOTO_OTRO_1_1 = "foto_otro_1_1";
    private static final String RESULT_TEST_FOTO_OTRO_1_2 = "foto_otro_1_2";
    private static final String RESULT_TEST_FOTO_OTRO_1_3 = "foto_otro_1_3";
    private static final String RESULT_TEST_FOTO_INST_PARALELA_1 = "foto_inst_paralela_1";
    private static final String RESULT_TEST_FOTO_INST_PARALELA_2 = "foto_inst_paralela_2";
    private static final String RESULT_TEST_FOTO_INST_PARALELA_3 = "foto_inst_paralela_3";
    private static final String RESULT_TEST_FOTO_BYPASS_1 = "foto_bypass_1";
    private static final String RESULT_TEST_FOTO_BYPASS_2 = "foto_bypass_2";
    private static final String RESULT_TEST_FOTO_BYPASS_3 = "foto_bypass_3";
    private static final String RESULT_TEST_FOTO_OTRO_2_1 = "foto_otro_2_1";
    private static final String RESULT_TEST_FOTO_OTRO_2_2 = "foto_otro_2_2";
    private static final String RESULT_TEST_FOTO_OTRO_2_3 = "foto_otro_2_3";

    private static final String RESULT_NUMERO_MEDIDOR = "numero_medidor";
    private static final String RESULT_DIAMETRO_MEDIDOR = "diametro_medidor";
    private static final String RESULT_LECTURA_MEDIDOR = "lectura_meiddor";
    private static final String RESULT_TEST_DIRECCION_RESIDENTE = "direccion_residente";
    private static final String RESULT_TEST_NUM_CLIENTE_RESIDENTE = "num_cliente_residente";
    private static final String RESULT_TEST_EMPRESA_INSPECTOR = "empresa_inspeccion";

    public LocalDatabase(Context context) {
        super(context, DB_MANE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ASSIGNMENT = DB_CREATE_TABLE + TABLE_ASSIGNMENT + "("
                + ASSIGNMENT_ID_KEY + " INTEGER PRIMARY_KEY, "
                + ASSIGNMENT_INSPECTOR + " TEXT, "
                + ASSIGNMENT_ID_INSPECTION + " TEXT, "
                + ASSIGNMENT_ADDRESS + " TEXT, "
                + ASSIGNMENT_TYPE + " TEXT, "
                + ASSIGNMENT_DATE + " TEXT, "
                + ASSIGNMENT_LAT + " TEXT, "
                + ASSIGNMENT_LNG + " TEXT, "
                + ASSIGNMENT_NAME + " TEXT, "
                + ASSIGNMENT_EMAIL + " TEXT, "
                + ASSIGNMENT_TELEFONO + " TEXT, "
                + ASSIGNMENT_FECHA_INI + " TEXT, "
                + ASSIGNMENT_MARCA_MEDIDOR + " TEXT, "
                + ASSIGNMENT_NUMERO_MEDIDOR + " TEXT, "
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
                + RESULT_MEDIDOR_UBICACION + " TEXT, "
                + RESULT_MEDIDOR_ESTADO + " TEXT, "
                + RESULT_MEDIDOR_OBS + " TEXT, "
                + RESULT_FOTO_MEDIDOR_ROTO + " TEXT, "
                + RESULT_FOTO_LECTURA_MEDIDOR + " TEXT, "
                + RESULT_FOTO_NUMERO_MEDIDOR + " TEXT, "
                + RESULT_METER_PANO_PHOTO + " TEXT, "
                + RESULT_METER_NUMBER_PROP_PHOTO + " TEXT, "
                + RESULT_FACE_PHOTO + " TEXT, "
                + RESULT_TEST_1 + " TEXT, "
                + RESULT_TEST_2 + " TEXT, "
                + RESULT_TEST_3 + " TEXT, "
                + RESULT_TEST_4 + " TEXT, "
                + RESULT_TEST_5 + " TEXT, "
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
                + RESULT_TEST_OBS_4 + " TEXT, "
                + RESULT_TEST_FOTO_RECHAZO + " TEXT, "
                + RESULT_TEST_FOTO_FALLA_MEDIDOR + " TEXT, "
                + RESULT_TEST_FOTO_LECTURA_MEDIDOR + " TEXT, "
                + RESULT_TEST_FOTO_NUMERO_MEDIDOR + " TEXT, "
                + RESULT_TEST_FOTO_PANO_MEDIDOR + " TEXT, "
                + RESULT_TEST_FOTO_NUMERO_PROPIEDAD + " TEXT, "
                + RESULT_TEST_FOTO_FACHADA_PROPIEDAD + " TEXT, "
                + RESULT_TEST_NOMBRE_RESIDENTE + " TEXT, "
                + RESULT_TEST_RUT_RESIDENTE + " TEXT, "
                + RESULT_TEST_TELEFONO_RESIDENTE + " TEXT, "
                + RESULT_TEST_EMAIL_RESIDENTE + " TEXT, "
                + RESULT_TEST_FECHA_RESIDENTE + " TEXT, "
                + RESULT_TEST_FOTO_USO_IMANES_1 + " TEXT, "
                + RESULT_TEST_FOTO_USO_IMANES_2 + " TEXT, "
                + RESULT_TEST_FOTO_USO_IMANES_3 + " TEXT, "
                + RESULT_TEST_FOTO_INV_TOMAS_1 + " TEXT, "
                + RESULT_TEST_FOTO_INV_TOMAS_2 + " TEXT, "
                + RESULT_TEST_FOTO_INV_TOMAS_3 + " TEXT, "
                + RESULT_TEST_FOTO_PERF_CUPULA_1 + " TEXT, "
                + RESULT_TEST_FOTO_PERF_CUPULA_2 + " TEXT, "
                + RESULT_TEST_FOTO_PERF_CUPULA_3 + " TEXT, "
                + RESULT_TEST_FOTO_CORTA_ENGRANAJE_1 + " TEXT, "
                + RESULT_TEST_FOTO_CORTA_ENGRANAJE_2 + " TEXT, "
                + RESULT_TEST_FOTO_CORTA_ENGRANAJE_3 + " TEXT, "
                + RESULT_TEST_FOTO_USO_ALAMBRE_1 + " TEXT, "
                + RESULT_TEST_FOTO_USO_ALAMBRE_2 + " TEXT, "
                + RESULT_TEST_FOTO_USO_ALAMBRE_3 + " TEXT, "
                + RESULT_TEST_FOTO_PRENSADO_1 + " TEXT, "
                + RESULT_TEST_FOTO_PRENSADO_2 + " TEXT, "
                + RESULT_TEST_FOTO_PRENSADO_3 + " TEXT, "
                + RESULT_TEST_FOTO_OTRO_1_1 + " TEXT, "
                + RESULT_TEST_FOTO_OTRO_1_2 + " TEXT, "
                + RESULT_TEST_FOTO_OTRO_1_3 + " TEXT, "
                + RESULT_TEST_FOTO_INST_PARALELA_1 + " TEXT, "
                + RESULT_TEST_FOTO_INST_PARALELA_2 + " TEXT, "
                + RESULT_TEST_FOTO_INST_PARALELA_3 + " TEXT, "
                + RESULT_TEST_FOTO_BYPASS_1 + " TEXT, "
                + RESULT_TEST_FOTO_BYPASS_2 + " TEXT, "
                + RESULT_TEST_FOTO_BYPASS_3 + " TEXT, "
                + RESULT_TEST_FOTO_OTRO_2_1 + " TEXT, "
                + RESULT_TEST_FOTO_OTRO_2_2 + " TEXT, "
                + RESULT_TEST_FOTO_OTRO_2_3 + " TEXT, "
                + RESULT_NUMERO_MEDIDOR + " TEXT, "
                + RESULT_DIAMETRO_MEDIDOR + " TEXT, "
                + RESULT_LECTURA_MEDIDOR + " TEXT, "
                + RESULT_TEST_OTRO_1_TEXT + " TEXT, "
                + RESULT_TEST_OTRO_2_TEXT + " TEXT, "
                + RESULT_TEST_DIRECCION_RESIDENTE + " TEXT, "
                + RESULT_TEST_NUM_CLIENTE_RESIDENTE + " TEXT, "
                + RESULT_TEST_EMPRESA_INSPECTOR + " TEXT)"
                + ";";

        Log.d("TAG", "CREATE_TABLE_ASSIGNMENT: " + CREATE_TABLE_ASSIGNMENT);
        Log.d("TAG", "CREATE_TABLE_CURRENT_LOCATION: " + CREATE_TABLE_CURRENT_LOCATION);
        Log.d("TAG", "CREATE_TABLE_INSPECTION_RESULT: " + CREATE_TABLE_INSPECTION_RESULT);

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


    /**
     * Guarda los datos de las inspecciones que descarga desde el servidor
     *
     * @param inspections devuelve una lista de inspecciones (objeto inspection)
     */
    public void guardaInspeccionesDescargadas(List<Inspection> inspections) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        for (Inspection inspection : inspections) {
            values.put(ASSIGNMENT_ID_INSPECTION, inspection.getId_inspeccion());
            values.put(ASSIGNMENT_INSPECTOR, inspection.getInspector());
            values.put(ASSIGNMENT_ADDRESS, inspection.getAddress());
            values.put(ASSIGNMENT_TYPE, inspection.getAssigment_type());
            values.put(ASSIGNMENT_DATE, inspection.getDate());
            values.put(ASSIGNMENT_LAT, inspection.getLat());
            values.put(ASSIGNMENT_LNG, inspection.getLng());
            values.put(ASSIGNMENT_NAME, inspection.getName_resident());
            values.put(ASSIGNMENT_TELEFONO, inspection.getPhono_resident());
            values.put(ASSIGNMENT_EMAIL, inspection.getMail_resident());
            values.put(ASSIGNMENT_FECHA_INI, inspection.getDateIni_resident());
            values.put(ASSIGNMENT_MARCA_MEDIDOR, inspection.getMarca_medidor());
            values.put(ASSIGNMENT_NUMERO_MEDIDOR, inspection.getNumero_medidor());
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

    /**
     * Guarda ubicacion actual del inspector
     *
     * @param lat latitud (double)
     * @param lng longitud (double)
     */
    public void guardaUbicacionActual(double lat, double lng) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CURRENT_LOCATION_LAT, String.valueOf(lat));
        values.put(CURRENT_LOCATION_LNG, String.valueOf(lng));
        db.insert(TABLE_CURRENT_LOCATION, null, values);
        db.close();
    }

    /**
     * Obtiene guardada del inspector
     */
    public LatLng getCurrentDbLocation() {

        double lat;
        double lng;

        SQLiteDatabase db = this.getReadableDatabase();
        String sqlCommand = SELECT_ALL + TABLE_CURRENT_LOCATION + " ORDER BY " + CURRENT_LOCATION_ID_KEY + " DESC LIMIT 1;";
        Cursor cursor = db.rawQuery(sqlCommand, null);

        if (cursor.moveToLast()) {
            lat = Double.valueOf(cursor.getString(1));
            lng = Double.valueOf(cursor.getString(2));
            cursor.close();
            return new LatLng(lat, lng);
        } else {
            return new LatLng(0, 0);
        }
    }

    /**
     * Crea la inspeccion en DB
     *
     * @param idInspeccion id de la inspecciion
     * @param empresa      nombre de la empresa que hace la inspeccion
     */
    public void creaInspeccion(int idInspeccion, String empresa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RESULT_ID_INSPECCION, String.valueOf(idInspeccion));
        values.put(RESULT_TEST_EMPRESA_INSPECTOR, empresa);
        db.insert(TABLE_INSPECTION_RESULT, null, values);
        db.close();
    }

    /**
     * Guarda los datos del residente
     *
     * @param residente    objeto residente
     * @param idInspeccion id de la inspeccion
     */
    public void guardaDatosResidente(Residente residente, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESULT_ID_INSPECCION, String.valueOf(idInspeccion));
        values.put(RESULT_TEST_DIRECCION_RESIDENTE, String.valueOf(residente.getDireccionResidente()));
        values.put(RESULT_TEST_NUM_CLIENTE_RESIDENTE, String.valueOf(residente.getNumeroCliente()));
        values.put(RESULT_TEST_NOMBRE_RESIDENTE, String.valueOf(residente.getNombreResidente()));
        values.put(RESULT_TEST_RUT_RESIDENTE, String.valueOf(residente.getRutResidente()));
        values.put(RESULT_TEST_TELEFONO_RESIDENTE, String.valueOf(residente.getTelefonoResidente()));
        values.put(RESULT_TEST_EMAIL_RESIDENTE, String.valueOf(residente.getEmailResidente()));

        db.update(TABLE_INSPECTION_RESULT, values, RESULT_ID_INSPECCION + " = ?", new String[]{String.valueOf(idInspeccion)});
        db.close();
    }

    /**
     * Obtiene los datos del residente
     *
     * @param idInspeccion id de la inspeccion
     */
    public Residente getDatosResidente(int idInspeccion) {

        Residente residente = new Residente();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_ASSIGNMENT, new String[]
                        {
                                ASSIGNMENT_NAME,
                                ASSIGNMENT_RUT,
                                ASSIGNMENT_TELEFONO,
                                ASSIGNMENT_EMAIL,
                                ASSIGNMENT_FECHA_INI,
                                ASSIGNMENT_SOCIAL_RISK
                        }

                , ASSIGNMENT_ID_INSPECTION + "=?",
                new String[]{String.valueOf(idInspeccion)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            residente.setNombreResidente(cursor.getString(0));
            residente.setRutResidente(cursor.getString(1));
            residente.setTelefonoResidente(cursor.getString(2));
            residente.setEmailResidente(cursor.getString(3));
            residente.setFechaResidente(cursor.getString(4));
            residente.setRiesgoSocial(cursor.getString(5));
            cursor.close();
        }
        return residente;
    }

    /**
     * Guarda los datos del medidor
     *
     * @param medidor      objeto medidor
     * @param idInspeccion id de la inspeccion
     */
    public void guardaDatosMedidor(Medidor medidor, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESULT_MEDIDOR_UBICACION, String.valueOf(medidor.getUbicacion()));
        values.put(RESULT_MEDIDOR_ESTADO, String.valueOf(medidor.getEstado()));
        values.put(RESULT_MEDIDOR_OBS, String.valueOf(medidor.getDescripcionFalla()));
        values.put(RESULT_NUMERO_MEDIDOR, String.valueOf(medidor.getNumeroMedidor()));
        values.put(RESULT_DIAMETRO_MEDIDOR, String.valueOf(medidor.getDiametroMedidor()));
        values.put(RESULT_LECTURA_MEDIDOR, String.valueOf(medidor.getLecturaMedidor()));

        db.update(TABLE_INSPECTION_RESULT, values, RESULT_ID_INSPECCION + " = ?", new String[]{String.valueOf(idInspeccion)});
        db.close();
    }

    /**
     * Obtiene los datos del medidor
     *
     * @param idInspeccion id de la inspeccion
     */
    public Medidor getDatosMedidor(int idInspeccion) {
        Medidor medidor = new Medidor();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_INSPECTION_RESULT, new String[]
                        {
                                RESULT_MEDIDOR_UBICACION,
                                RESULT_MEDIDOR_ESTADO,
                                RESULT_MEDIDOR_OBS,
                                RESULT_NUMERO_MEDIDOR,
                                RESULT_DIAMETRO_MEDIDOR,
                                RESULT_LECTURA_MEDIDOR
                        }

                , RESULT_ID_INSPECCION + "=?",
                new String[]{String.valueOf(idInspeccion)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            medidor.setUbicacion(cursor.getString(0));
            medidor.setEstado(cursor.getString(1));
            medidor.setDescripcionFalla(cursor.getString(2));
            medidor.setNumeroMedidor(cursor.getString(3));
            medidor.setDiametroMedidor(cursor.getString(4));
            medidor.setLecturaMedidor(cursor.getString(5));
            cursor.close();
        }
        return medidor;
    }

    /**
     * Guarda los datos de las pruebas (parte1)
     *
     * @param test         objeto TestParte1
     * @param idInspeccion id de la inspeccion
     */
    public void guardaDatosTestParte1(TestParte1 test, int idInspeccion) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESULT_TEST_1, String.valueOf(test.getTest1()));
        values.put(RESULT_TEST_2, String.valueOf(test.getTest2()));
        values.put(RESULT_TEST_3, String.valueOf(test.getTest3()));
        values.put(RESULT_TEST_4, String.valueOf(test.getTest4()));
        values.put(RESULT_TEST_5, String.valueOf(test.getTest4()));
        values.put(RESULT_TEST_USO_IMANES, String.valueOf(test.getUsoImanes()));
        values.put(RESULT_TEST_INV_TOMAS, String.valueOf(test.getInvertirTomas()));
        values.put(RESULT_TEST_PERF_CUP, String.valueOf(test.getPerforaCupula()));
        values.put(RESULT_TEST_CORTAR_ENG, String.valueOf(test.getCortaEngranaje()));
        values.put(RESULT_TEST_USO_ALAM, String.valueOf(test.getUsoAlambres()));
        values.put(RESULT_TEST_PRENSADO, String.valueOf(test.getPrensado()));
        values.put(RESULT_TEST_OTRO_1, String.valueOf(test.getOtro()));
        values.put(RESULT_TEST_OTRO_1_TEXT, String.valueOf(test.getOtroText()));
        values.put(RESULT_TEST_INST_PAR, String.valueOf(test.getInstalacionParalela()));
        values.put(RESULT_TEST_BYPASS, String.valueOf(test.getBypass()));
        values.put(RESULT_TEST_OTRO_2, String.valueOf(test.getOtro2()));
        values.put(RESULT_TEST_OTRO_2_TEXT, String.valueOf(test.getOtroText2()));

        db.update(TABLE_INSPECTION_RESULT, values, RESULT_ID_INSPECCION + " = ?", new String[]{String.valueOf(idInspeccion)});
        db.close();
    }

    /**
     * Obtiene los datos de las pruebas (parte1)
     *
     * @param idInspeccion id de la inspeccion
     */
    public TestParte1 getDatosTestParte1(int idInspeccion) {

        TestParte1 testParte1 = new TestParte1();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_INSPECTION_RESULT, new String[]
                        {
                                RESULT_TEST_1,
                                RESULT_TEST_2,
                                RESULT_TEST_3,
                                RESULT_TEST_4,
                                RESULT_TEST_5,
                                RESULT_TEST_USO_IMANES,
                                RESULT_TEST_INV_TOMAS,
                                RESULT_TEST_PERF_CUP,
                                RESULT_TEST_CORTAR_ENG,
                                RESULT_TEST_USO_ALAM,
                                RESULT_TEST_PRENSADO,
                                RESULT_TEST_OTRO_1,
                                RESULT_TEST_OTRO_1_TEXT,
                                RESULT_TEST_INST_PAR,
                                RESULT_TEST_BYPASS,
                                RESULT_TEST_OTRO_2,
                                RESULT_TEST_OTRO_2_TEXT
                        }

                , RESULT_ID_INSPECCION + "=?",
                new String[]{String.valueOf(idInspeccion)}, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            testParte1.setTest1(cursor.getString(0));
            testParte1.setTest2(cursor.getString(1));
            testParte1.setTest3(cursor.getString(2));
            testParte1.setTest4(cursor.getString(3));
            testParte1.setTest5(cursor.getString(4));
            testParte1.setUsoImanes(cursor.getString(5));
            testParte1.setInvertirTomas(cursor.getString(6));
            testParte1.setPerforaCupula(cursor.getString(7));
            testParte1.setCortaEngranaje(cursor.getString(8));
            testParte1.setUsoAlambres(cursor.getString(9));
            testParte1.setPrensado(cursor.getString(10));
            testParte1.setOtro(cursor.getString(11));
            testParte1.setOtroText(cursor.getString(12));
            testParte1.setOtro2(cursor.getString(13));
            testParte1.setOtroText2(cursor.getString(14));
            cursor.close();
        }
        return testParte1;
    }

    /**
     * Guarda los datos de las pruebas (parte2)
     *
     * @param test         objeto TestParte2
     * @param idInspeccion id de la inspeccion
     */
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

    /**
     * Guarda los datos de las pruebas (parte3)
     *
     * @param test         objeto TestParte3
     * @param idInspeccion id de la inspeccion
     */
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

    /**
     * Guarda las fotos
     *
     * @param idInspeccion id de la inspeccion
     */
    public void guardaFoto(Foto foto, int idInspeccion) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (String.valueOf(foto.getRechazoInspeccion()).length() > 10) {
            values.put(RESULT_TEST_FOTO_RECHAZO, String.valueOf(foto.getRechazoInspeccion()));
        }

        if (String.valueOf(foto.getFallaMedidor()).length() > 10) {
            values.put(RESULT_TEST_FOTO_FALLA_MEDIDOR, String.valueOf(foto.getFallaMedidor()));
        }

        if (String.valueOf(foto.getLecturaMedidor()).length() > 10) {
            values.put(RESULT_TEST_FOTO_LECTURA_MEDIDOR, String.valueOf(foto.getLecturaMedidor()));
        }

        if (String.valueOf(foto.getNumeroMedidor()).length() > 10) {
            values.put(RESULT_TEST_FOTO_NUMERO_MEDIDOR, String.valueOf(foto.getNumeroMedidor()));
        }

        if (String.valueOf(foto.getPanoramicaMedidor()).length() > 10) {
            values.put(RESULT_TEST_FOTO_PANO_MEDIDOR, String.valueOf(foto.getPanoramicaMedidor()));
        }

        if (String.valueOf(foto.getNumeroPropiedad()).length() > 10) {
            values.put(RESULT_TEST_FOTO_NUMERO_PROPIEDAD, String.valueOf(foto.getNumeroPropiedad()));
        }

        if (String.valueOf(foto.getFachadaPropiedad()).length() > 10) {
            values.put(RESULT_TEST_FOTO_FACHADA_PROPIEDAD, String.valueOf(foto.getFachadaPropiedad()));
        }

        if (String.valueOf(foto.getUsoImanes1()).length() > 10) {
            values.put(RESULT_TEST_FOTO_USO_IMANES_1, String.valueOf(foto.getUsoImanes1()));
        }

        if (String.valueOf(foto.getUsoImanes2()).length() > 10) {
            values.put(RESULT_TEST_FOTO_USO_IMANES_2, String.valueOf(foto.getUsoImanes2()));
        }

        if (String.valueOf(foto.getUsoImanes3()).length() > 10) {
            values.put(RESULT_TEST_FOTO_USO_IMANES_3, String.valueOf(foto.getUsoImanes3()));
        }

        if (String.valueOf(foto.getInvertirTomas1()).length() > 10) {
            values.put(RESULT_TEST_FOTO_INV_TOMAS_1, String.valueOf(foto.getInvertirTomas1()));
        }

        if (String.valueOf(foto.getInvertirTomas2()).length() > 10) {
            values.put(RESULT_TEST_FOTO_INV_TOMAS_2, String.valueOf(foto.getInvertirTomas2()));
        }

        if (String.valueOf(foto.getInvertirTomas3()).length() > 10) {
            values.put(RESULT_TEST_FOTO_INV_TOMAS_3, String.valueOf(foto.getInvertirTomas3()));
        }

        if (String.valueOf(foto.getPerforaCupula1()).length() > 10) {
            values.put(RESULT_TEST_FOTO_PERF_CUPULA_1, String.valueOf(foto.getPerforaCupula1()));
        }

        if (String.valueOf(foto.getPerforaCupula2()).length() > 10) {
            values.put(RESULT_TEST_FOTO_PERF_CUPULA_2, String.valueOf(foto.getPerforaCupula2()));
        }

        if (String.valueOf(foto.getPerforaCupula3()).length() > 10) {
            values.put(RESULT_TEST_FOTO_PERF_CUPULA_3, String.valueOf(foto.getPerforaCupula3()));
        }

        if (String.valueOf(foto.getCortaEngrnaje1()).length() > 10) {
            values.put(RESULT_TEST_FOTO_CORTA_ENGRANAJE_1, String.valueOf(foto.getCortaEngrnaje1()));
        }

        if (String.valueOf(foto.getCortaEngrnaje2()).length() > 10) {
            values.put(RESULT_TEST_FOTO_CORTA_ENGRANAJE_2, String.valueOf(foto.getCortaEngrnaje2()));
        }

        if (String.valueOf(foto.getCortaEngrnaje3()).length() > 10) {
            values.put(RESULT_TEST_FOTO_CORTA_ENGRANAJE_3, String.valueOf(foto.getCortaEngrnaje3()));
        }

        if (String.valueOf(foto.getUsoAlambre1()).length() > 10) {
            values.put(RESULT_TEST_FOTO_USO_ALAMBRE_1, String.valueOf(foto.getUsoAlambre1()));
        }

        if (String.valueOf(foto.getUsoAlambre2()).length() > 10) {
            values.put(RESULT_TEST_FOTO_USO_ALAMBRE_2, String.valueOf(foto.getUsoAlambre2()));
        }

        if (String.valueOf(foto.getUsoAlambre3()).length() > 10) {
            values.put(RESULT_TEST_FOTO_USO_ALAMBRE_3, String.valueOf(foto.getUsoAlambre3()));
        }

        if (String.valueOf(foto.getPrensado1()).length() > 10) {
            values.put(RESULT_TEST_FOTO_PRENSADO_1, String.valueOf(foto.getPrensado1()));
        }

        if (String.valueOf(foto.getPrensado2()).length() > 10) {
            values.put(RESULT_TEST_FOTO_PRENSADO_2, String.valueOf(foto.getPrensado2()));
        }

        if (String.valueOf(foto.getPrensado3()).length() > 10) {
            values.put(RESULT_TEST_FOTO_PRENSADO_3, String.valueOf(foto.getPrensado3()));
        }

        if (String.valueOf(foto.getOtro11()).length() > 10) {
            values.put(RESULT_TEST_FOTO_OTRO_1_1, String.valueOf(foto.getOtro11()));
        }

        if (String.valueOf(foto.getOtro12()).length() > 10) {
            values.put(RESULT_TEST_FOTO_OTRO_1_2, String.valueOf(foto.getOtro12()));
        }
        if (String.valueOf(foto.getOtro13()).length() > 10) {
            values.put(RESULT_TEST_FOTO_OTRO_1_3, String.valueOf(foto.getOtro13()));
        }

        if (String.valueOf(foto.getInstalacionParalela1()).length() > 10) {
            values.put(RESULT_TEST_FOTO_INST_PARALELA_1, String.valueOf(foto.getInstalacionParalela1()));
        }

        if (String.valueOf(foto.getInstalacionParalela2()).length() > 10) {
            values.put(RESULT_TEST_FOTO_INST_PARALELA_2, String.valueOf(foto.getInstalacionParalela2()));
        }

        if (String.valueOf(foto.getInstalacionParalela3()).length() > 10) {
            values.put(RESULT_TEST_FOTO_INST_PARALELA_3, String.valueOf(foto.getInstalacionParalela3()));
        }

        if (String.valueOf(foto.getBypass1()).length() > 10) {
            values.put(RESULT_TEST_FOTO_BYPASS_1, String.valueOf(foto.getBypass1()));
        }

        if (String.valueOf(foto.getBypass2()).length() > 10) {
            values.put(RESULT_TEST_FOTO_BYPASS_2, String.valueOf(foto.getBypass2()));
        }

        if (String.valueOf(foto.getBypass3()).length() > 10) {
            values.put(RESULT_TEST_FOTO_BYPASS_3, String.valueOf(foto.getBypass3()));
        }

        if (String.valueOf(foto.getOtro21()).length() > 10) {
            values.put(RESULT_TEST_FOTO_OTRO_2_1, String.valueOf(foto.getOtro21()));
        }

        if (String.valueOf(foto.getOtro22()).length() > 10) {
            values.put(RESULT_TEST_FOTO_OTRO_2_2, String.valueOf(foto.getOtro22()));
        }

        if (String.valueOf(foto.getOtro23()).length() > 10) {
            values.put(RESULT_TEST_FOTO_OTRO_2_3, String.valueOf(foto.getOtro23()));
        }

        db.update(TABLE_INSPECTION_RESULT, values, RESULT_ID_INSPECCION + " = ?", new String[]{String.valueOf(idInspeccion)});
        db.close();
    }

    /**
     * Obtiene las fotos
     *
     * @param idInspeccion id de la inspeccion
     */
    public Foto getFotos(int idInspeccion) {

        Foto fotos = new Foto();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_INSPECTION_RESULT, new String[]
                        {
                                RESULT_TEST_FOTO_RECHAZO,
                                RESULT_TEST_FOTO_FALLA_MEDIDOR,
                                RESULT_TEST_FOTO_LECTURA_MEDIDOR,
                                RESULT_TEST_FOTO_NUMERO_MEDIDOR,
                                RESULT_TEST_FOTO_PANO_MEDIDOR,
                                RESULT_TEST_FOTO_NUMERO_PROPIEDAD,
                                RESULT_TEST_FOTO_FACHADA_PROPIEDAD,
                                RESULT_TEST_FOTO_USO_IMANES_1,
                                RESULT_TEST_FOTO_USO_IMANES_2,
                                RESULT_TEST_FOTO_USO_IMANES_3,
                                RESULT_TEST_FOTO_INV_TOMAS_1,
                                RESULT_TEST_FOTO_INV_TOMAS_2,
                                RESULT_TEST_FOTO_INV_TOMAS_3,
                                RESULT_TEST_FOTO_PERF_CUPULA_1,
                                RESULT_TEST_FOTO_PERF_CUPULA_2,
                                RESULT_TEST_FOTO_PERF_CUPULA_3,
                                RESULT_TEST_FOTO_CORTA_ENGRANAJE_1,
                                RESULT_TEST_FOTO_CORTA_ENGRANAJE_2,
                                RESULT_TEST_FOTO_CORTA_ENGRANAJE_3,
                                RESULT_TEST_FOTO_USO_ALAMBRE_1,
                                RESULT_TEST_FOTO_USO_ALAMBRE_2,
                                RESULT_TEST_FOTO_USO_ALAMBRE_3,
                                RESULT_TEST_FOTO_PRENSADO_1,
                                RESULT_TEST_FOTO_PRENSADO_2,
                                RESULT_TEST_FOTO_PRENSADO_3,
                                RESULT_TEST_FOTO_OTRO_1_1,
                                RESULT_TEST_FOTO_OTRO_1_2,
                                RESULT_TEST_FOTO_OTRO_1_3,
                                RESULT_TEST_FOTO_INST_PARALELA_1,
                                RESULT_TEST_FOTO_INST_PARALELA_2,
                                RESULT_TEST_FOTO_INST_PARALELA_3,
                                RESULT_TEST_FOTO_BYPASS_1,
                                RESULT_TEST_FOTO_BYPASS_2,
                                RESULT_TEST_FOTO_BYPASS_3,
                                RESULT_TEST_FOTO_OTRO_2_1,
                                RESULT_TEST_FOTO_OTRO_2_2,
                                RESULT_TEST_FOTO_OTRO_2_3
                        }
                , RESULT_ID_INSPECCION + "=?",
                new String[]{String.valueOf(idInspeccion)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            fotos.setRechazoInspeccion(cursor.getString(0));
            fotos.setFallaMedidor(cursor.getString(1));
            fotos.setLecturaMedidor(cursor.getString(2));
            fotos.setNumeroMedidor(cursor.getString(3));
            fotos.setPanoramicaMedidor(cursor.getString(4));
            fotos.setNumeroPropiedad(cursor.getString(5));
            fotos.setFachadaPropiedad(cursor.getString(6));
            fotos.setUsoImanes1(cursor.getString(7));
            fotos.setUsoImanes2(cursor.getString(8));
            fotos.setUsoImanes3(cursor.getString(9));
            fotos.setInvertirTomas1(cursor.getString(10));
            fotos.setInvertirTomas2(cursor.getString(11));
            fotos.setInvertirTomas3(cursor.getString(12));
            fotos.setPerforaCupula1(cursor.getString(13));
            fotos.setPerforaCupula2(cursor.getString(14));
            fotos.setPerforaCupula3(cursor.getString(15));
            fotos.setCortaEngrnaje1(cursor.getString(16));
            fotos.setCortaEngrnaje2(cursor.getString(17));
            fotos.setCortaEngrnaje3(cursor.getString(18));
            fotos.setUsoAlambre1(cursor.getString(19));
            fotos.setUsoAlambre2(cursor.getString(20));
            fotos.setUsoAlambre3(cursor.getString(21));
            fotos.setPrensado1(cursor.getString(22));
            fotos.setPrensado2(cursor.getString(23));
            fotos.setPrensado3(cursor.getString(24));
            fotos.setOtro11(cursor.getString(25));
            fotos.setOtro12(cursor.getString(26));
            fotos.setOtro13(cursor.getString(27));
            fotos.setInstalacionParalela1(cursor.getString(28));
            fotos.setInstalacionParalela2(cursor.getString(29));
            fotos.setInstalacionParalela3(cursor.getString(30));
            fotos.setBypass1(cursor.getString(31));
            fotos.setBypass2(cursor.getString(32));
            fotos.setBypass3(cursor.getString(33));
            fotos.setOtro21(cursor.getString(34));
            fotos.setOtro22(cursor.getString(35));
            fotos.setOtro23(cursor.getString(36));
            cursor.close();
        }
        return fotos;
    }

    /**
     * Obtiene la fecha de residencia
     *
     * @param idInspeccion id de la inspeccion
     */
    public String getFechaResidencia(int idInspeccion) {

        String fecha = "";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INSPECTION_RESULT, new String[]
                        {
                                RESULT_TEST_FECHA_RESIDENTE
                        }
                , RESULT_ID_INSPECCION + "=?",
                new String[]{String.valueOf(idInspeccion)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
            fecha = cursor.getString(0);
            cursor.close();
        }
        return fecha;
    }

    /**
     * Guarda los resultados de la inspeccion
     *
     * @param idInspeccion id de la inspeccion
     */
    public ResultadoInspeccion getResultadoInspeccion(int idInspeccion) {

        ResultadoInspeccion resultado = new ResultadoInspeccion();

        Residente residente = new Residente();
        Foto fotos = new Foto();
        TestParte1 testParte1 = new TestParte1();
        TestParte2 testParte2 = new TestParte2();
        TestParte3 testParte3 = new TestParte3();
        Medidor medidor = new Medidor();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_INSPECTION_RESULT, new String[]
                        {
                                RESULT_TEST_NOMBRE_RESIDENTE,
                                RESULT_TEST_RUT_RESIDENTE,
                                RESULT_TEST_TELEFONO_RESIDENTE,
                                RESULT_TEST_EMAIL_RESIDENTE,
                                RESULT_TEST_FECHA_RESIDENTE,
                                RESULT_TEST_DIRECCION_RESIDENTE,
                                RESULT_TEST_NUM_CLIENTE_RESIDENTE,
                                RESULT_TEST_FOTO_RECHAZO,
                                RESULT_TEST_FOTO_FALLA_MEDIDOR,
                                RESULT_TEST_FOTO_LECTURA_MEDIDOR,
                                RESULT_TEST_FOTO_NUMERO_MEDIDOR,
                                RESULT_TEST_FOTO_PANO_MEDIDOR,
                                RESULT_TEST_FOTO_NUMERO_PROPIEDAD,
                                RESULT_TEST_FOTO_FACHADA_PROPIEDAD,
                                RESULT_TEST_FOTO_USO_IMANES_1,
                                RESULT_TEST_FOTO_USO_IMANES_2,
                                RESULT_TEST_FOTO_USO_IMANES_3,
                                RESULT_TEST_FOTO_INV_TOMAS_1,
                                RESULT_TEST_FOTO_INV_TOMAS_2,
                                RESULT_TEST_FOTO_INV_TOMAS_3,
                                RESULT_TEST_FOTO_PERF_CUPULA_1,
                                RESULT_TEST_FOTO_PERF_CUPULA_2,
                                RESULT_TEST_FOTO_PERF_CUPULA_3,
                                RESULT_TEST_FOTO_CORTA_ENGRANAJE_1,
                                RESULT_TEST_FOTO_CORTA_ENGRANAJE_2,
                                RESULT_TEST_FOTO_CORTA_ENGRANAJE_3,
                                RESULT_TEST_FOTO_USO_ALAMBRE_1,
                                RESULT_TEST_FOTO_USO_ALAMBRE_2,
                                RESULT_TEST_FOTO_USO_ALAMBRE_3,
                                RESULT_TEST_FOTO_PRENSADO_1,
                                RESULT_TEST_FOTO_PRENSADO_2,
                                RESULT_TEST_FOTO_PRENSADO_3,
                                RESULT_TEST_FOTO_OTRO_1_1,
                                RESULT_TEST_FOTO_OTRO_1_2,
                                RESULT_TEST_FOTO_OTRO_1_3,
                                RESULT_TEST_FOTO_INST_PARALELA_1,
                                RESULT_TEST_FOTO_INST_PARALELA_2,
                                RESULT_TEST_FOTO_INST_PARALELA_3,
                                RESULT_TEST_FOTO_BYPASS_1,
                                RESULT_TEST_FOTO_BYPASS_2,
                                RESULT_TEST_FOTO_BYPASS_3,
                                RESULT_TEST_FOTO_OTRO_2_1,
                                RESULT_TEST_FOTO_OTRO_2_2,
                                RESULT_TEST_FOTO_OTRO_2_3,
                                RESULT_TEST_1,
                                RESULT_TEST_2,
                                RESULT_TEST_3,
                                RESULT_TEST_4,
                                RESULT_TEST_5,
                                RESULT_TEST_USO_IMANES,
                                RESULT_TEST_INV_TOMAS,
                                RESULT_TEST_PERF_CUP,
                                RESULT_TEST_CORTAR_ENG,
                                RESULT_TEST_USO_ALAM,
                                RESULT_TEST_PRENSADO,
                                RESULT_TEST_OTRO_1,
                                RESULT_TEST_OTRO_1_TEXT,
                                RESULT_TEST_INST_PAR,
                                RESULT_TEST_BYPASS,
                                RESULT_TEST_OTRO_2,
                                RESULT_TEST_OTRO_2_TEXT,
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
                                RESULT_TEST_OBS_4,
                                RESULT_NUMERO_MEDIDOR,
                                RESULT_DIAMETRO_MEDIDOR,
                                RESULT_LECTURA_MEDIDOR,
                                RESULT_TEST_EMPRESA_INSPECTOR
                        }
                , RESULT_ID_INSPECCION + "=?",
                new String[]{String.valueOf(idInspeccion)}, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();

            /* Datos residente */
            residente.setNombreResidente(cursor.getString(0));
            residente.setRutResidente(cursor.getString(1));
            residente.setTelefonoResidente(cursor.getString(2));
            residente.setEmailResidente(cursor.getString(3));
            residente.setFechaResidente(cursor.getString(4));
            residente.setDireccionResidente(cursor.getString(5));
            residente.setNumeroCliente(cursor.getString(6));

            /* Fotos */
            fotos.setRechazoInspeccion(cursor.getString(7));
            fotos.setFallaMedidor(cursor.getString(8));
            fotos.setLecturaMedidor(cursor.getString(9));
            fotos.setNumeroMedidor(cursor.getString(10));
            fotos.setPanoramicaMedidor(cursor.getString(11));
            fotos.setNumeroPropiedad(cursor.getString(12));
            fotos.setFachadaPropiedad(cursor.getString(13));
            fotos.setUsoImanes1(cursor.getString(14));
            fotos.setUsoImanes2(cursor.getString(15));
            fotos.setUsoImanes3(cursor.getString(16));
            fotos.setInvertirTomas1(cursor.getString(17));
            fotos.setInvertirTomas2(cursor.getString(18));
            fotos.setInvertirTomas3(cursor.getString(19));
            fotos.setPerforaCupula1(cursor.getString(20));
            fotos.setPerforaCupula2(cursor.getString(21));
            fotos.setPerforaCupula3(cursor.getString(22));
            fotos.setCortaEngrnaje1(cursor.getString(23));
            fotos.setCortaEngrnaje2(cursor.getString(24));
            fotos.setCortaEngrnaje3(cursor.getString(25));
            fotos.setUsoAlambre1(cursor.getString(26));
            fotos.setUsoAlambre2(cursor.getString(27));
            fotos.setUsoAlambre3(cursor.getString(28));
            fotos.setPrensado1(cursor.getString(29));
            fotos.setPrensado2(cursor.getString(30));
            fotos.setPrensado3(cursor.getString(31));
            fotos.setOtro11(cursor.getString(32));
            fotos.setOtro12(cursor.getString(33));
            fotos.setOtro13(cursor.getString(34));
            fotos.setInstalacionParalela1(cursor.getString(35));
            fotos.setInstalacionParalela2(cursor.getString(36));
            fotos.setInstalacionParalela3(cursor.getString(37));
            fotos.setBypass1(cursor.getString(38));
            fotos.setBypass2(cursor.getString(39));
            fotos.setBypass3(cursor.getString(40));
            fotos.setOtro21(cursor.getString(41));
            fotos.setOtro22(cursor.getString(42));
            fotos.setOtro23(cursor.getString(43));

            /* Test 1 */
            testParte1.setTest1(cursor.getString(44));
            testParte1.setTest2(cursor.getString(45));
            testParte1.setTest3(cursor.getString(46));
            testParte1.setTest4(cursor.getString(47));
            testParte1.setTest5(cursor.getString(48));
            testParte1.setUsoImanes(cursor.getString(49));
            testParte1.setInvertirTomas(cursor.getString(50));
            testParte1.setPerforaCupula(cursor.getString(51));
            testParte1.setCortaEngranaje(cursor.getString(52));
            testParte1.setUsoAlambres(cursor.getString(53));
            testParte1.setPrensado(cursor.getString(54));
            testParte1.setOtro(cursor.getString(55));
            testParte1.setOtroText(cursor.getString(56));
            testParte1.setInstalacionParalela(cursor.getString(57));
            testParte1.setBypass(cursor.getString(58));
            testParte1.setOtro2(cursor.getString(59));
            testParte1.setOtroText2(cursor.getString(60));

            /* Test 2 */
            testParte2.setClaseMedidor(cursor.getString(61));
            testParte2.setAnoMedidor(cursor.getString(62));
            testParte2.setMarca(cursor.getString(63));
            testParte2.setRegistrador(cursor.getString(64));
            testParte2.setInstalacion(cursor.getString(65));
            testParte2.setTramoAntes(cursor.getString(66));
            testParte2.setTramoDespues(cursor.getString(67));
            testParte2.setObservaciones(cursor.getString(68));
            testParte2.setEstadoVerticales(cursor.getString(69));
            testParte2.setEstadoCortes(cursor.getString(70));
            testParte2.setSuministroAlternativo(cursor.getString(71));
            testParte2.setCumplePlano(cursor.getString(72));
            testParte2.setObservaciones2(cursor.getString(73));

            /* Test 3 */
            testParte3.setConstruccionNueva(cursor.getString(74));
            testParte3.setTipoPropiedad(cursor.getString(75));
            testParte3.setHabitantes(cursor.getString(76));
            testParte3.setBanos(cursor.getString(77));
            testParte3.setSuperficieEdificada(cursor.getString(78));
            testParte3.setSuperficieJardin(cursor.getString(79));
            testParte3.setAcceso(cursor.getString(80));
            testParte3.setSuperficieTerreno(cursor.getString(81));
            testParte3.setObservaciones1(cursor.getString(82));
            testParte3.setAutoAbastecimiento(cursor.getString(83));
            testParte3.setTipoFuente(cursor.getString(84));
            testParte3.setUso(cursor.getString(85));
            testParte3.setActivo(cursor.getString(86));
            testParte3.setCapacidadBomba(cursor.getString(87));
            testParte3.setResolucion(cursor.getString(88));
            testParte3.setCaudal(cursor.getString(89));
            testParte3.setObservaciones2(cursor.getString(90));

            /* Medidor */
            medidor.setNumeroMedidor(cursor.getString(91));
            medidor.setDiametroMedidor(cursor.getString(92));
            medidor.setLecturaMedidor(cursor.getString(93));

            resultado.setResidente(residente);
            resultado.setFotos(fotos);
            resultado.setTestParte1(testParte1);
            resultado.setTestParte2(testParte2);
            resultado.setTestParte3(testParte3);
            resultado.setMedidor(medidor);
            resultado.setEmpresaInspeccion(cursor.getString(94));
            cursor.close();
        }
        return resultado;
    }
}
