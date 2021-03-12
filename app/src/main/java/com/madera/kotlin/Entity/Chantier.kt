package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(

        ForeignKey(entity = User::class,
        parentColumns = arrayOf("refUser"),
        childColumns = arrayOf("userRef"),
        onDelete = ForeignKey.CASCADE),

        ForeignKey(entity = Projet::class,
        parentColumns = arrayOf("refProjet"),
        childColumns = arrayOf("projetRef"),
        onDelete = ForeignKey.CASCADE)

    )
)

data class Chantier(
    @PrimaryKey(autoGenerate = true) val idChantier: Long,
    @ColumnInfo(name = "refChantier") val refChantier: Long,
    @ColumnInfo(name = "projetRef") val projetRef: Long,
    @ColumnInfo(name = "userRef") val userRef: Long,
    @ColumnInfo(name = "nomChantier") val nomChantier: String,
    @ColumnInfo(name = "notesChantier") val notesChantier: String,
)