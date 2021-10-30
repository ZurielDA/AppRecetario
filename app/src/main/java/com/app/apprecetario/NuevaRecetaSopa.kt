package com.app.apprecetario

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.app.apprecetario.utilidad.AdminIngredientes

class NuevaRecetaSopa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_receta_sopa)

        IniciarComponentes()

    }

    private fun IniciarComponentes() {

        var adminIngredientes = AdminIngredientes()

        val etNombreReceta = findViewById<EditText>(R.id.etNombreReceta)
        val etProceso = findViewById<EditText>(R.id.etProceso)
        val etEnlaceUno = findViewById<EditText>(R.id.etEnlaceUno)
        val etEnlaceDos = findViewById<EditText>(R.id.etEnlaceDos)

        // Medida de ingredientes
        val spnCantidad = findViewById<Spinner>(R.id.spnCantidad)
        adminIngredientes.listaDespelgable(spnCantidad,this)

        val etIngrediente = findViewById<EditText>(R.id.etIngrediente)
        val etCantidad = findViewById<EditText>(R.id.etCantidad)
        val lvIngredientes = findViewById<ListView>(R.id.lvIngredientes)

        var ingrediente = arrayOf<String>()
        var cantidad = arrayOf<Int>()
        var medica = arrayOf<String>()
        var listaIngredientesObtenidos = arrayOf<String>()
        val btnAgregarIngrediente = findViewById<Button>(R.id.btnAgregarIngredienteRegistro)
        btnAgregarIngrediente.setOnClickListener {

            if (
            adminIngredientes.validarDatosIngrediente(this, etIngrediente.getText().toString(),
                                                       etCantidad.getText().toString()) ) {

            ingrediente += etIngrediente.getText().toString()
            cantidad += etCantidad.getText().toString().toInt()
            medica += spnCantidad.selectedItem.toString()

            listaIngredientesObtenidos = adminIngredientes.convertirString(ingrediente,cantidad,medica)
            adminIngredientes.llenarListView(this, lvIngredientes, listaIngredientesObtenidos)

            etIngrediente.setText("")
            etCantidad.setText("")
            spnCantidad.setSelection(0)
            }
        }

        var ingredienteEleminar: Int = 0
        lvIngredientes.setOnItemClickListener { adapterView, view, i, l ->
            ingredienteEleminar = i
            Toast.makeText(this, "Se selecciono: ${ingredienteEleminar}", Toast.LENGTH_SHORT).show()
        }

        val btnEliminar = findViewById<Button>(R.id.btnEliminarIngredienteRegistro)
        btnEliminar.setOnClickListener {

            ingrediente = adminIngredientes.EliminarElementoString(ingredienteEleminar,ingrediente)
            cantidad = adminIngredientes.EliminarElementoInt(ingredienteEleminar,cantidad)
            medica = adminIngredientes.EliminarElementoString(ingredienteEleminar,medica)

            listaIngredientesObtenidos = adminIngredientes.convertirString(ingrediente,cantidad,medica)
            adminIngredientes.llenarListView(this, lvIngredientes, listaIngredientesObtenidos)

        }

        val btnGuardar = findViewById<Button>(R.id.btnGuardarRegistro)
        btnGuardar.setOnClickListener {
            if (adminIngredientes.validarDatosReceta(this,etNombreReceta.getText().toString(), ingrediente,
                cantidad, medica, etProceso.getText().toString(), etEnlaceUno.getText().toString(),
                etEnlaceDos.getText().toString())){
            }

            /*
                falta aguardar los datos
             */

        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarRegistro)
        btnCancelar.setOnClickListener {
            finish()
        }

    }

}