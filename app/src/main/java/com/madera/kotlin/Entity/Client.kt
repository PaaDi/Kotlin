package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
class Client(
    @PrimaryKey(autoGenerate = true) val idClient : Int?,
    @ColumnInfo(name = "refClient") val refClient: Long,
    @ColumnInfo(name = "nom") val nom: String,
    @ColumnInfo(name = "adresse") val adresse: String,
    @ColumnInfo(name = "codePostal") val codePostal: Int,
    @ColumnInfo(name = "ville") val ville: String,
    @ColumnInfo(name = "professionnel") val professionnel: Boolean,
    @ColumnInfo(name = "secteur") val secteur: String,
    @ColumnInfo(name = "description") val description: String
)


