package com.madera.kotlin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.madera.kotlin.Dao.UserDao
import com.madera.kotlin.Entity.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .addCallback(MaderaBaseCallback(scope))
                        .build()

                INSTANCE = instance
                instance
            }
        }

        /**
         * Callback de la database Madera pour pré-remplissage
         * TODO Intégrer la liaison API pour mettre à jour la database avec les nouvelles informations
         */
        private class MaderaBaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback(){

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.userDao())
                    }
                }
            }

            suspend fun populateDatabase(userDao: UserDao){
                userDao.deleteAll()

                // Ajout d'un utilisateur dans la base
                var admin = User(0,"Administrateur","admin","Max","Paletou",1)

                userDao.createUser(admin)
            }
        }
    }

}