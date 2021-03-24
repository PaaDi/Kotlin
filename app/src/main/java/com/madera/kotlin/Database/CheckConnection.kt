package com.madera.kotlin.Database

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority

import com.androidnetworking.interfaces.JSONObjectRequestListener




class CheckConnection() {

    fun getMyResponse(listener: JSONObjectRequestListener?) {
        AndroidNetworking.post("http://maderaprod.mconan.ovh/API/checkconnexion")
                .addBodyParameter("validrequest", "OK")
                .setTag("test")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(listener)
    }
}