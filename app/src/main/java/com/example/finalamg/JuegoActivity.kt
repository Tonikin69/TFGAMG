package com.example.finalamg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalamg.databinding.ActivityHomeBinding
import com.example.finalamg.databinding.ActivityJuegoBinding

class JuegoActivity : AppCompatActivity() {
    private lateinit var binding : ActivityJuegoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}