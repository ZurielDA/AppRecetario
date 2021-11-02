package com.app.apprecetario.BD

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.app.apprecetario.Receta

class DAOReceta {

    fun registrarRecetas(context:Context, nombre: String, proceso: String, enlaceUno:String, enlaceDos: String,
                         categoria: Int, ingrediente: Array<String>, cantidad: Array<Int>, medica: Array<String>,
                         numeroPersona: Int): Boolean{

        var seRegistro: Boolean = true
        try {
            var conexionBD = ConexionBD()
            val bd = conexionBD.abrirConexion(context, "Administracion")
            val registroReceta = ContentValues()

            registroReceta.put("nombre", nombre)
            registroReceta.put("proceso", proceso)
            registroReceta.put("enlaceUno",enlaceUno)
            registroReceta.put("enlaceDos",enlaceDos)
            registroReceta.put("Categoria",categoria)
            registroReceta.put("numeroPersona",numeroPersona)

            var idReceta = bd.insert("receta", null, registroReceta)

            for(i in 0..ingrediente.size-1){
                val registroIngrediente = ContentValues()
                registroIngrediente.put("id_receta",idReceta)
                registroIngrediente.put("nombre",ingrediente[i])
                registroIngrediente.put("cantidad",cantidad[i])
                registroIngrediente.put("tipo",medica[i])
                bd.insert("ingrediente", null, registroIngrediente)
            }
            bd.close()

        }catch (ex: NullPointerException){
            ex.printStackTrace()
            seRegistro = false
        }
        return seRegistro
    }

    fun obtenerRecetas(context: Context, categoriaReceta: Int): MutableList<Receta>{
        var listaReceta: MutableList<Receta> = mutableListOf()
        try {
            val conexionBDReceta = ConexionBD()
            val bdReceta = conexionBDReceta.abrirConexion(context, "Administracion")

            val fileReceta = bdReceta.rawQuery("Select * From receta Where categoria = '${categoriaReceta}' ",
                null)

            while(fileReceta.moveToNext()){

                var receta = Receta()
                receta.idReceta = fileReceta.getInt(0)
                receta.nombreReceta = fileReceta.getString(1)
                receta.proceso = fileReceta.getString(2)
                receta.enlaceUno = fileReceta.getString(3)
                receta.enlaceDos = fileReceta.getString(4)
                receta.numeroPersona = fileReceta.getInt(6)

                val conexionBDIngrediente = ConexionBD()
                val bdIngrediente = conexionBDIngrediente.abrirConexion(context, "Administracion")

                val fileIngrediente = bdIngrediente.rawQuery("Select * From ingrediente Where id_receta = '${receta.idReceta}' ",
                    null)
                while(fileIngrediente.moveToNext()){
                    receta.idIngrediente +=  fileIngrediente.getInt(0)
                    receta.ingrediente += fileIngrediente.getString(2)
                    receta.cantidad += fileIngrediente.getInt(3)
                    receta.medida += fileIngrediente.getString(4)
                }
                bdIngrediente.close()
                listaReceta.add(receta)
            }
            bdReceta.close()
        }catch (ex: NullPointerException){
            ex.printStackTrace()
        }
        return listaReceta
    }

    fun eliminarReceta(context: Context, idReceta: Int): Boolean {
        try {
            val conexionBD = ConexionBD()
            val bd = conexionBD.abrirConexion(context, "Administracion")
            bd.execSQL("Delete from receta where id = '${idReceta}'")
            bd.close()
        }catch (ex: NullPointerException){
            ex.printStackTrace()
            return false
        }
        return true
    }

    fun obtenerReceta(context: Context, idReceta: Int): Receta{
        val receta = Receta()
        try {
            val conexionBDReceta = ConexionBD()
            val bdReceta = conexionBDReceta.abrirConexion(context, "Administracion")

            val fileReceta = bdReceta.rawQuery("Select * From receta Where id = '${idReceta}' ",
                null)
            if(fileReceta.moveToFirst()) {
                receta.idReceta = fileReceta.getInt(0)
                receta.nombreReceta = fileReceta.getString(1)
                receta.proceso = fileReceta.getString(2)
                receta.enlaceUno = fileReceta.getString(3)
                receta.enlaceDos = fileReceta.getString(4)
                receta.numeroPersona = fileReceta.getInt(6)

                bdReceta.close()
            }
            val conexionBDIngrediente = ConexionBD()
            val bdIngrediente = conexionBDIngrediente.abrirConexion(context, "Administracion")

            val fileIngrediente = bdIngrediente.rawQuery("Select * From ingrediente Where id_receta = '${idReceta}' ",
                null)
            while(fileIngrediente.moveToNext()){
                receta.idIngrediente +=  fileIngrediente.getInt(0)
                receta.ingrediente += fileIngrediente.getString(2)
                receta.cantidad += fileIngrediente.getInt(3)
                receta.medida += fileIngrediente.getString(4)
            }
            bdIngrediente.close()

        }catch (ex: NullPointerException){
            ex.printStackTrace()
        }
        return receta
    }

    fun modificarReceta(context:Context, idReceta: Int, nombre: String, proceso: String, enlaceUno:String,
                        enlaceDos: String, numeroPersona: Int): Boolean{
        var seRegistro: Boolean = true

        try {
            var conexionBD = ConexionBD()
            val bd = conexionBD.abrirConexion(context, "Administracion")

            val contentValues = ContentValues()

            contentValues.put("nombre",nombre)
            contentValues.put("proceso",proceso)
            contentValues.put("enlaceUno",enlaceUno)
            contentValues.put("enlaceDos",enlaceDos)
            contentValues.put("numeroPersona",numeroPersona)

            bd.update("receta",contentValues, "id = " + idReceta ,null)

            bd.close()
        }catch (ex: NullPointerException){
            ex.printStackTrace()
            seRegistro = false
        }
        return seRegistro
    }

    fun registrarIngrediente(context:Context, idReceta: Int, idIngrediente: Array<Int>,ingrediente: Array<String>,
                             cantidad: Array<Int>, medica: Array<String>): Boolean{

        var seRegistro: Boolean = true
        try {


            for(i in 0..ingrediente.size-1){
                var conexionBD = ConexionBD()
                val bd = conexionBD.abrirConexion(context, "Administracion")
                if(idIngrediente[i] == -1){
                    val registroIngrediente = ContentValues()
                    registroIngrediente.put("id_receta",idReceta)
                    registroIngrediente.put("nombre",ingrediente[i])
                    registroIngrediente.put("cantidad",cantidad[i])
                    registroIngrediente.put("tipo",medica[i])
                    bd.insert("ingrediente", null, registroIngrediente)
                }
                bd.close()
            }
        }catch (ex: NullPointerException){
            ex.printStackTrace()
            seRegistro = false
        }
        return seRegistro
    }

    fun modificarIngrediente(context:Context,ingredienteModificar: Array<Int>,idIngrediente: Array<Int>,ingrediente: Array<String>,
                             cantidad: Array<Int>, medica: Array<String>): Boolean{
        var seRegistro: Boolean = true

        try {


            val idInvalido = -1

            for(i in 0..ingredienteModificar.size-1){
                for(j in 0..ingrediente.size-1){
                    if(ingredienteModificar[i] == idIngrediente[j] && ingredienteModificar[i] != idInvalido) {
                        var conexionBD = ConexionBD()
                        val bd = conexionBD.abrirConexion(context, "Administracion")
                        val contentValues = ContentValues()

                        contentValues.put("nombre",ingrediente[j])
                        contentValues.put("cantidad",cantidad[j])
                        contentValues.put("tipo",medica[j])

                        bd.update("ingrediente",contentValues, "id = " + ingredienteModificar[i],null)
                        bd.close()
                    }
                }
            }

        }catch (ex: NullPointerException){
            ex.printStackTrace()
            seRegistro = false
        }
        return seRegistro
    }

    fun eliminarIngrediente(context:Context,idIngrediente: Array<Int>): Boolean{
        var seRegistro: Boolean = true

        try {
            for(i in 0..idIngrediente.size-1){
                var conexionBD = ConexionBD()
                val bd = conexionBD.abrirConexion(context, "Administracion")

                bd.delete("ingrediente", "id = " + idIngrediente[i], null)

                bd.close()
            }
        }catch (ex: NullPointerException){
            ex.printStackTrace()
            seRegistro = false
        }
        return seRegistro
    }
}