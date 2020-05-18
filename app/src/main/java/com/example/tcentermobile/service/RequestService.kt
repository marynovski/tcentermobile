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
        val url: String = "http://www.tcenter.pl/api/v/mobile/login"
        val courotine: Coroutine = Coroutine()

        return courotine.loginRequestCouroutine(json, url)
    }

}