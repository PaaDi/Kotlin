package com.madera.kotlin.Controller.Contact

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madera.kotlin.Database.MaderaAPI
import com.madera.kotlin.Entity.Contact
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.ContactViewModel

class ContactListAdapter(private val contactViewModel: ContactViewModel, context: Context) : ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactsComparator()){

    val API = MaderaAPI(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)
        val viewModel = contactViewModel
        holder.bind(current.nomContact + " " + current.prenomContact + " - " + current.fonctionContact, "Téléphone : " + current.numeroContact, "Email : " + current.mailContact, current, viewModel,API )
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactItemView: TextView = itemView.findViewById(R.id.textView)
        private val contactItemView2: TextView = itemView.findViewById(R.id.textView2)
        private val contactItemView3: TextView = itemView.findViewById(R.id.textView3)
        private val buttonSuppressContact: Button = itemView.findViewById(R.id.btn_deleteContact)
        val phoneTextView: ImageView = itemView.findViewById(R.id.iconPhone)
        val mailTextView: ImageView = itemView.findViewById(R.id.iconEmail)


        fun bind(text: String?, text2: String?, text3: String?, contact: Contact, viewModel: ContactViewModel, API : MaderaAPI){
            contactItemView.text = text
            contactItemView2.text = text2
            contactItemView3.text = text3
            phoneTextView.setImageResource(R.drawable.ic_phone)
            mailTextView.setImageResource(R.drawable.ic_email)
            buttonSuppressContact.setOnClickListener {
                viewModel.deleteContact(contact)
                API.suppressionContactAPI(contact.refContact.toString())
            }
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