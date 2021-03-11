package com.madera.kotlin.Dao

import androidx.room.*
import com.madera.kotlin.Entity.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createContact(contact: Contact)

    @Query("SELECT * FROM Contact WHERE clientRef = :ref")
    fun getAllContactsByClient(ref: Long)

    @Delete
    fun delete(contact: Contact)
}