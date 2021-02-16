package com.madera.kotlin.Controller.Client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ClientViewModel
import com.madera.kotlin.ViewModel.ClientViewModelFactory

class DetailsClientActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_CLIENT_ID = "clientId"
    }

    val clientViewModel: ClientViewModel by viewModels {
        ClientViewModelFactory((application as MaderaApplication).repositoryClient)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_client2)

        val clientId = intent.getIntExtra(EXTRA_CLIENT_ID, 1)

        val clientDetail = clientViewModel.getClientById(clientId)

        val clientName = findViewById<TextView>(R.id.nameClientDetails)
        clientName.text = clientDetail.nom


    }
}