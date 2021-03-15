package com.madera.kotlin.Controller.Home.ui.devis

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.madera.kotlin.R

class DevisFragment : Fragment() {

    private lateinit var devisViewModel: DevisViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        devisViewModel =
            ViewModelProvider(this).get(DevisViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_devis, container, false)
        val textView: TextView = root.findViewById(R.id.text_devis)
        devisViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}