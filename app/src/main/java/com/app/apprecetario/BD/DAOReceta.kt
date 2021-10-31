package com.app.apprecetario.BD

import android.content.ContentValues
import android.content.Context
import android.widget.Toast
import com.app.apprecetario.Receta

class DAOReceta {

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

    fun registrarRecetas(context:Context, nombre: String, proceso: String, enlaceUno:String, enlaceDos: String,
                         categoria: Int, ingrediente: Array<String>, cantidad: Array<Int>, medica: Array<String>): Boolean{

        var seRegistro: Boolean = true
        try {
            var conexionBD = ConexionBD()
            val bd = conexionBD.abrirConexion(context, "Administracion")
            val registro = ContentValues()

            registro.put("nombre", nombre)
            registro.put("proceso", proceso)
            registro.put("enlaceUno",enlaceUno)
            registro.put("enlaceDos",enlaceDos)
            registro.put("Categoria",categoria)

            var idReceta = bd.insert("receta", null, registro)

            for(i in 0..ingrediente.size-1){
                registro.put("id_receta",idReceta)
                registro.put("nombre",ingrediente[i])
                registro.put("cantidad",cantidad[i])
                registro.put("tipo",medica[i])
                bd.insert("ingrediente", null, registro)
            }
            bd.close()

        }catch (ex: NullPointerException){
            ex.printStackTrace()
            seRegistro = false
        }
        return seRegistro
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

}