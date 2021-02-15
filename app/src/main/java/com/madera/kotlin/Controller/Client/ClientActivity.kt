package com.madera.kotlin.Controller.Home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Controller.Client.ClientListAdapter
import com.madera.kotlin.Controller.Client.DetailsClientActivity
import com.madera.kotlin.Controller.Client.NewClientActivity
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ClientViewModel
import com.madera.kotlin.ViewModel.ClientViewModelFactory


class ClientActivity : AppCompatActivity(), ClientListAdapter.ClientsListAdapterListener {

    //region Global Component
    private val newClientActivityRequestCode = 1
    private lateinit var clientsAdapter: ClientListAdapter
    private lateinit var clients: MutableList<Client>
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

        clients = mutableListOf()
        //region Implement Recylcer
            val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_client)
            val adapterClient = ClientListAdapter(clients,this)
            recyclerView.adapter = adapterClient
            recyclerView.layoutManager = LinearLayoutManager(this)
        //endregion

        //region Components
        val btnCreateClient = findViewById(R.id.btnCreateClient) as Button

        val btnFilter = findViewById<Spinner>(R.id.spinner1)
        val items = arrayOf("Melvin", "Maxime", "Barbara", "Maximilien", "Mathis")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        //set the spinners adapter to the previously created one.
        btnFilter.adapter = adapter

        //region Observer

        clientViewModel.AllClients.observe(this, Observer { clients ->
            clients?.let { adapterClient.submitList(it) }
        })
           /* clientViewModel.AllClients.observe(this, Observer { clients ->
                updateClients(clients!!)
            })*/
        //endregion

        // Listener ClientActivity
        btnCreateClient.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@ClientActivity,
                "Création d'un nouveau client !",
                Toast.LENGTH_SHORT
            ).show()

            val i = Intent(this@ClientActivity, NewClientActivity::class.java)
            startActivityForResult(i, newClientActivityRequestCode)
        }

    }

    private fun updateClients(newClients: List<Client>) {
        clients.clear()
        clients.addAll(newClients)
        clientsAdapter.notifyDataSetChanged()
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
            // TODO : Récupération de l'ensemble des informations nécessaire à la création client
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

            clientViewModel.createClient(Client(null,nomClient,adresse,codePostal.toInt(),ville,checkPro,secteurActivite,description))


        }else{
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onClientSelected(client: Client) {
        TODO("Not yet implemented")
    }
}

