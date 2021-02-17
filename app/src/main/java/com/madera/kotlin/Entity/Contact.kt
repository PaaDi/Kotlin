package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Client::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE)))

data class Contact(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "nomContact") val nomContact: String,
    @ColumnInfo(name = "prenomContact") val prenomContact: String,
    @ColumnInfo(name = "fonctionContact") val fonctionContact: String,
    @ColumnInfo(name = "numeroContact") val numeroContact: String,
    @ColumnInfo(name = "mailContact") val mailContact: String,
)


