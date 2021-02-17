package com.madera.kotlin.Dao

import androidx.room.*
import com.madera.kotlin.Entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // onConflict permet ignore l'insertion s'il existe déjà en BDD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createUser(user: User) : Long

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("SELECT * FROM user WHERE pseudoUser=:login AND passwordUser=:pass")
    fun connectUser(login: String, pass: String) : Boolean

    // Permet d'observer le mouvement des valeurs et afficher les valeurs à jour grâce à Flow provenant des coroutines Kotlin (Pas utile pour les utilisateurs mais peut l'être sur d'autres DAO
    @Query("SELECT * FROM user ORDER BY idUser ASC")
    fun getUserUpdated(): Flow<List<User>>

}