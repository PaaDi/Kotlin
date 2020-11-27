package com.madera.kotlin.Sqlite

data class User(
    val pseudoUser: String,
    val passwordUser: String,
    val nameUser: String,
    val firstNameUser: String,
    val mailUser: String,
    val roleUser: Int
)