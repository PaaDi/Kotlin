package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "module")
data class Module(
    @PrimaryKey(autoGenerate = true) val idModule: Int,
    @ColumnInfo(name = "refModule") val refModule: Long,
    @ColumnInfo(name = "nomModule") val nomModule: String,
    @ColumnInfo(name = "categorieModule") val categorieModule: String,
    @ColumnInfo(name = "uniteModule") val uniteModule: String,
    @ColumnInfo(name = "qteuniteModule") val qteuniteModule: Int,
)
