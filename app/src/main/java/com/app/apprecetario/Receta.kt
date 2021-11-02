package com.app.apprecetario

class Receta {
    var idReceta:Int = -1
    var nombreReceta: String = ""
    var idIngrediente: Array<Int> = arrayOf()
    var ingrediente: Array<String> = arrayOf()
    var cantidad: Array<Int> = arrayOf()
    var medida: Array<String> = arrayOf()
    var proceso: String = ""
    var enlaceUno: String = ""
    var enlaceDos: String = ""
    var numeroPersona:Int = 1

    constructor(
        idReceta: Int,
        nombreReceta: String,
        idIngrediente: Array<Int>,
        ingrediente: Array<String>,
        cantidad: Array<Int>,
        medida: Array<String>,
        proceso: String,
        enlaceUno: String,
        enlaceDos: String,
        numeroPersona: Int
    ) {
        this.idReceta = idReceta
        this.nombreReceta = nombreReceta
        this.idIngrediente = idIngrediente
        this.ingrediente = ingrediente
        this.cantidad = cantidad
        this.medida = medida
        this.proceso = proceso
        this.enlaceUno = enlaceUno
        this.enlaceDos = enlaceDos
        this.numeroPersona = numeroPersona
    }
    constructor() {
        this.idReceta = idReceta
        this.nombreReceta = nombreReceta
        this.idIngrediente = idIngrediente
        this.ingrediente = ingrediente
        this.cantidad = cantidad
        this.medida = medida
        this.proceso = proceso
        this.enlaceUno = enlaceUno
        this.enlaceDos = enlaceDos
        this.numeroPersona = numeroPersona
    }

    override fun toString(): String {
        return nombreReceta
    }

}