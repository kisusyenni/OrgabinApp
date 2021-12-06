package com.kisusyenni.orgabin.auth

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kisusyenni.orgabin.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private lateinit var activityAuthBinding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAuthBinding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(activityAuthBinding.root)

        val inputUsername = activityAuthBinding.inputUsername
        val inputPassword = activityAuthBinding.inputPassword
        val btnSignIn = activityAuthBinding.btnSignIn

        btnSignIn.setOnClickListener {
            if(inputUsername.text.toString() == "admin" && inputPassword.text.toString() == "password") {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show()
            }
        }

    }
}