package com.madera.kotlin.Controller.Client

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ClientViewModel
import com.madera.kotlin.ViewModel.ClientViewModelFactory


class DetailsClientActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_CLIENT_ID = "clientId"
    }

    private lateinit var clientIdentifiant: TextView
    private lateinit var clientName: EditText
    private lateinit var clientActivity : EditText
    private lateinit var clientAdress : EditText
    private lateinit var clientCity: EditText
    private lateinit var clientPostal: EditText
    private lateinit var clientDescription: EditText
    private lateinit var clientProfessionnel: TextView
    private  lateinit var  buttonDelete: Button

    val clientViewModel: ClientViewModel by viewModels {
        ClientViewModelFactory((application as MaderaApplication).repositoryClient)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_client2)

        val clientId = intent.getIntExtra(EXTRA_CLIENT_ID, 1)
        val clientDetail = clientViewModel.getClientById(clientId)
        val imageHelpDetailsClient = findViewById(R.id.imageHelpDetailsClient) as ImageView

        clientIdentifiant = findViewById(R.id.idClientDetails)
        clientName = findViewById(R.id.nameClientDetails)
        clientActivity = findViewById(R.id.activityClientDetails)
        clientAdress = findViewById(R.id.adressClientDetails)
        clientCity = findViewById(R.id.cityClientDetails)
        clientPostal = findViewById(R.id.postalClientDetails)
        clientDescription = findViewById(R.id.descriptionClientDetails)
        clientProfessionnel = findViewById(R.id.professionnelClientDetails)

        clientIdentifiant.text = clientDetail.refClient.toString()
        clientName.setText(clientDetail.nom)
        clientActivity.setText(clientDetail.secteur)
        clientAdress.setText(clientDetail.adresse)
        clientCity.setText(clientDetail.ville)
        clientPostal.setText(clientDetail.codePostal.toString())
        clientDescription.setText(clientDetail.description)

        if (clientDetail.professionnel){
            clientProfessionnel.text = "Client Professionnel"
        }else{
            clientProfessionnel.text = "Client Particulier"
        }

    /*
    Button suppression treatment
     */
        buttonDelete = findViewById(R.id.delete_btn)

        buttonDelete.setOnClickListener {
            clientViewModel.deleteClient(clientDetail)
            super.onBackPressed()
        }

        // Bulle d'information
        imageHelpDetailsClient.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Vous voici sur l'espace informations personnels du client, il vous est possible de mettre à jour ou supprimer les données.")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Retour", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Informations du client !")
            // show alert dialog
            alert.show()
        }
    }
}