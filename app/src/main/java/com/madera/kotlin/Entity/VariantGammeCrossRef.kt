package com.madera.kotlin.Entity

import androidx.room.*
import java.util.*

@Entity(primaryKeys = ["idVariant", "idGamme"])
data class VariantGammeCrossRef(
        @ColumnInfo(name = "idVariant") val idVariant: Long,
        @ColumnInfo(name = "idGamme") val idGamme: Long,
)

