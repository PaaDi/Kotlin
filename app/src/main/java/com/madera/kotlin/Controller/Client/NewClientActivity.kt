package com.madera.kotlin.Controller.Client

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.madera.kotlin.Controller.Contact.NewContactActivity
import com.madera.kotlin.R

class NewClientActivity : AppCompatActivity() {

    //region Global Component
    private val newContactActivityRequestCode = 1

    private lateinit var ClientNameView: EditText
    private lateinit var ClientSecteurView: EditText
    private lateinit var ClientAdresseView: EditText
    private lateinit var ClientVilleView: EditText
    private lateinit var ClientCodePostalView: EditText
    private lateinit var ClientDescriptionView: EditText
    private lateinit var ClientIsProView: CheckBox
    //endregion

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_client_view)

        ClientNameView = findViewById(R.id.nameClientDetails)
        ClientSecteurView = findViewById(R.id.activityClientDetails)
        ClientAdresseView = findViewById(R.id.adressClientDetails)
        ClientVilleView = findViewById(R.id.cityClientDetails)
        ClientCodePostalView = findViewById(R.id.postalClientDetails)
        ClientDescriptionView = findViewById(R.id.descriptionClientDetails)
        ClientIsProView = findViewById(R.id.checkProfessionnel)


        val button = findViewById<Button>(R.id.button_save)
        val btnCreateContact = findViewById(R.id.btnCreateContact) as Button
        val imageHelpNewClient = findViewById(R.id.imageHelpNewClient) as ImageView

        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(ClientNameView.text) && TextUtils.isEmpty(ClientAdresseView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                // TODO : Envoi de l'ensbemble des informations nécessaire à la création client

                val clientName = ClientNameView.text.toString()
                val clientAdress = ClientAdresseView.text.toString()
                val clientSecteur = ClientSecteurView.text.toString()
                val clientVille = ClientVilleView.text.toString()
                val clientCodePostal = ClientCodePostalView.text.toString()
                val clientDescription = ClientDescriptionView.text.toString()
                val clientIsPro = ClientIsProView.isChecked.toString()

                replyIntent.putExtra(EXTRA_CLIENT_NAME, clientName)
                replyIntent.putExtra(EXTRA_CLIENT_ADRESS, clientAdress)
                replyIntent.putExtra(EXTRA_CLIENT_SECTEUR, clientSecteur)
                replyIntent.putExtra(EXTRA_CLIENT_VILLE, clientVille)
                replyIntent.putExtra(EXTRA_CLIENT_CODEPOSTAL, clientCodePostal)
                replyIntent.putExtra(EXTRA_CLIENT_ISPRO, clientIsPro)
                replyIntent.putExtra(EXTRA_CLIENT_DESCRIPTION, clientDescription)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

        // Listener ClientActivity
        btnCreateContact.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                    this@NewClientActivity,
                    "Vous souhaitez créer un nouveau contact ?",
                    Toast.LENGTH_SHORT
            ).show()

            val i = Intent(this@NewClientActivity, NewContactActivity::class.java)
            startActivityForResult(i, newContactActivityRequestCode)
        }

        // Bulle d'information
        imageHelpNewClient.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Vous voici sur le formulaire de création d'un client. Pour pouvoir valider la saisie, l'ensemble des champs doivent être saisis.")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Retour", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Création d'un client !")
            // show alert dialog
            alert.show()
        }


    }

    companion object{
        const val EXTRA_CLIENT_NAME = "nom"
        const val EXTRA_CLIENT_ADRESS = "adresse"
        const val EXTRA_CLIENT_SECTEUR = "secteur"
        const val EXTRA_CLIENT_VILLE = "ville"
        const val EXTRA_CLIENT_CODEPOSTAL = "codePostal"
        const val EXTRA_CLIENT_ISPRO = "isPro"
        const val EXTRA_CLIENT_DESCRIPTION = "description"
    }
}