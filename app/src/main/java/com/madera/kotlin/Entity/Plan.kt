package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(foreignKeys = arrayOf(

    ForeignKey(entity = Chantier::class,
        parentColumns = arrayOf("idChantier"),
        childColumns = arrayOf("chantierId"),
        onDelete = ForeignKey.CASCADE),)
)

data class Plan(
    @PrimaryKey(autoGenerate = true) val idPlan: Int,
    @ColumnInfo(name = "chantierId") val chantierId: Long,
    @ColumnInfo(name = "tailleX") val tailleX: Float,
    @ColumnInfo(name = "tailleY") val tailleY: Float,
    @ColumnInfo(name = "nbe_Etage") val nbe_Etage: Int,

)
