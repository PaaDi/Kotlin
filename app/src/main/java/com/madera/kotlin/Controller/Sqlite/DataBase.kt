package com.madera.kotlin.Controller.Sqlite

import android.content.ContentValues
import android.content.Context
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper;
import com.madera.kotlin.Entity.User
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONArrayRequestListener
import org.json.JSONArray
import org.json.JSONException

import org.json.JSONObject

class DataBase(context: Context) : SQLiteOpenHelper(context,"maderaBase.db", null, 1)
{

    override fun onCreate(db: SQLiteDatabase?)
    {
        db?.execSQL("CREATE TABLE users (idUser INTEGER PRIMARY KEY, pseudoUser TEXT, passwordUser TEXT, nameUser TEXT, firstNameUser TEXT, mailUser TEXT, roleUser INTEGER)")
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {
        TODO("Not yet implemented")
    }

    // Create user in DB
    fun createUser(user: User)
    {
        val values = ContentValues()
        values.put("pseudoUser", user.pseudoUser)
        values.put("passwordUser", user.passwordUser)
        values.put("nameUser", user.nameUser)
        values.put("firstNameUser", user.firstNameUser)
        values.put("mailUser", user.mailUser)
        values.put("roleUser", user.roleUser)

        writableDatabase.insert("users", null, values)
    }

    // Get users from DB
    fun getAllUsers() : MutableList<User>{
        val users = mutableListOf<User>()

        // Request and Get users with cursor
        readableDatabase.rawQuery("SELECT * FROM users", null).use { cursor ->
            while (cursor.moveToNext())
            {
                val user = User(
                    cursor.getString(cursor.getColumnIndex("pseudoUser")),
                    cursor.getString(cursor.getColumnIndex("passwordUser")),
                    cursor.getString(cursor.getColumnIndex("nameUser")),
                    cursor.getString(cursor.getColumnIndex("firstNameUser")),
                    cursor.getString(cursor.getColumnIndex("mailUser")),
                    cursor.getInt(cursor.getColumnIndex("roleUser"))
                )

                users.add(user)
            }

            }
        return users
        }

    // Récupère le nombre d'utilisateurs dans la BDD
    fun getUsersCount() : Int = DatabaseUtils.queryNumEntries(readableDatabase, "users", null).toInt()

    /* Login function */
    fun tryToConnect(user: String, pass: String) : Boolean{

        var connectSuccess:Boolean = false

        readableDatabase.rawQuery(
            "SELECT * FROM users WHERE pseudoUser = ? AND passwordUser = ?", arrayOf(
                user,
                pass
            )
        ).use { cursor ->

            if (cursor.count != 0){
               connectSuccess = true
            }


        }


        return connectSuccess
    }

 //region Test HTTPRequests

    fun connectToApi(user: String, pass: String){
       /* AndroidNetworking.post("http://maderaprod.mconan.ovh/API/login")
                .addBodyParameter("username", user)
                .addBodyParameter("password", pass)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONArray(object : JSONArrayRequestListener {
                    override fun onResponse(response: JSONArray) {

                    }

                    override fun onError(error: ANError) {
                        // handle error
                    }
                })*/
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
            .getAsJSONArray(object : JSONArrayRequestListener {
                override fun onResponse(response: JSONArray) {
                    var test = response.toString()
                }

                override fun onError(error: ANError) {
                    // handle error
                }
            })
    }

            // endregion



}



