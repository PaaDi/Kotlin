package com.madera.kotlin.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ModalitesPaiement(
    @PrimaryKey var id: Int,
    val nom: String?,
    val pourcentage_defaut: Float?,
    val obsolete: Boolean

)