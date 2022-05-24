package com.example.myapplication22.adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication22.R
import com.example.myapplication22.modelo.Personaje
import com.squareup.picasso.Picasso

class PersonajeAdapter(val listaPersonajes:ArrayList<Personaje>):RecyclerView.Adapter<PersonajeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder { // Crea un ViewHolder
        val view=LayoutInflater.from(parent.context).inflate(R.layout.vista_singular,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { // Los datos de la lista se asignan a el ViewHolder
        holder.tvNombre.text=listaPersonajes[position].nombre
        Picasso.get().load(listaPersonajes[position].foto).into(holder.ivFoto)
    }

    override fun getItemCount(): Int { // Nos muestra el tamaño de la lista
        return listaPersonajes.size
    }

    class ViewHolder(vista:View):RecyclerView.ViewHolder(vista){ // Crea una instancia con los valores de la vista individual
        val tvNombre:TextView = vista.findViewById(R.id.tvNombre)
        val ivFoto:ImageView = vista.findViewById(R.id.ivPersonaje)
    }
}