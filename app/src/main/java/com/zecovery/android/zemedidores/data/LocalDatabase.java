package com.zecovery.android.zemedidores.data;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zecovery.android.zemedidores.models.Assignment;

import java.util.List;

/**
 * Created by fbarrios80 on 15-08-17.
 */

public class LocalDatabase extends SQLiteOpenHelper {

    public LocalDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public LocalDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

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
