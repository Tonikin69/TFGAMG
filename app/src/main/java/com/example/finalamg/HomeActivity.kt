package com.example.finalamg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import com.example.finalamg.databinding.ActivityHomeBinding
import com.example.finalamg.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle : Bundle? = intent.extras
        val email : String? = bundle?.getString("email")
        setup(email ?: "")
    }

    fun setup(email: String){
        title="Inicio"
        binding.emailTx.text=email


        binding.cierresesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            onBackPressed()
        }
    }
}