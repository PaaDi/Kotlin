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
        val clientActivity = findViewById<TextView>(R.id.activityClientDetails)
        val clientAdress = findViewById<TextView>(R.id.adressClientDetails)
        val clientCity = findViewById<TextView>(R.id.cityClientDetails)
        val clientPostal = findViewById<TextView>(R.id.postalClientDetails)
        val clientDescription = findViewById<TextView>(R.id.descriptionClientDetails)
        val clientProfessionnal = findViewById<TextView>(R.id.professionnalClientDetails)

        clientName.text = clientDetail.nom
        clientActivity.text = clientDetail.secteur
        clientAdress.text = clientDetail.adresse
        clientCity.text = clientDetail.ville
        clientPostal.text = clientDetail.codePostal.toString()
        clientDescription.text = clientDetail.description
        clientProfessionnal.text = clientDetail.professionnel.toString()

    }
}