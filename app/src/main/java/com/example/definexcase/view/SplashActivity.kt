package com.example.definexcase.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.definexcase.R
import com.example.definexcase.consts.Constants
import com.example.definexcase.util.loadData
import com.example.definexcase.view.authentication.AuthenticationActivity

class SplashActivity : AppCompatActivity() {
    var token: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        token = loadData(this, Constants.TOKEN).toString()

        Handler(Looper.getMainLooper()).postDelayed({
            if (token.isNotEmpty() || token != "") {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                val intent = Intent(this, AuthenticationActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 3000)
    }
}