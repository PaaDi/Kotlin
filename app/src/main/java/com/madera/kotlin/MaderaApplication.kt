package com.madera.kotlin

import android.app.Application
import com.madera.kotlin.Database.MaderaBase
import com.madera.kotlin.Repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import com.androidnetworking.AndroidNetworking

class MaderaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // API Init
        AndroidNetworking.initialize(getApplicationContext());
    }
    // DATABASE Init
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { MaderaBase.getDatabase(this, applicationScope) }
    val repositoryUser by lazy { UserRepository(database.userDao()) }



}