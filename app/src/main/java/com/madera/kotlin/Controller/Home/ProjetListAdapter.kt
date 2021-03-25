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
import com.madera.kotlin.ViewModel.ClientViewModel

class ProjetListAdapter(private val cellClickListener: CellClickListener, private val viewModel: ClientViewModel) : ListAdapter<Projet, ProjetListAdapter.ProjetViewHolder>(ProjetsComparator()){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjetViewHolder{
        return ProjetViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ProjetViewHolder, position: Int){
        val current = getItem(position)
        val client = viewModel.getClientById(current.clientId)
        holder.bind(current.nomProjet + " -  Réf: " + current.refProjet.toString(), "Client: " + client.nom)

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(current.idProjet)
        }
    }


    class ProjetViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val clientItemView: TextView = itemView.findViewById(R.id.textView)
        private val clientItemView2: TextView = itemView.findViewById(R.id.textView2)
        val clientImage: ImageView = itemView.findViewById(R.id.iconChantier)
        val arrowImage : ImageView = itemView.findViewById(R.id.arrowIcon)


        fun bind(text: String?, text2: String?) {
            arrowImage.setImageResource(R.drawable.ic_play)
            clientImage.setImageResource(R.drawable.ic_particulier)
            clientItemView.text = text
            clientItemView2.text = text2


        }

        companion object{
            fun create(parent: ViewGroup): ProjetListAdapter.ProjetViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_projet, parent, false)
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