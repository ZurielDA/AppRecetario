package com.app.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ListView

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

        val btnConsultarReceta = findViewById<Button>(R.id.btnConsultarReceta)
        btnConsultarReceta.setOnClickListener {

        }

        val btnModificarReceta = findViewById<Button>(R.id.btnModificarReceta)
        btnConsultarReceta.setOnClickListener {

        }

        val btnEliminarReceta = findViewById<Button>(R.id.btnEliminarReceta)
        btnEliminarReceta.setOnClickListener {

        }

    }
}