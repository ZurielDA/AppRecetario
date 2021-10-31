package com.app.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.app.apprecetario.BD.ConexionBD
import com.app.apprecetario.BD.DAOReceta
import com.app.apprecetario.utilidad.AdminIngredientes

class CatalogoSopas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_sopas)

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
        var categoria = 2

        arrayDos = consultarReceta.obtenerRecetas(this, categoria)

        if(arrayDos.size > 0){
            llenarList.llenarListView(this,lvListaRecetas,arrayUno,arrayDos)
        }else{
            Toast.makeText(this, "No existe registro o no su pudo mostrar las recetas.",
                Toast.LENGTH_SHORT).show()
        }
        val btnConsultarReceta = findViewById<Button>(R.id.btnConsultarReceta)
        btnConsultarReceta.setOnClickListener {

        }

        val btnModificarReceta = findViewById<Button>(R.id.btnModificarReceta)
        btnModificarReceta.setOnClickListener {

        }

        var recetaEleminar: Int = 0
        lvListaRecetas.setOnItemClickListener { adapterView, view, i, l ->
            recetaEleminar = i
            Toast.makeText(this, "Se selecciono: ${arrayDos[i]}", Toast.LENGTH_SHORT).show()
        }

        val btnEliminarReceta = findViewById<Button>(R.id.btnEliminarReceta)
        btnEliminarReceta.setOnClickListener {

            var eliminacion = consultarReceta.eliminarReceta(this,arrayDos[recetaEleminar].idReceta)

            if(eliminacion){
                Toast.makeText(this, "Se elimino la receta", Toast.LENGTH_SHORT).show()
                actualizarRecetas(lvListaRecetas, categoria)

            }else{
                Toast.makeText(this, "No se pudo eliminar la receta", Toast.LENGTH_SHORT).show()
            }
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
            Toast.makeText(this, "No existe registro o no su pudo mostrar las recetas.",
                Toast.LENGTH_SHORT).show()
        }
    }
}