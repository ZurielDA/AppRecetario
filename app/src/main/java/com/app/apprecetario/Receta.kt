package com.app.apprecetario

class Receta constructor(var id:Int = DEFAULT_ID, var nombreReceta: String, ingredinete: ArrayList<String>,
                         var cantidad: ArrayList<Long>, var medida: String, var proceso: String,
                         var enlaceUno: String, var enlaceDos: String) {

    companion object{
        private const val DEFAULT_ID: Int = 0
    }

}