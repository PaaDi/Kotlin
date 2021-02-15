package com.madera.kotlin.Controller.Client

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.madera.kotlin.R

class DetailsClientActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_CLIENT_ID = "clientId"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_client2)

        val clientId = intent.getIntExtra(EXTRA_CLIENT_ID, 1)
    }
}