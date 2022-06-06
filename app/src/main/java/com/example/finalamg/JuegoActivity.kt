package com.example.finalamg


import android.app.Dialog
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.finalamg.databinding.ActivityFinjuegoBinding
import com.example.finalamg.databinding.ActivityJuegoBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import java.util.*


class JuegoActivity : AppCompatActivity() {
    lateinit var mp :MediaPlayer
    lateinit var mpp :MediaPlayer
    private val db = FirebaseFirestore.getInstance()
     var ancho=0
     var alto=0
    var fin = false
    var contador=0
    var suma="0"
    var seleccionnado=0
    var tienda=""
    private lateinit var binding : ActivityJuegoBinding
    private lateinit var bindingdos : ActivityFinjuegoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityJuegoBinding.inflate(layoutInflater)
        bindingdos= ActivityFinjuegoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val bundle : Bundle? = intent.extras
        val email : String? = bundle?.getString("email")
        val seleccion :Int? ? = bundle?.getInt("seleccionado")
        seleccionnado=seleccion!!
        db.collection("users").document(email!!).get().addOnSuccessListener {
            tienda = ((it.get("tienda") as String?).toString())
        }
        setup(email ?: "",seleccion ?: 0)
        if (seleccion==0){
            binding.zombie.setImageResource(R.drawable.rojo)
        }else if (seleccion==1){
            binding.zombie.setImageResource(R.drawable.naranja)
        }else if (seleccion==2){
            binding.zombie.setImageResource(R.drawable.morado)
        }else if (seleccion==3){
            binding.zombie.setImageResource(R.drawable.amarillo)
        }else if (seleccion==4){
            binding.zombie.setImageResource(R.drawable.verde)
        }else if (seleccion==5){
            binding.zombie.setImageResource(R.drawable.azul)
        }else if (seleccion==6){
            binding.zombie.setImageResource(R.drawable.rosa)
        }else if (seleccion==7){
            binding.zombie.setImageResource(R.drawable.negro)
        }else if (seleccion==8){
            binding.zombie.setImageResource(R.drawable.arcoiris)
        }

    }

    fun setup(email: String,seleccionado :Int){
        mpp= MediaPlayer.create(this,R.raw.reventon)
        contador(email ?: "")
        tamanio()
        if (!fin){
                binding.zombie.setOnClickListener {
                        contador++;
                        binding.contador.text = contador.toString()
                        binding.zombie.setImageResource(R.drawable.explosion)
                    mpp.start()
                        val handler = Handler()
                        handler.postDelayed({
                            if (seleccionado==0){
                                binding.zombie.setImageResource(R.drawable.rojo)
                            }else if (seleccionado==1){
                                binding.zombie.setImageResource(R.drawable.naranja)
                            }else if (seleccionado==2){
                                binding.zombie.setImageResource(R.drawable.morado)
                            }else if (seleccionado==3){
                                binding.zombie.setImageResource(R.drawable.amarillo)
                            }else if (seleccionado==4){
                                binding.zombie.setImageResource(R.drawable.verde)
                            }else if (seleccionado==5){
                                binding.zombie.setImageResource(R.drawable.azul)
                            }else if (seleccionado==6){
                                binding.zombie.setImageResource(R.drawable.rosa)
                            }else if (seleccionado==7){
                                binding.zombie.setImageResource(R.drawable.negro)
                            }else if (seleccionado==8){
                                binding.zombie.setImageResource(R.drawable.arcoiris)
                            }

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
        mp= MediaPlayer.create(this,R.raw.desinfle)
        object : CountDownTimer(26000, 1000) {
            override fun onTick(tiempo: Long) {
                binding.tiempo.setText((tiempo / 1000).toString())
            }

            override fun onFinish() {
                mp.start()
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
        eliminados.setText("Has explotado : "+contador)
        var muertos = contador
        dialogo.show()
        regresarjuego.setOnClickListener {
            val handlerr = Handler()
            val homeintent = Intent (this, JuegoActivity::class.java).apply {
                putExtra("email",email)
            }
            db.collection("users").document(email).get().addOnSuccessListener {
                suma=((it.get("monedas") as String?).toString())
                contador+=suma.toInt()
            }
            handlerr.postDelayed({
                db.collection("users").document(email).set(
                    hashMapOf("monedas" to contador.toString(),"tienda" to tienda)
                )
                db.collection("record").document(email).get().addOnSuccessListener {
                    var resultadostring=((it.get("personalrecord") as String?).toString())
                    var resultado = resultadostring.toInt()
                    if (muertos>resultado){
                        db.collection("record").document(email).set(
                            hashMapOf("personalrecord" to muertos.toString())
                        )
                    }
                }

                db.collection("record").get().addOnSuccessListener {
                    var lista: List<QueryDocumentSnapshot> = it.toList()
                    var listastring=lista.toString()
                    var listilla = listastring.split(",")
                    var gmails = listilla.filter { it.contains("doc=Document{key=record/") }
                    var gmailss=""
                    for (i in gmails){
                        gmailss+=i.substring(25)+" "
                    }
                    //value=ObjectValue{internalValue={personalrecord:
                    var puntos = listilla.filter { it.contains("value=ObjectValue{internalValue={personalrecord:") }
                    var puntoss=""
                    for (i in puntos){
                        puntoss+=i.substring(49)+" "
                    }

                    puntoss=puntoss.replace("}","")
                    puntoss=puntoss.replace("]","")
                    println(gmailss+"``````````````````````")
                    println(puntoss+"``````````````````````")

                }
                finish()
                startActivity(homeintent)
            }, 2000)

        }
        regresarmenu.setOnClickListener {

            val handlerr = Handler()
            val homeintentdos = Intent (this, HomeActivity::class.java).apply {
                putExtra("email",email)
                putExtra("seleccionado",seleccionnado)
            }
            db.collection("users").document(email).get().addOnSuccessListener {
                suma=((it.get("monedas") as String?).toString())
                contador+=suma.toInt()
            }
            handlerr.postDelayed({
                db.collection("users").document(email).set(
                    hashMapOf("monedas" to contador.toString(),"tienda" to tienda)
                )
                db.collection("record").document(email).get().addOnSuccessListener {
                    var resultadostring=((it.get("personalrecord") as String?).toString())
                    var resultado = resultadostring.toInt()
                    if (muertos>resultado){
                        db.collection("record").document(email).set(
                            hashMapOf("personalrecord" to muertos.toString())
                        )
                    }
                }

                db.collection("record").get().addOnSuccessListener {
                    var lista: List<QueryDocumentSnapshot> = it.toList()
                    var listastring=lista.toString()
                    var listilla = listastring.split(",")
                    var gmails = listilla.filter { it.contains("doc=Document{key=record/") }
                    var gmailss=""
                    for (i in gmails){
                        gmailss+=i.substring(25)+" "
                    }
                    //value=ObjectValue{internalValue={personalrecord:
                    var puntos = listilla.filter { it.contains("value=ObjectValue{internalValue={personalrecord:") }
                    var puntoss=""
                    for (i in puntos){
                        puntoss+=i.substring(49)+" "
                    }

                    puntoss=puntoss.replace("}","")
                    puntoss=puntoss.replace("]","")
                    println(gmailss+"``````````````````````")
                    println(puntoss+"``````````````````````")

                }
                finish()
                startActivity(homeintentdos)
            }, 2000)
        }

    }

}