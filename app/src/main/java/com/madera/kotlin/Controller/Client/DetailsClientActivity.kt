package com.madera.kotlin.Controller.Client

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.madera.kotlin.Controller.Contact.ContactListAdapter
import com.madera.kotlin.Controller.Contact.NewContactActivity
import com.madera.kotlin.Controller.MainActivity
import com.madera.kotlin.Database.CheckConnection
import com.madera.kotlin.Database.MaderaAPI
import com.madera.kotlin.Entity.Contact
import com.madera.kotlin.Entity.Request
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.*
import org.json.JSONObject


class DetailsClientActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_CLIENT_ID = "clientId"
    }

    private val newContactActivityRequestCode = 1
    private lateinit var userIdentifiant: TextView
    private lateinit var clientIdentifiant: TextView
    private lateinit var clientName: EditText
    private lateinit var clientActivity : EditText
    private lateinit var clientAdress : EditText
    private lateinit var clientCity: EditText
    private lateinit var clientPostal: EditText
    private lateinit var clientDescription: EditText

    private  lateinit var  buttonDelete: Button
    private lateinit var proCheckBox : CheckBox
    private lateinit var btnValideUpdate : Button
    private lateinit var btnDoUpdate: Button
    private var clientId = 0
    val API = MaderaAPI(this)

    //region viewModel Implement
    val userViewModel: UserViewModel by viewModels{
        UserViewModelFactory((application as MaderaApplication).repositoryUser)
    }

    val clientViewModel : ClientViewModel by viewModels {
        ClientViewModelFactory((application as MaderaApplication).repositoryClient)
    }

    val contactViewModel : ContactViewModel by viewModels {
        ContactViewModelFactory((application as MaderaApplication).repositoryContact)
    }

    val projetViewModel : ProjetViewModel by viewModels {
        ProjetViewModelFactory((application as MaderaApplication).repositoryProjet)
    }

    val chantierViewModel : ChantierViewModel by viewModels {
        ChantierViewModelFactory((application as MaderaApplication).repositoryChantier)
    }

    val requestViewModel : RequestViewModel by viewModels {
        RequestViewModelFactory((application as MaderaApplication).repositoryRequest)
    }
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_client2)

        val imageDisconnect = findViewById(R.id.imageDisconnect) as ImageView

        imageDisconnect.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                    this@DetailsClientActivity,
                    "Déconnexion !",
                    Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        //region Implement Recycler
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_contact)
        val adapterContact = ContactListAdapter(contactViewModel, this,requestViewModel,clientViewModel,projetViewModel,chantierViewModel)
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

        val btnBack = findViewById<Button>(R.id.btn_back)
        btnBack.setOnClickListener {
            super.onBackPressed()
        }


        clientIdentifiant = findViewById(R.id.idChantierDetails)
        clientName = findViewById(R.id.nameChantierDetails)
        clientActivity = findViewById(R.id.nameCommercialChantierDetails)
        clientAdress = findViewById(R.id.adressClientDetails)
        clientCity = findViewById(R.id.cityClientDetails)
        clientPostal = findViewById(R.id.postalClientDetails)
        clientDescription = findViewById(R.id.chantierNote)
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

        clientName.setText(clientDetail.nom)
        clientActivity.setText(clientDetail.secteur)
        clientAdress.setText(clientDetail.adresse)
        clientCity.setText(clientDetail.ville)
        clientPostal.setText(clientDetail.codePostal.toString())
        clientDescription.setText(clientDetail.description)

        if (clientDetail.professionnel){
            clientIdentifiant.text =  "Client Professionnel #" + clientDetail.refClient.toString()

        }else{
            clientIdentifiant.text =  "Client Particulier  #" + clientDetail.refClient.toString()
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

        }

        userIdentifiant = findViewById(R.id.nameUserDetailClient) as TextView
        userIdentifiant.text = MaderaApplication.globalTest.pseudoUser

        /*
        Button Valide Update
         */
        btnValideUpdate.setOnClickListener {
            val codePostal = clientPostal.text.toString()
            val codePostalToInt = codePostal.toInt()
            clientViewModel.updateClient(clientDetail.refClient,clientName.text.toString(),clientAdress.text.toString(),codePostalToInt,clientCity.text.toString(),proCheckBox.isChecked,clientActivity.text.toString(),clientDescription.text.toString())

            CheckConnection().getMyResponse(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val allRequest = requestViewModel.getAllRequest()
                    for (request in allRequest){
                        when(request.requestType){
                            "connectToApi" -> {
                                API.connectToApi(request.param1.toString(),request.param2.toString(),clientViewModel,contactViewModel, projetViewModel,chantierViewModel)
                                requestViewModel.deleteRequest(request)
                            }
                            "insertionClientAPI" -> {
                                API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "suppressionClientAPI" -> {
                                API.suppressionClientAPI(request.param1.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "insertionContactAPI" -> {
                                API.insertionContactAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "suppressionContactAPI" -> {
                                API.suppressionContactAPI(request.param1.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "updateClientAPI" -> {
                                API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            else -> {
                                val pasderequest = ""
                            }
                        }
                    }
                    API.insertionClientAPI("update",clientName.text.toString(),clientAdress.text.toString(),proCheckBox.isChecked.toString(),clientActivity.text.toString(),clientCity.text.toString(),codePostal,clientDescription.text.toString(),clientDetail.refClient.toString())
                }

                override fun onError(anError: ANError?) {
                    requestViewModel.saveRequest(Request(null,"updateClientAPI","update",clientName.text.toString(),clientAdress.text.toString(),proCheckBox.isChecked.toString(),clientActivity.text.toString(),clientCity.text.toString(),codePostal,clientDescription.text.toString(),clientDetail.refClient.toString()))
                }
            }
            );


            btnDoUpdate.isVisible = true
            proCheckBox.isVisible = false
            clientName.isEnabled = false
            clientActivity.isEnabled = false
            clientAdress.isEnabled = false
            clientCity.isEnabled =false
            clientPostal.isEnabled = false
            clientDescription.isEnabled = false
            btnValideUpdate.isVisible = false



        }
    /*
    Button suppression treatment
     */
        buttonDelete = findViewById(R.id.delete_btn)

        buttonDelete.setOnClickListener {
            clientViewModel.deleteClient(clientDetail)
            /**/
            CheckConnection().getMyResponse(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val allRequest = requestViewModel.getAllRequest()
                    for (request in allRequest){
                        when(request.requestType){
                            "connectToApi" -> {
                                API.connectToApi(request.param1.toString(),request.param2.toString(),clientViewModel,contactViewModel, projetViewModel,chantierViewModel)
                                requestViewModel.deleteRequest(request)
                            }
                            "insertionClientAPI" -> {
                                API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "suppressionClientAPI" -> {
                                API.suppressionClientAPI(request.param1.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "insertionContactAPI" -> {
                                API.insertionContactAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "suppressionContactAPI" -> {
                                API.suppressionContactAPI(request.param1.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "updateClientAPI" -> {
                                API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            else -> {
                                val pasderequest = ""
                            }
                        }
                    }
                    API.suppressionClientAPI(clientDetail.refClient.toString())
                }

                override fun onError(anError: ANError?) {
                    requestViewModel.saveRequest(Request(null,"suppressionClientAPI",clientDetail.refClient.toString(),null,null,null,null,null,null,null,null))
                }
            }
            );

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
            val client = clientViewModel.getClientById(clientId)

            /**/
            CheckConnection().getMyResponse(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    val allRequest = requestViewModel.getAllRequest()
                    for (request in allRequest){
                        when(request.requestType){
                            "connectToApi" -> {
                                API.connectToApi(request.param1.toString(),request.param2.toString(),clientViewModel,contactViewModel, projetViewModel,chantierViewModel)
                                requestViewModel.deleteRequest(request)
                            }
                            "insertionClientAPI" -> {
                                API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "suppressionClientAPI" -> {
                                API.suppressionClientAPI(request.param1.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "insertionContactAPI" -> {
                                API.insertionContactAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "suppressionContactAPI" -> {
                                API.suppressionContactAPI(request.param1.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            "updateClientAPI" -> {
                                API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                requestViewModel.deleteRequest(request)
                            }
                            else -> {
                                val pasderequest = ""
                            }
                        }
                    }
                    API.insertionContactAPI(nomContact,prenomContact,fonctionContact,phoneContact,mailContact,client.refClient.toString(),rnds.toString())
                }

                override fun onError(anError: ANError?) {
                    requestViewModel.saveRequest(Request(null,"insertionContactAPI",nomContact,prenomContact,fonctionContact,phoneContact,mailContact,client.refClient.toString(),rnds.toString(),null,null))
                }
            }
            );
            /**/

        }else{
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}