package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import com.madera.kotlin.Dao.ContactDao
import com.madera.kotlin.Entity.Contact
import kotlinx.coroutines.flow.Flow

class ContactRepository(private val contactDao: ContactDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createContact(contact: Contact){
        contactDao.createContact(contact)
    }

    fun getAllContactsByClient(id: Int) : Flow<List<Contact>>{
        return contactDao.getAllContactsByClient(id)
    }

   fun deleteContact(contact: Contact){
       contactDao.delete(contact)
   }
}