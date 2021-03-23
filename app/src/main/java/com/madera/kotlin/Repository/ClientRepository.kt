package com.madera.kotlin.Repository

import androidx.annotation.WorkerThread
import com.madera.kotlin.Dao.ClientDao
import com.madera.kotlin.Entity.Client
import kotlinx.coroutines.flow.Flow

class ClientRepository(private val clientDao: ClientDao) {

    // Utiliser Flow permet de notifier à l'observer quand les données vont être modifiés ou un client est ajouté
    val allClients: Flow<List<Client>> = clientDao.getAllClients()

    // Récupère un client pour afficher ses détails
    fun getClientById(id: Int?) : Client{
       return clientDao.getClientById(id)
    }

    fun getClientByRef(refCli: Long) : Client{
        return clientDao.getClientByRef(refCli)
    }

    // Vérifie si le client existe via sa référence
    fun isClientExist(ref : Long) : Boolean{
        return clientDao.isClientExist(ref)
    }

    // Mise à jour du client
    fun updateClient(ref: Long, nomUP : String, adresseUP : String, codePostalUP: Int, villeUP: String,proUP: Boolean, secteurUP : String, description: String){
        clientDao.updateClient(ref, nomUP, adresseUP, codePostalUP, villeUP, proUP, secteurUP, description)
    }

    // Room lance par défaut des querys suspends permettant de la lancer hors du thrad principal
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun createClient(client: Client){
        clientDao.createClient(client)
    }

    // Suppression d'un client
     fun deleteClient(client: Client) {
        clientDao.delete(client)
    }


}