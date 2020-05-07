package com.example.tcentermobile

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton: Button = findViewById(R.id.signInBtn)
        val directNumberSelect: Spinner = findViewById(R.id.directNumberSelect)
        val phoneNumberInput: EditText = findViewById(R.id.phoneInput)
        val passwordInput: EditText = findViewById(R.id.passwordInput)

        signInButton.setOnClickListener {
            var directNumber: String = directNumberSelect.selectedItem.toString()
            var phone: String = phoneNumberInput.text.toString()
            var password: String = passwordInput.text.toString()
            var loginWithSuccess: Boolean = loginRequest(directNumber+phone, password)
        }
    }

    private fun loginRequest(username: String, password: String) : Boolean
    {
        val url = URL("https://reqres.in/api/users")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.requestMethod = "POST"
        con.setRequestProperty("Content-Type", "application/json; utf-8")
        con.setRequestProperty("Accept", "application/json")
        con.doOutput = true
        var jsonInputString: String = "{\"name\": \"Upendra\", \"job\": \"Programmer\"}"

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
            println(response.toString())
        }
        return true
    }




}
