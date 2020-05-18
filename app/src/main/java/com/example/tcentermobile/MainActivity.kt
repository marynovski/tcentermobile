package com.example.tcentermobile


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.tcentermobile.service.LoginService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signInButton: Button        = findViewById(R.id.signInBtn)
        val directNumberSelect: Spinner = findViewById(R.id.directNumberSelect)
        val phoneNumberInput: EditText  = findViewById(R.id.phoneInput)
        val passwordInput: EditText     = findViewById(R.id.passwordInput)
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val ls: LoginService = LoginService()

        if (ls.checkIfUserIsLoggedIn(preferences)) {
            val intent = Intent(this, TicketListActivity::class.java)
            startActivity(intent)
        } else {
            signInButton.setOnClickListener {
                var directNumber: String = directNumberSelect.selectedItem.toString()
                var phone: String = phoneNumberInput.text.toString()
                var password: String = passwordInput.text.toString()


                ls.login(directNumber + phone, password, preferences)
            }
        }
    }
}
