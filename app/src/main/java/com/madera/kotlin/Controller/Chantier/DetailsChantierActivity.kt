package com.madera.kotlin.Controller.Chantier

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ChantierViewModel
import com.madera.kotlin.ViewModel.ChantierViewModelFactory
import com.madera.kotlin.ViewModel.UserViewModel
import com.madera.kotlin.ViewModel.UserViewModelFactory

class DetailsChantierActivity : AppCompatActivity() {

    //region Component Implement
    companion object{
        const val EXTRA_CHANTIER_ID = "chantierId"
    }

    val chantierViewModel: ChantierViewModel by viewModels {
        ChantierViewModelFactory((application as MaderaApplication).repositoryChantier)
    }
    val userViewModel: UserViewModel by viewModels{
        UserViewModelFactory((application as MaderaApplication).repositoryUser)
    }

    private lateinit var userIdentifiant: TextView
    private lateinit var nameChantier : EditText
    private lateinit var commercialChantier : EditText
    private lateinit var creationChantier : EditText
    private lateinit var lancementChantier : EditText
    private lateinit var adresseChantier : EditText
    private lateinit var notesChantier : EditText
    private lateinit var idChantierDetails : TextView

    private var chantierId = 0
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_chantier)

        //region NavBar Implement
        userIdentifiant = findViewById(R.id.nameUserDetailClient) as TextView
        userIdentifiant.text = MaderaApplication.globalTest.pseudoUser

        val btnBack = findViewById<Button>(R.id.btn_backDetailsChantier)
        btnBack.setOnClickListener {
            super.onBackPressed()
        }
        //endregion

        //region Database Query
        chantierId = intent.getIntExtra(EXTRA_CHANTIER_ID,1)
        val chantierDetails = chantierViewModel.getChantierById(chantierId)
        //endregion

        //region Implement Infos
        nameChantier = findViewById(R.id.nameChantierDetails)
        commercialChantier = findViewById(R.id.nameCommercialChantierDetails)
        creationChantier = findViewById(R.id.creationChantierDetails)
        lancementChantier = findViewById(R.id.chantierLancement)
        adresseChantier = findViewById(R.id.chantierAdresse)
        notesChantier = findViewById(R.id.chantierNote)
        idChantierDetails = findViewById(R.id.idChantierDetails)

        nameChantier.isEnabled = false
        commercialChantier.isEnabled = false
        creationChantier.isEnabled = false
        lancementChantier.isEnabled = false
        adresseChantier.isEnabled = false
        notesChantier.isEnabled = false

        idChantierDetails.setText("Chantier #"+ chantierDetails.refChantier)

        val user = userViewModel.getUserById(chantierDetails.userId)
        commercialChantier.setText(user.nameUser + " " + user.firstNameUser)
        nameChantier.setText(chantierDetails.nomChantier)
        creationChantier.setText(chantierDetails.datecreationChantier)
        lancementChantier.setText(chantierDetails.datelancementChantier)
        adresseChantier.setText(chantierDetails.adresseChantier + " - " + chantierDetails.villeChantier + " " + chantierDetails.codePostalChantier)
        notesChantier.setText(chantierDetails.notesChantier)

        //endregion
    }
}