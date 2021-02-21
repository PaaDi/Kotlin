package com.madera.kotlin.Entity

import androidx.room.*

data class DevisWithModalites(
        @Embedded val devis: Devis,
        @Relation(
                parentColumn = "idDevis",
                entityColumn = "idModalite",
                associateBy = Junction(DevisModaliteCrossRef::class)
        )
        val modalitesPaiement: List<ModalitesPaiement>
)

