package com.madera.kotlin

import android.app.Application
import com.madera.kotlin.Database.MaderaBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import com.androidnetworking.AndroidNetworking
import com.madera.kotlin.Entity.Projet
import com.madera.kotlin.Entity.User
import com.madera.kotlin.Repository.*

class MaderaApplication : Application() {

    companion object{
        lateinit var globalTest : User
    }
    override fun onCreate() {
        super.onCreate()
        // API Init
        AndroidNetworking.initialize(getApplicationContext());


    }
    // DATABASE Init
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { MaderaBase.getDatabase(this, applicationScope) }
    val repositoryUser by lazy { UserRepository(database.userDao())}
    val repositoryClient by lazy { ClientRepository(database.clientDao())}
    val repositoryChantier by lazy { ChantierRepository(database.chantierDao())}
    val repositoryProjet by lazy {ProjetRepository(database.projetDao())}
    val repositoryContact by lazy { ContactRepository(database.contactDao()) }
    val repositoryRequest by lazy { RequestRepository(database.requestDao()) }




}