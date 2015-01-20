package com.sbhachu.gojimochallenge.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sbhachu.gojimochallenge.data.model.Qualification;
import com.sbhachu.gojimochallenge.data.model.QualificationSubject;
import com.sbhachu.gojimochallenge.data.model.Subject;

import java.sql.SQLException;

/**
 * DatabaseHelper.class
 * OrmLite Database Helper Class
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String TAG = DatabaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "gojimo_challenge.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(TAG, "onCreate");
            TableUtils.createTable(connectionSource, QualificationSubject.class);
            TableUtils.createTable(connectionSource, Qualification.class);
            TableUtils.createTable(connectionSource, Subject.class);
        } catch (SQLException e) {
            Log.e(TAG, "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            Log.i(TAG, "onUpgrade");
            TableUtils.dropTable(connectionSource, QualificationSubject.class, true);
            TableUtils.dropTable(connectionSource, Qualification.class, true);
            TableUtils.dropTable(connectionSource, Subject.class, true);

            // recreate tables
            this.onCreate(database, connectionSource);
        } catch (SQLException e) {
            Log.e(TAG, "Can't drop database", e);
            throw new RuntimeException(e);
        }
    }
}
