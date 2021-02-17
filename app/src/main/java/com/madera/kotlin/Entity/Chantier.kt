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
    @PrimaryKey(autoGenerate = true) val idChantier: Long,
    @ColumnInfo(name = "projetId") val projetId: Long,
    @ColumnInfo(name = "userId") val userId: Long,
    @ColumnInfo(name = "nomChantier") val nomChantier: String,
    @ColumnInfo(name = "notesChantier") val notesChantier: String,
)