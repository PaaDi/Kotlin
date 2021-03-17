package com.madera.kotlin.Controller.Contact

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.madera.kotlin.Controller.Client.NewClientActivity
import com.madera.kotlin.R

class NewContactActivity : AppCompatActivity() {

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_contact_view)

        val imageHelpContact = findViewById(R.id.imageHelpContact) as ImageView

        // Bulle d'information
        imageHelpContact.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Vous voici sur le formulaire de création d'un contact. Pour pouvoir valider la saisie, l'ensemble des champs doivent être saisis. Il vous est aussi possible de créer un contact et l'associé au client.")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Retour", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Création d'un contact !")
            // show alert dialog
            alert.show()
        }



    }
}