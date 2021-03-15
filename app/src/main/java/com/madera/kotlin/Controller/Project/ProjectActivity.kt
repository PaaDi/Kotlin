package com.madera.kotlin.Controller.Home

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.SpinnerAdapter
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.R


class ProjectActivity : AppCompatActivity() {

    var btn_popup: Button? = null

    lateinit var title: Array<String>
    var spinner_item: String? = null

    var adapter: SpinnerAdapter? = null

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_view)

    }

    

}