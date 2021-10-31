package com.app.apprecetario.utilidad

import android.R
import android.content.Context
import android.webkit.URLUtil
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import android.widget.Toast
import com.app.apprecetario.Receta

class AdminIngredientes {

    fun convertirString(ingrediente:Array<String>, cantidad: Array<Int>, medida: Array<String>): Array<String>{
        var listaIngredientes = arrayOf<String>()

        for (i in 0..(ingrediente.size-1)){
            listaIngredientes += (ingrediente[i] + " " + cantidad[i] + " " + medida[i])
        }

        return listaIngredientes
    }

    fun EliminarElementoString (index:Int, Elemento:Array<String>): Array<String>{
        var arrayAuxiliar = arrayOf<String>()

        for (i in 0..(Elemento.size-1)){
            if(i != index ){
                arrayAuxiliar += Elemento[i]
            }
        }
        return arrayAuxiliar
    }

    fun EliminarElementoInt (index:Int, Elemento:Array<Int>): Array<Int>{
        var arrayAuxiliar = arrayOf<Int>()

        for (i in 0..(Elemento.size-1)){
            if(i != index ){
                arrayAuxiliar += Elemento[i]
            }
        }
        return arrayAuxiliar
    }

    fun listaDespelgable(desplegable: Spinner, contexto: Context){
        val listaCantidades = arrayOf("Kg", "Gr", "Lt", "Ml","Pza")
        val adaptador = ArrayAdapter<String>(contexto, R.layout.simple_spinner_item, listaCantidades)
        desplegable.adapter = adaptador
    }

    fun llenarListView( contexto: Context,listViewEntrada: ListView, arrayEntrada1: Array<String>,
                        arrayEntrada2: MutableList<Receta>){

        if (arrayEntrada1.size > 0){
            val listaIngredientes = ArrayAdapter<String>(contexto,android.R.layout.simple_list_item_1, arrayEntrada1)
            listViewEntrada.adapter = listaIngredientes
        }else{
            val listaIngredientes = ArrayAdapter<Receta>(contexto,android.R.layout.simple_list_item_1, arrayEntrada2)
            listViewEntrada.adapter = listaIngredientes
        }
    }

    fun validarDatosIngrediente(context: Context, ingrediente: String, cantidad: String): Boolean{
        if (ingrediente == "" && cantidad == ""){
            Toast.makeText(context, "Nombre de ingrediente y Cantidad vacio. Favor de completar para hacer el registro.",
                           Toast.LENGTH_SHORT).show()
            return false
        }

        if (ingrediente == ""){
            Toast.makeText(context, "Nombre de ingrediente vacio. Favor de completarlo para registralo.",
                           Toast.LENGTH_SHORT).show()
            return false
        }

        if (cantidad == ""){
            Toast.makeText(context, "Cantidad de ingrediente vacio. Favor de completarlo para registralo.",
                Toast.LENGTH_SHORT).show()
            return false
        }

        try {
            val valorInt = cantidad.toInt();
            if (valorInt <= 0){
                Toast.makeText(context, "Cantidad de ingrediente debe ser mayor a cero.",
                    Toast.LENGTH_SHORT).show()
                return false
            }
        } catch (nfe: NumberFormatException){
            Toast.makeText(context, "La cantidad debe ser un valor númerico de tipo entero",
                Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }

    fun validarDatosReceta(context: Context, NombreReceta: String, ingrediente: Array<String> ,cantidad: Array<Int>,
                           medica: Array<String> , Proceso: String ,EnlaceUno: String, EnlaceDos: String): Boolean {
        if (NombreReceta == "" && ingrediente.size < 1 && cantidad.size < 1  && medica.size < 1 && Proceso == "" &&
            EnlaceUno == "" && EnlaceDos == ""){
            Toast.makeText(context, "Los campos estan vacios. Favor de llenarlos",
                Toast.LENGTH_SHORT).show()
            return false
        }

        if (NombreReceta == ""){
            Toast.makeText(context, "Nombre de receta esta vacio. Favor de llenarlos",
                Toast.LENGTH_SHORT).show()
            return false
        }
        if ( ingrediente.size < 1 && cantidad.size < 1  && medica.size < 1 ){
            Toast.makeText(context, "No existe algun ingrediente. Favor de agregar.",
                Toast.LENGTH_SHORT).show()
            return false
        }
        if ( Proceso == ""){
            Toast.makeText(context, "El proceso estan vacio. Favor de llenarlos",
                Toast.LENGTH_SHORT).show()
            return false
        }

        /*
        if (URLUtil.isValidUrl(EnlaceUno)){
            Toast.makeText(context, "La liga introducida no es valida.",
                Toast.LENGTH_SHORT).show()
            return false
        }
        if (URLUtil.isValidUrl(EnlaceDos)){
            Toast.makeText(context, "La liga introducida no es valida.",
                Toast.LENGTH_SHORT).show()
            return false
        }
         */

        return true
    }
}