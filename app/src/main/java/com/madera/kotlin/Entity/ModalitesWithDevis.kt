package com.madera.kotlin.Entity

import androidx.room.*

data class ModalitesWithDevis(
        @Embedded val modalitesPaiement: ModalitesPaiement,
        @Relation(
                parentColumn = "idModalite",
                entityColumn = "idDevis",
                associateBy = Junction(DevisModaliteCrossRef::class)
        )
        val devis: List<Devis>
)

