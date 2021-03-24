package com.madera.kotlin.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "request")
data class Request(
        @PrimaryKey(autoGenerate = true) val idRequest: Int?,
        @ColumnInfo(name = "requestType") val requestType: String,
        @ColumnInfo(name = "param1") val param1: String?,
        @ColumnInfo(name = "param2") val param2: String?,
        @ColumnInfo(name = "param3") val param3: String?,
        @ColumnInfo(name = "param4") val param4: String?,
        @ColumnInfo(name = "param5") val param5: String?,
        @ColumnInfo(name = "param6") val param6: String?,
        @ColumnInfo(name = "param7") val param7: String?,
        @ColumnInfo(name = "param8") val param8: String?,
        @ColumnInfo(name = "param9") val param9: String?,
)

