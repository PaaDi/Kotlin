package com.madera.kotlin.Controller.Client

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Controller.Home.ClientActivity
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.R

class ClientListAdapter(private val cellClickListener: CellClickListener) : ListAdapter<Client, ClientListAdapter.ClientViewHolder>(ClientsComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder{
        return ClientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int){
        val current = getItem(position)
        holder.bind(current.nom + " " + current.adresse + " " + current.professionnel + " " + current.idClient,current.adresse)

        holder.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(current.idClient)
        }
    }

    class ClientViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val clientItemView: TextView = itemView.findViewById(R.id.textView)
        private val clientItemView2: TextView = itemView.findViewById(R.id.textView2)

        fun bind(text: String?, text2: String?){
            clientItemView.text = text
            clientItemView2.text = text2
        }

        companion object{
            fun create(parent: ViewGroup): ClientViewHolder{
                val view: View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_client, parent, false)
                return ClientViewHolder(view)
            }
        }
    }

    class ClientsComparator : DiffUtil.ItemCallback<Client>(){
        override fun areItemsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Client, newItem: Client): Boolean {
            return oldItem.nom == newItem.nom
        }
    }

}

interface CellClickListener{
    fun onCellClickListener(id: Int?)
}