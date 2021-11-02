package com.app.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.app.apprecetario.BD.ConexionBD
import com.app.apprecetario.BD.DAOReceta
import com.app.apprecetario.utilidad.AdminIngredientes

class CatalogoSopas : AppCompatActivity() {

    var tipoCatalogo: String = ""
    var elementoCategoria: String = ""
    var categoria: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_sopas)

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

        val tvTituloReceta = findViewById<TextView>(R.id.tvTituloReceta)
        tvTituloReceta.setText("Lista de Recetas de " + tipoCatalogo)

        IniciarComponentes()
    }

    private fun IniciarComponentes() {
        val btnSalirSopas = findViewById<Button>(R.id.btnSalirSopas)
        btnSalirSopas.setOnClickListener {
            finish()
        }

        val btnNuevaReceta = findViewById<Button>(R.id.btnNuevaReceta)
        btnNuevaReceta.setOnClickListener {
            val registroSopas = Intent(this,NuevaRecetaSopa::class.java)

            registroSopas.putExtra("Categoria",tipoCatalogo)
            registroSopas.putExtra("Elemento",elementoCategoria)
            registroSopas.putExtra("NumeroCategoria",categoria)

            startActivity(registroSopas)
        }

        val lvListaRecetas = findViewById<ListView>(R.id.lvListaRecetas)
        var consultarReceta = DAOReceta()
        var llenarList = AdminIngredientes()
        var arrayUno: Array<String> = arrayOf()
        var arrayDos: MutableList<Receta> = mutableListOf()
        /*
         * 1 = Postres
         * 2 = Sopas
         * 3 = Guisados
         * 4 = Pastas
        */


        arrayDos = consultarReceta.obtenerRecetas(this, categoria)

        if(arrayDos.size > 0){
            llenarList.llenarListView(this,lvListaRecetas,arrayUno,arrayDos)
        }else{
            Toast.makeText(this, "No existe registro o no su pudo mostrar las recetas.",
                Toast.LENGTH_SHORT).show()
        }

        var recetaSeleccionada: Int = -1
        lvListaRecetas.setOnItemClickListener { adapterView, view, i, l ->
            recetaSeleccionada = i
            Toast.makeText(this, "Se selecciono: ${arrayDos[i]}", Toast.LENGTH_SHORT).show()
        }

        val btnConsultarReceta = findViewById<Button>(R.id.btnConsultarReceta)
        if(arrayDos.size > 0){
            btnConsultarReceta.setOnClickListener {
                if(recetaSeleccionada != -1){
                    val ventanaConsultaSopa = Intent(this,ConsultarRecetaSopa::class.java)
                    val idReceta = arrayDos[recetaSeleccionada].idReceta

                    ventanaConsultaSopa.putExtra("idReceta", idReceta)
                    startActivity(ventanaConsultaSopa)
                }else{
                        Toast.makeText(this, "Debe seleccionar una Receta", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            btnConsultarReceta.isClickable = false
        }

        val btnModificarReceta = findViewById<Button>(R.id.btnModificarReceta)
        if(arrayDos.size > 0){
            btnModificarReceta.setOnClickListener {
                if(recetaSeleccionada != -1){
                    val ventanaModificaSopa = Intent(this,ModificarRecetaSopa::class.java)
                    val idReceta = arrayDos[recetaSeleccionada].idReceta

                    ventanaModificaSopa.putExtra("idReceta", idReceta)
                    startActivity(ventanaModificaSopa)
                }else{
                    Toast.makeText(this, "Debe seleccionar una Receta", Toast.LENGTH_SHORT).show()
                }
            }
        }else{
            btnModificarReceta.isClickable = false
        }



        val btnEliminarReceta = findViewById<Button>(R.id.btnEliminarReceta)
        if(arrayDos.size > 0){
                btnEliminarReceta.setOnClickListener {
                    if(recetaSeleccionada != -1){
                        var eliminacion = consultarReceta.eliminarReceta(this,arrayDos[recetaSeleccionada].idReceta)

                        if(eliminacion){
                            Toast.makeText(this, "Se elimino la receta", Toast.LENGTH_SHORT).show()
                            actualizarRecetas(lvListaRecetas, categoria)
                            recetaSeleccionada = -1
                        }else{
                            Toast.makeText(this, "No se pudo eliminar la receta", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "Debe seleccionar una Receta", Toast.LENGTH_SHORT).show()
                    }
                }
        }else{
            btnEliminarReceta.isClickable = false
        }
    }

    fun actualizarRecetas(lvListaRecetas:ListView, categoria: Int){
        var consultarReceta = DAOReceta()
        var llenarList = AdminIngredientes()
        var arrayUno: Array<String> = arrayOf()
        var arrayDos: MutableList<Receta> = mutableListOf()

        arrayDos = consultarReceta.obtenerRecetas(this, categoria)

        if(arrayDos.size > 0){
            llenarList.llenarListView(this,lvListaRecetas,arrayUno,arrayDos)
        }else{
            llenarList.llenarListView(this,lvListaRecetas,arrayUno,arrayDos)
        }
    }
}