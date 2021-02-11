package com.madera.kotlin.Database

import android.content.ContentValues.TAG
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import org.json.JSONException

import org.json.JSONObject

class MaderaAPI(context: Context) : SQLiteOpenHelper(context,"maderaBase.db", null, 1)
{

    override fun onCreate(db: SQLiteDatabase?)
    {
       // db?.execSQL("CREATE TABLE users (idUser INTEGER PRIMARY KEY, pseudoUser TEXT, passwordUser TEXT, nameUser TEXT, firstNameUser TEXT, mailUser TEXT, roleUser INTEGER)")
        
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        TODO("Not yet implemented")
    }

    //region Requêtes API

    /**
     * Connexion à l'API, renvoi la mise à jour de la base de données
     */
    fun connectToApi(user: String, pass: String){

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
                    override fun onResponse(response: JSONObject ) {
                        var test = response.toString()
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



