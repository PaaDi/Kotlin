package com.madera.kotlin.Database

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.madera.kotlin.Entity.*
import com.madera.kotlin.ViewModel.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import kotlin.math.log


class MaderaAPI(context: Context)
{

    //region Requêtes API
    /**
     * Récupération des utilisateurs
     */
    fun getAllUsersAPI(viewModel: UserViewModel){
        val jsonObject = JSONObject()
        try {
            jsonObject.put("validrequest", "OK")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        AndroidNetworking.post("http://maderaprod.mconan.ovh/API/getallusers")
                .addBodyParameter("validrequest", "OK")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val jsonArry = response.optJSONArray("users")
                        for (i in 0 until jsonArry.length()){
                            val jsonObject = jsonArry.getJSONObject(i)
                            val id = jsonObject.get("id")
                            val username = jsonObject.get("username")
                            val password = jsonObject.get("password")
                            val nom = jsonObject.get("nom")
                            val prenom = jsonObject.get("prenom")
                            val email = jsonObject.get("email")
                            val idrole = jsonObject.get("idrole")
                            val refuser = jsonObject.get("refuser")

                            try {
                                    val user = User(id.toString().toLong(),refuser.toString().toLong(),username.toString(),password.toString(),nom.toString(),prenom.toString(),idrole.toString().toInt())
                                    viewModel.insert(user)
                            }catch (e: Exception){
                                e.printStackTrace()
                            }

                        }
                    }

                    override fun onError(error: ANError) {
                        if (error.getErrorCode() != 0) {
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        }
                    }
                })
    }



    /**
     * Connexion à l'API, renvoi la mise à jour de la base de données
     */
    fun connectToApi(user: String, pass: String, viewModelClient: ClientViewModel, viewModelContact : ContactViewModel, viewModelProjet: ProjetViewModel, viewModelChantier: ChantierViewModel) {

            val jsonObject = JSONObject()
            try {
                jsonObject.put("username", user)
                jsonObject.put("password", pass)
            } catch (e: JSONException) {
                e.printStackTrace()
            }

            AndroidNetworking.post("http://maderaprod.mconan.ovh/API/login/")
                .addBodyParameter("username", user)
                .addBodyParameter("password", pass)
                .setTag("test")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        var isOk = response.get("valide")
                        if (isOk == true) {
                            getAllClientsAPI(viewModelClient)
                            getAllContactsAPI(viewModelContact,viewModelClient)
                            getAllProjetsAPI(viewModelProjet,viewModelClient)
                            getAllChantiersAPI(viewModelChantier,viewModelProjet)
                        } else {
                            val ok = "wewentthere"
                        }

                    }

                    override fun onError(error: ANError) {
                        if (error.getErrorCode() != 0) {
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        }
                    }
                })

        }

    /**
     * Requête de récupération des clients
     */
    fun getAllClientsAPI(viewModel: ClientViewModel){
        val jsonObject = JSONObject()
        try {
            jsonObject.put("validrequest", "OK")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        AndroidNetworking.post("http://maderaprod.mconan.ovh/API/getallclients")
                .addBodyParameter("validrequest", "OK")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                       val jsonArry = response.optJSONArray("clients")
                        for (i in 0 until jsonArry.length()){
                            val jsonObject = jsonArry.getJSONObject(i)
                            val nom = jsonObject.get("nom")
                            val adresse = jsonObject.get("adresse")
                            val codepostal = jsonObject.get("codepostal")
                            val ville = jsonObject.get("ville")
                            val estPro = jsonObject.get("estpro")
                            val secteur = jsonObject.get("secteur")
                            val description = jsonObject.get("description")
                            val refClient = jsonObject.get("refClient")

                            try {

                                if (viewModel.isClientExist(refClient.toString().toLong())){
                                    viewModel.updateClient(refClient.toString().toLong(),nom.toString(),adresse.toString(),codepostal.toString().toInt(),ville.toString(),estPro.toString().toBoolean(),secteur.toString(),description.toString())
                                }else{
                                    val client = Client(null,refClient.toString().toLong(),nom.toString(),adresse.toString(),codepostal.toString().toInt(),ville.toString(),estPro.toString().toBoolean(),secteur.toString(),description.toString())
                                    viewModel.createClient(client)
                                }

                            }catch (e: Exception){
                                e.printStackTrace()
                            }

                        }
                    }

                    override fun onError(error: ANError) {
                        if (error.getErrorCode() != 0) {
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        }
                    }
                })
    }

    /**
     * Requête de récupération des contacts
     */
    fun getAllContactsAPI(viewModel: ContactViewModel, viewModelCli : ClientViewModel){
        val jsonObject = JSONObject()
        try {
            jsonObject.put("validrequest", "OK")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        AndroidNetworking.post("http://maderaprod.mconan.ovh/API/getallcontacts")
                .addBodyParameter("validrequest", "OK")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val jsonArry = response.optJSONArray("contacts")
                        for (i in 0 until jsonArry.length()){
                            val jsonObject = jsonArry.getJSONObject(i)
                            val refClient = jsonObject.get("refClient")
                            val nom = jsonObject.get("nom")
                            val prenom = jsonObject.get("prenom")
                            val fonction = jsonObject.get("fonction")
                            val telephone = jsonObject.get("telephone")
                            val mail = jsonObject.get("mail")
                            val refContact = jsonObject.get("refContact")


                            try {

                                if (viewModel.isContactExist(refContact.toString().toLong())){

                                }else{
                                    val cli = viewModelCli.getClientByRef(refClient.toString().toLong())
                                   val contact = Contact(null,refContact.toString().toLong(),cli.idClient,nom.toString(),prenom.toString(),fonction.toString(),telephone.toString(),mail.toString())
                                    viewModel.createContact(contact)
                                }

                            }catch (e: Exception){
                                e.printStackTrace()
                            }

                        }
                    }

                    override fun onError(error: ANError) {
                        if (error.getErrorCode() != 0) {
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        }
                    }
                })
    }

    /**
     * Requête de récupération des projets
     */
    fun getAllProjetsAPI(viewModelProjet: ProjetViewModel, viewModelClient: ClientViewModel){
        val jsonObject = JSONObject()
        try {
            jsonObject.put("validrequest", "OK")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        AndroidNetworking.post("http://maderaprod.mconan.ovh/API/getallprojets")
                .addBodyParameter("validrequest", "OK")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val jsonArry = response.optJSONArray("projets")
                        for (i in 0 until jsonArry.length()){
                            val jsonObject = jsonArry.getJSONObject(i)
                            val refClient = jsonObject.get("refClient")
                            val refProjet = jsonObject.get("refProjet")
                            val nom = jsonObject.get("nom")
                            val dateCreation = jsonObject.get("dateCreation")
                            val notes = jsonObject.get("notes")

                            try {

                                if (viewModelProjet.isProjetExist(refProjet.toString().toLong())){
                                    val client = viewModelClient.getClientByRef(refClient.toString().toLong())
                                    viewModelProjet.updateProjet(client.idClient,nom.toString(),notes.toString(),refProjet.toString().toLong())
                                }else{
                                    val client = viewModelClient.getClientByRef(refClient.toString().toLong())
                                    viewModelProjet.createProject(Projet(null,refProjet.toString().toLong(),client.idClient,nom.toString(),dateCreation.toString(),notes.toString()))
                                }

                            }catch (e: Exception){
                                e.printStackTrace()
                            }

                        }
                    }

                    override fun onError(error: ANError) {
                        if (error.getErrorCode() != 0) {
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        }
                    }
                })
    }

    /**
     * Requête de récupération des chantiers
     */
    fun getAllChantiersAPI(viewModelChantier: ChantierViewModel, viewModelProjet: ProjetViewModel){
        val jsonObject = JSONObject()
        try {
            jsonObject.put("validrequest", "OK")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        AndroidNetworking.post("http://maderaprod.mconan.ovh/API/getallchantiers")
                .addBodyParameter("validrequest", "OK")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject) {
                        val jsonArry = response.optJSONArray("chantiers")
                        for (i in 0 until jsonArry.length()){
                            val jsonObject = jsonArry.getJSONObject(i)
                            val refProjet = jsonObject.get("refProjet")
                            val nom = jsonObject.get("nom")
                            val notes = jsonObject.get("notes")
                            val refChantier = jsonObject.get("refChantier")
                            val adresse = jsonObject.get("adresse")
                            val ville = jsonObject.get("ville")
                            val codePostal = jsonObject.get("codePostal")
                            val datecreation = jsonObject.get("datecreation")
                            val datelancement = jsonObject.get("datelancement")
                            val idUser = jsonObject.get("idUser")

                            try {

                                if (viewModelChantier.isChantierExist(refChantier.toString().toLong())){
                                    val projet = viewModelProjet.getProjectByRef(refProjet.toString().toLong())
                                    viewModelChantier.updateChantier(projet.idProjet,idUser.toString().toInt(),nom.toString(),adresse.toString(),codePostal.toString().toInt(),ville.toString(),notes.toString(),datecreation.toString(),datelancement.toString(),refChantier.toString().toLong())
                                }else{
                                    val projet = viewModelProjet.getProjectByRef(refProjet.toString().toLong())
                                    viewModelChantier.createChantier(Chantier(null,refChantier.toString().toLong(),projet.idProjet,idUser.toString().toInt(),nom.toString(),adresse.toString(),codePostal.toString().toInt(),ville.toString(),notes.toString(),datecreation.toString(),datelancement.toString()))
                                }

                            }catch (e: Exception){
                                e.printStackTrace()
                            }

                        }
                    }

                    override fun onError(error: ANError) {
                        if (error.getErrorCode() != 0) {
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Log.d(TAG, "onError errorDetail : " + error.getErrorDetail());
                            Log.d(TAG, "onError errorCode : " + error.getErrorCode());
                            Log.d(TAG, "onError errorBody : " + error.getErrorBody());
                        }
                    }
                })
    }

    // endregion



}



