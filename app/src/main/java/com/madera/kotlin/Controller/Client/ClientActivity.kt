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
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Controller.Client.ClientListAdapter
import com.madera.kotlin.Controller.Client.NewClientActivity
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ClientViewModel
import com.madera.kotlin.ViewModel.ClientViewModelFactory


class ClientActivity : AppCompatActivity() {

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
            val adapterClient = ClientListAdapter()
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

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newClientActivityRequestCode && resultCode == Activity.RESULT_OK){
            // TODO : Récupération de l'ensemble des informations nécessaire à la création client
            data?.getStringExtra(NewClientActivity.EXTRA_REPLY)?.let {
                val client = Client(it)
                clientViewModel.createClient(client)
            }
        }else{
            Toast.makeText(
                    applicationContext,
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG
            ).show()
        }
    }
}

