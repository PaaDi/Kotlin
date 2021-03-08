package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = arrayOf(ForeignKey(entity = Client::class,
        parentColumns = arrayOf("refClient"),
        childColumns = arrayOf("clientRef"),
        onDelete = ForeignKey.CASCADE)))

data class Contact(
    @PrimaryKey(autoGenerate = true) val idContact: Int,
    @ColumnInfo(name = "refContact") val refContact: Long,
    @ColumnInfo(name = "clientRef") val clientRef: Long,
    @ColumnInfo(name = "nomContact") val nomContact: String,
    @ColumnInfo(name = "prenomContact") val prenomContact: String,
    @ColumnInfo(name = "fonctionContact") val fonctionContact: String,
    @ColumnInfo(name = "numeroContact") val numeroContact: String,
    @ColumnInfo(name = "mailContact") val mailContact: String,
)


