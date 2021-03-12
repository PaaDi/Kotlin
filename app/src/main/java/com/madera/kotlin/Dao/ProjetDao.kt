package com.madera.kotlin.Dao

import androidx.room.Dao
import androidx.room.Query
import com.madera.kotlin.Entity.Projet
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjetDao {
    @Query("SELECT * FROM Projet WHERE refProjet = :ref")
    fun getProjectByRef(ref: Long) : Projet
}