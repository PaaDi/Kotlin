package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "variant")
data class Variants(
    @PrimaryKey(autoGenerate = true) val idVariant: Int,
    @ColumnInfo(name = "refVariant") val refVariant: Long,
    @ColumnInfo(name = "nomVariant") val nomVariant: String,

)
