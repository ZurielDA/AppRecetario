package com.app.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        IniciarComponentes()
    }

    private fun IniciarComponentes() {

        val imgBtnPostres = findViewById<ImageButton>(R.id.imgBtnPostres)
        imgBtnPostres.setOnClickListener {

        }

        val imgBtnSopas = findViewById<ImageButton>(R.id.imgBtnSopas)
        imgBtnSopas.setOnClickListener {
            val ventanaSopas = Intent(this,CatalogoSopas::class.java)
            startActivity(ventanaSopas)
        }

        val imgBtnGuisados = findViewById<ImageButton>(R.id.imgBtnGuisados)
        imgBtnGuisados.setOnClickListener {

        }

        val imgBtnPastas = findViewById<ImageButton>(R.id.imgBtnPastas)
        imgBtnPastas.setOnClickListener {

        }

    }

}