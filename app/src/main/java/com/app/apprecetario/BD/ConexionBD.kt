package com.app.apprecetario.BD

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class ConexionBD {
    fun abrirConexion(context: Context, nombreBd:String ): SQLiteDatabase {

        val admin = AdminSQLlite (context, "nombreBd", null, 1)
        val bd: SQLiteDatabase = admin.writableDatabase
        return bd
    }
}