package com.madera.kotlin.Repository

import com.madera.kotlin.Dao.ProjetDao
import com.madera.kotlin.Entity.Projet

class ProjetRepository(private val projetDao: ProjetDao) {

    fun getProjectByRef(ref: Long) : Projet{
        return projetDao.getProjectByRef(ref)
    }
}