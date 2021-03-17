package com.madera.kotlin.Controller.Home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.R


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_view)

        //region Components
        val btnListProject = findViewById(R.id.btnListProject) as Button
        val btnListClient = findViewById(R.id.btnListClient) as Button
        val imageHelp = findViewById(R.id.imageHelp) as ImageView

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

        // Bulle d'information
        imageHelp.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Pour afficher la liste des projets/clients, veuillez cliquer sur un des boutons.")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Retour", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Page d'accueil !")
            // show alert dialog
            alert.show()
        }

    }
}