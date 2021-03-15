package com.madera.kotlin.Controller.Home.ui.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.madera.kotlin.R

class ProjectFragment : Fragment() {

    private lateinit var projectViewModel: ProjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        projectViewModel =
            ViewModelProvider(this).get(ProjectViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_project, container, false)
        val textView: TextView = root.findViewById(R.id.text_project)
        projectViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}