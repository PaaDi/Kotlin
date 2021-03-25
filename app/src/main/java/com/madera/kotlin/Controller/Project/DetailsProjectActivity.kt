package com.madera.kotlin.Controller.Project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Controller.Chantier.DetailsChantierActivity
import com.madera.kotlin.Controller.Client.CellClickListener
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.*

class DetailsProjectActivity : AppCompatActivity(), CellClickListener {
    //region Component Implement
    companion object{
        const val EXTRA_PROJECT_ID = "projetId"
    }

    val projetViewModel : ProjetViewModel by viewModels {
        ProjetViewModelFactory((application as MaderaApplication).repositoryProjet)
    }
    val clientViewModel : ClientViewModel by viewModels {
        ClientViewModelFactory((application as MaderaApplication).repositoryClient)
    }
    val chantierViewModel: ChantierViewModel by viewModels {
        ChantierViewModelFactory((application as MaderaApplication).repositoryChantier)
    }


    private lateinit var userIdentifiant: TextView
    private lateinit var idProjectDetails : TextView
    private lateinit var nomProjetDetails : EditText
    private lateinit var nomClientProjetDetails : EditText
    private lateinit var noteProjetDetails : EditText
    private var projectId = 0
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_project)

        //region NavBar Implement
            userIdentifiant = findViewById(R.id.nameUserDetailClient) as TextView
            userIdentifiant.text = MaderaApplication.globalTest.pseudoUser

            val btnBack = findViewById<Button>(R.id.btn_backDetailsProjet)
            btnBack.setOnClickListener {
                super.onBackPressed()
            }
        //endregion

        //region Database Query
            projectId = intent.getIntExtra(EXTRA_PROJECT_ID, 1)
            val projectDetails = projetViewModel.getProjectById(projectId)
        //endregion

        //region Recycler Implement
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_chantier)
        val adapterChantier = ChantierListAdapter(this,projetViewModel)
        recyclerView.adapter = adapterChantier
        recyclerView.layoutManager = LinearLayoutManager(this)
        //endregion

        //region Observer Implement
        chantierViewModel.getAllChantiersByProjectId(projectId).observe(this,{
            chantiers -> chantiers?.let { adapterChantier.submitList(it) }
        })
        //endregion

        //region Implement Info
        idProjectDetails = findViewById(R.id.idChantierDetails)
        nomProjetDetails = findViewById(R.id.nameChantierDetails)
        nomClientProjetDetails = findViewById(R.id.nameCommercialChantierDetails)
        noteProjetDetails = findViewById(R.id.chantierNote)

        idProjectDetails.isEnabled = false
        nomProjetDetails.isEnabled = false
        nomClientProjetDetails.isEnabled = false
        noteProjetDetails.isEnabled = false

        idProjectDetails.setText("Projet #"+projectDetails.refProjet + " - " + projectDetails.datecreationProjet)
        nomProjetDetails.setText(projectDetails.nomProjet)

        val nameClient = clientViewModel.getClientById(projectDetails.clientId)

        nomClientProjetDetails.setText(nameClient.nom)
        noteProjetDetails.setText(projectDetails.notesProjet)
        //endregion
    }

    override fun onCellClickListener(id: Int?) {
        val intent = Intent(this, DetailsChantierActivity::class.java)
        intent.putExtra(DetailsChantierActivity.EXTRA_CHANTIER_ID, id)
        startActivity(intent)
    }
}