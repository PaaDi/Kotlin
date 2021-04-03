package com.madera.kotlin.Controller.Home

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.madera.kotlin.Controller.Client.CellClickListener
import com.madera.kotlin.Controller.Client.ClientListAdapter
import com.madera.kotlin.Controller.Client.DetailsClientActivity
import com.madera.kotlin.Controller.Client.NewClientActivity
import com.madera.kotlin.Controller.MainActivity
import com.madera.kotlin.Database.CheckConnection
import com.madera.kotlin.Database.MaderaAPI
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.Entity.Request
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.*
import org.json.JSONObject


class ClientActivity : AppCompatActivity(), CellClickListener {

    //region Global Component
    private val newClientActivityRequestCode = 1
    private lateinit var userIdentifiant: TextView
    //endregion
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

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_view)
        //region Implement Recylcer
            /* FOR CLIENT */
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_client)
            val adapterClient = ClientListAdapter(this)
            recyclerView.adapter = adapterClient
            recyclerView.layoutManager = LinearLayoutManager(this)

        //endregion

        //region Components
        val btnCreateClient = findViewById(R.id.btnCreateClient) as Button
        val imageHelpClient = findViewById(R.id.imageHelpClient) as ImageView
        val imageDisconnect = findViewById(R.id.imageDisconnect) as ImageView

        //region Observer
            clientViewModel.AllClients.observe(this, Observer { clients ->
                clients?.let { adapterClient.submitList(it) }
            })
        //endregion

        imageDisconnect.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                    this@ClientActivity,
                    "Déconnexion !",
                    Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }

        // Listener NewClientActivity
        btnCreateClient.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@ClientActivity,
                "Vous souhaitez créer un nouveau client ?",
                Toast.LENGTH_SHORT
            ).show()

            val i = Intent(this@ClientActivity, NewClientActivity::class.java)
            startActivityForResult(i, newClientActivityRequestCode)
        }

        // Bulle d'information
        imageHelpClient.setOnClickListener {
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
            alert.setTitle("Espace Client !")
            // show alert dialog
            alert.show()
        }

        userIdentifiant = findViewById(R.id.nameUserClient) as TextView
        userIdentifiant.text = MaderaApplication.globalTest.pseudoUser

        val btnBack = findViewById<Button>(R.id.btn_backClient)
        btnBack.setOnClickListener {
            super.onBackPressed()
        }

    }

    override fun onCellClickListener(id: Int?) {
        val intent = Intent(this,DetailsClientActivity::class.java)
        intent.putExtra(DetailsClientActivity.EXTRA_CLIENT_ID, id)
        startActivity(intent)
    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var nomClient = ""
        var secteurActivite = ""
        var checkPro = false
        var adresse = ""
        var ville = ""
        var codePostal = ""
        var description = "" +
                ""
        if (requestCode == newClientActivityRequestCode && resultCode == Activity.RESULT_OK){

            data?.getStringExtra(NewClientActivity.EXTRA_CLIENT_NAME)?.let {
                nomClient = it
            }
            data?.getStringExtra(NewClientActivity.EXTRA_CLIENT_ADRESS)?.let {
                adresse = it
            }
            data?.getStringExtra(NewClientActivity.EXTRA_CLIENT_CODEPOSTAL)?.let {
                codePostal = it
            }
            data?.getStringExtra(NewClientActivity.EXTRA_CLIENT_DESCRIPTION)?.let {
                description = it
            }
            data?.getStringExtra(NewClientActivity.EXTRA_CLIENT_ISPRO)?.let {
                if (it == "true"){
                    checkPro = true
                }else{
                    checkPro = false
                }
            }
            data?.getStringExtra(NewClientActivity.EXTRA_CLIENT_SECTEUR)?.let {
                secteurActivite = it
            }
            data?.getStringExtra(NewClientActivity.EXTRA_CLIENT_VILLE)?.let {
                ville = it
            }
            val rnds = (0..99999999).random().toLong()
            clientViewModel.createClient(Client(null, rnds,nomClient,adresse,codePostal.toInt(),ville,checkPro,secteurActivite,description))

            val API = MaderaAPI(this)

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
                                requestViewModel.saveRequest(Request(null,"insertionClientAPI","create",nomClient,adresse,checkPro.toString(),secteurActivite,ville,codePostal,description,rnds.toString()))
                            }
                        }
                    }
                    API.insertionClientAPI("create",nomClient,adresse,checkPro.toString(),secteurActivite,ville,codePostal,description,rnds.toString())
                }

                override fun onError(anError: ANError?) {
                    requestViewModel.saveRequest(Request(null,"insertionClientAPI","create",nomClient,adresse,checkPro.toString(),secteurActivite,ville,codePostal,description,rnds.toString()))
                }
            }
            );

        }else{
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}

