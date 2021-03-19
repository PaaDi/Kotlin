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
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R

class NewContactActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_CLIENT_ID_FORNEWCONTACT = "clientId"

        const val EXTRA_CONTACT_NAME = "nom"
        const val EXTRA_CONTACT_PRENOM = "prenom"
        const val EXTRA_CONTACT_FONCTION = "fonction"
        const val EXTRA_CONTACT_PHONE = "phone"
        const val EXTRA_CONTACT_MAIL = "email"
    }

    //region Global Component
    private lateinit var userIdentifiant: TextView
    private lateinit var ContactNameView: EditText
    private lateinit var ContactPrenomView: EditText
    private lateinit var ContactFonctionView: EditText
    private lateinit var ContactPhoneView: EditText
    private lateinit var ContactMailView:EditText
    //endregion

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_contact_view)

        ContactNameView = findViewById(R.id.nameContactDetails)
        ContactPrenomView = findViewById(R.id.surnameContactDetails)
        ContactFonctionView = findViewById(R.id.fonctionContactDetails)
        ContactPhoneView = findViewById(R.id.phoneContactDetails)
        ContactMailView = findViewById(R.id.mailContactDetails)

        val imageHelpContact = findViewById(R.id.imageHelpContact) as ImageView
        val testAccueil = findViewById<TextView>(R.id.txtAccueilProject)
        val createContact = findViewById<Button>(R.id.button_saveContact)

        val idClient = intent.getIntExtra(EXTRA_CLIENT_ID_FORNEWCONTACT,1).toString()

        // Listerner CreateContact
        createContact.setOnClickListener {
            val replyIntent = Intent()

            val contactName = ContactNameView.text.toString()
            val contactPrenom = ContactPrenomView.text.toString()
            val contactFonction = ContactFonctionView.text.toString()
            val contactPhone = ContactPhoneView.text.toString()
            val contactMail = ContactMailView.text.toString()

            replyIntent.putExtra(EXTRA_CONTACT_NAME, contactName)
            replyIntent.putExtra(EXTRA_CONTACT_PRENOM,contactPrenom)
            replyIntent.putExtra(EXTRA_CONTACT_FONCTION,contactFonction)
            replyIntent.putExtra(EXTRA_CONTACT_PHONE,contactPhone)
            replyIntent.putExtra(EXTRA_CONTACT_MAIL,contactMail)

            setResult(Activity.RESULT_OK, replyIntent)

            finish()

        }

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

        userIdentifiant = findViewById(R.id.nameUserContact) as TextView
        userIdentifiant.text = MaderaApplication.globalTest.pseudoUser



    }
}