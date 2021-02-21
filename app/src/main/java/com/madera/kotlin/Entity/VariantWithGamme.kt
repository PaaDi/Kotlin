package com.madera.kotlin.Entity

import androidx.room.*

data class VariantWithGamme(
        @Embedded val variants: Variants,
        @Relation(
                parentColumn = "idVariant",
                entityColumn = "idGamme",
                associateBy = Junction(DevisModaliteCrossRef::class)
        )
        val gamme: List<Gamme>
)

