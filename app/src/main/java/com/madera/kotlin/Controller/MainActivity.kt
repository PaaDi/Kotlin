package com.madera.kotlin.Controller
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.madera.kotlin.Controller.Authentification.PasswordMissActivity
import com.madera.kotlin.Controller.Sqlite.DataBase
import com.madera.kotlin.Entity.User
import com.madera.kotlin.Controller.Menu.MenuActivity
import com.madera.kotlin.R
import com.madera.kotlin.Sqlite.dbTest


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect_view)
        AndroidNetworking.initialize(getApplicationContext());
        //region Components
        val btnConnect = findViewById(R.id.btnConnect) as Button
        val titlePassMiss = findViewById(R.id.titlePassMiss) as TextView
        val logoInfo = findViewById(R.id.logoInfo) as ImageView
        val userToConnect = findViewById(R.id.userName) as TextView
        val passToConnect = findViewById(R.id.userPass) as TextView
        //endregion

        //region DBQueries
        /* DB connect to activity */
        val database = DataBase(this)


        /* TESTS add user in BDD if it's empty */
        if (database.getUsersCount() == 0)
        {
            database.createUser(User("admin", "admin", "Paletou", "Max", "maxime.paletou@viacesi.fr", 1))
        }

        //endregion

        titlePassMiss.setMovementMethod(LinkMovementMethod.getInstance());

        //region Events Listeners
        // Listener Button Connexion
         btnConnect.setOnClickListener {

             // Try to connect user
            /* var connectUser = database.tryToConnect(userToConnect.text.toString(),passToConnect.text.toString())


             if (connectUser){
                 Toast.makeText(this@MainActivity, "Connexion réussie !", Toast.LENGTH_SHORT).show()
                 val i = Intent(this, MenuActivity::class.java)
                 startActivity(i)

             }else{
                 Toast.makeText(this@MainActivity, "Mot de passe ou utilisateur incorrect !", Toast.LENGTH_SHORT).show()
             }*/

            // database.connectToApi(userToConnect.text.toString(),passToConnect.text.toString())

            val dbClass = dbTest()
             dbClass.loginToAPI(userToConnect.text.toString(),passToConnect.text.toString())

            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton

            //val i = Intent(this, PasswordMissActivity::class.java)
            //startActivity(i)
        }

        // Listener Mot de passe oublié
        titlePassMiss.setOnClickListener {
            // Le code a exécuté quand l'utilisateur à cliquer sur le bouton
            Toast.makeText(this@MainActivity, "Vous avez oubliez votre mot de passe ?", Toast.LENGTH_SHORT).show()
            val i = Intent(this, PasswordMissActivity::class.java)
            startActivity(i)
        }

        // Listener Bulle d'info
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