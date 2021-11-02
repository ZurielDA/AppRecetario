package com.app.apprecetario

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.app.apprecetario.BD.ConexionBD
import com.app.apprecetario.BD.DAOReceta
import com.app.apprecetario.utilidad.AdminIngredientes

class ConsultarRecetaSopa : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var receta = Receta()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consultar_receta_sopa)

        val bundle = intent.extras
        val idReceta = bundle?.getInt("idReceta")

        if (idReceta != null) {
            inicializarElementos(idReceta)
        }
    }

    fun inicializarElementos(idReceta: Int){
        val adminIngredientes = AdminIngredientes()
        val daoReceta = DAOReceta()

        val spnNumeroPersonas = findViewById<Spinner>(R.id.spnNumeroPersonas)
        adminIngredientes.listaDespelgableInt(spnNumeroPersonas,this)
        receta = daoReceta.obtenerReceta(this, idReceta)

        spnNumeroPersonas.setSelection(receta.numeroPersona-1)
        var numeroPersonasSeleccionada: Int = 0

        spnNumeroPersonas.onItemSelectedListener = this

        val etmProceso = findViewById<EditText>(R.id.etmProceso)
        etmProceso.setText(receta.proceso)

        val btnEnlaceUno = findViewById<Button>(R.id.btnEnlaceUno)
        if (receta.enlaceUno != ""){
            btnEnlaceUno.setOnClickListener {
                val contenidoExterno = Intent(this, ContenidoExternoReceta::class.java)

                contenidoExterno.putExtra("direccion",receta.enlaceUno)
                startActivity(contenidoExterno)
            }
        }else{
            btnEnlaceUno.isClickable = false
        }
        val btnEnlaceDos = findViewById<Button>(R.id.btnEnlaceDos)
        if (receta.enlaceDos != ""){
            btnEnlaceDos.setOnClickListener {
                val contenidoExterno = Intent(this, ContenidoExternoReceta::class.java)

                contenidoExterno.putExtra("direccion",receta.enlaceDos)
                startActivity(contenidoExterno)
            }
        }else{
            btnEnlaceDos.isClickable = false
        }

        val btnSalir = findViewById<Button>(R.id.btnSalir)
        btnSalir.setOnClickListener {
            finish()
        }

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val lvListaIngredientes = findViewById<ListView>(R.id.lvListaIngredientes)
        val adminIngredientes = AdminIngredientes()
        var numeroPersonas = position + 1
        var nuevaCantidad = arrayOf<Int>()

            for (elemento in receta.cantidad){
                var division = (elemento / receta.numeroPersona)
                nuevaCantidad += (division * numeroPersonas)
                /*
                *Falta implementar el cambio de medida (Kg -> Gr, Lt -> Ml)
                */
            }
        val listaIngredientesObtenidos = adminIngredientes.convertirString(receta.ingrediente, nuevaCantidad, receta.medida)
        adminIngredientes.llenarListView(this, lvListaIngredientes, listaIngredientesObtenidos,mutableListOf())
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

}