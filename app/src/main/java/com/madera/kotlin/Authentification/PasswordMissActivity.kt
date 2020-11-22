package com.madera.kotlin.Authentification

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.MainActivity
import com.madera.kotlin.R

class PasswordMissActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.passwordmiss_view)

        // Déclaration des composants de la vue
        val btnRetour = findViewById(R.id.btnRetour) as Button
        val logoInfo = findViewById(R.id.logoInfo) as ImageView
        val btnEnvoyerMail = findViewById(R.id.btnEnvoyerMail) as Button

        //Listeners
        btnRetour.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(this@PasswordMissActivity, "Retour à la page d'accueil !", Toast.LENGTH_SHORT).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        btnEnvoyerMail.setOnClickListener {

            //TODO Envoyer un mail à l'utilisateur pour récupération du mot de passe et rediriger vers la page

            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(this@PasswordMissActivity, "Envoi validé !", Toast.LENGTH_SHORT).show()
            val i = Intent(this, PasswordSendActivity::class.java)
            startActivity(i)
        }
        logoInfo.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Pas de panique, il est possible de récupérer son mot de passe en saississant une adresse e-mail disposant des accès à l'application.")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Retour", DialogInterface.OnClickListener {
                        dialog, id -> dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Mot de passe oublié ?")
            // show alert dialog
            alert.show()
        }
    }
}