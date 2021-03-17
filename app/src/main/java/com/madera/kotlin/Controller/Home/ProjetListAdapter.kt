package com.madera.kotlin.Controller.Home

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.madera.kotlin.Entity.Projet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Controller.Client.CellClickListener


import com.madera.kotlin.R

class ProjetListAdapter(private val cellClickListener: CellClickListener) : ListAdapter<Projet, ProjetListAdapter.ProjetViewHolder>(ProjetsComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjetViewHolder{
        return ProjetViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProjetViewHolder, position: Int){
        val current = getItem(position)
        holder.bind(current.nomProjet, current.nomProjet)

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(current.clientId)
        }
    }


    class ProjetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val clientItemView: TextView = itemView.findViewById(R.id.textView)
        private val clientItemView2: TextView = itemView.findViewById(R.id.textView2)
        val clientImage: ImageView = itemView.findViewById(R.id.iconClient)


        fun bind(text: String?, text2: String?) {
            clientItemView.text = text
            clientItemView2.text = text2

        }

        companion object{
            fun create(parent: ViewGroup): ProjetListAdapter.ProjetViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_client, parent, false)
                return ProjetListAdapter.ProjetViewHolder(view)
            }
        }
    }

    class ProjetsComparator : DiffUtil.ItemCallback<Projet>(){
        override fun areItemsTheSame(oldItem: Projet, newItem: Projet): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Projet, newItem: Projet): Boolean {
            return oldItem.refProjet == newItem.refProjet
        }
    }
}