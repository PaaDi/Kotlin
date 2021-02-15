package com.madera.kotlin.Controller.Client

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.madera.kotlin.R

class NewClientActivity : AppCompatActivity() {

    private lateinit var ClientNameView: EditText
    private lateinit var ClientSecteurView: EditText
    private lateinit var ClientAdresseView: EditText
    private lateinit var ClientVilleView: EditText
    private lateinit var ClientCodePostalView: EditText
    private lateinit var ClientDescriptionView: EditText
    private lateinit var ClientIsProView: CheckBox

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_client_view)

        ClientNameView = findViewById(R.id.txtName)
        ClientSecteurView = findViewById(R.id.txtSecteur)
        ClientAdresseView = findViewById(R.id.txtAdress)
        ClientVilleView = findViewById(R.id.txtCity)
        ClientCodePostalView = findViewById(R.id.txtPostal)
        ClientDescriptionView = findViewById(R.id.txtDesc)
        ClientIsProView = findViewById(R.id.checkProfessionnel)


        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(ClientNameView.text) && TextUtils.isEmpty(ClientAdresseView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                // TODO : Envoi de l'ensbemble des informations nécessaire à la création client

                val clientName = ClientNameView.text.toString()
                val clientAdress = ClientAdresseView.text.toString()
                val clientSecteur = ClientSecteurView.text.toString()
                val clientVille = ClientVilleView.text.toString()
                val clientCodePostal = ClientCodePostalView.text.toString()
                val clientDescription = ClientDescriptionView.text.toString()
                val clientIsPro = ClientIsProView.isChecked.toString()

                replyIntent.putExtra(EXTRA_CLIENT_NAME, clientName)
                replyIntent.putExtra(EXTRA_CLIENT_ADRESS, clientAdress)
                replyIntent.putExtra(EXTRA_CLIENT_SECTEUR, clientSecteur)
                replyIntent.putExtra(EXTRA_CLIENT_VILLE, clientVille)
                replyIntent.putExtra(EXTRA_CLIENT_CODEPOSTAL, clientCodePostal)
                replyIntent.putExtra(EXTRA_CLIENT_ISPRO, clientIsPro)
                replyIntent.putExtra(EXTRA_CLIENT_DESCRIPTION, clientDescription)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }


    }

    companion object{
        const val EXTRA_CLIENT_NAME = "nom"
        const val EXTRA_CLIENT_ADRESS = "adresse"
        const val EXTRA_CLIENT_SECTEUR = "secteur"
        const val EXTRA_CLIENT_VILLE = "ville"
        const val EXTRA_CLIENT_CODEPOSTAL = "codePostal"
        const val EXTRA_CLIENT_ISPRO = "isPro"
        const val EXTRA_CLIENT_DESCRIPTION = "description"
    }
}