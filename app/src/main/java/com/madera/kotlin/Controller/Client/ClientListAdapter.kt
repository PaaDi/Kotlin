package com.madera.kotlin.Controller.Client

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.R

class ClientListAdapter : ListAdapter<Client, ClientListAdapter.ClientViewHolder>(ClientsComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder{
        return ClientViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int){
        val current = getItem(position)
        holder.bind(current.nom)
    }

    class ClientViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val clientItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?){
            clientItemView.text = text
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