package com.madera.kotlin.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madera.kotlin.Entity.Chantier
import kotlinx.coroutines.flow.Flow

@Dao
interface ChantierDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createChantier(chantier:Chantier)

    @Query("""SELECT * FROM Chantier
        INNER JOIN Projet ON projet.idProjet = Chantier.projetId
        WHERE userId = :ref
    """)
    fun getAllChantiersByUser(ref : Int) : Flow<List<Chantier>>

}