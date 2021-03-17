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
import com.madera.kotlin.Controller.Client.CellClickListener
import com.madera.kotlin.Controller.Client.ClientListAdapter
import com.madera.kotlin.Controller.Client.DetailsClientActivity
import com.madera.kotlin.Controller.Client.NewClientActivity
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ClientViewModel
import com.madera.kotlin.ViewModel.ClientViewModelFactory


class ClientActivity : AppCompatActivity(), CellClickListener {

    //region Global Component
    private val newClientActivityRequestCode = 1


    //endregion
    //region viewModel Implement
    val clientViewModel: ClientViewModel by viewModels {
        ClientViewModelFactory((application as MaderaApplication).repositoryClient)
    }
    //endregion

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.client_view)

        //region Implement Recylcer
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_client)
            val adapterClient = ClientListAdapter(this)
            recyclerView.adapter = adapterClient
            recyclerView.layoutManager = LinearLayoutManager(this)
        //endregion

        //region Components
        val btnCreateClient = findViewById(R.id.btnCreateClient) as Button
        val imageHelpClient = findViewById(R.id.imageHelpClient) as ImageView


        //region Observer
            clientViewModel.AllClients.observe(this, Observer { clients ->
                clients?.let { adapterClient.submitList(it) }
            })
        //endregion

        // Listener ClientActivity
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
            dialogBuilder.setMessage("Sur cette page est affiché la liste des clients. Vous pouvez ajouter, éditer ou alors supprimer un client à la liste.")
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


        }else{
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}

