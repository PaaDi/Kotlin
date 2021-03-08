package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "typevariant")
data class TypeVariants(
    @PrimaryKey(autoGenerate = true) val idTypevariant: Int,
    @ColumnInfo(name = "refTypevariant") val refTypevariant: Long,
    @ColumnInfo(name = "nomTypevariant") val nomTypevariant: String,
    @ColumnInfo(name = "categorieTypevariant") val categorieTypevariant: String,
)
