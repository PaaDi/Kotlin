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

    @Query("SELECT * FROM client WHERE refClient=:ref")
    fun isClientExist(ref : Long): Boolean

    @Query("UPDATE client SET nom = :nomUP, adresse = :adresseUP, codePostal = :codePostalUP, ville = :villeUP, professionnel = :proUP, secteur = :secteurUP, description = :description WHERE refClient = :ref")
    fun updateClient(ref: Long, nomUP : String, adresseUP : String, codePostalUP: Int, villeUP: String,proUP: Boolean, secteurUP : String, description: String)

    @Delete
    fun delete(client: Client)
}