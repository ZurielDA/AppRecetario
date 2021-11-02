package com.app.apprecetario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.app.apprecetario.BD.ConexionBD
import com.app.apprecetario.BD.DAOReceta
import com.app.apprecetario.utilidad.AdminIngredientes

class ModificarRecetaSopa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_receta_sopa)

        val bundle = intent.extras
        val idReceta = bundle?.getInt("idReceta")

        if (idReceta != null) {
            incializarComponentes(idReceta)
        }

    }

    private fun incializarComponentes(idReceta: Int) {

        val daoReceta = DAOReceta()
        var receta = Receta()
        val adminIngredientes = AdminIngredientes()


        receta = daoReceta.obtenerReceta(this, idReceta)

        val etNombreReceta = findViewById<EditText>(R.id.etNombreReceta)
        etNombreReceta.setText(receta.nombreReceta)

        val etProceso = findViewById<EditText>(R.id.etProceso)
        etProceso.setText(receta.proceso)

        val etEnlaceUno = findViewById<EditText>(R.id.etEnlaceUno)
        etEnlaceUno.setText(receta.enlaceUno)

        val etEnlaceDos = findViewById<EditText>(R.id.etEnlaceDos)
        etEnlaceDos.setText(receta.enlaceDos)

        val etNumeroPersonas = findViewById<EditText>(R.id.etNumeroPersona)
        etNumeroPersonas.setText(receta.numeroPersona.toString())

        var listaIngredientesObtenidos = arrayOf<String>()
        val lvIngredientes = findViewById<ListView>(R.id.lvIngredientes)
        listaIngredientesObtenidos = adminIngredientes.convertirString(receta.ingrediente, receta.cantidad, receta.medida)
        adminIngredientes.llenarListView(this, lvIngredientes, listaIngredientesObtenidos,mutableListOf())

        val etIngrediente = findViewById<EditText>(R.id.etIngrediente)
        val etCantidad = findViewById<EditText>(R.id.etCantidad)
        val spnCantidad = findViewById<Spinner>(R.id.spnCantidad)
        adminIngredientes.listaDespelgableString(spnCantidad,this)

        var arrayNull: MutableList<Receta> = mutableListOf()
        val btnAgregarIngrediente = findViewById<Button>(R.id.btnAgregarIngredienteRegistro)
        btnAgregarIngrediente.setOnClickListener {

            if (adminIngredientes.validarDatosIngrediente(this, etIngrediente.getText().toString(),
                    etCantidad.getText().toString()) ) {

                receta.idIngrediente +=  -1
                receta.ingrediente += etIngrediente.getText().toString()
                receta.cantidad += etCantidad.getText().toString().toInt()
                receta.medida += spnCantidad.selectedItem.toString()

                listaIngredientesObtenidos = adminIngredientes.convertirString(receta.ingrediente,receta.cantidad,
                                                                               receta.medida)
                adminIngredientes.llenarListView(this, lvIngredientes, listaIngredientesObtenidos,arrayNull)

                etIngrediente.setText("")
                etCantidad.setText("")
                spnCantidad.setSelection(0)
            }
        }

        val etIngredienteModificar = findViewById<EditText>(R.id.etIngredienteModificar)
        val etCantidadModificar = findViewById<EditText>(R.id.etCantidadModificar)
        val spnCantidadModificar = findViewById<Spinner>(R.id.spnCantidadModificar)
        adminIngredientes.listaDespelgableString(spnCantidadModificar,this)

        var ingredienteSeleccionado: Int = -1
        val listaCantidades = arrayOf("Kg", "Gr", "Lt", "Ml","Pza")
        lvIngredientes.setOnItemClickListener { adapterView, view, i, l ->
            ingredienteSeleccionado = i
            Toast.makeText(this, "Se selecciono: ${receta.ingrediente[i]+" "+receta.cantidad[i]+" "+receta.medida[i]}",
                Toast.LENGTH_SHORT).show()
            etIngredienteModificar.setText(receta.ingrediente[i])
            etCantidadModificar.setText(receta.cantidad[i].toString())
            var contador = 0
            for (medida in listaCantidades){

                if(receta.medida[i] == medida){
                    spnCantidadModificar.setSelection(contador)
                }
                contador++
            }
        }

        var ingredienteModificar = arrayOf<Int>()
        var ingredienteEliminar = arrayOf<Int>()

        val btnEliminar = findViewById<Button>(R.id.btnEliminarIngredienteRegistro)
        btnEliminar.setOnClickListener {
            if(ingredienteSeleccionado != -1){

                receta.ingrediente = adminIngredientes.EliminarElementoString(ingredienteSeleccionado,receta.ingrediente)
                receta.cantidad = adminIngredientes.EliminarElementoInt(ingredienteSeleccionado,receta.cantidad)
                receta.medida = adminIngredientes.EliminarElementoString(ingredienteSeleccionado,receta.medida)

                listaIngredientesObtenidos = adminIngredientes.convertirString(receta.ingrediente,receta.cantidad,receta.medida)
                adminIngredientes.llenarListView(this, lvIngredientes, listaIngredientesObtenidos,arrayNull)

                if(receta.idIngrediente[ingredienteSeleccionado] != -1){
                    ingredienteEliminar += receta.idIngrediente[ingredienteSeleccionado]
                    ingredienteModificar = adminIngredientes.EliminarElementoInt(receta.idIngrediente[ingredienteSeleccionado],ingredienteModificar)
                }
            }
        }

        val btnModificar = findViewById<Button>(R.id.btnModificar)
        btnModificar.setOnClickListener {
            if (adminIngredientes.validarDatosIngrediente(this, etIngredienteModificar.getText().toString(),
                    etCantidadModificar.getText().toString()) ) {
                receta.ingrediente[ingredienteSeleccionado] = etIngredienteModificar.getText().toString()
                receta.cantidad [ingredienteSeleccionado] = etCantidadModificar.getText().toString().toInt()
                receta.medida[ingredienteSeleccionado] = spnCantidadModificar.selectedItem.toString()

                ingredienteModificar += receta.idIngrediente

                listaIngredientesObtenidos = adminIngredientes.convertirString(receta.ingrediente,receta.cantidad,receta.medida)
                adminIngredientes.llenarListView(this, lvIngredientes, listaIngredientesObtenidos,arrayNull)

                etIngredienteModificar.setText("")
                etCantidadModificar.setText("")
                spnCantidadModificar.setSelection(0)
            }
        }

        val btnGuardar = findViewById<Button>(R.id.btnGuardarRegistro)
        btnGuardar.setOnClickListener {
            var nombreReceta = etNombreReceta.getText().toString()
            var proceso = etProceso.getText().toString()
            var enlaceUno = etEnlaceUno.getText().toString()
            var enlaceDos = etEnlaceDos.getText().toString()
            var numeroPersona = etNumeroPersonas.getText().toString()

            if (adminIngredientes.validarDatosReceta(this, nombreReceta, receta.ingrediente, receta.cantidad, receta.medida,
                    proceso, enlaceUno, enlaceDos, numeroPersona)){

                val seGuardoReceta = daoReceta.modificarReceta(this, idReceta, nombreReceta, proceso, enlaceUno, enlaceDos,
                                                                numeroPersona.toInt())

                //No se guardan todos los ingredientes que se registran desde el modificar
                //Ademas se ejecuta incorrectamente con el de eliminar de abajo
                val seGuardoIngrediente = daoReceta.registrarIngrediente(this,idReceta,receta.idIngrediente, receta.ingrediente,
                    receta.cantidad, receta.medida)

                var seModificoIngrediente: Boolean = true
                if(ingredienteModificar.size > 0){
                    seModificoIngrediente = daoReceta.modificarIngrediente(this,ingredienteModificar,receta.idIngrediente,
                        receta.ingrediente,receta.cantidad, receta.medida)
                }

                // No se borran todos los ingredientes que ya estan registrados en la BD
                //Ademas se ejecuta incorrectamente con el de eliminar de abajo
                var seEliminoIngrediente: Boolean = true
                if(ingredienteEliminar.size > 0){
                    seEliminoIngrediente = daoReceta.eliminarIngrediente(this,ingredienteEliminar)
                }

                var string: String = " "
                for (elemento in ingredienteEliminar){
                    string += "" + elemento
                }

                if(seGuardoReceta && seGuardoIngrediente && seModificoIngrediente && seEliminoIngrediente){
                    Toast.makeText(this, "Se registro y actualizo los ingredientes", Toast.LENGTH_LONG).show()
                    finish()
                }else{
                    Toast.makeText(this, "Ocurrio un error inesperado. Intente más tarde", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }

        val btnCancelar = findViewById<Button>(R.id.btnCancelarRegistro)
        btnCancelar.setOnClickListener {
            finish()
        }

    }
}