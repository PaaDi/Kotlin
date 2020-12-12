package com.madera.kotlin.Controller.Menu

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.R


class MenuActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_view)
        //get the spinner from the xml.
        val dropdown = findViewById<Spinner>(R.id.spinner1)
        //create a list of items for the spinner.
        val items = arrayOf("François", "Yves", "Guillaume", "Marta","Mélanie" )
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        //set the spinners adapter to the previously created one.
        dropdown.adapter = adapter

    }
}