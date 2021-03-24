package com.madera.kotlin.Controller
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.madera.kotlin.Controller.Authentification.PasswordMissActivity
import com.madera.kotlin.Controller.Home.HomeActivity
import com.madera.kotlin.Database.CheckConnection
import com.madera.kotlin.Database.MaderaAPI
import com.madera.kotlin.Entity.Request
import com.madera.kotlin.Entity.User
import com.madera.kotlin.MaderaApplication
import com.madera.kotlin.R
import com.madera.kotlin.MaderaApplication.Companion.globalTest
import com.madera.kotlin.ViewModel.*
import org.json.JSONObject


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.connect_view)


        val MAPP = MaderaApplication()

        //region Components
            val btnConnect = findViewById(R.id.btnConnect) as Button
            val titlePassMiss = findViewById(R.id.titlePassMiss) as TextView
            val logoInfo = findViewById(R.id.logoInfo) as ImageView
            val userToConnect = findViewById(R.id.userName) as TextView
            val passToConnect = findViewById(R.id.userPass) as TextView
        //endregion

        //region viewModel Implement
            val userViewModel: UserViewModel by viewModels{
                UserViewModelFactory((application as MaderaApplication).repositoryUser)
            }

            val clientViewModel : ClientViewModel by viewModels {
                ClientViewModelFactory((application as MaderaApplication).repositoryClient)
            }

            val contactViewModel : ContactViewModel by viewModels {
                ContactViewModelFactory((application as MaderaApplication).repositoryContact)
            }

            val projetViewModel : ProjetViewModel by viewModels {
                ProjetViewModelFactory((application as MaderaApplication).repositoryProjet)
            }

            val chantierViewModel : ChantierViewModel by viewModels {
                ChantierViewModelFactory((application as MaderaApplication).repositoryChantier)
            }

            val requestViewModel : RequestViewModel by viewModels {
                RequestViewModelFactory((application as MaderaApplication).repositoryRequest)
            }
        //endregion
        //region API
            val API = MaderaAPI(this)

            /*Création des utilisateurs*/
            API.getAllUsersAPI(userViewModel)

        //endregion
        titlePassMiss.setMovementMethod(LinkMovementMethod.getInstance());
        //region Events Listeners
            // Bouton de connexion
             btnConnect.setOnClickListener {

                 // Test de connexion utilisateur
                 val connectUser = userViewModel.connectUser(userToConnect.text.toString(),passToConnect.text.toString())

                 if (connectUser){

                     Toast.makeText(this@MainActivity, "Connexion réussie !", Toast.LENGTH_SHORT).show()

                     CheckConnection().getMyResponse(object : JSONObjectRequestListener {
                         override fun onResponse(response: JSONObject?) {
                             val allRequest = requestViewModel.getAllRequest()
                             for (request in allRequest){
                                 when(request.requestType){
                                     "connectToApi" -> {
                                         API.connectToApi(request.param1.toString(),request.param2.toString(),clientViewModel,contactViewModel, projetViewModel,chantierViewModel)
                                         requestViewModel.deleteRequest(request)
                                     }
                                     "insertionClientAPI" -> {
                                         API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                         requestViewModel.deleteRequest(request)
                                     }
                                     "suppressionClientAPI" -> {
                                         API.suppressionClientAPI(request.param1.toString())
                                         requestViewModel.deleteRequest(request)
                                     }
                                     "insertionContactAPI" -> {
                                         API.insertionContactAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString())
                                         requestViewModel.deleteRequest(request)
                                     }
                                     "suppressionContactAPI" -> {
                                         API.suppressionContactAPI(request.param1.toString())
                                         requestViewModel.deleteRequest(request)
                                     }
                                     "updateClientAPI" -> {
                                         API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                         requestViewModel.deleteRequest(request)
                                     }
                                     else -> {
                                         val pasderequest = ""
                                     }
                                 }
                             }
                             API.connectToApi(userToConnect.text.toString(),passToConnect.text.toString(), clientViewModel,contactViewModel, projetViewModel,chantierViewModel)
                         }

                         override fun onError(anError: ANError?) {
                             requestViewModel.saveRequest(Request(null,"connectToApi",userToConnect.text.toString(),passToConnect.text.toString(),null,null,null,null,null,null,null))
                         }
                     }
                     );


                     val i = Intent(this, HomeActivity::class.java)
                     globalTest = userViewModel.getUserByName(userToConnect.text.toString())
                     startActivity(i)
                 }else{
                     Toast.makeText(this@MainActivity, "Mot de passe ou utilisateur incorrect !", Toast.LENGTH_SHORT).show()
                 }

                 // Test de connexion API
                 //database.connectToApi(userToConnect.text.toString(),passToConnect.text.toString())

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