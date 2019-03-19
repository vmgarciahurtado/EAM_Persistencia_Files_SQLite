package com.example.victor.files_sqlite.Infraestructura;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by victor on 3/19/19.
 */

public class Conexion extends SQLiteOpenHelper {

    private static final String database = "administracion.db";
    private static final SQLiteDatabase.CursorFactory factory = null;
    private static final int version = 1;
    SQLiteDatabase db;

    public Conexion(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Conexion(Context context){
        super(context,database,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(" +
                "cedula text primary key, " +
                "nombre text, " +
                "apellido text, " +
                "edad integer" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
        onCreate(db);
    }

    public void cerrarConexion(){
        db.close();
    }

    public boolean ejecutarInsert(String tabla,ContentValues registro){
        try {
            db = this.getWritableDatabase();
            int res = (int) db.insert(tabla,null,registro);
            cerrarConexion();
            if (res != -1){
                return  true;
            }else {
                return false;
            }

        }catch (Exception e){
            return false;
        }

    }

    public boolean ejecutarDelete(String tabla,String condicion){
        db = this.getWritableDatabase();
        int cant = db.delete(tabla,condicion,null);
        cerrarConexion();
        if (cant >= 1){
            return true;
        }else {
            return false;
        }
    }

    public boolean ejecutarUpdate(String tabla,String condicion,ContentValues registro){
        try {
            db = this.getWritableDatabase();
            int cant = db.update(tabla,registro,condicion,null);
            cerrarConexion();

            if (cant == 1){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }

    public Cursor ejecutarSearch(String consulta){
        try {
            db = this.getWritableDatabase();

            Cursor fila = db.rawQuery(consulta,null);
            return fila;
        }catch (Exception e){
            cerrarConexion();
            return null;
        }
    }
}
