package com.app.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class CatalogoSopas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catalogo_sopas)

        val btnNuevaReceta = findViewById<Button>(R.id.btnNuevaReceta)
        btnNuevaReceta.setOnClickListener {
            val registroSopas = Intent(this,NuevaRecetaSopa::class.java)
            startActivity(registroSopas)
        }

    }
}