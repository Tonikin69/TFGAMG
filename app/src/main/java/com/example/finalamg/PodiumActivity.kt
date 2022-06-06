package com.example.finalamg

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.isDigitsOnly
import com.example.finalamg.databinding.ActivityHomeBinding
import com.example.finalamg.databinding.ActivityPodiumBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot

class PodiumActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private lateinit var binding : ActivityPodiumBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPodiumBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db.collection("record").get().addOnSuccessListener {
            var lista: List<QueryDocumentSnapshot> = it.toList()
            var listastring=lista.toString()
            var listilla = listastring.split(",")
            var gmails = listilla.filter { it.contains("doc=Document{key=record/") }
            var gmailss=""
            for (i in gmails){
                gmailss+=i.substring(25)+" "
            }
            var puntos = listilla.filter { it.contains("value=ObjectValue{internalValue={personalrecord:") }
            var puntoss=""
            for (i in puntos){
                puntoss+=i.substring(49)+" "
            }

            puntoss=puntoss.replace("}","")
            puntoss=puntoss.replace("]","")
            gmailss=gmailss.replace(",","")


            var listapuntuación = puntoss.split(" ")
            var listanombres= gmailss.split(" ")

            var listaentera = mutableListOf<String>()

            for (i in 0..listanombres.size-2){
                 listaentera+=listapuntuación[i]+"   "+listanombres[i]
            }
            var listafinal = listaentera.sorted().reversed()



            var listastringpuntos=mutableListOf<String>()

            for (i in 0..listapuntuación.size-2){
                listastringpuntos+=listapuntuación[i]
            }




            var listaintpuntos = mutableListOf<Int>()

            for (i in 0..listapuntuación.size-2){
                listaintpuntos+=listapuntuación[i].toInt()
            }

            var listaintpuntosordenada=listaintpuntos.sorted().reversed()

            var listastringpuntosordenada=mutableListOf<String>()

            for (i in 0..listaintpuntosordenada.size-1){
                listastringpuntosordenada+=listaintpuntosordenada[i].toString()
            }

            var nombresrepetios=" "


            var listafinalporfin = mutableListOf<String>()
            for (i in 0..listastringpuntosordenada.size-1){
                for (j in 0..listastringpuntos.size-1){
                    if (listastringpuntosordenada[i].equals(listastringpuntos[j]) and !nombresrepetios.contains(listanombres[j])){
                        println(listastringpuntosordenada[i]+"  "+listastringpuntos[j]+"  "+listanombres[j]+"          xoc")
                        listafinalporfin+=listastringpuntos[j]+"  "+listanombres[j]
                        nombresrepetios+=listanombres[j]
                        break
                    }
                }
            }














            println(listafinal+"xxx")
            println(listanombres+"xxx")
            println(listapuntuación+"xxx")
            println(listastringpuntos+"xjx")
            println(listastringpuntosordenada+"xjx")
            println(listafinalporfin+"xpx")




            binding.pos1.text=listafinalporfin[0].replace("@gmail.com","")
            binding.pos2.text=listafinalporfin[1].replace("@gmail.com","")
            binding.pos3.text=listafinalporfin[2].replace("@gmail.com","")
            binding.pos4.text=listafinalporfin[3].replace("@gmail.com","")
            binding.pos5.text=listafinalporfin[4].replace("@gmail.com","")
            binding.pos6.text=listafinalporfin[5].replace("@gmail.com","")
            binding.pos7.text=listafinalporfin[6].replace("@gmail.com","")
            binding.pos8.text=listafinalporfin[7].replace("@gmail.com","")
            binding.pos9.text=listafinalporfin[8].replace("@gmail.com","")
            binding.pos10.text=listafinalporfin[9].replace("@gmail.com","")











        }
        binding.regreso.setOnClickListener {
            onBackPressed()
            finish()
        }
    }
}