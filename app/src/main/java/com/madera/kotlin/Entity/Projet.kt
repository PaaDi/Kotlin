package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "projet")
data class Projet(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nomProjet") val nomProjet: String,
    @ColumnInfo(name = "datecreationProjet") val datecreationProjet: Date,
    @ColumnInfo(name = "notesProjet") val notesProjet: String,
)