package com.kisusyenni.orgabin.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.kisusyenni.orgabin.R
import com.kisusyenni.orgabin.ui.auth.AuthActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }
}