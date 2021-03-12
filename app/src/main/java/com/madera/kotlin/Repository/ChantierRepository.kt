package com.madera.kotlin.Repository

import com.madera.kotlin.Dao.ChantierDao
import com.madera.kotlin.Entity.Chantier
import kotlinx.coroutines.flow.Flow

class ChantierRepository(private val chantierDao: ChantierDao) {

    fun getAllChantiersByUser(ref : Long) : Flow<List<Chantier>>{
        return chantierDao.getAllChantiersByUser(ref)
    }
}