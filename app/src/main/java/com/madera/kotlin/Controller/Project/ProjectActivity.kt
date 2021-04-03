package com.madera.kotlin.Controller.Home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.Controller.MainActivity
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R


class ProjectActivity : AppCompatActivity() {

    private lateinit var userIdentifiant: TextView
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_view)

        val imageHelpProject = findViewById(R.id.imageHelpProject) as ImageView
        val imageDisconnect = findViewById(R.id.imageDisconnect) as ImageView

        imageDisconnect.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                    this@ProjectActivity,
                    "Déconnexion !",
                    Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }


        imageHelpProject.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Vous voici sur l'espace client, vous avez la possibilité d'ajouter/éditer/supprimer un client dans la liste.")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Retour", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Espace Projet !")
            // show alert dialog
            alert.show()
        }

        userIdentifiant = findViewById(R.id.nameUserProject) as TextView
        userIdentifiant.text = MaderaApplication.globalTest.pseudoUser


    }

    

}