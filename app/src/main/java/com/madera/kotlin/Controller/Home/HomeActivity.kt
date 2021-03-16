package com.madera.kotlin.Controller.Home

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.R


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_view)

        //region Components
        val btnListProject = findViewById(R.id.btnListProject) as Button
        val btnListClient = findViewById(R.id.btnListClient) as Button

        //region Events Listeners
        // Listener ProjectActivity
        btnListProject.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@HomeActivity,
                "Redirection vers l'espace projet !",
                Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, ProjectActivity::class.java)
            startActivity(i)
        }

        // Listener ClientActivity
        btnListClient.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@HomeActivity,
                "Redirection vers l'espace client !",
                Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, ClientActivity::class.java)
            startActivity(i)
        }


    }
}