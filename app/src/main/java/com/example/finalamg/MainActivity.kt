package com.example.finalamg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.finalamg.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    //53:9C:6A:F9:9D:D7:F1:5A:FE:4B:60:45:7E:8A:D1:10:C5:09:C8:B9

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var textonombre= ""
        var textocontrasena= ""

        binding.txnombre.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                textonombre=binding.txnombre.text.toString()
                comprobarnombre(textonombre)
                if (! comprobarnombre(textonombre)){
                    binding.nombre.error="Debe de tener mas de 3 caracteres"
                }else{
                    binding.nombre.error=null
                }

                binding.siguiente.isEnabled = comprobarnombre(textonombre) && comprobarcontrasena(textocontrasena)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.txcontra.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                textocontrasena=binding.txcontra.text.toString()
                comprobarcontrasena(textocontrasena)
                if (! comprobarcontrasena(textocontrasena)){
                    binding.contrasena.error="Debe de tener mas de 8 caracteres y algun numero"
                }else{
                    binding.contrasena.error=null
                }
                binding.siguiente.isEnabled = comprobarnombre(textonombre) && comprobarcontrasena(textocontrasena)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.siguiente.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(textonombre,textocontrasena)
        }



    }

    fun comprobarnombre(nombre :String):Boolean{
        return nombre.length>3
    }


    fun comprobarcontrasena(contra :String):Boolean{
        var prueba1 =false
        var prueba2 =false

        if (contra.length>8){
            prueba1=true
        }

        contra.forEach {
            if (it.isDigit()){
                prueba2=true
            }
        }

        return prueba1 and prueba2
    }
}