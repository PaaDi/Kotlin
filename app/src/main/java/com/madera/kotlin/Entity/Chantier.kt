package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(

        ForeignKey(entity = User::class,
        parentColumns = arrayOf("idUser"),
        childColumns = arrayOf("userId"),
        onDelete = ForeignKey.CASCADE),

        ForeignKey(entity = Projet::class,
        parentColumns = arrayOf("idProjet"),
        childColumns = arrayOf("projetId"),
        onDelete = ForeignKey.CASCADE)

    )
)

data class Chantier(
    @PrimaryKey(autoGenerate = true) val idChantier: Int?,
    @ColumnInfo(name = "refChantier") val refChantier: Long,
    @ColumnInfo(name = "projetId") val projetId: Int?,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "nomChantier") val nomChantier: String,
    @ColumnInfo(name = "adresseChantier") val adresseChantier: String,
    @ColumnInfo(name = "codePostalChantier") val codePostalChantier: Int,
    @ColumnInfo(name = "villeChantier") val villeChantier: String,
    @ColumnInfo(name = "notesChantier") val notesChantier: String,
    @ColumnInfo(name = "datecreationChantier") val datecreationChantier: String,
    @ColumnInfo(name = "datelancementChantier") val datelancementChantier: String,
)