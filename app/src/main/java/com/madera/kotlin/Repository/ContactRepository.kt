package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import com.madera.kotlin.Dao.ContactDao
import com.madera.kotlin.Entity.Contact

class ContactRepository(private val contactDao: ContactDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createContact(contact: Contact){
        contactDao.createContact(contact)
    }

    //TODO : Ajouter les appels aux requêtes manquantes
}