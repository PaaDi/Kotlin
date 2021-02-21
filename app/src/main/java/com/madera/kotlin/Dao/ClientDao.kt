package com.madera.kotlin.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
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
}