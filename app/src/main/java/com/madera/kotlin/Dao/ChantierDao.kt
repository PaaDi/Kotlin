package com.madera.kotlin.Dao

import androidx.room.Dao
import androidx.room.Query
import com.madera.kotlin.Entity.Chantier
import kotlinx.coroutines.flow.Flow

@Dao
interface ChantierDao {

    @Query("""SELECT * FROM Chantier
        INNER JOIN Projet ON projet.refProjet = Chantier.projetRef
        WHERE userRef = :ref
    """)
    fun getAllChantiersByUser(ref : Long) : Flow<List<Chantier>>

}