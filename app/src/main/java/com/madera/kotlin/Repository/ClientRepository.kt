package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import com.madera.kotlin.Dao.ClientDao
import com.madera.kotlin.Entity.Client
import kotlinx.coroutines.flow.Flow

class ClientRepository(private val clientDao: ClientDao) {

    // Utiliser Flow permet de notifier à l'observer quand les données vont être modifiés ou un client est ajouté
    val allClients: Flow<List<Client>> = clientDao.getAllClients()

    // Récupère un client pour afficher ses détails
    fun getClientById(id: Int) : Client{
       return clientDao.getClientById(id)
    }

    // Room lance par défaut des querys suspends permettant de la lancer hors du thrad principal
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createClient(client: Client){
        clientDao.createClient(client)
    }
}