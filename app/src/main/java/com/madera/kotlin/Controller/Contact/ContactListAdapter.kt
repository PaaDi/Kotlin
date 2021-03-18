package com.madera.kotlin.Controller.Contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Entity.Contact
import com.madera.kotlin.R

class ContactListAdapter() : ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactsComparator()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.nomContact, current.prenomContact)
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactItemView: TextView = itemView.findViewById(R.id.textView)
        private val contactItemView2: TextView = itemView.findViewById(R.id.textView2)

        fun bind(text: String?, text2: String?){
            contactItemView.text = text
            contactItemView2.text = text2
        }

        companion object{
            fun create(parent: ViewGroup) : ContactViewHolder{
                val view : View = LayoutInflater.from(parent.context)
                        .inflate(R.layout.recyclerview_contact, parent, false)
                return ContactViewHolder(view)
            }
        }

    }

    class ContactsComparator : DiffUtil.ItemCallback<Contact>(){
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.idContact == newItem.idContact
        }
    }

}