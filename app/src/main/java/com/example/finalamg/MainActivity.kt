package com.example.finalamg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.example.finalamg.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
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

                //binding.registrarse.isEnabled = comprobarnombre(textonombre) && comprobarcontrasena(textocontrasena)
                if (comprobarnombre(textonombre) && comprobarcontrasena(textocontrasena)){
                    binding.registrarse.visibility= View.VISIBLE
                    binding.iniciosesion.visibility=View.VISIBLE
                }else{
                    binding.registrarse.visibility= View.INVISIBLE
                    binding.iniciosesion.visibility=View.INVISIBLE
                }

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
                //binding.registrarse.isEnabled = comprobarnombre(textonombre) && comprobarcontrasena(textocontrasena)
                if (comprobarnombre(textonombre) && comprobarcontrasena(textocontrasena)){
                    binding.registrarse.visibility= View.VISIBLE
                    binding.iniciosesion.visibility=View.VISIBLE
                }else{
                    binding.registrarse.visibility= View.INVISIBLE
                    binding.iniciosesion.visibility=View.INVISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        binding.registrarse.setOnClickListener {
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(textonombre,textocontrasena).addOnCompleteListener {
                if (it.isSuccessful){
                        db.collection("users").document(textonombre).set(
                            hashMapOf("monedas" to "0")
                        )
                    db.collection("record").document(textonombre).set(
                        hashMapOf("personalrecord" to "0")
                    )
                    goHome(it.result?.user?.email ?: "")
                }else{
                    showAlert()
                }
            }
        }
        binding.iniciosesion.setOnClickListener {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(textonombre,textocontrasena).addOnCompleteListener {
                if (it.isSuccessful){
                    goHome(it.result?.user?.email ?: "")
                }else{
                    showAlert()
                }
            }
        }



    }
    fun showAlert(){
        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setTitle("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog=builder.create()
        dialog.show()
    }
    fun goHome(email: String){
        val homeintent = Intent (this, HomeActivity::class.java).apply {
            putExtra("email",email)
        }
        startActivity(homeintent)
        finish()
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