package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import com.madera.kotlin.Dao.RequestDao
import com.madera.kotlin.Entity.Request

class RequestRepository(private val requestDao: RequestDao) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun saveRequest(request: Request){
        requestDao.saveRequest(request)
    }

    fun deleteRequest(request: Request){
        requestDao.deleteRequest(request)
    }

    fun getAllRequest():List<Request>{
        return requestDao.getAllRequest()
    }
}