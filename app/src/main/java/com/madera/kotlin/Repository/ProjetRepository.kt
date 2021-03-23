package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import com.madera.kotlin.Dao.ProjetDao
import com.madera.kotlin.Entity.Projet
import kotlinx.coroutines.flow.Flow


class ProjetRepository(private val projetDao: ProjetDao) {

    fun getProjectByRef(ref: Long) : Projet{
        return projetDao.getProjectByRef(ref)
    }

    fun getAllProjetsByUser(id: Int) : Flow<List<Projet>> {
        return projetDao.getProjectByUser(id)
    }

    fun isProjetExist(refProj: Long) : Boolean{
        return projetDao.isProjetExist(refProj)
    }

    fun updateProjet(clientId: Int?,nomProjet :String,notes:String,refProj: Long){
        projetDao.updateProjet(clientId, nomProjet, notes, refProj)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createProject(projet: Projet){
        projetDao.createProject(projet)
    }
}