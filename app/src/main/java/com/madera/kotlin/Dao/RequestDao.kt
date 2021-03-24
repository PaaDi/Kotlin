package com.madera.kotlin.Dao

import androidx.room.*
import com.madera.kotlin.Entity.Client
import com.madera.kotlin.Entity.Request

@Dao
interface RequestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRequest(request: Request)

    @Query("SELECT * FROM request")
    fun getAllRequest():List<Request>

    @Delete
    fun deleteRequest(request: Request)
}