package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(foreignKeys = arrayOf(

    ForeignKey(entity = Client::class,
        parentColumns = arrayOf("idClient"),
        childColumns = arrayOf("clientId"),
        onDelete = ForeignKey.CASCADE),)
)

data class Projet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "clientId") val clientId: Long,
    @ColumnInfo(name = "nomProjet") val nomProjet: String,
    @ColumnInfo(name = "datecreationProjet") val datecreationProjet: Date,
    @ColumnInfo(name = "notesProjet") val notesProjet: String,
)