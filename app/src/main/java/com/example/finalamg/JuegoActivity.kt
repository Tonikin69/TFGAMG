package com.example.finalamg

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.example.finalamg.databinding.ActivityJuegoBinding
import java.util.*


class JuegoActivity : AppCompatActivity() {
     var ancho=0
     var alto=0
    private lateinit var binding : ActivityJuegoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityJuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setup()
    }

    fun setup(){
        var contador=0
        tamanio()
        binding.zombie.setOnClickListener {
            contador++;
            binding.contador.text=contador.toString()
            binding.zombie.setImageResource(R.drawable.sangre)
            val handler = Handler()
                        handler.postDelayed({
                            binding.zombie.setImageResource(R.drawable.zombie)
                            moverse()
                        },500)



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

}