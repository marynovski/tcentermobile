package com.example.tcentermobile.service

import android.content.SharedPreferences
import org.json.JSONObject

class LoginService {

    fun checkIfUserIsLoggedIn(preferences: SharedPreferences): Boolean {
        val isAuth = preferences.getBoolean("IS_AUTHENTICATED", false)

        return isAuth
    }

    fun login(username: String, password: String, preferences: SharedPreferences)
    {
        val jsonRequestString: String = "{\"username\": \"$username\", \"password\": \"$password\"}"
        val rs: RequestService = RequestService()
        val jsonResponse: JSONObject = JSONObject(rs.sendLoginPostRequest(jsonRequestString))

        println(jsonResponse)
        println(jsonResponse.getString("params"))

        val params = JSONObject(jsonResponse.getString("params"))
        val status = JSONObject(params.getString("status"))
        val user_data = JSONObject(params.getString("user_data"))
        //RESPONSE STATUS
        val code = status.getString("code")
        val msg  = status.getString("message")

        if(code == "1") {
            //USER DATA
            val id      = user_data.getString("id")
            val name    = user_data.getString("name")
            val surname = user_data.getString("surname")
            val phone   = user_data.getString("phone")
            val token   = user_data.getString("apiToken")

            val editor = preferences.edit()
            editor.putBoolean("IS_AUTHENTICATED", true)
            editor.putString("ID", id)
            editor.putString("NAME", name)
            editor.putString("SURNAME", surname)
            editor.putString("PHONE", phone)
            editor.putString("TOKEN", token)
            editor.apply()

            println("$code : $msg")
        } else {
            println("$code : $msg")
        }




    }

}

