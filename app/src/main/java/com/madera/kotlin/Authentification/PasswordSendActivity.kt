package com.madera.kotlin.Authentification

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.MainActivity
import com.madera.kotlin.R

class PasswordSendActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.passwordsend_view)

        // Déclaration des composants de la vue
        val btnRetour = findViewById(R.id.btnRetour) as Button
        val btnEnvoyerMail = findViewById(R.id.btnEnvoyerMail) as Button

        //Listeners
        btnRetour.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(this@PasswordSendActivity, "Retour à la page d'accueil !", Toast.LENGTH_SHORT).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
        btnEnvoyerMail.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(this@PasswordSendActivity, "Redirection", Toast.LENGTH_SHORT).show()
            val i = Intent(this, PasswordMissActivity::class.java)
            startActivity(i)
        }

    }
}