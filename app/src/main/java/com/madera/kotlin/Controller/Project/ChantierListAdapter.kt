package com.madera.kotlin.Controller.Project

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.madera.kotlin.Controller.Client.CellClickListener
import com.madera.kotlin.Entity.Chantier
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ProjetViewModel

class ChantierListAdapter(private val cellClickListener: CellClickListener, private val viewModel: ProjetViewModel) : ListAdapter<Chantier, ChantierListAdapter.ChantierViewHolder>(ChantierComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChantierViewHolder {
        return ChantierViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ChantierViewHolder, position: Int) {
        val current = getItem(position)

        holder.bind("#" + current.refChantier + " - " + current.nomChantier,current.adresseChantier + " - " + current.villeChantier + " " + current.codePostalChantier)

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(current.idChantier)
        }
    }

    class ChantierViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        private val chantierItemView : TextView = itemView.findViewById(R.id.textViewChantier1)
        private val chantierItemView2 : TextView = itemView.findViewById(R.id.textViewChantier2)

        var arrowImage : ImageView = itemView.findViewById(R.id.arrowIconChantier)
        var chantierImage : ImageView = itemView.findViewById(R.id.iconChantierChantier)

        fun bind(text: String?, text2: String?){
            chantierItemView.text = text
            chantierItemView2.text = text2
            arrowImage.setImageResource(R.drawable.ic_play)
            chantierImage.setImageResource(R.drawable.ic_chantier)
        }

        companion object{
            fun create(parent: ViewGroup): ChantierListAdapter.ChantierViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_chantier,parent,false)
                return ChantierListAdapter.ChantierViewHolder(view)
            }
        }


    }

    class ChantierComparator : DiffUtil.ItemCallback<Chantier>(){
        override fun areItemsTheSame(oldItem: Chantier, newItem: Chantier): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Chantier, newItem: Chantier): Boolean {
            return oldItem.refChantier == newItem.refChantier
        }
    }

}