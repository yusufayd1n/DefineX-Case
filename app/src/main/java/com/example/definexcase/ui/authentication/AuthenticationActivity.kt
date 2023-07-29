package com.example.definexcase.ui.authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.definexcase.R
import com.example.definexcase.databinding.ActivityAuthenticationBinding

class AuthenticationActivity : AppCompatActivity() {
    private lateinit var binding : ActivityAuthenticationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthenticationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}