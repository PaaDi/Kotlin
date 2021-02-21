package com.madera.kotlin.Entity

import androidx.room.*

data class GammeWithVariant(
        @Embedded val gamme: Gamme,
        @Relation(
                parentColumn = "idGamme",
                entityColumn = "idVariant",
                associateBy = Junction(DevisModaliteCrossRef::class)
        )
        val variant: List<Variants>
)

