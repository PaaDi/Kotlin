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
}