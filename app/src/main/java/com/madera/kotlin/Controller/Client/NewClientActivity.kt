package com.madera.kotlin.Controller.Client

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.madera.kotlin.R

class NewClientActivity : AppCompatActivity() {

    private lateinit var editClientView: EditText
    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_client_view)

        editClientView = findViewById(R.id.txtName)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editClientView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                // TODO : Envoi de l'ensbemble des informations nécessaire à la création client
                val client = editClientView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY, client)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }


    }

    companion object{
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}