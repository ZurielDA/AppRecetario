package com.app.apprecetario.BD

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AdminSQLlite (context: Context, name:String, factory: SQLiteDatabase.CursorFactory?, version: Int):
    SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase) {


        db.execSQL("create table if not exists receta( id integer primary key autoincrement," +
                "nombre text," +
                "proceso text,"+
                "enlaceUno text,"+
                "enlaceDos text," +
                "categoria int)")

        db.execSQL("create table if not exists ingrediente( id integer primary key autoincrement," +
                "id_receta integer," +
                "nombre text," +
                "cantidad real," +
                "tipo text," +
                "foreign key(id_receta) references receta(id))")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}