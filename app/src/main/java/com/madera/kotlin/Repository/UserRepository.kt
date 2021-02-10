package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import com.madera.kotlin.Dao.UserDao
import com.madera.kotlin.Entity.User

class UserRepository(private val userDao: UserDao) {

    // Insertion de l'utilisateur dans la BDD
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User){
        userDao.createUser(user)
    }

    // Insertion de l'utilisateur dans la BDD
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun connectUser(login: String, pass: String){
        userDao.connectUser(login,pass)
    }
}