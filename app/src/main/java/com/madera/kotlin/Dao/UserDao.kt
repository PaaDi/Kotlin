package com.madera.kotlin.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madera.kotlin.Entity.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    // onConflict permet ignore l'insertion s'il existe déjà en BDD
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createUser(user: User) : Long

    @Query("SELECT * FROM user WHERE pseudoUser=:login AND passwordUser=:pass")
    fun connectUser(login: String, pass: String) : User

    // Permet d'observer le mouvement des valeurs et afficher les valeurs à jour grâce à Flow provenant des coroutines Kotlin (Pas utile pour les utilisateurs mais peut l'être sur d'autres DAO
    @Query("SELECT * FROM user ORDER BY id ASC")
    fun getUserUpdated(): Flow<List<User>>

}