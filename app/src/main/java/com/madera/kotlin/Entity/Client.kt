package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "client")
data class Client(
    /*@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,*/
        @PrimaryKey @ColumnInfo(name = "nom") val nom: String,
   /* @ColumnInfo(name = "adresse") val adresse: String,
    @ColumnInfo(name = "codePostal") val codePostal: Int,
    @ColumnInfo(name = "ville") val ville: String,
    @ColumnInfo(name = "numero") val numero: String,
    @ColumnInfo(name = "professionnel") val professionnel: Boolean,
    @ColumnInfo(name = "secteur") val secteur: String,
    @ColumnInfo(name = "description") val description: String*/
)


