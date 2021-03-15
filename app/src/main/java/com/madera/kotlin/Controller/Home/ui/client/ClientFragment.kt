package com.madera.kotlin.Controller.Home.ui.chantier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.madera.kotlin.R


class ClientFragment : Fragment() {


    private lateinit var clientViewModel: ClientViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        clientViewModel =
            ViewModelProvider(this).get(ClientViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_client, container, false)
        //region Components

        val btnCreateClient: Button = root.findViewById(R.id.btnCreateClient)
        val textView: TextView = root.findViewById(R.id.text_client)

        clientViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        return root
    }
}