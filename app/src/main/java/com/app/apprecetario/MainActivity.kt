package com.app.apprecetario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imgBtn = findViewById<ImageButton>(R.id.imgBtnSopas)
        imgBtn.setOnClickListener {
            val ventanaSopas = Intent(this,CatalogoSopas::class.java)
            startActivity(ventanaSopas)
        }

    }

}