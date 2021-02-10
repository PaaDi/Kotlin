package com.madera.kotlin.Controller
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.androidnetworking.AndroidNetworking
import com.madera.kotlin.Controller.Authentification.PasswordMissActivity
import com.madera.kotlin.Controller.Sqlite.DataBase
import com.madera.kotlin.Entity.User
import com.madera.kotlin.Controller.Home.HomeActivity
import com.madera.kotlin.Controller.Sqlite.dbTestUNUSED
import com.madera.kotlin.Database.MaderaBase
import com.madera.kotlin.R


class MainActivity : AppCompatActivity() {
    companion object{
        var dataBase: MaderaBase? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect_view)
        AndroidNetworking.initialize(getApplicationContext());

        //region Database Implementation
            MainActivity.dataBase = Room.databaseBuilder(this, MaderaBase::class.java, "MaderaBase").build()
        //endregion

        //region Components
            val btnConnect = findViewById(R.id.btnConnect) as Button
            val titlePassMiss = findViewById(R.id.titlePassMiss) as TextView
            val logoInfo = findViewById(R.id.logoInfo) as ImageView
            val userToConnect = findViewById(R.id.userName) as TextView
            val passToConnect = findViewById(R.id.userPass) as TextView
        //endregion

        //region DBQueries

        //endregion

        titlePassMiss.setMovementMethod(LinkMovementMethod.getInstance());

        //region Events Listeners
            // Bouton de connexion
             btnConnect.setOnClickListener {

                 // Test de connexion utilisateur
                 //TODO: Remplacer la connexion locale par la connexion API
                 /*var connectUser = database.tryToConnect(userToConnect.text.toString(),passToConnect.text.toString())

                 if (connectUser){
                     Toast.makeText(this@MainActivity, "Connexion réussie !", Toast.LENGTH_SHORT).show()
                     val i = Intent(this, HomeActivity::class.java)
                     startActivity(i)
                 }else{
                     Toast.makeText(this@MainActivity, "Mot de passe ou utilisateur incorrect !", Toast.LENGTH_SHORT).show()
                 }

                 // Test de connexion API
                 database.connectToApi(userToConnect.text.toString(),passToConnect.text.toString())*/

                val i = Intent(this, HomeActivity::class.java)
                startActivity(i)
            }

            // Bouton mot de passe oublié
            titlePassMiss.setOnClickListener {
                // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
                Toast.makeText(this@MainActivity, "Vous avez oubliez votre mot de passe ?", Toast.LENGTH_SHORT).show()
                val i = Intent(this, PasswordMissActivity::class.java)
                startActivity(i)
            }

            // Bulle d'information
            logoInfo.setOnClickListener {
                // build alert dialog
                val dialogBuilder = AlertDialog.Builder(this)

                // set message of alert dialog
                dialogBuilder.setMessage("Pour accéder à votre espace personnel, veuillez saisir votre nom d'utilisateur ainsi que votre mot de passe.")
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action
                        .setPositiveButton("Retour", DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Bienvenue !")
                // show alert dialog
                alert.show()
            }
        //endregion
    }
}