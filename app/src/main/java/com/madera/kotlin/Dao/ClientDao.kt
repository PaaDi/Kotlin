package com.madera.kotlin.Dao

import androidx.room.*
import com.madera.kotlin.Entity.Client
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createClient(client: Client)

    @Query("SELECT * FROM client ORDER BY nom ASC")
    fun getAllClients(): Flow<List<Client>>

    @Query("SELECT * FROM client WHERE idClient=:id")
    fun getClientById(id: Int): Client

    @Delete
    fun delete(client: Client): Boolean
}