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

    @Query("SELECT * FROM Chantier WHERE idChantier=:id")
    fun getChantierById(id:Int?): Chantier

    @Query("SELECT * FROM Chantier WHERE projetId =:id")
    fun getAllChantiersByProjectId(id: Int): Flow<List<Chantier>>

    @Query("SELECT * FROM Chantier WHERE refChantier=:ref")
    fun isChantierExist(ref:Long) : Boolean

    @Query("UPDATE Chantier SET projetId=:projetId,userId=:userId,nomChantier=:nom,adresseChantier=:adresse,codePostalChantier=:codePostal,villeChantier=:ville,notesChantier=:notes,datecreationChantier=:datecreation,datelancementChantier=:datelancement WHERE refChantier=:refChantier")
    fun updateChantier(projetId : Int?, userId : Int, nom:String,adresse:String,codePostal:Int,ville:String,notes:String,datecreation:String,datelancement:String, refChantier: Long)
}