package com.madera.kotlin.Entity

import androidx.room.*
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(foreignKeys = arrayOf(

    ForeignKey(entity = Chantier::class,
        parentColumns = arrayOf("idChantier"),
        childColumns = arrayOf("chantierId"),
        onDelete = ForeignKey.CASCADE),)
)

data class Devis(
    @PrimaryKey(autoGenerate = true) val idDevis: Int,
    @ColumnInfo(name = "refDevis") val refDevis: Long,
    @ColumnInfo(name = "chantierId") val chantierId: Int,
    @ColumnInfo(name = "nomDevis") val nomDevis: String,
    @ColumnInfo(name = "etatDevis") val etatDevis: String,
    @ColumnInfo(name = "date_creationDevis") val date_creationDevis: Date,
    @ColumnInfo(name = "remiseDevis") val remiseDevis: Float,
    @ColumnInfo(name = "prixHTDevis") val prixHTDevis: Float,
    @ColumnInfo(name = "taux_TVADevis") val taux_TVADevis: Float,
    @ColumnInfo(name = "m_lineaireDevis") val m_lineaireDevis: Float,
    @ColumnInfo(name = "documentDevis") val documentDevis: String,
    @ColumnInfo(name = "notesDevis") val notesDevis: String,
)
