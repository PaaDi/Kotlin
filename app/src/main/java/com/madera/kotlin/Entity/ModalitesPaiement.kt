package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "modalitespaiement")
data class ModalitesPaiement(
    @PrimaryKey(autoGenerate = true) val idModalite: Int,
    @ColumnInfo(name = "refModalite") val refClient: Long,
    @ColumnInfo(name = "nomModalite") val nomModalite: String,
    @ColumnInfo(name = "pourcentage_defaut") val pourcentage_defaut: Float,
    @ColumnInfo(name = "obsoleteModalite") val obsoleteModalite: Boolean,

)
