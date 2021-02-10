package com.madera.kotlin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.madera.kotlin.Dao.UserDao
import com.madera.kotlin.Entity.User
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(
    User::class
), version = 1)
abstract class MaderaBase : RoomDatabase() {

    // Insertion des DAO
    abstract fun userDao() : UserDao

    // Singleton de ma database
    companion object{
        @Volatile
        private var INSTANCE: MaderaBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): MaderaBase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MaderaBase::class.java,
                    "Madera-db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }

}