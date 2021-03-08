package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(foreignKeys = arrayOf(

    ForeignKey(entity = Client::class,
        parentColumns = arrayOf("refClient"),
        childColumns = arrayOf("clientRef"),
        onDelete = ForeignKey.CASCADE),)
)

data class Projet(
    @PrimaryKey(autoGenerate = true) val idProjet: Int,
    @ColumnInfo(name = "refProjet") val refProjet: Long,
    @ColumnInfo(name = "clientRef") val clientRef: Long,
    @ColumnInfo(name = "nomProjet") val nomProjet: String,
    @ColumnInfo(name = "datecreationProjet") val datecreationProjet: Date,
    @ColumnInfo(name = "notesProjet") val notesProjet: String,
)