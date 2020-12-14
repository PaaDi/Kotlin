package com.madera.kotlin.Controller.Menu

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.R


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_view)

        //region Components
        val btnListProject = findViewById(R.id.btnListProject) as Button

        //region Events Listeners
        // Listener Mot de passe oublié
        btnListProject.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@MenuActivity,
                "Redirection vers l'espace projet",
                Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, ProjectActivity::class.java)
            startActivity(i)
        }

        val dropdown = findViewById<Spinner>(R.id.spinner1)
        val items = arrayOf("François", "Yves", "Guillaume", "Marta", "Mélanie")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        //set the spinners adapter to the previously created one.
        dropdown.adapter = adapter

    }
}