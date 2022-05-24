package com.example.finalamg

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import com.example.finalamg.databinding.ActivityHomeBinding
import com.example.finalamg.databinding.ActivityTiendaBinding
import com.google.firebase.firestore.FirebaseFirestore

class TiendaActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding : ActivityTiendaBinding
    var envio=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTiendaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle : Bundle? = intent.extras
        val email : String? = bundle?.getString("email")
        var monedastring=""
        var monedasint=0

        val handlerr = Handler()
        handlerr.postDelayed({
            preparar(email!!)
        }, 1000)

        db.collection("users").document(email!!).get().addOnSuccessListener {
            binding.monedastienda.setText(it.get("monedas") as String?)
            monedastring=((it.get("monedas") as String?).toString())
            monedasint=monedastring.toInt()
        }
        binding.globo1.setOnClickListener {
            comprar(monedasint,1,email)
        }
        binding.globo2.setOnClickListener {
            comprar(monedasint,2,email)
        }
        binding.globo3.setOnClickListener {
            comprar(monedasint,3,email)
        }
        binding.globo4.setOnClickListener {
            comprar(monedasint,4,email)
        }
        binding.globo5.setOnClickListener {
            comprar(monedasint,5,email)
        }
        binding.globo6.setOnClickListener {
            comprar(monedasint,6,email)
        }
        binding.globo7.setOnClickListener {
            comprar(monedasint,7,email)
        }
        binding.globo8.setOnClickListener {
            comprar(monedasint,8,email)
        }
        binding.volver.setOnClickListener {
            val homeintent = Intent (this, HomeActivity::class.java).apply {
                putExtra("email",email)
                putExtra("seleccionado",envio)
            }
            startActivity(homeintent)
            finish()
        }


    }

    fun preparar(email: String){

        var mitienda=" "
        db.collection("users").document(email!!).get().addOnSuccessListener {
            binding.monedastienda.setText(it.get("monedas") as String?)
            mitienda=((it.get("tienda") as String?).toString())
            if (mitienda.equals("1")){
                binding.precio1.text="comprado"
            }else  if (mitienda.equals("2")){
                binding.precio1.text="comprado"
                binding.precio2.text="comprado"
            }else  if (mitienda.equals("3")){
                binding.precio1.text="comprado"
                binding.precio2.text="comprado"
                binding.precio3.text="comprado"
            }
            else  if (mitienda.equals("4")){
                binding.precio1.text="comprado"
                binding.precio2.text="comprado"
                binding.precio3.text="comprado"
                binding.precio4.text="comprado"
            }
            else  if (mitienda.equals("5")){
                binding.precio1.text="comprado"
                binding.precio2.text="comprado"
                binding.precio3.text="comprado"
                binding.precio4.text="comprado"
                binding.precio5.text="comprado"
            }
            else  if (mitienda.equals("6")){
                binding.precio1.text="comprado"
                binding.precio2.text="comprado"
                binding.precio3.text="comprado"
                binding.precio4.text="comprado"
                binding.precio5.text="comprado"
                binding.precio6.text="comprado"
            }
            else  if (mitienda.equals("7")){
                binding.precio1.text="comprado"
                binding.precio2.text="comprado"
                binding.precio3.text="comprado"
                binding.precio4.text="comprado"
                binding.precio5.text="comprado"
                binding.precio6.text="comprado"
                binding.precio7.text="comprado"
            }
            else  if (mitienda.equals("8")){
                binding.precio1.text="comprado"
                binding.precio2.text="comprado"
                binding.precio3.text="comprado"
                binding.precio4.text="comprado"
                binding.precio5.text="comprado"
                binding.precio6.text="comprado"
                binding.precio7.text="comprado"
                binding.precio8.text="comprado"
            }

        }

    }



    fun comprar(monedas: Int,seleccionado: Int,email :String){
        var mismonedas=monedas

        val builder= AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setTitle("No tienes monedas suficientes")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog =builder.create()

        if (seleccionado == 1 && binding.precio1.text.equals("comprado")){
            binding.precio1.text=("seleccionado")
            envio=1
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio7.equals("seleccionado")){
                binding.precio7.text="comprado"

            }else if (binding.precio8.text.equals("seleccionado")){
                binding.precio8.text="comprado"

            }
        }else if (seleccionado == 2 && binding.precio2.text.equals("comprado")){
            binding.precio2.text=("seleccionado")
            envio=2
            if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio7.text.equals("seleccionado")){
                binding.precio7.text="comprado"

            }else if (binding.precio8.text.equals("seleccionado")){
                binding.precio8.text="comprado"

            }
        }else if (seleccionado == 3 && binding.precio3.text.equals("comprado")){
            binding.precio3.text=("seleccionado")
            envio=3
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio7.text.equals("seleccionado")){
                binding.precio7.text="comprado"

            }else if (binding.precio8.text.equals("seleccionado")){
                binding.precio8.text="comprado"

            }
        }else if (seleccionado == 4 && binding.precio4.text.equals("comprado")){
            binding.precio4.text=("seleccionado")
            envio=4
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio7.text.equals("seleccionado")){
                binding.precio7.text="comprado"

            }else if (binding.precio8.text.equals("seleccionado")){
                binding.precio8.text="comprado"

            }
        }else if (seleccionado == 5 && binding.precio5.text.equals("comprado")){
            binding.precio5.text=("seleccionado")
            envio=5
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio7.text.equals("seleccionado")){
                binding.precio7.text="comprado"

            }else if (binding.precio8.text.equals("seleccionado")){
                binding.precio8.text="comprado"

            }
        }else if (seleccionado == 6 && binding.precio6.text.equals("comprado")){
            binding.precio6.text=("seleccionado")
            envio=6
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio7.text.equals("seleccionado")){
                binding.precio7.text="comprado"

            }else if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }else if (binding.precio8.text.equals("seleccionado")){
                binding.precio8.text="comprado"

            }
        }else if (seleccionado == 7 && binding.precio7.text.equals("comprado")){
            binding.precio7.text=("seleccionado")
            envio=7
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }else if (binding.precio8.text.equals("seleccionado")){
                binding.precio8.text="comprado"

            }
        }else if (seleccionado == 8 && binding.precio8.text.equals("comprado")){
            binding.precio8.text=("seleccionado")
            envio=8
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio7.text.equals("seleccionado")){
                binding.precio7.text="comprado"

            }else if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }
        }else if (seleccionado == 1 && monedas < 1500){
            dialog.show()
        }else if (seleccionado == 2 && monedas <3500){
            dialog.show()
        }else if (seleccionado == 3 && monedas <5000){
            dialog.show()
        }else if (seleccionado == 4 && monedas <10000){
            dialog.show()
        }else if (seleccionado == 5 && monedas <25000){
            dialog.show()
        }else if (seleccionado == 6 && monedas <50000){
            dialog.show()
        }else if (seleccionado == 7 && monedas <350000){
            dialog.show()
        }else if (seleccionado == 8 && monedas <1000000){
            dialog.show()
        }else if (seleccionado == 7 && binding.precio1.text.equals("comprado")){
            binding.precio7.text=("seleccionado")
            envio=7
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio8.text.equals("seleccionado")){
                binding.precio8.text="comprado"

            }else if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }
        }else if (seleccionado == 8 && binding.precio1.text.equals("comprado")){
            binding.precio8.text=("seleccionado")
            envio=8
            if (binding.precio2.text.equals("seleccionado")){
                binding.precio2.text="comprado"

            }else if (binding.precio3.text.equals("seleccionado")){
                binding.precio3.text="comprado"

            }else if (binding.precio4.text.equals("seleccionado")){
                binding.precio4.text="comprado"

            }else if (binding.precio5.text.equals("seleccionado")){
                binding.precio5.text="comprado"

            }else if (binding.precio6.text.equals("seleccionado")){
                binding.precio6.text="comprado"

            }else if (binding.precio7.text.equals("seleccionado")){
                binding.precio7.text="comprado"

            }else if (binding.precio1.text.equals("seleccionado")){
                binding.precio1.text="comprado"

            }
        }else if (seleccionado==1 && !binding.precio1.text.equals("seleccionado")){
            mismonedas=mismonedas-1500
            db.collection("users").document(email).set(
                hashMapOf("tienda" to 1.toString(),"monedas" to mismonedas.toString())
            )
            binding.precio1.text="comprado"
        }else if (seleccionado==2 && !binding.precio2.text.equals("seleccionado")){
            mismonedas=mismonedas-3500
            db.collection("users").document(email).set(
                hashMapOf("tienda" to 2.toString(),"monedas" to mismonedas.toString())
            )
            binding.precio1.text="comprado"
            binding.precio2.text="comprado"
        }else if (seleccionado==3 && !binding.precio3.text.equals("seleccionado")){
            mismonedas=mismonedas-5000
            db.collection("users").document(email).set(
                hashMapOf("tienda" to 3.toString(), "monedas" to mismonedas.toString())
            )
            binding.precio1.text="comprado"
            binding.precio2.text="comprado"
            binding.precio3.text="comprado"
        } else if (seleccionado==4 && !binding.precio4.text.equals("seleccionado")){
            mismonedas=mismonedas-10000
            db.collection("users").document(email).set(
                hashMapOf("tienda" to 4.toString(),"monedas" to mismonedas.toString())
            )
            binding.precio1.text="comprado"
            binding.precio2.text="comprado"
            binding.precio3.text="comprado"
            binding.precio4.text="comprado"
        } else if (seleccionado==5 && !binding.precio5.text.equals("seleccionado")){
            mismonedas=mismonedas-25000
            db.collection("users").document(email).set(
                hashMapOf("tienda" to 5.toString(),"monedas" to mismonedas.toString())
            )
            binding.precio1.text="comprado"
            binding.precio2.text="comprado"
            binding.precio3.text="comprado"
            binding.precio4.text="comprado"
            binding.precio5.text="comprado"
        } else if (seleccionado==6 && !binding.precio6.text.equals("seleccionado")){
            mismonedas=mismonedas-50000
            db.collection("users").document(email).set(
                hashMapOf("tienda" to 6.toString(),"monedas" to mismonedas.toString())
            )
            binding.precio1.text="comprado"
            binding.precio2.text="comprado"
            binding.precio3.text="comprado"
            binding.precio4.text="comprado"
            binding.precio5.text="comprado"
            binding.precio6.text="comprado"
        } else if (seleccionado==7 && !binding.precio7.text.equals("seleccionado")){
            mismonedas=mismonedas-350000
            db.collection("users").document(email).set(
                hashMapOf("tienda" to 7.toString(), "monedas" to mismonedas.toString())
            )
            binding.precio1.text="comprado"
            binding.precio2.text="comprado"
            binding.precio3.text="comprado"
            binding.precio4.text="comprado"
            binding.precio5.text="comprado"
            binding.precio6.text="comprado"
            binding.precio7.text="comprado"
        } else if (seleccionado==8 && !binding.precio8.text.equals("seleccionado")){
            mismonedas=mismonedas-350000
            db.collection("users").document(email).set(
                hashMapOf("tienda" to 8.toString(),"monedas" to mismonedas.toString())
            )
            binding.precio1.text="comprado"
            binding.precio2.text="comprado"
            binding.precio3.text="comprado"
            binding.precio4.text="comprado"
            binding.precio5.text="comprado"
            binding.precio6.text="comprado"
            binding.precio7.text="comprado"
            binding.precio8.text="comprado"
        }

        db.collection("users").document(email!!).get().addOnSuccessListener {
            binding.monedastienda.setText(it.get("monedas") as String?)
        }


    }
}