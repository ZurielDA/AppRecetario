package com.app.apprecetario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button

class ContenidoExternoReceta : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contenido_externo_receta)

        var btnSalirNavegador = findViewById<Button>(R.id.btnSalirNavegador)
        btnSalirNavegador.setOnClickListener{
            finish()
        }

        val bundle = intent.extras
        val url = bundle?.getString("direccion")

        val webView = findViewById<WebView>(R.id.webView)
        if (url != null) {
            webView.loadUrl(url)
        }
    }
}