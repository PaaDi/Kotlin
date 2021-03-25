package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import com.madera.kotlin.Dao.ChantierDao
import com.madera.kotlin.Entity.Chantier
import kotlinx.coroutines.flow.Flow

class ChantierRepository(private val chantierDao: ChantierDao) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createChantier(chantier: Chantier){
        return chantierDao.createChantier(chantier)
    }

    fun getAllChantiersByUser(ref : Int) : Flow<List<Chantier>>{
        return chantierDao.getAllChantiersByUser(ref)
    }

    fun getChantierById(id:Int?): Chantier{
        return chantierDao.getChantierById(id)
    }

    fun getAllChantiersByProjectId(id: Int): Flow<List<Chantier>>{
        return chantierDao.getAllChantiersByProjectId(id)
    }

    fun isChantierExist(ref:Long) : Boolean{
        return chantierDao.isChantierExist(ref)
    }

    fun updateChantier(projetId : Int?, userId : Int, nom:String,adresse:String,codePostal:Int,ville:String,notes:String,datecreation:String,datelancement:String, refChantier: Long){
        chantierDao.updateChantier(projetId, userId, nom, adresse, codePostal, ville, notes, datecreation, datelancement, refChantier)
    }
}