package com.madera.kotlin.Controller.Client

import android.content.Intent
import android.hardware.HardwareBuffer.create
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Controller.Home.ClientActivity
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.R

class ClientListAdapter(private val clients: List<Client>, private val listener: ClientsListAdapterListener?) : ListAdapter<Client, ClientListAdapter.ClientViewHolder>(ClientsComparator()),
    View.OnClickListener {

    interface ClientsListAdapterListener{
        fun onClientSelected(client: Client)
    }

    class ClientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.cardViewClient)!!
        val clientTypeIcon = itemView.findViewById<ImageView>(R.id.clientTypeIcon)
        val nameClient = itemView.findViewById<TextView>(R.id.clientNameList)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_client, parent, false)

        return ClientViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clients[position]
        with(holder){
            cardView.setOnClickListener(this@ClientListAdapter)
            cardView.tag = client
            nameClient.text = client.nom
            if (client.professionnel == true){
                clientTypeIcon.setImageResource(R.drawable.ic_baseline_clientprofessionnel)
            }else{
                clientTypeIcon.setImageResource(R.drawable.ic_baseline_clientparticulier)
            }
        }
    }

    override fun getItemCount(): Int = clients.size

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
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