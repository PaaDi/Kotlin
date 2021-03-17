package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import androidx.room.Query
import com.madera.kotlin.Dao.UserDao
import com.madera.kotlin.Entity.User

class UserRepository(private val userDao: UserDao) {

    // Insertion de l'utilisateur dans la BDD
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User){
        userDao.createUser(user)
    }

    fun getUserByName(pseudo:String) : User{
        return userDao.getUserByName(pseudo)
    }

    fun connectUser(login: String, pass: String) : Boolean
    {
        return userDao.connectUser(login,pass)
    }
}