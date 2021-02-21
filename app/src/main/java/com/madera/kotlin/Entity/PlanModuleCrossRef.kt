package com.madera.kotlin.Entity

import androidx.room.*
import java.util.*

@Entity(primaryKeys = ["idPlan", "idModule"])
data class PlanModuleCrossRef(
        @ColumnInfo(name = "idPlan") val idPlan: Long,
        @ColumnInfo(name = "idModule") val idModule: Long,
        @ColumnInfo(name = "qte") val qte: Int,
        @ColumnInfo(name = "pos_x") val pos_x : Float,
        @ColumnInfo(name = "pos_y") val pos_y: Float,
)

