package com.madera.kotlin.Controller.Home.ui.chantier

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.madera.kotlin.R

class ChantierFragment : Fragment() {

    private lateinit var chantierViewModel: ChantierViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        chantierViewModel =
            ViewModelProvider(this).get(ChantierViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_chantier, container, false)
        val textView: TextView = root.findViewById(R.id.text_chantier)
        chantierViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}