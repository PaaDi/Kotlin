package com.madera.kotlin.Controller.Home

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.Controller.Client.NewClientActivity
import com.madera.kotlin.R


class ClientActivity : AppCompatActivity() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_view)

        //region Components
        val btnCreateClient = findViewById(R.id.btnCreateClient) as Button

        val btnFilter = findViewById<Spinner>(R.id.spinner1)
        val items = arrayOf("Melvin", "Maxime", "Barbara", "Maximilien", "Mathis")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        //set the spinners adapter to the previously created one.
        btnFilter.adapter = adapter

        // Listener ClientActivity
        btnCreateClient.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@ClientActivity,
                "Création d'un nouveau client !",
                Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, NewClientActivity::class.java)
            startActivity(i)
        }

    }
}