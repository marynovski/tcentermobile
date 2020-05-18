package com.example.tcentermobile.service

import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.os.AsyncTask
import com.example.tcentermobile.utils.Coroutine

class RequestService {

    fun sendLoginPostRequest(json: String): String {
        val url: String = "http://192.168.1.4/tcenterapi/api.php"
        val courotine: Coroutine = Coroutine()

        return courotine.loginRequestCouroutine(json, url)
    }

}