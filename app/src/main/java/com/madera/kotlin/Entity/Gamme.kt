package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "gamme")
data class Gamme(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "nomGamme") val nomGamme: String,
    @ColumnInfo(name = "sans_angle") val sans_angle: String,
    @ColumnInfo(name = "angle_ouvrant") val angle_ouvrant: Date,
    @ColumnInfo(name = "angle_fermant") val angle_fermant: Float,
)
