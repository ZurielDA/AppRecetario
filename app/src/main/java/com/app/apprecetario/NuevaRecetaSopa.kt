package com.app.apprecetario

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class NuevaRecetaSopa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_receta_sopa)

        //Elementos para agregacion de un ingrediente
        val etIngrediente = findViewById<EditText>(R.id.etIngrediente)
        val etCantidad = findViewById<EditText>(R.id.etCantidad)
        val spnCantidad = findViewById<Spinner>(R.id.spnCantidad)
        val btnAgregarIngrediente = findViewById<Button>(R.id.btnAgregarIngredienteRegistro)
        val lvIngredientes = findViewById<ListView>(R.id.lvIngredientes)

        //Medidas de los ingredientes
        val listaCantidades = arrayOf("Kg","Pza")
        val adaptador = ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listaCantidades)
        spnCantidad.adapter = adaptador
        
        //Cantidad de ingredientes y sus elementos
        var numeroIngrediente: Int = 0
        var columnaIncial: Int = 0
        var columnaFinal: Int = 2
        
        //Lista de ingredientes
        var listaIngredientes = arrayOf<Array<String>>()
        var ingrediente = arrayOf<String>()

        //Agrega un nuevo ingredientes
        btnAgregarIngrediente.setOnClickListener {
                                                            // ¿Este for es necesario?
            for (i in numeroIngrediente..numeroIngrediente) {
                var elemento = arrayOf<String>()
                //Registro de cada elemento del ingrediente
                for (j in columnaIncial..columnaFinal) {
                    if (j == 0){
                        elemento += etIngrediente.getText().toString()
                    }
                    if (j == 1){
                        elemento += etCantidad.getText().toString()
                    }
                    if (j == 2){
                        elemento += spnCantidad.selectedItem.toString()
                    }
                }
                // Elementos del ingrediente arreglo
                listaIngredientes += elemento
                elemento = arrayOf("")
            }
            //Registro de ingrediente en fila
            ingrediente = arrayOf("")
            var filaIngrediente: String = ""

            //La matriz listaIngredientes pasa a ser un arreglo ingrediente
            for (elemento in listaIngredientes ) {
                for (dato in elemento) {
                    filaIngrediente = filaIngrediente + dato + " "
                }
                ingrediente += filaIngrediente

                filaIngrediente = ""
            }
            //Se añade el arreglo a la listView
            val listaIngredientes = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ingrediente)
            lvIngredientes.adapter = listaIngredientes
        }

        var ingredienteEleminar: String = ""

        lvIngredientes.setOnItemClickListener { adapterView, view, i, l ->
            ingredienteEleminar = ingrediente[i]
        }

        val btnEliminar = findViewById<Button>(R.id.btnEliminarIngredienteRegistro)

        btnEliminar.setOnClickListener {
            var listaIngredientesAuxiliar = arrayOf<Array<String>>()
            var filaIngrediente: String = ""

            //La matriz listaIngredientes pasa a ser un arreglo ingrediente
            for (elemento in listaIngredientes ) {
                for (dato in elemento) {
                    filaIngrediente = filaIngrediente + dato + " "
                }
                if (ingredienteEleminar != filaIngrediente ){
                    listaIngredientesAuxiliar = listaIngredientesAuxiliar + elemento
                }
                filaIngrediente = ""
            }
            listaIngredientes = listaIngredientesAuxiliar
            numeroIngrediente  -= 1

            //Registro de ingrediente en fila
            ingrediente = arrayOf("")

            filaIngrediente = ""

            //La matriz listaIngredientes pasa a ser un arreglo ingrediente
            for (elemento in listaIngredientes ) {
                for (dato in elemento) {
                    filaIngrediente = filaIngrediente + dato + " "
                }
                ingrediente += filaIngrediente

                filaIngrediente = ""
            }
            //Se añade el arreglo a la listView
            val listaIngredientes = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, ingrediente)
            lvIngredientes.adapter = listaIngredientes
        }


        val etProceso = findViewById<EditText>(R.id.etProceso)
        val etEnlaceUno = findViewById<EditText>(R.id.etEnlaceUno)
        val etEnlaceDos = findViewById<EditText>(R.id.etEnlaceDos)
        val btnGuardar = findViewById<Button>(R.id.btnGuardarRegistro)
        val btnCancelar = findViewById<Button>(R.id.btnCancelarRegistro)








    }
}