package com.madera.kotlin.Database

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.ViewModel.ClientViewModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.Exception
import kotlin.math.log


class MaderaAPI(context: Context)
{

    //region Requêtes API
    /**
     * Connexion à l'API, renvoi la mise à jour de la base de données
     */
    fun connectToApi(user: String, pass: String, viewModel: ClientViewModel) {

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
                            getAllClientsAPI(viewModel)
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

    // endregion



}



