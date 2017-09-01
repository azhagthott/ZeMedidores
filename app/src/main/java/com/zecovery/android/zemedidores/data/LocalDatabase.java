package com.zecovery.android.zemedidores.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zecovery.android.zemedidores.models.Assignment;

import java.util.List;

import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_ADDRESS;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_DATE;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_DESCRIPTION;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_EMAIL;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_ID_INSPECTION;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_ID_KEY;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_LAT;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_LNG;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_METER_LOCATION;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_METER_STATUS;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_NAME;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_ORIGIN;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_SECTOR;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_SOCIAL_RISK;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_TYPE;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_UID;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_VISIBILITY;
import static com.zecovery.android.zemedidores.data.Constant.ASSIGNMENT_ZONE;
import static com.zecovery.android.zemedidores.data.Constant.DB_CREATE_TABLE;
import static com.zecovery.android.zemedidores.data.Constant.DB_DROP_TABLE;
import static com.zecovery.android.zemedidores.data.Constant.DB_MANE;
import static com.zecovery.android.zemedidores.data.Constant.DB_VERSION;
import static com.zecovery.android.zemedidores.data.Constant.TABLE_ASSIGNMENT;

/**
 * Created by fbarrios80 on 15-08-17.
 */

public class LocalDatabase extends SQLiteOpenHelper {

    public LocalDatabase(Context context) {
        super(context, DB_MANE, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_ASSIGNMENT = DB_CREATE_TABLE + TABLE_ASSIGNMENT + "("
                + ASSIGNMENT_ID_KEY + "INTEGER PRIMARY_KEY, "
                + ASSIGNMENT_ID_INSPECTION + "TEXT, "
                + ASSIGNMENT_NAME + "TEXT, "
                + ASSIGNMENT_DESCRIPTION + "TEXT, "
                + ASSIGNMENT_ORIGIN + "TEXT, "
                + ASSIGNMENT_ADDRESS + "TEXT, "
                + ASSIGNMENT_DATE + "TEXT, "
                + ASSIGNMENT_UID + "TEXT, "
                + ASSIGNMENT_EMAIL + "TEXT, "
                + ASSIGNMENT_METER_LOCATION + "TEXT, "
                + ASSIGNMENT_METER_STATUS + "TEXT, "
                + ASSIGNMENT_TYPE + "TEXT, "
                + ASSIGNMENT_SOCIAL_RISK + "TEXT, "
                + ASSIGNMENT_ZONE + "TEXT, "
                + ASSIGNMENT_SECTOR + "TEXT, "
                + ASSIGNMENT_VISIBILITY + "TEXT, "
                + ASSIGNMENT_LAT + "TEXT, "
                + ASSIGNMENT_LNG + "TEXT);";
        db.execSQL(CREATE_TABLE_ASSIGNMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(DB_DROP_TABLE + TABLE_ASSIGNMENT);
        onCreate(db);
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
}
