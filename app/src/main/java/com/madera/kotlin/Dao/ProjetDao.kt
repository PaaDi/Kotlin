package com.madera.kotlin.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.madera.kotlin.Entity.Projet
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProject(projet:Projet)


    @Query("SELECT * FROM Projet WHERE refProjet = :ref")
    fun getProjectByRef(ref: Long) : Projet

    @Query("SELECT * FROM Projet WHERE refProjet =:refProj")
    fun isProjetExist(refProj: Long) : Boolean

    @Query("""SELECT * FROM Projet
        INNER JOIN Chantier ON Chantier.projetId = Projet.idProjet
        WHERE Chantier.userId = :id
    """)
    fun getProjectByUser(id: Int) : Flow<List<Projet>>

    @Query("UPDATE Projet SET clientId=:clientId,nomProjet=:nomProjet,notesProjet=:notes WHERE refProjet=:refProj")
    fun updateProjet(clientId: Int?,nomProjet :String,notes:String,refProj: Long)
}