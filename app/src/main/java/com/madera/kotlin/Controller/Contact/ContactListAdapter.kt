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
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.madera.kotlin.Database.CheckConnection
import com.madera.kotlin.Database.MaderaAPI
import com.madera.kotlin.Entity.Contact
import com.madera.kotlin.Entity.Request
import com.madera.kotlin.R
import com.madera.kotlin.ViewModel.*
import org.json.JSONObject

class ContactListAdapter(private val contactViewModel: ContactViewModel, context: Context, private val requestViewModel: RequestViewModel, private val clientViewModel: ClientViewModel, private val projetViewModel: ProjetViewModel, private val chantierViewModel: ChantierViewModel) : ListAdapter<Contact, ContactListAdapter.ContactViewHolder>(ContactsComparator()){

    val API = MaderaAPI(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = getItem(position)

        holder.bind(current.nomContact + " " + current.prenomContact + " - " + current.fonctionContact, "Téléphone : " + current.numeroContact, "Email : " + current.mailContact, current, contactViewModel,API, requestViewModel, clientViewModel,projetViewModel,chantierViewModel )
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactItemView: TextView = itemView.findViewById(R.id.textView)
        private val contactItemView2: TextView = itemView.findViewById(R.id.textView2)
        private val contactItemView3: TextView = itemView.findViewById(R.id.textView3)
        private val buttonSuppressContact: Button = itemView.findViewById(R.id.btn_deleteContact)
        val phoneTextView: ImageView = itemView.findViewById(R.id.iconPhone)
        val mailTextView: ImageView = itemView.findViewById(R.id.iconEmail)
        val userTextView: ImageView = itemView.findViewById(R.id.iconUser)


        fun bind(text: String?, text2: String?, text3: String?, contact: Contact, contactViewModel: ContactViewModel, API : MaderaAPI,requestViewModel: RequestViewModel, clientViewModel: ClientViewModel, projetViewModel: ProjetViewModel, chantierViewModel: ChantierViewModel){
            contactItemView.text = text
            contactItemView2.text = text2
            contactItemView3.text = text3
            phoneTextView.setImageResource(R.drawable.ic_phone)
            mailTextView.setImageResource(R.drawable.ic_email)
            userTextView.setImageResource(R.drawable.ic_user)
            buttonSuppressContact.setOnClickListener {
                contactViewModel.deleteContact(contact)

                CheckConnection().getMyResponse(object : JSONObjectRequestListener {
                    override fun onResponse(response: JSONObject?) {
                        val allRequest = requestViewModel.getAllRequest()
                        for (request in allRequest){
                            when(request.requestType){
                                "connectToApi" -> {
                                    API.connectToApi(request.param1.toString(),request.param2.toString(),clientViewModel,contactViewModel, projetViewModel,chantierViewModel)
                                    requestViewModel.deleteRequest(request)
                                }
                                "insertionClientAPI" -> {
                                    API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                    requestViewModel.deleteRequest(request)
                                }
                                "suppressionClientAPI" -> {
                                    API.suppressionClientAPI(request.param1.toString())
                                    requestViewModel.deleteRequest(request)
                                }
                                "insertionContactAPI" -> {
                                    API.insertionContactAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString())
                                    requestViewModel.deleteRequest(request)
                                }
                                "suppressionContactAPI" -> {
                                    API.suppressionContactAPI(request.param1.toString())
                                    requestViewModel.deleteRequest(request)
                                }
                                "updateClientAPI" -> {
                                    API.insertionClientAPI(request.param1.toString(),request.param2.toString(),request.param3.toString(),request.param4.toString(),request.param5.toString(),request.param6.toString(),request.param7.toString(),request.param8.toString(), request.param9.toString())
                                    requestViewModel.deleteRequest(request)
                                }
                                else -> {
                                    val pasderequest = ""
                                }
                            }
                        }
                        API.suppressionContactAPI(contact.refContact.toString())
                    }

                    override fun onError(anError: ANError?) {
                        requestViewModel.saveRequest(Request(null,"suppressionContactAPI",contact.refContact.toString(),null,null,null,null,null,null,null,null))

                    }
                }
                );

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