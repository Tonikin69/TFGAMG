package com.example.finalamg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.activity.OnBackPressedCallback
import com.example.finalamg.databinding.ActivityHomeBinding
import com.example.finalamg.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
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
        val handlerr = Handler()
        handlerr.postDelayed({
            db.collection("users").document(email).get().addOnSuccessListener {
                binding.monedasTx.setText(it.get("monedas") as String?)
            }
        }, 1000)

        title="Inicio"

        binding.cierresesion.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val homeintent = Intent (this, MainActivity::class.java)
            startActivity(homeintent)
        }

        binding.cambiarnombre.setOnClickListener {
            //futuratienda
        }
        binding.jugar.setOnClickListener {
            val homeintent = Intent (this, JuegoActivity::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeintent)
        }
        binding.podium.setOnClickListener {
            val homeintent = Intent (this, PodiumActivity::class.java)
            startActivity(homeintent)
        }

    }
    fun goHome(){
        val homeintent = Intent (this, HomeActivity::class.java).apply {
        }
        startActivity(homeintent)
    }
}