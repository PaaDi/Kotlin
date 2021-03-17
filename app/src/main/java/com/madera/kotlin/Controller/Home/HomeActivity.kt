package com.madera.kotlin.Controller.Home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Controller.Client.CellClickListener
import com.madera.kotlin.Controller.Client.DetailsClientActivity
import com.madera.kotlin.Entity.Chantier
import com.madera.kotlin.Entity.Projet
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.MaderaApplication.Companion.globalTest
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ChantierViewModel
import com.madera.kotlin.ViewModel.ChantierViewModelFactory
import com.madera.kotlin.ViewModel.ProjetViewModel
import com.madera.kotlin.ViewModel.ProjetViewModelFactory


class HomeActivity : AppCompatActivity(), CellClickListener {
    val projetViewModel : ProjetViewModel by viewModels {
        ProjetViewModelFactory((application as MaderaApplication).repositoryProjet)
    }
    val chantierViewModel: ChantierViewModel by viewModels {
        ChantierViewModelFactory((application as MaderaApplication).repositoryChantier)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_view)
        val rnds = (0..99999999).random().toLong()
        //region Implement Recycler
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_projet)
        val adapterProjet = ProjetListAdapter(this)
        recyclerView.adapter = adapterProjet
        recyclerView.layoutManager = LinearLayoutManager(this)
        //endregion

        //region Observer
        projetViewModel.getAllProjetsByUser(globalTest.idUser.toInt()).observe(this,{
            projets -> projets?.let { adapterProjet.submitList(it) }
        })
        //endregion

        //region Components
        val btnListProject = findViewById(R.id.btnListProject) as Button
        val btnListClient = findViewById(R.id.btnListClient) as Button
        val imageHelpHome = findViewById(R.id.imageHelpHome) as ImageView

        //region Events Listeners
        // Listener ProjectActivity
        btnListProject.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@HomeActivity,
                "Redirection vers l'espace projet !",
                Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, ProjectActivity::class.java)
            startActivity(i)
        }

        // Listener ClientActivity
        btnListClient.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@HomeActivity,
                "Redirection vers l'espace client !",
                Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, ClientActivity::class.java)
            startActivity(i)
        }

        // Bulle d'information
        imageHelpHome.setOnClickListener {
            // build alert dialog
            val dialogBuilder = AlertDialog.Builder(this)

            // set message of alert dialog
            dialogBuilder.setMessage("Pour afficher la liste des projets/clients, veuillez cliquer sur un des boutons.")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action
                    .setPositiveButton("Retour", DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                    })

            // create dialog box
            val alert = dialogBuilder.create()
            // set title for alert dialog box
            alert.setTitle("Page d'Accueil !")
            // show alert dialog
            alert.show()
        }


    }

    override fun onCellClickListener(id: Int?) {
        val intent = Intent(this, DetailsClientActivity::class.java)
        intent.putExtra(DetailsClientActivity.EXTRA_CLIENT_ID, id)
        startActivity(intent)
    }
}