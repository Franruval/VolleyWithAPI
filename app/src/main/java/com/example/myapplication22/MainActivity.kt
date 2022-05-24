package com.example.myapplication22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication22.adaptador.PersonajeAdapter
import com.example.myapplication22.modelo.Personaje

class MainActivity : AppCompatActivity() {
    lateinit var myRecycler:RecyclerView
    lateinit var adaptador:PersonajeAdapter
    lateinit var listaPersonajes:ArrayList<Personaje>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listaPersonajes = ArrayList<Personaje>()
        myRecycler = findViewById(R.id.rvPersonajes)
        adaptador = PersonajeAdapter(listaPersonajes)
        myRecycler.adapter = adaptador
        getPersonajes()
        myRecycler.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
    }

    fun getPersonajes(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://rickandmortyapi.com/api/character"

        val objectRequest = JsonObjectRequest(
            Request.Method.GET,url,null,
            { respuesta ->
                //object si es un array meter directo
                val personajesJson = respuesta.getJSONArray("results")
                for (indice in 0..personajesJson.length()-1){
                    val personajeIndJson = personajesJson.getJSONObject(indice)
                    val personaje = Personaje(personajeIndJson.getString("name"),personajeIndJson.getString("image"))
                    listaPersonajes.add(personaje)
                }
                adaptador.notifyDataSetChanged()
            },
            {
                Log.e("PersonajesApi","Error")
            })
        queue.add(objectRequest)
    }

}