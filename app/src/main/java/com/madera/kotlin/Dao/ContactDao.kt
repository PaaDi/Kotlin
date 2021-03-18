package com.madera.kotlin.Dao

import androidx.room.*
import com.madera.kotlin.Entity.Contact
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createContact(contact: Contact)

    @Query("SELECT * FROM Contact WHERE clientId = :id")
    fun getAllContactsByClient(id: Int) : Flow<List<Contact>>

    @Delete
    fun delete(contact: Contact)
}