package com.example.tcentermobile.utils

import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class Coroutine {

    fun getLoginResponseJob(json: String, url: String): String = runBlocking()
    {
            var jsonResponse: String = "ERROR GET LOGIN JOB"
            val url = URL(url)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection
            con.requestMethod = "POST"
            con.setRequestProperty("Content-Type", "application/json; utf-8")
            con.setRequestProperty("Accept", "application/json")
            con.doOutput = true
            var jsonInputString: String = json

            con.outputStream.use { os ->
                val input = jsonInputString.toByteArray(charset("utf-8"))
                os.write(input, 0, input.size)
            }

            BufferedReader(
                InputStreamReader(con.inputStream, "utf-8")
            ).use { br ->
                val response = StringBuilder()
                var responseLine: String? = null
                while (br.readLine().also { responseLine = it } != null) {
                    response.append(responseLine!!.trim { it <= ' ' })
                }
                jsonResponse = response.toString()
            }

        return@runBlocking jsonResponse
    }

    fun loginRequestCouroutine(json: String, url: String): String
    {
        var jsonResponse: String = "ERROR LOGIN REQUEST"
        runBlocking {
            val getLoginResponseJob = async(Dispatchers.IO) { getLoginResponseJob(json, url) }

            runBlocking {
                jsonResponse = getLoginResponseJob.await().toString()
            }
        }

        return jsonResponse
    }


}