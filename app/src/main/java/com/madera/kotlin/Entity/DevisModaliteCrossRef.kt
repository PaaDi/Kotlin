package com.madera.kotlin.Entity

import androidx.room.*
import java.util.*

@Entity(primaryKeys = ["idDevis", "idModalite"])
data class DevisModaliteCrossRef(
        @ColumnInfo(name = "idDevis") val idDevis: Long,
        @ColumnInfo(name = "idModalite") val idModalite: Long,
        @ColumnInfo(name = "pourcentageFixe") val pourcentageFixe: Float,
        @ColumnInfo(name = "estPaye") val estPaye : Boolean,
        @ColumnInfo(name = "dateEstime") val dateEstime: Date,
        @ColumnInfo(name = "dateReelle") val dateReelle: Date,
        @ColumnInfo(name = "remise") val remise: Float,
)

