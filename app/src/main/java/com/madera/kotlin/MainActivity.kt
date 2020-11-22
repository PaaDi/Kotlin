package com.madera.kotlin

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.Authentification.PasswordMissActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect_view)

        // Déclaration des composants de la vue
        val btnConnect = findViewById(R.id.btnConnect) as Button
        val titlePassMiss = findViewById(R.id.titlePassMiss) as TextView
        val logoInfo = findViewById(R.id.logoInfo) as ImageView

        titlePassMiss.setMovementMethod(LinkMovementMethod.getInstance());


        // Listener Button Connexion
         btnConnect.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(this@MainActivity, "Connexion réussie !", Toast.LENGTH_SHORT).show()
            val i = Intent(this, PasswordMissActivity::class.java)
            startActivity(i)
        }

        // Listener Mot de passe oublié
        titlePassMiss.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(this@MainActivity, "Vous avez oubliez votre mot de passe ?", Toast.LENGTH_SHORT).show()
            val i = Intent(this, PasswordMissActivity::class.java)
            startActivity(i)
        }

        // Listener Bulle d'info
        logoInfo.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Pour accéder à votre espace personnel, veuillez saisir votre nom d'utilisateur ainsi que votre mot de passe.")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Retour", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Bienvenue !")
            // show alert dialog
            alert.show()
        }
    }
}