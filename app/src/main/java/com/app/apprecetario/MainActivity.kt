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
            val ventanaPostres = Intent(this,CatalogoSopas::class.java)

            ventanaPostres.putExtra("Categoria","Postres")
            ventanaPostres.putExtra("Elemento","Postre")
            ventanaPostres.putExtra("NumeroCategoria",1)

            startActivity(ventanaPostres)
        }

        val imgBtnSopas = findViewById<ImageButton>(R.id.imgBtnSopas)
        imgBtnSopas.setOnClickListener {
            val ventanaSopas = Intent(this,CatalogoSopas::class.java)

            ventanaSopas.putExtra("Categoria","Sopas")
            ventanaSopas.putExtra("Elemento","Sopa")
            ventanaSopas.putExtra("NumeroCategoria",2)

            startActivity(ventanaSopas)
        }

        val imgBtnGuisados = findViewById<ImageButton>(R.id.imgBtnGuisados)
        imgBtnGuisados.setOnClickListener {
            val ventanaGuisados = Intent(this,CatalogoSopas::class.java)

            ventanaGuisados.putExtra("Categoria","Guisados")
            ventanaGuisados.putExtra("Elemento","Guisado")
            ventanaGuisados.putExtra("NumeroCategoria",3)

            startActivity(ventanaGuisados)
        }

        val imgBtnPastas = findViewById<ImageButton>(R.id.imgBtnPastas)
        imgBtnPastas.setOnClickListener {
            val ventanaPastas = Intent(this,CatalogoSopas::class.java)

            ventanaPastas.putExtra("Categoria","Pastas")
            ventanaPastas.putExtra("Elemento","Pasta")
            ventanaPastas.putExtra("NumeroCategoria",4)

            startActivity(ventanaPastas)
        }

    }

}