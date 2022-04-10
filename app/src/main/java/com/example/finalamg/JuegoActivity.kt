package com.example.finalamg


import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.finalamg.databinding.ActivityFinjuegoBinding
import com.example.finalamg.databinding.ActivityJuegoBinding
import java.util.*


class JuegoActivity : AppCompatActivity() {
     var ancho=0
     var alto=0
    var fin = false
    var contador=0
    private lateinit var binding : ActivityJuegoBinding
    private lateinit var bindingdos : ActivityFinjuegoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityJuegoBinding.inflate(layoutInflater)
        bindingdos= ActivityFinjuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle : Bundle? = intent.extras
        val email : String? = bundle?.getString("email")
        setup(email ?: "")
    }

    fun setup(email: String){
        contador(email ?: "")
        tamanio()
        if (!fin){
                binding.zombie.setOnClickListener {
                        contador++;
                        binding.contador.text = contador.toString()
                        binding.zombie.setImageResource(R.drawable.sangre)
                        val handler = Handler()
                        handler.postDelayed({
                            binding.zombie.setImageResource(R.drawable.zombie)
                            moverse()
                        }, 100)
                }
        }

    }
    fun tamanio(){

        val dm1 = resources.displayMetrics
        alto = dm1.heightPixels
        ancho = dm1.widthPixels

    }
    fun moverse(){
        var minimo=0
        var maximox=ancho-binding.zombie.width
        var maximoy=alto-binding.zombie.height
        var aleatorio = Random()
        var randomx: Int = aleatorio.nextInt(maximox - minimo + 1) + minimo
        var randomy: Int = aleatorio.nextInt(maximoy - minimo + 1) + minimo
        binding.zombie.x=randomx.toFloat()
        binding.zombie.y=randomy.toFloat()

    }
    fun contador(email: String){
        object : CountDownTimer(10000, 1000) {
            override fun onTick(tiempo: Long) {
                binding.tiempo.setText((tiempo / 1000).toString())
            }

            override fun onFinish() {
                binding.tiempo.setText("0")
                fin=true
                finjuego(email ?: "")
            }
        }.start()
    }
    fun finjuego(email: String){
        var dialogo: Dialog = Dialog(this)
        dialogo.setContentView(R.layout.activity_finjuego);
        dialogo.setCanceledOnTouchOutside(false)
        var eliminados = dialogo.findViewById<TextView>(R.id.eliminados)
        var regresarmenu = dialogo.findViewById<Button>(R.id.regresarmenu)
        var regresarjuego = dialogo.findViewById<Button>(R.id.regresarjuego)
        regresarjuego.setOnClickListener {
            val homeintent = Intent (this, JuegoActivity::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeintent)
        }
        regresarmenu.setOnClickListener {
            val homeintentdos = Intent (this, HomeActivity::class.java).apply {
                putExtra("email",email)
            }
            startActivity(homeintentdos)
        }
        eliminados.setText("Has matado : "+contador)
        dialogo.show()

    }

}