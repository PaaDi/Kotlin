package com.madera.kotlin.Controller.DBUnused;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.ContentValues.TAG;

public class dbTestUNUSED {

 public void loginToAPI(String user, String pass){
     JSONObject jsonObject = new JSONObject();
     try {
         jsonObject.put("username", user);
         jsonObject.put("password", pass);
     } catch (
             JSONException e) {
         e.printStackTrace();
     }

     AndroidNetworking.post("http://maderaprod.mconan.ovh/API/login/")
             //.addJSONObjectBody(jsonObject) // posting json
            //.setTag("test")
             .addBodyParameter("username", user)
             .addBodyParameter("password", pass)
             .build()
             .getAsJSONArray(new JSONArrayRequestListener() {
                 @Override
                 public void onResponse(JSONArray response) {
                     String test = response.toString();
                 }
                 @Override
                 public void onError(ANError error) {
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
             });

 }
}
