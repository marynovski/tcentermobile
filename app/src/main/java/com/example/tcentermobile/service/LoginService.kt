package com.example.tcentermobile.service

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import android.widget.Toast.*
import com.example.tcentermobile.MainActivity
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class LoginService {

    fun checkIfUserIsLoggedIn(preferences: SharedPreferences): Boolean {
        val isAuth = preferences.getBoolean("IS_AUTHENTICATED", false)

        return isAuth
    }

    fun login(username: String, password: String, preferences: SharedPreferences, context: Context)
    {

        val jsonRequestString: String = "{\"params\":{\"username\":\"dima\",\"password\":\"dima\"}}"
        val rs: RequestService = RequestService()
        val jsonResponseString: String = rs.sendLoginPostRequest(jsonRequestString)
        var jsonResponse: JSONObject = JSONObject("{}")
        
        try {
            jsonResponse = JSONObject("{\"json\": $jsonResponseString}")
            println(jsonResponse)
        }  catch (e: JSONException) {
            Log.e("JSONE", e.toString());
        }

        println(jsonResponse.getString("json"))

        val json: JSONObject      = JSONObject(jsonResponse.getString("json"))
        val status: JSONObject    = JSONObject(json.getString("status"))
        val code: String          = status.getString("code")
        val message: String       = status.getString("message")


//TODO MAKE SHARED PREFS SAVE AFTER LOGIN
        when (code) {
            "0" -> {
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                println("$code : $message")
            }
            "1" -> {
                println("$code : $message")
                val user_data: JSONObject  = JSONObject(json.getString("user_data"))
            }
            else -> { // Note the block
                print("x is neither 1 nor 2")
            }
        }
//
//        val params = JSONObject(jsonResponse.getString("params"))
//        val status = JSONObject(params.getString("status"))
//        val user_data = JSONObject(params.getString("user_data"))
//        //RESPONSE STATUS
//        val code = status.getString("code")
//        val msg  = status.getString("message")

//        if(code == "1") {
//            //USER DATA
//            val id      = user_data.getString("id")
//            val name    = user_data.getString("name")
//            val surname = user_data.getString("surname")
//            val phone   = user_data.getString("phone")
//            val token   = user_data.getString("apiToken")
//
//            val editor = preferences.edit()
//            editor.putBoolean("IS_AUTHENTICATED", true)
//            editor.putString("ID", id)
//            editor.putString("NAME", name)
//            editor.putString("SURNAME", surname)
//            editor.putString("PHONE", phone)
//            editor.putString("TOKEN", token)
//            editor.apply()
//
//            println("$code : $msg")
//        } else {
//            println("$code : $msg")
//        }




    }

}

