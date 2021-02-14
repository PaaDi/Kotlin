package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chantier")
data class Chantier(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nomChantier") val nomChantier: String,
    @ColumnInfo(name = "notesChantier") val notesChantier: String,
)

