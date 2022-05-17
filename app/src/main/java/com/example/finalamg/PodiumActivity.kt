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







            println(listafinal+"ñññ")

            binding.pos1.text=listafinal[0]
            binding.pos2.text=listafinal[1]
            binding.pos3.text=listafinal[2]
            binding.pos4.text=listafinal[3]










        }
    }
}