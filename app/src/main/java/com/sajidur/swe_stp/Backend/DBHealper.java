package com.sajidur.swe_stp.Backend;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHealper extends SQLiteOpenHelper {
    private final static String TAG = "DatabaseHelper";
    private final Context context;
    private static final String DATABASE_NAME = "swestp.db";
    private static final int DATABASE_VERSION = 1;
    public static String pathToSaveDBFile ;

    public DBHealper(Context context, String path) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;
        pathToSaveDBFile=path+"/"+DATABASE_NAME;
    }

    public DBHealper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context=context;

    }


    public String getPathToSaveDBFile(){
        return pathToSaveDBFile;
    }




    private boolean checkDataBase() {
        boolean checkDB = false;
        try {
            File file = new File(pathToSaveDBFile);
            checkDB = file.exists();
        } catch(SQLiteException e) {
            Log.d(TAG, e.getMessage());
        }
        return checkDB;
    }


    public void prepareDatabase() throws IOException {
        boolean dbExist = checkDataBase();
        if (dbExist) {
            Log.d(TAG, "Database exists.");
            int currentDBVersion = getVersionId();
            if (DATABASE_VERSION > currentDBVersion) {
                Log.d(TAG, "Database version is higher than old.");
                deleteDb();
                try {
                    copyDataBase();
                } catch (IOException e) {
                    Log.e(TAG, e.getMessage());
                }
            }
        } else {
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }




    private void copyDataBase() throws IOException {
        OutputStream os = new FileOutputStream(pathToSaveDBFile);
        InputStream is = context.getAssets().open("sqlite/"+DATABASE_NAME);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
        is.close();
        os.flush();
        os.close();
    }


    public void deleteDb() {
        File file = new File(pathToSaveDBFile);
        if(file.exists()) {
            file.delete();
            Log.d(TAG, "Database deleted.");
        }
    }



    public int getVersionId() {
        int v=0;
        try {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(pathToSaveDBFile, null, SQLiteDatabase.OPEN_READONLY);

            String query = "SELECT * FROM DbVersion";

            Cursor cursor = db.rawQuery(query, null);
            cursor.moveToFirst();
            v = cursor.getInt(0);
            db.close();
        }catch (Exception e){

        }
        System.out.println(v);
        return v;

    }

    @Override
    public void onCreate(SQLiteDatabase db) {



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
