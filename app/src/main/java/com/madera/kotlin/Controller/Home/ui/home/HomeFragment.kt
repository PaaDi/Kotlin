package com.madera.kotlin.Controller.Home.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.madera.kotlin.Controller.Home.ClientActivity
import com.madera.kotlin.Controller.Home.ProjectActivity

import com.madera.kotlin.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        //region Components
        val btnListProject = root.findViewById(R.id.btnListProject) as Button
        val btnListClient = root.findViewById(R.id.btnListClient) as Button

        //region Events Listeners
        // Listener ProjectActivity
        /*btnListProject.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(
                this@HomeViewActivity,
                "Redirection vers l'espace projet !",
                Toast.LENGTH_SHORT
            ).show()
            val i = Intent(this, ProjectActivity::class.java)
            startActivity(i)
        }*/

        // Listener ClientActivity
        btnListClient.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            activity?.let {
                val i = Intent(it, ClientActivity::class.java)
                it.startActivity(i)
            }


        }

        return root
    }
}