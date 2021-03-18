package com.madera.kotlin.Controller.Client

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Controller.Contact.ContactListAdapter
import com.madera.kotlin.Controller.Contact.NewContactActivity
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.Entity.Contact
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ClientViewModel
import com.madera.kotlin.ViewModel.ClientViewModelFactory
import com.madera.kotlin.ViewModel.ContactViewModel
import com.madera.kotlin.ViewModel.ContactViewModelFactory
import kotlin.properties.Delegates


class DetailsClientActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_CLIENT_ID = "clientId"
    }

    private val newContactActivityRequestCode = 1
    private lateinit var clientIdentifiant: TextView
    private lateinit var clientName: EditText
    private lateinit var clientActivity : EditText
    private lateinit var clientAdress : EditText
    private lateinit var clientCity: EditText
    private lateinit var clientPostal: EditText
    private lateinit var clientDescription: EditText
    private lateinit var clientProfessionnel: TextView
    private  lateinit var  buttonDelete: Button
    private lateinit var proCheckBox : CheckBox
    private lateinit var btnValideUpdate : Button
    private lateinit var btnDoUpdate: Button
    private var clientId = 0

    val clientViewModel: ClientViewModel by viewModels {
        ClientViewModelFactory((application as MaderaApplication).repositoryClient)
    }

    val contactViewModel : ContactViewModel by viewModels {
        ContactViewModelFactory((application as MaderaApplication).repositoryContact)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_client2)

        val btnSupressContact = findViewById<Button>(R.id.btn_deleteContact)

        //region Implement Recycler
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_contact)
        val adapterContact = ContactListAdapter(contactViewModel)
        recyclerView.adapter = adapterContact
        recyclerView.layoutManager = LinearLayoutManager(this)
        //endregion



        clientId = intent.getIntExtra(EXTRA_CLIENT_ID, 1)
        val clientDetail = clientViewModel.getClientById(clientId)
        val imageHelpDetailsClient = findViewById(R.id.imageHelpDetailsClient) as ImageView

        //region Observer
        contactViewModel.getAllContactsByClient(clientId).observe(this, Observer {
            contacts ->
            contacts?.let { adapterContact.submitList(it)
        }
        })
        //endregion

        // Listener NewContactActivity
        val btnCreateContact = findViewById<Button>(R.id.button_addContact)

        btnCreateContact.setOnClickListener {
            val i = Intent(this@DetailsClientActivity, NewContactActivity::class.java)
            i.putExtra(NewContactActivity.EXTRA_CLIENT_ID_FORNEWCONTACT, clientId)
            startActivityForResult(i, newContactActivityRequestCode)
        }




        clientIdentifiant = findViewById(R.id.idClientDetails)
        clientName = findViewById(R.id.nameClientDetails)
        clientActivity = findViewById(R.id.activityClientDetails)
        clientAdress = findViewById(R.id.adressClientDetails)
        clientCity = findViewById(R.id.cityClientDetails)
        clientPostal = findViewById(R.id.postalClientDetails)
        clientDescription = findViewById(R.id.descriptionClientDetails)
        clientProfessionnel = findViewById(R.id.professionnelClientDetails)
        proCheckBox = findViewById(R.id.checkProfessionnel)
        btnValideUpdate = findViewById(R.id.btn_valideUpdate)
        btnDoUpdate = findViewById(R.id.btn_DoUpdate)

        clientName.isEnabled = false
        clientActivity.isEnabled = false
        clientAdress.isEnabled = false
        clientCity.isEnabled =false
        clientPostal.isEnabled = false
        clientDescription.isEnabled = false
        proCheckBox.isChecked = clientDetail.professionnel
        proCheckBox.isVisible = false
        btnValideUpdate.isVisible = false

        clientIdentifiant.text = "Client #"+ clientDetail.refClient.toString()
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
        Button update client
         */
        btnDoUpdate.setOnClickListener {
            btnDoUpdate.isVisible = false
            proCheckBox.isVisible = true
            clientName.isEnabled = true
            clientActivity.isEnabled = true
            clientAdress.isEnabled = true
            clientCity.isEnabled =true
            clientPostal.isEnabled = true
            clientDescription.isEnabled = true
            btnValideUpdate.isVisible = true
            clientProfessionnel.isVisible = false
        }

        /*
        Button Valide Update
         */
        btnValideUpdate.setOnClickListener {
            val codePostal = clientPostal.text.toString()
            val codePostalToInt = codePostal.toInt()
            clientViewModel.createClient(Client(clientId,clientDetail.refClient,clientName.text.toString(),clientAdress.text.toString(),codePostalToInt,clientCity.text.toString(),proCheckBox.isChecked,clientActivity.text.toString(),clientDescription.text.toString()))
            btnDoUpdate.isVisible = true
            proCheckBox.isVisible = false
            clientName.isEnabled = false
            clientActivity.isEnabled = false
            clientAdress.isEnabled = false
            clientCity.isEnabled =false
            clientPostal.isEnabled = false
            clientDescription.isEnabled = false
            btnValideUpdate.isVisible = false
            clientProfessionnel.isVisible = true

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

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var nomContact = ""
        var prenomContact = ""
        var fonctionContact = ""
        var phoneContact = ""
        var mailContact = ""

        if (requestCode == newContactActivityRequestCode && resultCode == Activity.RESULT_OK){
            data?.getStringExtra(NewContactActivity.EXTRA_CONTACT_NAME)?.let {
                nomContact = it
            }
            data?.getStringExtra(NewContactActivity.EXTRA_CONTACT_PRENOM)?.let {
                prenomContact = it
            }
            data?.getStringExtra(NewContactActivity.EXTRA_CONTACT_FONCTION)?.let {
                fonctionContact = it
            }
            data?.getStringExtra(NewContactActivity.EXTRA_CONTACT_PHONE)?.let {
                phoneContact = it
            }
            data?.getStringExtra(NewContactActivity.EXTRA_CONTACT_MAIL)?.let {
                mailContact = it
            }
            val rnds = (0..99999999).random().toLong()
            contactViewModel.createContact(Contact(null,rnds,clientId,nomContact,prenomContact,fonctionContact,phoneContact,mailContact))

        }else{
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}