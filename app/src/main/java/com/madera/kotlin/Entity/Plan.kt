package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "plan")
data class Plan(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "tailleX") val tailleX: Float,
    @ColumnInfo(name = "tailleY") val tailleY: Float,
    @ColumnInfo(name = "nbeEtage") val nbeEtage: Int,

)
