package com.example.listado

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AlumnoAdapter (private val context: Context, private val mList: List<Alumno>): RecyclerView.Adapter<AlumnoAdapter.ViewHolder>() {
    private var clickListener: ClickListener?=null

    interface ClickListener {
        fun onItemClick(view: View,position: Int)

    }
    fun setOnIteamClickListener(clickListener: ClickListener){
        this.clickListener=clickListener
    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view), View.OnClickListener
    {
        val imagen: ImageView = view.findViewById(R.id.imgtcv)
        val nombre: TextView = view.findViewById(R.id.nombre)
        val cuenta: TextView = view.findViewById(R.id.cuenta)
        val correo: TextView = view.findViewById(R.id.correo)
        init {
            if (clickListener != null) {
                view.setOnClickListener(this)
            }
        }
        override fun onClick(view: View) {
            if (view != null) {
                clickListener?.onItemClick(view, bindingAdapterPosition)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.card_view_design,parent,false)
        return ViewHolder(view)

        //
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    val alumnosModel=mList[position]
        //imagen
        Glide.with(context).load(alumnosModel.image).into(holder.imagen)
        holder.nombre.text=alumnosModel.nombre
        holder.cuenta.text=alumnosModel.cuenta
        holder.correo.text=alumnosModel.correo
    }


    override fun getItemCount():Int{
        return mList.size
    }


}


