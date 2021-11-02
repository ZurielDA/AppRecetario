package com.app.apprecetario

import android.content.ContentValues
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.app.apprecetario.BD.ConexionBD
import com.app.apprecetario.BD.DAOReceta
import com.app.apprecetario.utilidad.AdminIngredientes

class NuevaRecetaSopa : AppCompatActivity() {

    var tipoCatalogo: String = ""
    var elementoCategoria: String = ""
    var categoria: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nueva_receta_sopa)

        val bundle = intent.extras
        val ct = bundle?.getString("Categoria")
        val tc = bundle?.getString("Elemento")
        val nc = bundle?.getInt("NumeroCategoria")

        if (ct != null) {
            tipoCatalogo = ct

        }
        if (tc != null) {
            elementoCategoria = tc
        }
        if (nc != null) {
            categoria = nc
        }

        val tvTituloNuevaReceta = findViewById<TextView>(R.id.tvTituloNuevaReceta)
        tvTituloNuevaReceta.setText("Nueva Receta de " + elementoCategoria)

        IniciarComponentes()
    }

    private fun IniciarComponentes() {

        val etNombreReceta = findViewById<EditText>(R.id.etNombreReceta)
        val etProceso = findViewById<EditText>(R.id.etProceso)
        val etEnlaceUno = findViewById<EditText>(R.id.etEnlaceUno)
        val etEnlaceDos = findViewById<EditText>(R.id.etEnlaceDos)
        val etNumeroPersonas = findViewById<EditText>(R.id.etNumeroPersona)

        // Medida de ingredientes
        val spnCantidad = findViewById<Spinner>(R.id.spnCantidad)
        var adminIngredientes = AdminIngredientes()
        adminIngredientes.listaDespelgableString(spnCantidad,this)

        val etIngrediente = findViewById<EditText>(R.id.etIngrediente)
        val etCantidad = findViewById<EditText>(R.id.etCantidad)
        val lvIngredientes = findViewById<ListView>(R.id.lvIngredientes)

        var ingrediente = arrayOf<String>()
        var cantidad = arrayOf<Int>()
        var medica = arrayOf<String>()
        var listaIngredientesObtenidos = arrayOf<String>()
        var arrayNull: MutableList<Receta> = mutableListOf()
        val btnAgregarIngrediente = findViewById<Button>(R.id.btnAgregarIngredienteRegistro)
        btnAgregarIngrediente.setOnClickListener {

            if (adminIngredientes.validarDatosIngrediente(this, etIngrediente.getText().toString(),
                                                                  etCantidad.getText().toString()) ) {

            ingrediente += etIngrediente.getText().toString()
            cantidad += etCantidad.getText().toString().toInt()
            medica += spnCantidad.selectedItem.toString()

            listaIngredientesObtenidos = adminIngredientes.convertirString(ingrediente,cantidad,medica)
            adminIngredientes.llenarListView(this, lvIngredientes, listaIngredientesObtenidos,arrayNull)

            etIngrediente.setText("")
            etCantidad.setText("")
            spnCantidad.setSelection(0)
            }
        }

        var ingredienteEleminar: Int = 0
        lvIngredientes.setOnItemClickListener { adapterView, view, i, l ->
            ingredienteEleminar = i
            Toast.makeText(this, "Se selecciono: ${ingrediente[i] +" "+ cantidad[i] +" "+ medica[i]}",
                            Toast.LENGTH_SHORT).show()
        }

        val btnEliminar = findViewById<Button>(R.id.btnEliminarIngredienteRegistro)
        btnEliminar.setOnClickListener {

            ingrediente = adminIngredientes.EliminarElementoString(ingredienteEleminar,ingrediente)
            cantidad = adminIngredientes.EliminarElementoInt(ingredienteEleminar,cantidad)
            medica = adminIngredientes.EliminarElementoString(ingredienteEleminar,medica)

            listaIngredientesObtenidos = adminIngredientes.convertirString(ingrediente,cantidad,medica)
            adminIngredientes.llenarListView(this, lvIngredientes, listaIngredientesObtenidos,arrayNull)
        }

        val btnGuardar = findViewById<Button>(R.id.btnGuardarRegistro)
        btnGuardar.setOnClickListener {
            var nombreReceta = etNombreReceta.getText().toString()
            var proceso = etProceso.getText().toString()
            var enlaceUno = etEnlaceUno.getText().toString()
            var enlaceDos = etEnlaceDos.getText().toString()
            var numeroPersona = etNumeroPersonas.getText().toString()

            if (adminIngredientes.validarDatosReceta(this, nombreReceta, ingrediente, cantidad, medica,
                    proceso, enlaceUno, enlaceDos, numeroPersona)){
                var daoReceta = DAOReceta()
                var valorInt: Int = 0

                try {
                    valorInt = numeroPersona.toInt()
                } catch (nfe: NumberFormatException){
                    nfe.printStackTrace()
                }

                var registro = daoReceta.registrarRecetas(this, nombreReceta, proceso, enlaceUno, enlaceDos,
                    categoria, ingrediente, cantidad, medica, valorInt)
                if(registro){
                    Toast.makeText(this, "Se registro la receta", Toast.LENGTH_LONG).show()
                }else {
                    Toast.makeText(this, "No se pudo realizar el registro", Toast.LENGTH_LONG).show()
                }
                finish()
            }

        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarRegistro)
        btnCancelar.setOnClickListener {
            finish()
        }
    }
}